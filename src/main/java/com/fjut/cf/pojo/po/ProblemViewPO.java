package com.fjut.cf.pojo.po;

/**
 * @author axiang [2019/10/18]
 */
public class ProblemViewPO {
    private Integer id;
    private Integer problemId;
    private String timeLimit;
    private String memoryLimit;
    private String intFormat;
    private Integer spj;
    private String description;
    private String input;
    private String output;

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

    public String getIntFormat() {
        return intFormat;
    }

    public void setIntFormat(String intFormat) {
        this.intFormat = intFormat;
    }

    public Integer getSpj() {
        return spj;
    }

    public void setSpj(Integer spj) {
        this.spj = spj;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "ProblemViewPO{" +
                "id=" + id +
                ", problemId=" + problemId +
                ", timeLimit='" + timeLimit + '\'' +
                ", memoryLimit='" + memoryLimit + '\'' +
                ", intFormat='" + intFormat + '\'' +
                ", spj=" + spj +
                ", description='" + description + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
