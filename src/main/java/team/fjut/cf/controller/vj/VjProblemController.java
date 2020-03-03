package team.fjut.cf.controller.vj;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.component.judge.vjudge.VirtualJudgeHttpClient;
import team.fjut.cf.component.judge.vjudge.pojo.RequestProblemHtmlParams;
import team.fjut.cf.component.judge.vjudge.pojo.RequestProblemListParams;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.vo.ResultJsonVO;
import team.fjut.cf.service.SystemInfoService;

import java.util.Date;
import java.util.Objects;

/**
 * VJudge 题目相关API
 *
 * @author axiang [2020/2/10]
 */
@RestController
@CrossOrigin
@RequestMapping("/vj/problem")
public class VjProblemController {
    @Autowired
    VirtualJudgeHttpClient virtualJudgeHttpClient;

    @Autowired
    SystemInfoService systemInfoService;

    @GetMapping("/list")
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

    @GetMapping("/info")
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

}
