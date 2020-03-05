package team.fjut.cf.util;

import team.fjut.cf.pojo.enums.BugType;
import team.fjut.cf.pojo.enums.CodeLanguage;
import team.fjut.cf.pojo.enums.SubmitResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author axiang [2020/3/4]
 */
public class Enums2ListUtils {
    public static List<HashMap<String, Object>> parseBugType() {
        List<HashMap<String, Object>> list = new ArrayList<>();
        for (BugType item : BugType.values()) {
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("value",item.getCode());
            map.put("label", item.getName());
            list.add(map);
        }
        return list;
    }

    public static List<HashMap<String, Object>> parseCodeLanguage(){
        List<HashMap<String, Object>> list = new ArrayList<>();
        for (CodeLanguage item : CodeLanguage.values()) {
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("value",item.getCode());
            map.put("label", item.getName());
            list.add(map);
        }
        return list;
    }

    public static List<HashMap<String, Object>> parseSubmitResult(){
        List<HashMap<String, Object>> list = new ArrayList<>();
        for (SubmitResult item : SubmitResult.values()) {
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("value",item.getCode());
            map.put("label", item.getName());
            list.add(map);
        }
        return list;
    }
}
