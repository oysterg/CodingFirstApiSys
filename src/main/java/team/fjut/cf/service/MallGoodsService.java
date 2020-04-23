package team.fjut.cf.service;

import team.fjut.cf.pojo.po.MallGoods;

import java.util.List;

/**
 * @author axiang [2019/11/12]
 */
public interface MallGoodsService {
    /**
     * 分页查询商品信息记录
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<MallGoods> pages(Integer pageNum, Integer pageSize);

    /**
     * 查询商品记录总数
     *
     * @return
     */
    Integer selectAllCount();

    /**
     * 根据ID查询商品
     *
     * @param id
     * @return
     */
    MallGoods selectByGoodsId(Integer id);

    /**
     * @author zhongml [2020/4/21]
     * 条件查询商品列表
     *
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param id
     * @param name
     * @return
     */
    List<MallGoods> selectByCondition(Integer pageNum, Integer pageSize, String sort, Integer id, String name);

    /**
     * @author zhongml [2020/4/21]
     * 条件查询商品数量
     *
     * @param id
     * @param name
     * @return
     */
    int countByCondition(Integer id, String name);

    /**
     * @author zhongml [2020/4/21]
     * 新增一件商品
     *
     * @param mallGoods
     * @return
     */
    int createGoods(MallGoods mallGoods);

    /**
     * @author zhongml [2020/4/21]
     * 根据ID修改商品信息
     *
     * @param mallGoods
     * @return
     */
    int updateGoods(MallGoods mallGoods);

    /**
     * @author zhongml [2020/4/21]
     * 根据ID删除商品
     *
     * @param id
     * @return
     */
    int deleteGoods(Integer id);

}
