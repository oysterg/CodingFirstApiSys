package com.fjut.cf.controller;

import com.fjut.cf.pojo.enums.ResultJsonCode;
import com.fjut.cf.pojo.po.ProblemTagPO;
import com.fjut.cf.pojo.vo.ResultJsonVO;
import com.fjut.cf.service.ProblemTagService;
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

    @GetMapping("/get")
    public ResultJsonVO getAllProblemTag() {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        List<ProblemTagPO> problemTagPOS = problemTagService.select();
        resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS);
        resultJsonVO.addInfo(problemTagPOS);
        return resultJsonVO;
    }
}
