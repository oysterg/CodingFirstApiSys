package team.fjut.cf.controller.admin;

import org.springframework.web.bind.annotation.*;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.MallGoods;
import team.fjut.cf.pojo.po.MallOrderPO;
import team.fjut.cf.pojo.vo.MallOrderVO;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.MallGoodsService;
import team.fjut.cf.service.MallOrderService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
/**
 * @author zhongml [2020/4/21]
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/mall")
public class MallManagerController {
    @Resource
    MallGoodsService mallGoodsService;

    @Resource
    MallOrderService mallOrderService;

    /**
     * @param page
     * @param limit
     * @param sort
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/list")
    public ResultJson getMallGoodsList(@RequestParam("page") Integer page,
                                       @RequestParam("limit") Integer limit,
                                       @RequestParam(value="sort", required = false) String sort,
                                       @RequestParam(value="id", required = false) Integer id,
                                       @RequestParam(value="name", required = false) String name) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        List<MallGoods> mallGoods = mallGoodsService.selectByCondition(page, limit, sort, id, name);
        Integer count = mallGoodsService.countByCondition(id, name);
        resultJson.addInfo(mallGoods);
        resultJson.addInfo(count);
        return resultJson;
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/info")
    public ResultJson getMallGoods(@RequestParam("id") Integer id) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        MallGoods mallGoods = mallGoodsService.selectByGoodsId(id);
        if (Objects.isNull(mallGoods)) {
            resultJson.setStatus(ResultCode.RESOURCE_NOT_EXIST);
        } else {
            resultJson.addInfo(mallGoods);
        }
        return resultJson;
    }

    /**
     * @param name
     * @param cost
     * @param stock
     * @param goodsType
     * @param buyLimit
     * @param buyVerifyLimit
     * @param visible
     * @param pcitureUrl
     * @param description
     * @param shelfUser
     * @return
     */
    @PostMapping("/create")
    public ResultJson createMallGoods(@RequestParam("name") String name,
                                      @RequestParam("cost") Integer cost,
                                      @RequestParam("stock") Integer stock,
                                      @RequestParam("goodsType") Integer goodsType,
                                      @RequestParam("buyLimit") Integer buyLimit,
                                      @RequestParam("buyVerifyLimit") Integer buyVerifyLimit,
                                      @RequestParam("visible") Integer visible,
                                      @RequestParam("pictureUrl") String pcitureUrl,
                                      @RequestParam("description") String description,
                                      @RequestParam("shelfUser") String shelfUser) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        MallGoods mallGoods = new MallGoods();
        mallGoods.setName(name);
        mallGoods.setCost(cost);
        mallGoods.setStock(stock);
        mallGoods.setGoodsType(goodsType);
        mallGoods.setBuyLimit(buyLimit);
        mallGoods.setBuyVerifyLimit(buyVerifyLimit);
        mallGoods.setVisible(visible);
        mallGoods.setPictureUrl(pcitureUrl);
        mallGoods.setDescription(description);
        mallGoods.setShelfUser(shelfUser);
        mallGoods.setShelfTime(new Date());
        int result = mallGoodsService.createGoods(mallGoods);
        if (result != 1) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL);
        }
        return resultJson;
    }

    /**
     * @param id
     * @param name
     * @param cost
     * @param stock
     * @param goodsType
     * @param buyLimit
     * @param buyVerifyLimit
     * @param visible
     * @param pcitureUrl
     * @param description
     * @return
     */
    @PutMapping("/update")
    public ResultJson updateMallGoods(@RequestParam("id") Integer id,
                                      @RequestParam("name") String name,
                                      @RequestParam("cost") Integer cost,
                                      @RequestParam("stock") Integer stock,
                                      @RequestParam("goodsType") Integer goodsType,
                                      @RequestParam("buyLimit") Integer buyLimit,
                                      @RequestParam("buyVerifyLimit") Integer buyVerifyLimit,
                                      @RequestParam("visible") Integer visible,
                                      @RequestParam("pictureUrl") String pcitureUrl,
                                      @RequestParam("description") String description) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        MallGoods mallGoods = new MallGoods();
        mallGoods.setId(id);
        mallGoods.setName(name);
        mallGoods.setCost(cost);
        mallGoods.setStock(stock);
        mallGoods.setGoodsType(goodsType);
        mallGoods.setBuyLimit(buyLimit);
        mallGoods.setBuyVerifyLimit(buyVerifyLimit);
        mallGoods.setVisible(visible);
        mallGoods.setPictureUrl(pcitureUrl);
        mallGoods.setDescription(description);
        int result = mallGoodsService.updateGoods(mallGoods);
        if (result != 1) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL);
        }
        return resultJson;
    }

    @DeleteMapping("/delete")
    public ResultJson deleteMallGoods(@RequestParam("id") Integer id) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        int result = mallGoodsService.deleteGoods(id);
        if (result != 1) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL);
        }
        return resultJson;
    }

    /**
     * @param page
     * @param limit
     * @param sort
     * @param goodsId
     * @param orderUser
     * @param orderStatus
     * @param orderCancel
     * @return
     */
    @GetMapping("/order/list")
    public ResultJson getMallOrderList(@RequestParam("page") Integer page,
                                       @RequestParam("limit") Integer limit,
                                       @RequestParam(value="sort", required = false) String sort,
                                       @RequestParam(value="goodsId", required = false) Integer goodsId,
                                       @RequestParam(value="orderName", required = false) String orderUser,
                                       @RequestParam(value="orderStatus", required = false) Integer orderStatus,
                                       @RequestParam(value="orderCancel", required = false) Integer orderCancel) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        List<MallOrderVO> mallGoods = mallOrderService.selectByCondition(page, limit, sort, goodsId, orderUser, orderStatus, orderCancel);
        Integer count = mallOrderService.countByCondition(goodsId, orderUser, orderStatus, orderCancel);
        resultJson.addInfo(mallGoods);
        resultJson.addInfo(count);
        return resultJson;
    }

    /**
     * @param id
     * @param orderStatus
     * @param orderCancel
     * @return
     */
    @PutMapping("/order/update")
    public ResultJson updateMallOrder(@RequestParam("id") Integer id,
                                      @RequestParam(value="orderStatus", required = false) Integer orderStatus,
                                      @RequestParam(value="orderCancel", required = false) Integer orderCancel) {
        ResultJson resultJson = new ResultJson(ResultCode.REQUIRED_SUCCESS);
        MallOrderPO mallOrderPO = new MallOrderPO();
        mallOrderPO.setId(id);
        if (Objects.nonNull(orderCancel)) {
            mallOrderPO.setOrderCancel(orderCancel);
        }
        if (Objects.nonNull(orderStatus)) {
            mallOrderPO.setOrderStatus(orderStatus);
        }
        int result = mallOrderService.updateOrder(mallOrderPO);
        if (result != 1) {
            resultJson.setStatus(ResultCode.BUSINESS_FAIL);
        }
        return resultJson;
    }
}
