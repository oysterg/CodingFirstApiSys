package com.fjut.cf.controller;

import com.fjut.cf.component.interceptor.LoginRequired;
import com.fjut.cf.pojo.enums.ResultJsonCode;
import com.fjut.cf.pojo.vo.ResultJsonVO;
import com.fjut.cf.util.UUIDUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author axiang [2019/11/15]
 */
@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {
    @Value("${cf.config.filePath}")
    private String filePath;

    @Value("${cf.config.picturePath}")
    private String picturePath;

    @LoginRequired
    @PostMapping("/upload")
    public ResultJsonVO uploadFile(@RequestParam("file") MultipartFile file,
                                   @RequestParam("type") Integer type) throws Exception {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        String originalFileName = file.getOriginalFilename();
        int len = originalFileName.split("\\.").length;
        String suffix = "";
        if (len > 0) {
            suffix = originalFileName.split("\\.")[len - 1];
        }
        String newName = UUIDUtils.getUUID32() + "." + suffix;
        String fileUrl = "";
        if (type == 0) {
            fileUrl = filePath + newName;
        } else if (type == 1) {
            fileUrl = picturePath + newName;
        }
        InputStream is = file.getInputStream();
        OutputStream out = new FileOutputStream(fileUrl);
        IOUtils.copy(is, out);
        is.close();
        out.close();
        resultJsonVO.setStatus(ResultJsonCode.REQUIRED_SUCCESS);
        resultJsonVO.addInfo("/image/" + newName);
        return resultJsonVO;
    }


}
