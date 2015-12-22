/*
 *
 */
package tiger.core.domain;

import tiger.common.dal.enums.AttachTypeEnum;
import tiger.common.util.annotation.CopyIgnore;
import tiger.core.base.BaseDomain;

import java.util.Map;

/**
 * 附件模型
 *
 * @author Domi.hp
 * @version v 0.1 2015年9月19日 下午11:18:42 Domi.hp Exp $
 */
public class AttachDomain extends BaseDomain {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -7490265426829849343L;

    /**
     * The url.
     */
    private String url;

    /**
     * The name.
     */
    private String name;

    /**
     * 附件大小，单位为字节
     */
    private Integer size;

    /**
     * The attach type.
     */
    @CopyIgnore
    private AttachTypeEnum attachType;

    /**
     * The meta dal.
     */
    @CopyIgnore
    private Map<String, String> metaData;

    /**
     * The account Id
     */
    private Long accountId;

    /**
     * 是否被删除
     */
    @CopyIgnore
    private Boolean isDel;
    /**
     * Getter method for property <tt>url</tt>.
     *
     * @return property value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter method for property <tt>url</tt>.
     *
     * @param url value to be assigned to property url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Getter method for property <tt>attachType</tt>.
     *
     * @return property value of attachType
     */
    public AttachTypeEnum getAttachType() {
        return attachType;
    }

    /**
     * Setter method for property <tt>attachType</tt>.
     *
     * @param attachType value to be assigned to property attachType
     */
    public void setAttachType(AttachTypeEnum attachType) {
        this.attachType = attachType;
    }

    /**
     * Gets the meta dal.
     *
     * @return the meta dal
     */
    public Map<String, String> getMetaData() {
        return metaData;
    }

    /**
     * Sets the meta dal.
     *
     * @param metaData the meta dal
     */
    public void setMetaData(Map<String, String> metaData) {
        this.metaData = metaData;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
}
