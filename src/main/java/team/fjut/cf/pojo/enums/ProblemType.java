package team.fjut.cf.pojo.enums;

/**
 * @author axiang [2019/11/14]
 */
public enum ProblemType {
    BASIS(0, "基础"),
    DATA_STRUCTURE(1, "数据结构"),
    MATH(2, "数学"),
    GEOMETRIC(3, "几何"),
    GRAPH_THEORY(4, "图论"),
    SEARCH(5, "搜索"),
    DP(6, "动态规划");


    private int id;
    private String name;

    ProblemType(int id, String name) {
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

    public static String getNameByID(int id) {
        for (ProblemType t : ProblemType.values()) {
            if (t.getId() == id) {
                return t.getName();
            }
        }
        return null;
    }


}
