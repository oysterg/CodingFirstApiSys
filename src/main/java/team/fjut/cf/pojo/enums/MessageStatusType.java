package team.fjut.cf.pojo.enums;

/**
 * @author axiang [2020/3/12]
 */
public enum MessageStatusType {
    /**
     * 未读
     */
    NO_READ(0, "未读"),
    /**
     * 已读
     */
    HAVE_READ(1, "已读"),
    /**
     * 标记
     */
    STAR(2, "已标记");

    private int code;
    private String name;

    MessageStatusType(int code, String name) {
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
        for (MessageStatusType item : MessageStatusType.values()) {
            if (item.getCode() == code) {
                return item.getName();
            }
        }
        return null;
    }
}
