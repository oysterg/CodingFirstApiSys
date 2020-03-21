package team.fjut.cf.component.judge.vjudge.pojo.enums;

/**
 * VJ的评测结果类型枚举
 *
 * @author axiang [2020/3/21]
 */
public enum StatusType {
    /**
     *
     */
    AC(1, "Accepted"),
    PE(2, "Presentation Error"),
    WA(3, "Wrong Answer"),
    TLE(4, "Time Limit Exceeded"),
    MLE(5, "Memory Limit Exceeded"),
    OLE(6, "Output Limit Exceeded"),
    RE(7, "Runtime Error"),
    CE(8, "Compilation Error"),
    UE(9,"Unknown Error"),
    SE(10, "Submit Error"),
    Q_AND_G(11,"Queuing && Judging");

    private Integer key;
    private String value;

    StatusType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueByKey(String key) {
        for (StatusType t : StatusType.values()) {
            if (t.getKey().equals(key)) {
                return t.getValue();
            }
        }
        return null;
    }
}
