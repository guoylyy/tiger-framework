/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * JSON 数据对象互转类.
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 10, 2015 9:24:27 PM yiliang.gyl Exp $
 */
public class JsonConverterUtil {
    
    /** The Constant mapper. */
    private final static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
    }

    /**
     * 序列化
     *
     * @param o the o
     * @return the string
     */
    public static String serialize(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 反序列化
     *
     * @param jsonText the json text
     * @param type the type
     * @return the object
     */
    @SuppressWarnings("rawtypes")
    public static Object deserialize(String jsonText, TypeReference type) {
        try {
            return mapper.readValue(jsonText, type);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
