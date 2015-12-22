/**
 * 404 Team
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.redis;

/**
 * @author zhangbin
 * @version v0.1 2015/10/3 16:03
 */
public interface RedisComponent {

    Integer saveObject(String key, String content);

    Integer  deleteObject(String key);

    String getObject(String key);
}
