package team.fjut.cf.pojo.vo;

import java.util.Date;

/**
 * @author axiang [2019/10/31]
 */
public class JudgeStatusVO {
    private Integer id;
    private String nick;
    private String username;
    private Integer problemId;
    private String result;
    private String language;
    private String timeUsed;
    private String memoryUsed;
    private String code;
    private Integer codeLength;
    private Date submitTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(String timeUsed) {
        this.timeUsed = timeUsed;
    }

    public String getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(String memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(Integer codeLength) {
        this.codeLength = codeLength;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    @Override
    public String toString() {
        return "JudgeStatusVO{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", problemId=" + problemId +
                ", result='" + result + '\'' +
                ", language='" + language + '\'' +
                ", timeUsed='" + timeUsed + '\'' +
                ", memoryUsed='" + memoryUsed + '\'' +
                ", codeLength=" + codeLength +
                ", submitTime=" + submitTime +
                '}';
    }
}
