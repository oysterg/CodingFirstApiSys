package team.fjut.cf.pojo.enums;

/**
 * @author zhongml [2020/4/22]
 */
public enum MallOrderStatus {
    ORDER_CANCELED(-1, "订单取消"),
    WAIT_CONFIRM(0, "待确认"),
    CONFIRMED(1, "待发货"),
    DELIVERING(2, "已发货"),
    ORDER_FINISHED(3, "订单完成");

    private int id;
    private String name;


    MallOrderStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameById(int id) {
        for (MallOrderStatus mallOrderStatus : MallOrderStatus.values()) {
            if (mallOrderStatus.getId() == id) {
                return mallOrderStatus.getName();
            }
        }
        return null;
    }
}
