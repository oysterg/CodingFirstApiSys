package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
@Data
@Table(name = "t_mall_goods")
public class MallGoods {
    Integer id;
    String name;
    Integer cost;
    Integer goodsType;
    Integer stock;
    String description;
    String pictureUrl;
    Integer visible;
    String shelfUser;
    Date shelfTime;
    Integer buyLimit;
    Integer buyVerifyLimit;
}
