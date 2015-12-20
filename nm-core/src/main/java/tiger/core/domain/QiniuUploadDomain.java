/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.domain;

/**
 * 七牛云上传文件domain.
 *
 * @author alfred.yx
 * @version v 0.1 Oct 4, 2015 12:28:09 AM alfred Exp $
 */
public class QiniuUploadDomain {
    
    /** 七牛云授权文件上传token. */
    private String token;
    
    /** 七牛云存储空间文件key. */
    private String key;
    
    /**
     * Gets the token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token.
     *
     * @param token the new token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets the key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key the new key
     */
    public void setKey(String key) {
        this.key = key;
    }
    
}
