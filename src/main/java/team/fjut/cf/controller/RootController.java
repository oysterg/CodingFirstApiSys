package team.fjut.cf.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.vo.ResultJsonVO;

/**
 * 根路径下的 Controller
 *
 * @author axiang [2020/2/21]
 */
@RestController
@CrossOrigin
@RequestMapping("/")
public class RootController {
    @GetMapping("/")
    public ResultJsonVO getRootInfo() throws InterruptedException {
        //Thread.sleep(1000);
        return new ResultJsonVO(ResultJsonCode.TOKEN_OUTDATED);
    }
}
