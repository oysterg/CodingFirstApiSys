package com.fjut.cf.pojo.enums;

/**
 * @author axiang [2019/10/22]
 */
public enum AwardLevel {
    NONE(-2, "无奖项"),
    TENACIOUSLY(-1, "顽强拼搏奖"),
    ENCOURAGING(0, "优秀奖/鼓励奖"),
    BRAZE(1, "铜奖"),
    SILVER(2, "银奖"),
    GOLD(3, "金奖"),
    LV1(4, "一等奖"),
    LV2(5, "二等奖"),
    LV3(6, "三等奖");

    private int code;
    private String name;

    AwardLevel(int code, String name) {
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

    public static String getNameByCode(int id) {
        for (AwardLevel t : AwardLevel.values()) {
            if (t.getCode() == id) {
                return t.getName();
            }
        }
        return null;
    }
}
