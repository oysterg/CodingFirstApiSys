package com.fjut.cf.pojo.vo;

/**
 * @author axiang [2019/11/11]
 */
public class ChallengeBlockConditionVO {
    private Integer blockId;
    private String name;
    private Integer num;

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "ChallengeBlockConditionVO{" +
                "blockId=" + blockId +
                ", name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
