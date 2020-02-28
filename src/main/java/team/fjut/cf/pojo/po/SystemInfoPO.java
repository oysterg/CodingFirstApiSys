package team.fjut.cf.pojo.po;

import lombok.Data;

import java.util.Date;

/**
 * @author axiang [2019/10/21]
 */
@Data
public class SystemInfoPO {
    Integer id;
    String name;
    String value;
    Date insertTime;
}
