package team.fjut.cf.pojo.enums;

/**
 * @author axiang [2019/10/18]
 */
public enum BugType {
    OTHER(0, "其他"),
    SYSTEM_FAIL(1, "系统漏洞"),
    FUNCTION_ERROR(2, "功能异常"),
    LOGICAL_ERROR(3, "逻辑错误"),
    VIEW_PROBLEM(4, "界面问题"),
    PROBLEM_ABOUT(5,"题目相关");

    private int code;
    private String name;

    BugType(int code, String name) {
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
        for (BugType t : BugType.values()) {
            if (t.getCode() == id) {
                return t.getName();
            }
        }
        return null;
    }
}
