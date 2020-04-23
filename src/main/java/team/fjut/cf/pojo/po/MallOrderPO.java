package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
@Data
@Table(name = "t_mall_order")
public class MallOrderPO {
    private Integer id;
    private Integer goodsId;
    private Integer originCost;
    private Integer realCost;
    private String orderUser;
    private Date orderTime;
    private String reviewUser;
    private Integer orderStatus;
    private Integer orderCancel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getOriginCost() {
        return originCost;
    }

    public void setOriginCost(Integer originCost) {
        this.originCost = originCost;
    }

    public Integer getRealCost() {
        return realCost;
    }

    public void setRealCost(Integer realCost) {
        this.realCost = realCost;
    }

    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getReviewUser() {
        return reviewUser;
    }

    public void setReviewUser(String reviewUser) {
        this.reviewUser = reviewUser;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderCancel() {
        return orderCancel;
    }

    public void setOrderCancel(Integer orderCancel) {
        this.orderCancel = orderCancel;
    }

    @Override
    public String toString() {
        return "MallOrderPO{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", originCost=" + originCost +
                ", realCost=" + realCost +
                ", orderUser='" + orderUser + '\'' +
                ", orderTime=" + orderTime +
                ", reviewUser='" + reviewUser + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderCancel=" + orderCancel +
                '}';
    }
}
