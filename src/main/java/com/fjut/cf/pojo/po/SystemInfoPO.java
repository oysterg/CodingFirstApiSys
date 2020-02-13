package com.fjut.cf.pojo.po;

/**
 * @author axiang [2019/10/21]
 */
public class SystemInfoPO {
    private Integer id;
    private String name;
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SystemInfoPO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + value + '\'' +
                '}';
    }
}
