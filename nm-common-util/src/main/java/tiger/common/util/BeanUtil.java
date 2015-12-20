/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util;

import org.springframework.beans.BeanUtils;
import tiger.common.util.annotation.CopyIgnore;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务 Bean 操作工具类
 * 
 * @author yiliang.gyl
 * @version v 0.1 Sep 23, 2015 11:16:54 AM yiliang.gyl Exp $
 */
public class BeanUtil {

    /**
     * 复制一个类的信息到另外一个类，如果有 CopyIgnore 注解那么就忽略.
     *
     * @param source the source
     * @param target the target
     * @return the object
     */
    public static void copyPropertiesWithIgnores(Object source, Object target) {
        List<String> ignoreProperties = new ArrayList<String>();
        Class<?> actualEditable = source.getClass();
        Field[] fields = actualEditable.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation an : annotations) {
                if (an instanceof CopyIgnore) {
                    ignoreProperties.add(field.getName());
                }
            }
        }
        BeanUtils.copyProperties(source, target, ignoreProperties.toArray(new String[ignoreProperties.size()]));
    }
    

    /**
     * 枚举转成 Map
     *
     * @param methods 枚举的方法列表
     * @return the map 
     * @throws Exception the exception
     */
    public static Map<String, String> concreteEnumToMap(Object enn,
                                                  Method[] methods) throws Exception {
        Map<String, String> result = new HashMap<String, String>();
        for (Method m : methods) {
            if (m.getName().matches("get\\w+")) {
                String key = m.getName().substring(3);
                key = key.substring(0, 1).toLowerCase() + key.substring(1);
                if (m.getParameterCount() == 0 && m.getGenericReturnType() == String.class) {
                    result.put(key, m.invoke(enn).toString());
                }
            }
        }
        return result;
    }

}
