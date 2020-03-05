package team.fjut.cf.service;

import team.fjut.cf.pojo.po.UserAuth;
import team.fjut.cf.pojo.po.UserBaseInfo;
import team.fjut.cf.pojo.po.UserCustomInfo;
import team.fjut.cf.pojo.vo.UserAcNumBorderVO;
import team.fjut.cf.pojo.vo.UserAcbBorderVO;
import team.fjut.cf.pojo.vo.UserCustomInfoVO;
import team.fjut.cf.pojo.vo.UserRatingBorderVO;

import java.util.List;

/**
 * @author axiang [2019/10/11]
 */
public interface UserInfoService {
    /**
     * 注册用户
     * @param userBaseInfo
     * @param userAuth
     * @param userCustomInfo
     * @return
     */
    Boolean registerUser(UserBaseInfo userBaseInfo, UserAuth userAuth, UserCustomInfo userCustomInfo);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    Boolean userLogin(String username, String password);

    /**
     * 根据用户名查询该用户名是否存在
     *
     * @param username
     * @return
     */
    Boolean selectExistByUsername(String username);



    /**
     * 根据用户名查找用户所有信息
     *
     * @param username
     * @return
     */
    UserBaseInfo selectByUsername(String username);

    /**
     * 根据用户名查询用户个性化信息
     *
     * @param username
     * @return
     */
    UserCustomInfoVO selectUserCustomInfo(String username);

    /**
     * 查询ACB榜单
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UserAcbBorderVO> selectAcbBorder(int pageNum, int pageSize);

    /**
     * 查询AC题数榜单
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UserAcNumBorderVO> selectAcNumBorder(int pageNum, int pageSize);

    /**
     * 查询积分榜单
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UserRatingBorderVO> selectRatingBorder(int pageNum, int pageSize);


}
