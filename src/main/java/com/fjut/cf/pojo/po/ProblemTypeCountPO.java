package com.fjut.cf.pojo.po;

/**
 * @author axiang [2019/12/13]
 */
public class ProblemTypeCountPO {
    private Integer problemType;
    private Integer totalCount;

    public Integer getProblemType() {
        return problemType;
    }

    public void setProblemType(Integer problemType) {
        this.problemType = problemType;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "ProblemTypeCountPO{" +
                "problemType=" + problemType +
                ", totalCount=" + totalCount +
                '}';
    }
}
