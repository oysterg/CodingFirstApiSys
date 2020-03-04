package team.fjut.cf.pojo.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2019/10/18]
 */
@Data
@Table(name = "t_bug_report")
public class BugReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    Integer id;
    String username;
    String title;
    String currentPath;
    Integer type;
    String text;
    Date reportTime;
    Integer isFixed;
}
