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
@Table(name = "t_system_log")
public class SystemLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    Integer id;
    String requestUrl;
    String httpMethod;
    String ipAddress;
    String javaMethod;
    String params;
    String responseBody;
    Date time;
}
