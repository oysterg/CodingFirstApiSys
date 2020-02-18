package team.fjut.cf.pojo.enums;

/**
 * @author QAQ [2017/11/5]
 */
public enum CodeLanguage {
    GPP(0, "G++"),
    GCC(1, "GCC"),
    JAVA(2, "JAVA"),
    PYTHON2(3, "Python2"),
    GPP11(4, "G++11"),
    GCC11(5, "GCC11"),
    V_CPP(6, "VC++"),
    C_SHARP(7, "C#"),
    GO_1_8(8, "GO 1.8"),
    JS(9, "JavaScript"),
    PASCAL(10, "Pascal");

    private int code;
    private String name;

    CodeLanguage(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(int code) {
        for (CodeLanguage l : CodeLanguage.values()) {
            if (l.getCode() == code) {
                return l.getName();
            }
        }
        return null;
    }

    public static Integer getCodeByName(String name) {
        for (CodeLanguage l : CodeLanguage.values()) {
            if (name.equals(l.getName())) {
                return l.getCode();
            }
        }
        return null;
    }

}
