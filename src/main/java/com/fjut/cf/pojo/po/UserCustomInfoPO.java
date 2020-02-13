package com.fjut.cf.pojo.po;

/**
 * @author axiang [2019/10/14]
 */
public class UserCustomInfoPO {
    private Integer id;
    private String username;
    private String avatarUrl;
    private Integer adjectiveId;
    private Integer articleId;
    private Integer sealId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getAdjectiveId() {
        return adjectiveId;
    }

    public void setAdjectiveId(Integer adjectiveId) {
        this.adjectiveId = adjectiveId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getSealId() {
        return sealId;
    }

    public void setSealId(Integer sealId) {
        this.sealId = sealId;
    }

    @Override
    public String toString() {
        return "UserCustomInfoPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", adjectiveId=" + adjectiveId +
                ", articleId=" + articleId +
                '}';
    }
}
