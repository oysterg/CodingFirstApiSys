package team.fjut.cf.pojo.enums;

/**
 * 评测结果返回值
 *
 * @author axiang [2019/10/22]
 */
public enum SubmitResult {
    PENDING(0, "Pending..."),
    AC(1, "Accepted"),
    WA(2, "Wrong Answer"),
    CE(3, "Compilation Error"),
    RE(4, "Runtime Error"),
    TLE(5, "Time Limit Exceeded"),
    MLE(6, "Memory Limit Exceeded"),
    OLE(7, "Output Limit Exceeded"),
    PE(8, "Presentation Error"),
    DANGER(9, "System Error"),
    RUNNING(10, "Running..."),
    ERROR(11, "Submit Error"),
    JUDGING(12, "Judging..."),
    SC(13, "Score");

    private int code;
    private String name;

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

    SubmitResult(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public boolean isAc() {
        return this == SubmitResult.AC;
    }

    public boolean isErr() {
        return this == SubmitResult.WA ||
                this == SubmitResult.CE ||
                this == SubmitResult.RE ||
                this == SubmitResult.TLE ||
                this == SubmitResult.MLE ||
                this == SubmitResult.OLE ||
                this == SubmitResult.PE;
    }

    public boolean isPd() {
        return this == SubmitResult.PENDING ||
                this == SubmitResult.DANGER ||
                this == SubmitResult.RUNNING ||
                this == SubmitResult.ERROR ||
                this == SubmitResult.JUDGING;
    }

    public static String getNameByCode(int code) {
        for (SubmitResult r : SubmitResult.values()) {
            if (code == r.getCode()) {
                return r.getName();
            }
        }
        return null;
    }

    public static Integer getCodeByName(String name)
    {
        for(SubmitResult r: SubmitResult.values())
        {
            if(name.equals(r.getName()))
            {
                return r.getCode();
            }
        }
        return null;
    }
}
