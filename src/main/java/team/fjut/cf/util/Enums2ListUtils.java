package team.fjut.cf.util;

import team.fjut.cf.pojo.enums.BugType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author axiang [2020/3/4]
 */
public class Enums2ListUtils {
    public static List<HashMap<String, Object>> parseBugType() {
        List<HashMap<String, Object>> list = new ArrayList<>();
        for (BugType bug : BugType.values()) {
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("value",bug.getCode());
            map.put("label", bug.getName());
            list.add(map);
        }
        return list;
    }
}
