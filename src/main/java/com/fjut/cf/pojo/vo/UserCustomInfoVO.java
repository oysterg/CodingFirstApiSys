package com.fjut.cf.pojo.vo;

/**
 * @author axiang [2019/11/12]
 */
public class UserCustomInfoVO {
    private Integer id;
    private String username;
    private String avatarUrl;
    private String adjectiveTitle;
    private String articleTitle;
    private String sealUrl;

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

    public String getAdjectiveTitle() {
        return adjectiveTitle;
    }

    public void setAdjectiveTitle(String adjectiveTitle) {
        this.adjectiveTitle = adjectiveTitle;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getSealUrl() {
        return sealUrl;
    }

    public void setSealUrl(String sealUrl) {
        this.sealUrl = sealUrl;
    }

    @Override
    public String toString() {
        return "UserCustomInfoVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", adjectiveTitle='" + adjectiveTitle + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", sealUrl='" + sealUrl + '\'' +
                '}';
    }
}
