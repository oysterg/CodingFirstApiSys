package team.fjut.cf.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.component.judge.vjudge.VirtualJudgeHttpClient;
import team.fjut.cf.component.judge.vjudge.pojo.RequestProblemHtmlParams;
import team.fjut.cf.component.judge.vjudge.pojo.RequestProblemListParams;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.po.SystemInfoPO;
import team.fjut.cf.pojo.vo.ResultJsonVO;
import team.fjut.cf.service.SystemInfoService;

import java.util.Date;
import java.util.Objects;

/**
 * VJudge相关API
 *
 * @author axiang [2020/2/10]
 */
@RestController
@CrossOrigin
@RequestMapping("/vj")
public class VirtualJudgeController {
    @Autowired
    VirtualJudgeHttpClient virtualJudgeHttpClient;

    @Autowired
    SystemInfoService systemInfoService;

    @GetMapping("/problem/list")
    public ResultJsonVO getVJProblemList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize,
                                         @RequestParam(value = "OJId", required = false) String OJId,
                                         @RequestParam(value = "category", required = false) String category,
                                         @RequestParam(value = "probNum", required = false) String probNum,
                                         @RequestParam(value = "title", required = false) String title,
                                         @RequestParam(value = "source", required = false) String source) {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        RequestProblemListParams params = new RequestProblemListParams();
        params.setStart((pageNum - 1) * pageSize);
        params.setLength(pageSize);
        params.setOJId(StringUtils.isEmpty(OJId) ? "All" : OJId);
        params.setCategory(StringUtils.isEmpty(category) ? "all" : category);
        if (!Objects.isNull(probNum)) {
            params.setProbNum(probNum);
        }
        if (!Objects.isNull(title)) {
            params.setTitle(title);
        }
        if (!Objects.isNull(source)) {
            params.setSource(source);
        }
        JSONObject jsonObject = virtualJudgeHttpClient.postProblemList(params);
        if (Objects.isNull(jsonObject)) {
            resultJsonVO.setStatus(ResultJsonCode.RESOURCE_NOT_EXIST);
        } else {
            resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS);
            resultJsonVO.addInfo(jsonObject);
            resultJsonVO.addInfo(new Date());
        }
        return resultJsonVO;
    }

    @GetMapping("/problem/info")
    public ResultJsonVO getProblemInfo(@RequestParam("OJId") String OJId,
                                       @RequestParam("probNum") String probNum,
                                       @RequestParam("username") String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        RequestProblemHtmlParams params = new RequestProblemHtmlParams();
        params.setOJId(OJId);
        params.setProbNum(probNum);
        resultJsonVO.addInfo(virtualJudgeHttpClient.getProblemInfo(params));
        return resultJsonVO;
    }

    /**
     * 获取VJudge的 remoteOjs的json数据
     * 获取后缓存到数据库中
     * 如果数据库中数据落后4小时则重新获取
     *
     * @return
     */
    @GetMapping("/util/ojs")
    public ResultJsonVO getVJRemoteOJs() {
        String nameInDb = "vj_remote_ojs";
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        // 先找一下数据库中是否有缓存这个数据
        SystemInfoPO VJRemoteOjsInfo = systemInfoService.selectByName(nameInDb);
        //如果没有缓存，则去vj站点获取
        if (Objects.isNull(VJRemoteOjsInfo)) {
            JSONObject oJs = virtualJudgeHttpClient.postRemoteOJs();
            resultJsonVO.addInfo(oJs);
            SystemInfoPO systemInfoPO = new SystemInfoPO();
            systemInfoPO.setName(nameInDb);
            systemInfoPO.setValue(oJs.toJSONString());
            systemInfoPO.setInsertTime(new Date());
            systemInfoService.insert(systemInfoPO);
        }
        // 如果已有记录
        else {
            // 如果超过了4个小时，重新获取并更新数据
            if (System.currentTimeMillis() - VJRemoteOjsInfo.getInsertTime().getTime()
                    >= 1000 * 60 * 60 * 4) {
                JSONObject oJs = virtualJudgeHttpClient.postRemoteOJs();
                resultJsonVO.addInfo(oJs);
                VJRemoteOjsInfo.setValue(oJs.toJSONString());
                VJRemoteOjsInfo.setInsertTime(new Date());
                systemInfoService.update(VJRemoteOjsInfo);
            } else {
                String value = VJRemoteOjsInfo.getValue();
                JSONObject jsonObject = JSONObject.parseObject(value);
                resultJsonVO.addInfo(jsonObject);
            }
        }
        return resultJsonVO;
    }
}
