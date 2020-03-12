package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.component.interceptor.PrivateRequired;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.vo.ResultJsonVO;
import team.fjut.cf.pojo.vo.UserMessageListVO;
import team.fjut.cf.service.UserMessageService;

import java.util.List;

/**
 * @author axiang [2019/11/11]
 */
@RestController
@CrossOrigin
@RequestMapping("/user/message")
public class UserMessageController {
    @Autowired
    UserMessageService userMessageService;


    /**
     * 接收人的参数命名为username，为了让权限认证注解起作用
     *
     * @param toUsername
     * @param fromUsername
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PrivateRequired
    @PostMapping("/info")
    public ResultJsonVO getUserMessage(@RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize,
                                       @RequestParam("username") String toUsername,
                                       @RequestParam(value = "fromUsername", required = false) String fromUsername,
                                       @RequestParam(value = "status", required = false) Integer status,
                                       @RequestParam(value = "title", required = false) String title
                                       ) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        if (StringUtils.isEmpty(fromUsername)) {
            fromUsername = null;
        }
        if (StringUtils.isEmpty(title)) {
            title = null;
        } else {
            title = "%" + title + "%";
        }
        List<UserMessageListVO> userMessageListVOS = userMessageService.pagesByConditions(pageNum, pageSize, toUsername, fromUsername, status, title);
        Integer count = userMessageService.countByConditions(toUsername, fromUsername, status, title);
        resultJsonVO.addInfo(userMessageListVOS);
        resultJsonVO.addInfo(count);
        return resultJsonVO;
    }

    //@PrivateRequired
    //@GetMapping("/unread")
    //public ResultJsonVO getUserUnreadMessage(@RequestParam("username") String username,
    //                                         @RequestParam("pageNum") Integer pageNum,
    //                                         @RequestParam("pageSize") Integer pageSize) {
    //    ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
    //    List<UserMessage> userMessages = userMessageService.pagesUnreadByUsername(username, pageNum, pageSize);
    //    Integer count = userMessageService.selectCountUnreadByUsername(username);
    //    resultJsonVO.addInfo(userMessages);
    //    resultJsonVO.addInfo(count);
    //    return resultJsonVO;
    //}
    //
    //@PrivateRequired
    //@GetMapping("/unread/count")
    //public ResultJsonVO getUserUnreadMessageCount(@RequestParam("username") String username) {
    //    ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
    //    Integer count = userMessageService.selectCountUnreadByUsername(username);
    //    resultJsonVO.addInfo(count);
    //    return resultJsonVO;
    //}
    //
    //@PrivateRequired
    //@PostMapping("/unread/set_read")
    //public ResultJsonVO setUserMessageRead(@RequestParam("username") String username,
    //                                       @RequestParam("id") Integer id) {
    //    ResultJsonVO resultJsonVO = new ResultJsonVO();
    //    Integer integer = userMessageService.updateSetReadByUsernameAndId(username, id);
    //    if (integer == 1) {
    //        resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS);
    //    } else {
    //        resultJsonVO.setStatus(ResultJsonCode.BUSINESS_FAIL, "设置未读消息失败");
    //    }
    //    return resultJsonVO;
    //}
    //
    //@PrivateRequired
    //@PostMapping("/unread/set_all_read")
    //public ResultJsonVO setUserMessageAllRead(@RequestParam("username") String username) {
    //    ResultJsonVO resultJsonVO = new ResultJsonVO();
    //    Integer integer = userMessageService.updateSetReadByUsername(username);
    //    if (integer >= 1) {
    //        resultJsonVO.addInfo(integer);
    //        resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS);
    //    } else {
    //        resultJsonVO.setStatus(ResultJsonCode.BUSINESS_FAIL, "设置未读消息失败");
    //    }
    //    return resultJsonVO;
    //}

}
