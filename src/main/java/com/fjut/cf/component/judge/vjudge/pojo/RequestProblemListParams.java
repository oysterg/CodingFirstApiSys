package com.fjut.cf.component.judge.vjudge.pojo;

/**
 * VJ请求题目相关参数类
 * start 记录开始位置
 * length 一次性记录的大小
 * OJId 查找OJ的名称
 * category 仓库名，默认为"all"，代表全部题目集
 * probNum 查找的题目ID
 * title 查找标题
 * source 查找的题目源
 *
 * @author axiang [2020/2/10]
 */
public class RequestProblemListParams {
    private int start;
    private int length;
    private String OJId;
    private String probNum;
    private String title;
    private String source;
    private String category;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "VirtualJudgeRequestProblemParams{" +
                "start=" + start +
                ", length=" + length +
                ", OJId='" + OJId + '\'' +
                ", probNum='" + probNum + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
