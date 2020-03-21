package team.fjut.cf.component.judge.vjudge.pojo.enums;

/**
 * VJ的语言简写类型枚举
 *
 * @author axiang [2020/3/21]
 */
public enum LanguageType {
    /**
     *
     */
    CPP("CPP", "C++"),
    C("C", "C"),
    JAVA("JAVA", "Java"),
    PASCAL("PASCAL", "Pascal"),
    PYTHON("PYTHON", "Python"),
    CSHARP("CSHARP", "C#"),
    RUBY("RUBY", "Ruby"),
    OTHER("OTHER", "Other");

    private String key;
    private String value;

    LanguageType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueByKey(String key) {
        for (LanguageType t : LanguageType.values()) {
            if (t.getKey().equals(key)) {
                return t.getValue();
            }
        }
        return null;
    }
}
