package com.fjut.cf.pojo.enums;

/**
 * @author axiang [2019/11/18]
 */
public enum ContestPermission {
    PUBLIC(0, "公开"),
    CODE(1, "密码"),
    PRIVATE(2, "私有"),
    REGISTER(3, "注册"),
    OFFICIAL(4, "正式"),
    TEAM(5, "组队");

    private int code;
    private String name;


    ContestPermission(int code, String name) {
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
        for (ContestPermission contestPermission : ContestPermission.values()) {
            if (contestPermission.getCode() == code) {
                return contestPermission.getName();
            }
        }
        return null;
    }
}
