package com.fjut.cf.pojo.po;

/**
 * @author axiang [2019/10/21]
 */
public class ProblemTagPO {
    private Integer id;
    private String name;
    private Integer tagType;
    private String createUser;
    private Integer priority;

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

    public Integer getTagType() {
        return tagType;
    }

    public void setTagType(Integer tagType) {
        this.tagType = tagType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "ProblemTagPO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tagType=" + tagType +
                ", createUser='" + createUser + '\'' +
                ", priority=" + priority +
                '}';
    }
}
