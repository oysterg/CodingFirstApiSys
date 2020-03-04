package team.fjut.cf.pojo.vo;

import team.fjut.cf.pojo.po.UserBaseInfo;
import team.fjut.cf.pojo.po.UserCustomInfo;

/**
 * @author axiang [2019/10/14]
 */
public class UserInfoVO {
    private UserCustomInfo customInfo;
    private UserBaseInfo baseInfo;

    public UserCustomInfo getUserCustomInfoPO() {
        return customInfo;
    }

    public void setUserCustomInfoPO(UserCustomInfo userCustomInfo) {
        this.customInfo = userCustomInfo;
    }

    public UserBaseInfo getUserBaseInfoPO() {
        return baseInfo;
    }

    public void setUserBaseInfoPO(UserBaseInfo userBaseInfo) {
        this.baseInfo = userBaseInfo;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(baseInfo.toString()).append(customInfo.toString()).toString();
    }
}
