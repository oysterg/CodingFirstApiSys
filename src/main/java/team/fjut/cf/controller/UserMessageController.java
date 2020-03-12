package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import team.fjut.cf.component.interceptor.PrivateRequired;
import team.fjut.cf.pojo.enums.ResultJsonCode;
import team.fjut.cf.pojo.po.UserMessage;
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
    public ResultJsonVO getUserMessageList(@RequestParam("pageNum") int pageNum,
                                           @RequestParam("pageSize") int pageSize,
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

    @PrivateRequired
    @PostMapping("/info")
    public ResultJsonVO getMessageInfo(@RequestParam("messageId") int messageId) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        UserMessage userMessage = userMessageService.selectById(messageId);
        resultJsonVO.addInfo(userMessage);
        return resultJsonVO;
    }

    @PrivateRequired
    @PostMapping("/notRead/count")
    public ResultJsonVO getUserNotReadMsgCount(@RequestParam("username") String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        Integer count = userMessageService.countByConditions(username, null, 0, null);
        resultJsonVO.addInfo(count);
        return resultJsonVO;
    }

    @PrivateRequired
    @PostMapping("/setStatus")
    public ResultJsonVO setMessageStatus(@RequestParam("messageId") int messageId,
                                         @RequestParam("status") int status) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        int i = userMessageService.setStatus(messageId, status);
        if (i != 1) {
            resultJsonVO.setStatus(ResultJsonCode.BUSINESS_FAIL);
        }
        return resultJsonVO;
    }

    @PrivateRequired
    @PostMapping("/setStatus/all")
    public ResultJsonVO setAllMessageStatus(@RequestParam("username") String username,
                                            @RequestParam("status") int status) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        int i = userMessageService.setAllStatus(username, status);
        resultJsonVO.addInfo(i);
        return resultJsonVO;
    }

    @PrivateRequired
    @PostMapping("/delete")
    public ResultJsonVO deleteMessage(@RequestParam("messageId") int messageId) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        int i = userMessageService.delete(messageId);
        if (i != 1) {
            resultJsonVO.setStatus(ResultJsonCode.BUSINESS_FAIL);
        }
        return resultJsonVO;
    }

    @PrivateRequired
    @PostMapping("/delete/all")
    public ResultJsonVO deleteAllMessage(@RequestParam("username") String username) {
        ResultJsonVO resultJsonVO = new ResultJsonVO(ResultJsonCode.REQUIRED_SUCCESS);
        int i = userMessageService.deleteAll(username);
        resultJsonVO.addInfo(i);
        return resultJsonVO;
    }

}
