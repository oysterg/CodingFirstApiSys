package team.fjut.cf.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import team.fjut.cf.component.judge.vjudge.pojo.VjAccount;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * 读取用户名关键字json文件
 *
 * @author axiang [2020/3/12]
 */

public class JsonFileUtils {
    public static boolean isUsernameRestrict(String username) {
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

    public static String getMsgTemplate(String name) {
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

    public static VjAccount getRandomVJAccount() {
        try {
            Resource resource = new ClassPathResource("conf/VJAccounts.json");
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
