package tiger.common.data.dataobject;

import tiger.common.util.annotation.CopyIgnore;

public class SystemParamsDO extends BaseDO{

    /**  */
    private static final long serialVersionUID = 5143791441765457506L;

    private String paramName;

    private String paramValue;

    @CopyIgnore
    private Byte isActive;

    @CopyIgnore
    private String paramType;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }
}
