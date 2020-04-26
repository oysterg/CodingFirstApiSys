package team.fjut.cf.pojo.enums;

/**
 * @author axiang [2019/10/22]
 */
public enum ContestLevel {
    /**
     *
     */
    NONE(-1, "其他、不显示在奖项列表中"),
    ACM_PROVINCE(0, "ACM省赛"),
    ACM_REGIONAL(1, "ACM/ICPC区域赛"),
    EC_FINAL(2, "EC-Final"),
    WORLD_FINAL(3, "世界总决赛"),
    LAN_QIAO_BEI(4, "全国蓝桥杯大赛"),
    ACM_INVITATIONAL(5, "ACM全国邀请赛"),
    CCPC(6, "全国大学生程序设计竞赛");

    private int code;
    private String name;


    ContestLevel(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByCode(int code) {
        for (ContestLevel contestLevel : ContestLevel.values()) {
            if (contestLevel.getCode() == code) {
                return contestLevel.getName();
            }
        }
        return null;
    }

    // add by zhongml [2020/4/26]
    public static Integer getCodeByName(String name) {
        for (ContestLevel contestLevel : ContestLevel.values()) {
            if (contestLevel.getName().equals(name)) {
                return contestLevel.getCode();
            }
        }
        return null;
    }
}
