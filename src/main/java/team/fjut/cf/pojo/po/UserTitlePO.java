package team.fjut.cf.pojo.po;

/**
 * @author axiang [2019/11/12]
 */
public class UserTitlePO {
    private Integer id;
    private Integer type;
    private String name;
    private String pictureUrl;
    private Integer lifeTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Integer lifeTime) {
        this.lifeTime = lifeTime;
    }

    @Override
    public String toString() {
        return "UserTitlePO{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", lifeTime=" + lifeTime +
                '}';
    }
}
