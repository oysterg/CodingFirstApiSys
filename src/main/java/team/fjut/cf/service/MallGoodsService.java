package team.fjut.cf.service;

import team.fjut.cf.pojo.po.MallGoodsPO;

import java.util.List;

/**
 * @author axiang [2019/11/12]
 */
public interface MallGoodsService {
    /**
     * 分页查询商品信息记录
     *
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<MallGoodsPO> pages(Integer startIndex, Integer pageSize);

    /**
     * 查询商品总数
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
    MallGoodsPO selectByGoodsId(Integer id);

}
