package com.fjut.cf.component.judge.vjudge.pojo;

/**
 * VJ 请求题目html页面参数
 * @author axiang [2020/2/18]
 */
public class RequestProblemHtmlParams {
    private String OJId;
    private String probNum;

    public String getOJId() {
        return OJId;
    }

    public void setOJId(String OJId) {
        this.OJId = OJId;
    }

    public String getProbNum() {
        return probNum;
    }

    public void setProbNum(String probNum) {
        this.probNum = probNum;
    }

    @Override
    public String toString() {
        return "RequestProblemHtmlParams{" +
                "OJId='" + OJId + '\'' +
                ", probNum='" + probNum + '\'' +
                '}';
    }
}
