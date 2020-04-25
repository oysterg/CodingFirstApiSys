package team.fjut.cf.pojo.enums;

/**
 * 比赛报名审核状态
 *
 * @author zhongml [2020/4/23]
 */
public enum ContestReviewStatus {
    /**
     *
     */
    WAIT_REVIEW(0, "还未审核"),
    REVIEW_PAST(1, "审核通过"),
    REVIEW_REFUSED(2, "审核失败");


    private int id;
    private String name;

    ContestReviewStatus(int id, String name) {
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
        for (ContestReviewStatus item : ContestReviewStatus.values()) {
            if (item.getId() == id) {
                return item.getName();
            }
        }
        return null;
    }
}
