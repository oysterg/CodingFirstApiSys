package team.fjut.cf.controller.admin;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.component.token.TokenModel;
import team.fjut.cf.component.token.jwt.JwtTokenManager;
import team.fjut.cf.config.interceptor.annotation.CaptchaRequired;
import team.fjut.cf.config.interceptor.annotation.LoginRequired;
import team.fjut.cf.config.interceptor.annotation.PrivateRequired;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.*;
import team.fjut.cf.pojo.vo.*;
import team.fjut.cf.service.*;
import team.fjut.cf.utils.IpUtils;
import team.fjut.cf.utils.JsonFileTool;
import team.fjut.cf.utils.UUIDUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author axiang [2020/4/28]
 */
@RestController
@RequestMapping("/admin/user")
@CrossOrigin
public class UserManagerController {
    @Resource
    UserBaseInfoService userBaseInfoService;

    @Resource
    UserTitleService userTitleService;

    @Resource
    UserCustomInfoService userCustomInfoService;

    @GetMapping("/list")
    public ResultJson getUserList(@RequestParam("page") Integer pageNum,
                                  @RequestParam("limit") Integer pageSize,
                                  @RequestParam(name = "username", required = false) String username) {
        ResultJson resultJson = new ResultJson();
        if (!StringUtils.isEmpty(username)) {
            // 拼接查询字符串
            username = "%" + username + "%";
        } else {
            // 拼接查询字符串如果为空字符或者null则 置为null
            username = null;
        }
        List<UserInfoAdminVO> userInfoAdminVOS = userBaseInfoService.pageByCondition(pageNum, pageSize, username);
        Integer total = userBaseInfoService.countByCondition(username);
        resultJson.addInfo(userInfoAdminVOS);
        resultJson.addInfo(total);
        return resultJson;
    }

    @GetMapping("/title/list")
    public ResultJson getUserTitle(@RequestParam("page") Integer pageNum,
                                  @RequestParam("limit") Integer pageSize,
                                  @RequestParam(name = "name", required = false) String name) {
        ResultJson resultJson = new ResultJson();
        if (!StringUtils.isEmpty(name)) {
            // 拼接查询字符串
            name = "%" + name + "%";
        } else {
            // 拼接查询字符串如果为空字符或者null则 置为null
            name = null;
        }
        List<UserTitle> userTitles = userTitleService.pageByCondition(pageNum, pageSize, name);
        Integer total = userTitleService.countByCondition(name);
        resultJson.addInfo(userTitles);
        resultJson.addInfo(total);
        return resultJson;
    }

    @GetMapping("/title/all")
    public ResultJson getAllTitle() {
        ResultJson resultJson = new ResultJson();
        List<UserTitle> userTitles = userTitleService.selectAll();
        resultJson.addInfo(userTitles);
        return resultJson;
    }

    @RequestMapping("/title/add")
    public ResultJson createTitle(@RequestBody UserTitle userTitle) {
        ResultJson resultJson = new ResultJson();
        int result = userTitleService.createTitle(userTitle);
        if (result != 1) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL);
        }
        return resultJson;
    }

    @DeleteMapping("/title/delete")
    public ResultJson deleteTitle(@RequestParam("id") Integer id) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        int result = userTitleService.deleteTitle(id);
        if (result != 1) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL);
        }
        return resultJson;
    }



}




