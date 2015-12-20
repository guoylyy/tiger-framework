package tiger.common.util.permission;

/**
 * Created by globit on 9/1/15.
 */
public enum PermissionRelation {

    OR("OR", "关系或"),

    AND("AND", "关系与");

    /** 枚举的值 */
    private String value;

    /** 枚举的描述 */
    private String description;

    PermissionRelation(String value, String description) {
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
}
