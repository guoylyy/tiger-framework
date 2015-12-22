/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.dal.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 8:09 PM yiliang.gyl Exp $
 */
@Service
public class RedisComponentImpl implements  RedisComponent{

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Integer saveObject(String key, String content) {
        if(!RedisConstants.IS_OPEN){
            return null;
        }
        int rc = redisTemplate.opsForValue().append(key, content);
        return rc;
    }


    @Override
    public Integer deleteObject(String key){
        if(!RedisConstants.IS_OPEN){
            return null;
        }
        redisTemplate.delete(key);
        return 1;
    }


    @Override
    public String getObject(String key) {
        if(!RedisConstants.IS_OPEN){
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }
}
