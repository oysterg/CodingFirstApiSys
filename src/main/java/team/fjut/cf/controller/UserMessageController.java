package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.config.interceptor.PrivateRequired;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.UserMessage;
import team.fjut.cf.pojo.vo.ResultJson;
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
     * 获取用户的消息列表
     * 接收人的参数命名为username，为了让权限认证注解起作用
     *
     * @param toUsername
     * @param fromUsername
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PrivateRequired
    @PostMapping("/list")
    public ResultJson getUserMessageList(@RequestParam("pageNum") int pageNum,
                                         @RequestParam("pageSize") int pageSize,
                                         @RequestParam("username") String toUsername,
                                         @RequestParam(value = "fromUsername", required = false) String fromUsername,
                                         @RequestParam(value = "status", required = false) Integer status,
                                         @RequestParam(value = "title", required = false) String title
    ) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
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
        resultJson.addInfo(userMessageListVOS);
        resultJson.addInfo(count);
        return resultJson;
    }

    @PrivateRequired
    @PostMapping("/info")
    public ResultJson getMessageInfo(@RequestParam("messageId") int messageId) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        UserMessage userMessage = userMessageService.selectById(messageId);
        resultJson.addInfo(userMessage);
        return resultJson;
    }

    @PrivateRequired
    @PostMapping("/notRead/count")
    public ResultJson getUserNotReadMsgCount(@RequestParam("username") String username) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        Integer count = userMessageService.countByConditions(username, null, 0, null);
        resultJson.addInfo(count);
        return resultJson;
    }

    @PrivateRequired
    @PostMapping("/setStatus")
    public ResultJson setMessageStatus(@RequestParam("messageId") int messageId,
                                       @RequestParam("status") int status) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        int i = userMessageService.setStatus(messageId, status);
        if (i != 1) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL);
        }
        return resultJson;
    }

    @PrivateRequired
    @PostMapping("/setStatus/all")
    public ResultJson setAllMessageStatus(@RequestParam("username") String username,
                                          @RequestParam("status") int status) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        int i = userMessageService.setAllStatus(username, status);
        resultJson.addInfo(i);
        return resultJson;
    }

    @PrivateRequired
    @PostMapping("/delete")
    public ResultJson deleteMessage(@RequestParam("messageId") int messageId) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        int i = userMessageService.delete(messageId);
        if (i != 1) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL);
        }
        return resultJson;
    }

    @PrivateRequired
    @PostMapping("/delete/all")
    public ResultJson deleteAllMessage(@RequestParam("username") String username) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        int i = userMessageService.deleteAll(username);
        resultJson.addInfo(i);
        return resultJson;
    }

}
