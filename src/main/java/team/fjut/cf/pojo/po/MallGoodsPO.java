package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
@Data
@Table(name = "t_mall_goods")
public class MallGoodsPO {
    private Integer id;
    private String name;
    private Integer cost;
    private Integer goodsType;
    private Integer stock;
    private String description;
    private String pictureUrl;
    private Integer visible;
    private String shelfUser;
    private Date shelfTime;
    private Integer buyLimit;
    private Integer buyVerifyLimit;

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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getShelfUser() {
        return shelfUser;
    }

    public void setShelfUser(String shelfUser) {
        this.shelfUser = shelfUser;
    }

    public Date getShelfTime() {
        return shelfTime;
    }

    public void setShelfTime(Date shelfTime) {
        this.shelfTime = shelfTime;
    }

    public Integer getBuyLimit() {
        return buyLimit;
    }

    public void setBuyLimit(Integer buyLimit) {
        this.buyLimit = buyLimit;
    }

    public Integer getBuyVerifyLimit() {
        return buyVerifyLimit;
    }

    public void setBuyVerifyLimit(Integer buyVerifyLimit) {
        this.buyVerifyLimit = buyVerifyLimit;
    }

    @Override
    public String toString() {
        return "MallGoodsPO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", goodsType=" + goodsType +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", visible=" + visible +
                ", shelfUser='" + shelfUser + '\'' +
                ", shelfTime=" + shelfTime +
                ", buyLimit=" + buyLimit +
                ", buyVerifyLimit=" + buyVerifyLimit +
                '}';
    }
}
