package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author axiang [2019/11/12]
 */
@Data
@Table(name = "t_user_title")
public class UserTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    Integer id;
    Integer type;
    String name;
    String pictureUrl;
    Integer lifeTime;

}
