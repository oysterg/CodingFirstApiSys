package team.fjut.cf.pojo.enums;

import java.util.Objects;

/**
 * @author axiang [2019/10/22]
 */
public enum ProblemDifficultLevel {
    /**
     * 简单
     */
    EASY(1, "简单"),
    /**
     * 中等
     */
    MEDIUM(2, "中等"),
    /**
     * 困难
     */
    HARD(3, "困难");

    private int code;
    private String name;

    ProblemDifficultLevel(int code, String name) {
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

    public static String getNameByCode(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }
        for (ProblemDifficultLevel level : ProblemDifficultLevel.values()) {
            if (level.getCode() == code) {
                return level.getName();
            }
        }
        return null;
    }
}
