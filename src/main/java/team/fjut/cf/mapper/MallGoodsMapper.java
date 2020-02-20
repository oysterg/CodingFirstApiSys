package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.MallGoodsPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author axiang [2019/11/12]
 */
public interface MallGoodsMapper {

    /**
     * 查询全部商品信息记录
     *
     * @return
     */
    List<MallGoodsPO> selectAll();

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
