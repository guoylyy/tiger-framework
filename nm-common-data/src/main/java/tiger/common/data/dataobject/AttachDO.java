package tiger.common.data.dataobject;

import tiger.common.util.annotation.CopyIgnore;

public class AttachDO extends BaseDO{
    /**  */
    private static final long serialVersionUID = 3619140984841268642L;

    private String url;

    private String name;

    private Integer size; // 文件大小，单位为字节

    @CopyIgnore
    private String attachType;

    @CopyIgnore
    private String metaData;

    private Long accountId;

    @CopyIgnore
    private Byte isDel; // 附件是否已删除

    private String type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getAttachType() {
        return attachType;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
