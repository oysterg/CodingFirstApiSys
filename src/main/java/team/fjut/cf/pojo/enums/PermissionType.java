package team.fjut.cf.pojo.enums;

/**
 * @author axiang [2020/4/16]
 */
public enum PermissionType {
    /**
     * 管理员基本权限
     */
    BASE_ADMIN(00, "管理员"),
    TEST_1(01, "测试权限1"),
    TEST_2(02, "测试权限2"),
    TEST_12(12, "测试权限12");

    private int id;
    private String name;

    PermissionType(int id, String name) {
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
        for (PermissionType t : PermissionType.values()) {
            if (t.getId() == id) {
                return t.getName();
            }
        }
        return null;
    }

    public static PermissionType getEnumByID(int id) {
        for (PermissionType t : PermissionType.values()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
