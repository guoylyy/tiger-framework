package tiger.common.util.permission;

/**
 * Created by globit on 9/1/15.
 */
public enum PermissionEnum {
    /** 门店属性修改 */
    TIGER_ACCOUNT_MODIFY("TIGER_ACCOUNT_MODIFY", "账户修改权限")
    ;


    /** 枚举的值 */
    private String value;

    /** 枚举描述 */
    private String description;

    PermissionEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter method for property <tt>description</tt>.
     *
     * @return property value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 通过枚举<code>value</code>获得枚举。
     *
     * @param value
     * @return
     */
    public static PermissionEnum getByValue(String value) {
        for (PermissionEnum permission : values()) {
            if (permission.getValue().equals(value)) {
                return permission;
            }
        }
        return null;
    }

}
