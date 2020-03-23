package team.fjut.cf.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import team.fjut.cf.component.judge.vjudge.pojo.VjAccount;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * 读取用户名关键字json文件
 *
 * @author axiang [2020/3/12]
 */
@Component
public class JsonFileTool {

    @Value("${spring.profiles.active}")
    private String profiles;

    public boolean isUsernameRestrict(String username) {
        try {
            Resource resource = new ClassPathResource("templates/Keyword.json");
            final String str = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
            JSONObject jsonObject = JSONObject.parseObject(str);
            JSONArray array = jsonObject.getJSONArray("keyword");
            for (Object o : array) {
                String tempStr = o.toString();
                if (username.toLowerCase().contains(tempStr)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {

            return true;
        }
    }

    public String getMsgTemplate(String name) {
        try {
            Resource resource = new ClassPathResource("templates/MsgTemplate.json");
            final String str = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
            JSONObject jsonObject = JSONObject.parseObject(str);
            Object obj = jsonObject.get(name);
            return obj.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public VjAccount getRandomVJAccount() {
        try {
            String fileName = String.format("vj-accounts-%s.json", profiles);
            Resource resource = new ClassPathResource("conf/" + fileName);
            final String str = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
            JSONArray array = JSONObject.parseArray(str);
            Random random = new Random();
            int randInt = random.nextInt(array.size());
            JSONObject o = (JSONObject) array.get(randInt);
            VjAccount vjAccount = new VjAccount();
            vjAccount.setUsername(o.get("user").toString());
            vjAccount.setPassword(o.get("pwd").toString());
            return vjAccount;
        } catch (Exception e) {
            return new VjAccount();
        }
    }

}
