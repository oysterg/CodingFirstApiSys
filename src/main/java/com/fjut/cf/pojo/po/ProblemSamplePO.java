package com.fjut.cf.pojo.po;

/**
 * @author axiang [2019/10/18]
 */
public class ProblemSamplePO {
    private Integer id;
    private Integer problemId;
    private Integer caseOrder;
    private String inputCase;
    private String outputCase;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getCaseOrder() {
        return caseOrder;
    }

    public void setCaseOrder(Integer caseOrder) {
        this.caseOrder = caseOrder;
    }

    public String getInputCase() {
        return inputCase;
    }

    public void setInputCase(String inputCase) {
        this.inputCase = inputCase;
    }

    public String getOutputCase() {
        return outputCase;
    }

    public void setOutputCase(String outputCase) {
        this.outputCase = outputCase;
    }

    @Override
    public String toString() {
        return "ProblemSamplePO{" +
                "id=" + id +
                ", problemId=" + problemId +
                ", caseOrder=" + caseOrder +
                ", inputCase='" + inputCase + '\'' +
                ", outputCase='" + outputCase + '\'' +
                '}';
    }
}
