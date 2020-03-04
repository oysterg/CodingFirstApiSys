package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
@Data
@Table(name = "t_acb_record")
public class AcbRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    Integer id;
    String username;
    Double acbChange;
    Double currentAcb;
    Integer reason;
    Date time;
    String mark;
}
