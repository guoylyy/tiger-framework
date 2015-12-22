package tiger.common.dal.enums;

public enum SystemEnum implements BaseEnum {
    API("API", "API");

    private SystemEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 通过枚举<code>code</code>获取枚举
     *
     * @param code
     * @return
     */
    public static SystemEnum getEnumByCode(String code) {
        for (SystemEnum bt : SystemEnum.values()) {
            if (bt.getCode().equals(code)) {
                return bt;
            }
        }
        return null;
    }
}
