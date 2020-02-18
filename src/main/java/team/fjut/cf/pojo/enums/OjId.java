package team.fjut.cf.pojo.enums;

/**
 * oj对应枚举类
 *
 * @author axiang [2019/10/23]
 */
public enum OjId {
    DEFAULT(-1, "默认"),
    HDU(0, "HDU"),
    BNUOJ(1, "BNUOJ"),
    NBUT(2, "NBUT"),
    PKU(3, "PKU"),
    HUST(4, "HUST"),
    CF(5, "CF"),
    CODE_VS(6, "CodeVs"),
    LOCAL(7, "自主出题"),
    AC_DREAM(8, "AcDream"),
    FOJ(9, "FOJ"),
    LOCAL_GAME(10, "自主出题（游戏）"),
    CF_GYM(11, "CF_Gym"),
    OTHER(12, "其他"),
    BZOJ(13, "BZOJ"),
    ZOJ(14, "ZOJ"),
    SPOJ(15, "SPOJ");


    private int code;
    private String name;

    OjId(int code, String name) {
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
        for (OjId ojid : OjId.values()) {
            if (ojid.getCode() == code) {
                return ojid.getName();
            }
        }
        return null;
    }
}
