package com.fjut.cf.pojo.po;

/**
 * @author axiang [2019/10/21]
 */
public class ChallengeBlockPO {
    private Integer id;
    private String name;
    private Integer blockType;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBlockType() {
        return blockType;
    }

    public void setBlockType(Integer blockType) {
        this.blockType = blockType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ChallengeBlockPO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blockType=" + blockType +
                ", description='" + description + '\'' +
                '}';
    }
}
