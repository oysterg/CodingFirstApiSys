package team.fjut.cf.component.judge.vjudge.pojo;

/**
 * @author axiang [2020/2/19]
 */
public class ProblemDescription {
    private String probTitle;
    private String originUrl;
    private String virtualJudgeUrl;
    private String timeLimit;
    private String memoryLimit;
    private String OS;
    private String author;
    private String source;
    private String problemDescriptionUrl;

    public String getProbTitle() {
        return probTitle;
    }

    public void setProbTitle(String probTitle) {
        this.probTitle = probTitle;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getVirtualJudgeUrl() {
        return virtualJudgeUrl;
    }

    public void setVirtualJudgeUrl(String virtualJudgeUrl) {
        this.virtualJudgeUrl = virtualJudgeUrl;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(String memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProblemDescriptionUrl() {
        return problemDescriptionUrl;
    }

    public void setProblemDescriptionUrl(String problemDescriptionUrl) {
        this.problemDescriptionUrl = problemDescriptionUrl;
    }

    @Override
    public String toString() {
        return "ProblemDescription{" +
                "probTitle='" + probTitle + '\'' +
                ", originUrl='" + originUrl + '\'' +
                ", virtualJudgeUrl='" + virtualJudgeUrl + '\'' +
                ", timeLimit='" + timeLimit + '\'' +
                ", memoryLimit='" + memoryLimit + '\'' +
                ", OS='" + OS + '\'' +
                ", author='" + author + '\'' +
                ", source='" + source + '\'' +
                ", problemDescriptionUrl='" + problemDescriptionUrl + '\'' +
                '}';
    }
}
