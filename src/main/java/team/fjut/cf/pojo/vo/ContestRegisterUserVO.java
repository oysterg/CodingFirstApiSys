package team.fjut.cf.pojo.vo;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author axiang [2020/4/23]
 */
@Data
public class ContestRegisterUserVO {
    private Integer Id;
    private Integer contestId;
    private String title;
    private String contestKind;
    private String username;
    private Date registerTime;
    private String reviewStatus;
    private String reviewInfo;
    private Date reviewTime;
}
