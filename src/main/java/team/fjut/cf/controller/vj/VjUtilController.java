package team.fjut.cf.controller.vj;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fjut.cf.component.judge.vjudge.VirtualJudgeHttpClient;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.po.SystemInfo;
import team.fjut.cf.pojo.vo.ResultJsonVO;
import team.fjut.cf.service.SystemInfoService;

import java.util.Date;
import java.util.Objects;

/**
 * VJudge 工具类相关API
 * @author axiang [2020/3/3]
 */
@RestController
@CrossOrigin
@RequestMapping("/vj/util")
public class VjUtilController {

    @Autowired
    SystemInfoService systemInfoService;

    @Autowired
    VirtualJudgeHttpClient virtualJudgeHttpClient;

    /**
     * 获取VJudge的 remoteOjs的json数据
     * 获取后缓存到数据库中
     * 如果数据库中数据落后4小时则重新获取
     *
     * @return
     */
    @GetMapping("/ojs")
    public ResultJsonVO getVJRemoteOJs() {
        String nameInDb = "vj_remote_ojs";
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        // 先找一下数据库中是否有缓存这个数据
        SystemInfo systemInfo = systemInfoService.selectOne(nameInDb);
        //如果没有缓存，则去vj站点获取
        if (Objects.isNull(systemInfo)) {
            JSONObject oJs = virtualJudgeHttpClient.postRemoteOJs();
            resultJsonVO.addInfo(oJs);
            systemInfo = new SystemInfo();
            systemInfo.setName(nameInDb);
            systemInfo.setValue(oJs.toJSONString());
            systemInfo.setInsertTime(new Date());
            systemInfoService.insert(systemInfo);
        }
        // 如果已有记录
        else {
            // 如果超过了4个小时，重新获取并更新数据
            if (System.currentTimeMillis() - systemInfo.getInsertTime().getTime()
                    >= 1000 * 60 * 60 * 4) {
                JSONObject oJs = virtualJudgeHttpClient.postRemoteOJs();
                resultJsonVO.addInfo(oJs);
                systemInfo.setValue(oJs.toJSONString());
                systemInfo.setInsertTime(new Date());
                systemInfoService.update(systemInfo);
            } else {
                String value = systemInfo.getValue();
                JSONObject jsonObject = JSONObject.parseObject(value);
                resultJsonVO.addInfo(jsonObject);
            }
        }
        return resultJsonVO;
    }
}
