package team.fjut.cf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.fjut.cf.pojo.vo.ResultJsonVO;
import team.fjut.cf.util.UUIDUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author axiang [2019/11/15]
 */
@RestController
@CrossOrigin
@RequestMapping("/upload")
public class UploadController {
    @Value("${cf.config.file.globalPath}")
    private String globalPath;

    @Value("${cf.config.file.picPath}")
    private String picPath;

    @Value("${cf.config.file.avatarPath}")
    private String avatarPath;

    @PostMapping("/avatar")
    public ResultJsonVO uploadAvatar(@RequestParam("avatar") final MultipartFile file)
            throws IOException {
        ResultJsonVO resultJsonVO = new ResultJsonVO();
        final byte[] bytes = file.getBytes();
        String randFileName = UUIDUtils.getUUID32();
        int len = file.getOriginalFilename().split("\\.").length;
        String suffix = "";
        if (len > 0) {
            suffix = file.getOriginalFilename().split("\\.")[len - 1];
        }
        final Path path = Paths.get(avatarPath + randFileName + "." + suffix);
        Files.write(path, bytes);
        resultJsonVO.addInfo(randFileName + "." + suffix);
        return resultJsonVO;
    }


}
