package team.fjut.cf.pojo.vo;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author zhongml [2020/4/22]
 */
@Data
public class MallOrderVO {
    private Integer id;
    private Integer goodsId;
    private Integer originCost;
    private Integer realCost;
    private String orderUser;
    private Date orderTime;
    private String reviewUser;
    private String orderStatus;
    private Integer orderCancel;
}
