package com.fjut.cf.pojo.po;

import java.util.Date;

/**
 * @author axiang [2019/10/18]
 */
public class UserPermissionPO {
    private Integer id;
    private String username;
    private Integer permissionId;
    private String granter;
    private Date grantTime;

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

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getGranter() {
        return granter;
    }

    public void setGranter(String granter) {
        this.granter = granter;
    }

    public Date getGrantTime() {
        return grantTime;
    }

    public void setGrantTime(Date grantTime) {
        this.grantTime = grantTime;
    }

    @Override
    public String toString() {
        return "UserPermissionPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", permissionId=" + permissionId +
                ", granter='" + granter + '\'' +
                ", grantTime=" + grantTime +
                '}';
    }
}
