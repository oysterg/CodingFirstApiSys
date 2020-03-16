package team.fjut.cf.controller;

import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.ProblemTagPO;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.ProblemTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author axiang [2019/10/30]
 */
@RestController
@CrossOrigin
@RequestMapping("/problem_tag")
public class ProblemTagController {
    @Autowired
    ProblemTagService problemTagService;

    @GetMapping("/all")
    public ResultJson getAllProblemTag() {
        ResultJson resultJson = new ResultJson();
        List<ProblemTagPO> problemTagPOS = problemTagService.select();
        resultJson.setStatus(ResultCode.REQUIRED_SUCCESS);
        resultJson.addInfo(problemTagPOS);
        return resultJson;
    }
}
