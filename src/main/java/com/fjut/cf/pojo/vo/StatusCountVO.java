package com.fjut.cf.pojo.vo;

import java.util.Date;

/**
 * @author axiang [2019/10/30]
 */
public class StatusCountVO {
    private Integer totalCount;
    private Integer acCount;
    private Date submitDay;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getAcCount() {
        return acCount;
    }

    public void setAcCount(Integer acCount) {
        this.acCount = acCount;
    }

    public Date getSubmitDay() {
        return submitDay;
    }

    public void setSubmitDay(Date submitDay) {
        this.submitDay = submitDay;
    }

    @Override
    public String toString() {
        return "StatusCountVO{" +
                "totalCount=" + totalCount +
                ", acCount=" + acCount +
                ", submitDay=" + submitDay +
                '}';
    }
}
