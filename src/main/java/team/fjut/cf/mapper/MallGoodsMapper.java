package team.fjut.cf.mapper;

import team.fjut.cf.pojo.po.MallGoods;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author axiang [2019/11/12]
 */
public interface MallGoodsMapper extends Mapper<MallGoods> {

    /**
     * 查询全部商品信息记录
     *
     * @return
     */
    List<MallGoods> all();

    /**
     * 查询商品总数
     *
     * @return
     */
    Integer allCount();

    /**
     * 根据ID查询商品
     *
     * @param id
     * @return
     */
    MallGoods selectByGoodsId(@Param("id") Integer id);

}
