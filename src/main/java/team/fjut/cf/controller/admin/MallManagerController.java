package team.fjut.cf.controller.admin;

import org.springframework.web.bind.annotation.*;
import team.fjut.cf.pojo.enums.ResultCode;
import team.fjut.cf.pojo.po.MallGoods;
import team.fjut.cf.pojo.vo.ResultJson;
import team.fjut.cf.service.MallGoodsService;

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
}
