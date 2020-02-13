package com.fjut.cf.pojo.enums;

/**
 * @author axiang [2019/11/18]
 */
public enum ContestKind {
    PRACTICE(0, "练习"),
    RATING(1, "积分"),
    INTEREST(2, "趣味"),
    OFFICIAL(3, "正式"),
    HIDE(4, "隐藏"),
    DIY(5, "DIY");

    private int code;
    private String name;


    ContestKind(int code, String name) {
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
        for (ContestKind contestKind : ContestKind.values()) {
            if (contestKind.getCode() == code) {
                return contestKind.getName();
            }
        }
        return null;
    }
}
