package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.MallGoodsPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [2019/11/12]
 */
public interface MallGoodsMapper {
    /**
     * 分页查询商品信息记录
     *
     * @param startIndex
     * @param pageSize
     * @return
     */
   List<MallGoodsPO> pages(@Param("startIndex") Integer startIndex,
                           @Param("pageSize") Integer pageSize);

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
    MallGoodsPO selectByGoodsId(@Param("id") Integer id);

}
