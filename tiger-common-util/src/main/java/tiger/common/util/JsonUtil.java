/*
 * Copyright 2013 Mallik Ankati. All rights reserved.
 *  
 * 
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 * 
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 * 
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied.
 */
package tiger.common.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

/**
 * Json utility to convert json to object and object to json.
 *
 * Note : <br />
 * 1) Bean must follow java bean specification from Sun other wise this utility
 * will not work.
 *
 * 2) Bean should not contain inner beans
 *
 * @author mallik
 * @version 1.0
 * @since 02/23/2011
 */
public class JsonUtil {

    private static Logger logger = Logger.getLogger(JsonUtil.class.getName());

    /**
     * It takes jsonString and target class, which will return targetClass.
     *
     * @param jsonString
     * @return
     */
    public static <T> T fromJson(String jsonString, Class<T> beanClass) {
        T target = null;
        if (jsonString == null || beanClass == null) {
            throw new RuntimeException("Invalid argument " + jsonString);
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            // It should have problem with inner classes
            target = localJson(jsonObject, beanClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return target;
    }

    public static <T, S> Map<T, S> fromJson(String jsonString,
                                            Class<?> mapClass, Class<T> keyClass, Class<S> sClass) {
        Map<T, S> target = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            target = fromJson(jsonObject, mapClass, keyClass, sClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return target;
    }

    @SuppressWarnings("unchecked")
    public static <T, S> Map<T, S> fromJson(JSONObject jsonObject,
                                            Class<?> mapClass, Class<T> keyClass, Class<S> sClass) {
        Map<T, S> target = null;
        try {
            target = new HashMap<T, S>();
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = jsonObject.get(key);
                if (value instanceof JSONObject) {
                    target.put((T) key,
                            localJson(jsonObject.getJSONObject(key), sClass));
                } else {
                    target.put((T) key, (S) JSONObject.wrap(value));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return target;
    }

    public static <T, S> T fromJson(String jsonString, Class<T> listClass,
                                    Class<S> beanClass) {
        JSONArray array = null;
        try {
            array = new JSONArray(jsonString);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return fromJson(array, listClass, beanClass);
    }

    /**
     * This API used for converting jsonArray to List<T> or Map<Key, Value>
     *
     * @param <T>
     * @param jsonArray
     * @param listClass
     * @param beanClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T, S> T fromJson(JSONArray jsonArray, Class<T> listClass,
                                    Class<S> beanClass) {
        T target = null; // localJson(jsonObject, beanClass);
        try {
            if (jsonArray != null && jsonArray.length() > 0) {
                target = listClass.newInstance();
                List<S> temp = (ArrayList<S>) target;
                int size = jsonArray.length();
                for (int index = 0; index < size; index++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(index);
                    // JSONObject jsonObject = jsonArray.getJSONObject(index);
                    if (jsonObject != null) {
                        S tempObject = localJson(jsonObject, beanClass);
                        temp.add(tempObject);
                    } else {
                        temp.add((S) jsonArray.get(index));
                    }

                    /*
                     * S tempObject = localJson(jsonObject, beanClass);
                     * temp.add(tempObject);
                     */}
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return target;
    }

    public static <T> T fromJson(JSONObject jsonObject, Class<T> beanClass) {
        T target = localJson(jsonObject, beanClass);
        return target;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static <T> T localJson(JSONObject jsonObject, Class<T> beanClass) {
        T target = null;
        try {
            // It should have problem with inner classes
            target = beanClass.newInstance();

            if (target instanceof Map) {
                Map temp = (Map) target;
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    Object value = jsonObject.get(key);
                    temp.put(key, JSONObject.wrap(value));
                }
            } else {
                BeanInfo beanInfo = Introspector.getBeanInfo(beanClass,
                        Object.class);
                for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                    Method writeMethod = pd.getWriteMethod();
                    Method readMethod = pd.getReadMethod();
                    String name = pd.getName();
                    Class<?> returnClass = readMethod.getReturnType();
                    Field field = null;
                    try {
                        field = beanClass.getDeclaredField(name);
                    } catch (NoSuchFieldException e) {
                        try {
                            field = beanClass.getSuperclass().getDeclaredField(
                                    name);
                        } catch (NoSuchFieldException ignore) {
                            // ignore this exception
                        }
                    }
                    Object value = getValueFromJson(jsonObject, name, true);
                    if (value != null) {
                        Object result = wrap(value, returnClass, field);
                        if (result != null) {
                            setProperty(target, writeMethod, result);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return target;
    }

    private static void setProperty(Object target, Method method, Object value)
            throws Exception {
        // logger.info("method call [ name:" + method.getName() + ", value:" +
        // value + "]");
        method.invoke(target, value);
    }

    private static Object wrap(Object object, Class<?> returnClass, Field field)
            throws Exception {
        // logger.info("returnType:" + returnClass.getName());
        if (returnClass.getName().equalsIgnoreCase("java.lang.Short")
                || returnClass.getName().equalsIgnoreCase("short")) {
            if (object != JSONObject.NULL) {
                Integer tempInt = (Integer) object;
                return tempInt.shortValue();
            }
        }
        if (returnClass.getName().equalsIgnoreCase("java.lang.Byte")
                || returnClass.getName().equalsIgnoreCase("byte")) {
            if (object != JSONObject.NULL) {
                Integer tempInt = (Integer) object;
                return tempInt.byteValue();
            }
        }
        if (returnClass.getName().equalsIgnoreCase("java.lang.Character")
                || returnClass.getName().equalsIgnoreCase("char")) {
            if (object != JSONObject.NULL) {
                return object;
            }
        }
        if (returnClass.getName().equalsIgnoreCase("java.lang.Integer")
                || returnClass.getName().equalsIgnoreCase("int")) {
            if (object != JSONObject.NULL) {
                if (object instanceof String) {
                    return Integer.parseInt((String) object);
                }
                return object;
            }
        }
        if (returnClass.getName().equalsIgnoreCase("java.lang.Long")
                || returnClass.getName().equalsIgnoreCase("long")) {
            if (object != JSONObject.NULL) {
                if (object instanceof Integer) {
                    return new Long((Integer) object);
                }
                return object;
            }
        }
        if (returnClass.getName().equalsIgnoreCase("java.lang.Boolean")
                || returnClass.getName().equalsIgnoreCase("boolean")) {
            if (object != JSONObject.NULL) {
                Boolean bool = (Boolean) object;
                return bool;
            }
        }
        if (returnClass.getName().equalsIgnoreCase("java.lang.Float")
                || returnClass.getName().equalsIgnoreCase("float")) {
            if (object != JSONObject.NULL) {
                if (object instanceof Integer) {
                    return new Float((Integer) object);
                }
                if (object instanceof Long) {
                    return new Float((Long) object);
                }
                if (object instanceof Double) {
                    Double doub = (Double) object;
                    return doub.floatValue();
                }
            }
        }
        if (returnClass.getName().equalsIgnoreCase("java.math.BigDecimal")) {
            if (object instanceof BigDecimal) {
                return new Double((Integer) object);
            }
            if (object instanceof Float) {
                return new Double((Float) object);
            }
            if (object instanceof Long) {
                return new Double((Long) object);
            }
            if (object instanceof String) {
                return new BigDecimal((String) object);
            }
            return object;
        }
        if (returnClass.getName().equalsIgnoreCase("java.lang.Double")
                || returnClass.getName().equalsIgnoreCase("double")) {
            if (object instanceof Integer) {
                return new Double((Integer) object);
            }
            if (object instanceof Float) {
                return new Double((Float) object);
            }
            if (object instanceof Long) {
                return new Double((Long) object);
            }
            return object;
        }
        if (returnClass.getName().equalsIgnoreCase("java.lang.String")) {
            if (object != JSONObject.NULL) {
                return object;
            }
        }
        if (returnClass.isArray()) {
            if (object instanceof JSONArray) {
                JSONArray array = (JSONArray) object;
                Object value = handleArray(array, returnClass);
                return value;
            }
        }
        if (object instanceof JSONObject) {
            // logger.info("JSONObject type:" + object);
            if (returnClass.getName().equalsIgnoreCase("java.util.Map")
                    || returnClass.getName().equalsIgnoreCase(
                    "java.util.HashMap")) {
                JSONObject jsonObject = (JSONObject) object;
                Object value = handleMap(jsonObject, returnClass, field);
                return value;
            } else {
                JSONObject jsonObject = (JSONObject) object;
                Object value = fromJson(jsonObject.toString(), returnClass);
                return value;
            }
        }
        if (object instanceof JSONArray) {
            // logger.info("Collection type:" + object);
            if (returnClass.getName().equalsIgnoreCase("java.util.List")
                    || returnClass.getName().equalsIgnoreCase(
                    "java.util.ArrayList")) {
                JSONArray array = (JSONArray) object;
                Object value = handleList(array, returnClass, field);
                return value;
            } else {

            }
        }
        if (object instanceof JSONString) {
            logger.info("JSONString type:" + object);
        }
        return null;
    }

    private static Object handleArray(JSONArray array, Class<?> returnType)
            throws Exception {
        Object value = null;
        int length = array.length();
        if (length > 0) {
            value = Array.newInstance(returnType.getComponentType(), length);
            for (int index = 0; index < length; index++) {
                Object tempValue = array.get(index);
                Array.set(value, index, tempValue);
            }
        }
        return value;
    }

    private static List<?> handleList(JSONArray array, Class<?> returnType,
                                      Field field) throws Exception {
        List<Object> list = null;
        int length = array.length();
        if (length > 0) {
            list = new ArrayList<Object>();
            ParameterizedType type = (ParameterizedType) field.getGenericType();
            for (int index = 0; index < length; index++) {
                Object value = array.get(index);
                if (value instanceof JSONObject) {
                    JSONObject jsonObject = (JSONObject) value;
                    Type actualType = type.getActualTypeArguments()[0];
                    Object convertedValue = fromJson(jsonObject.toString(),
                            (Class<?>) actualType);
                    list.add(convertedValue);
                } else {
                    list.add(value);
                }
            }
        }
        return list;
    }

    private static Map<?, ?> handleMap(JSONObject sourceJson,
                                       Class<?> returnType, Field field) throws Exception {
        Map<Object, Object> map = new HashMap<Object, Object>();
        Iterator<String> it = sourceJson.keys();
        while (it.hasNext()) {
            String key = it.next();
            ParameterizedType type = (ParameterizedType) field.getGenericType();
            Object value = sourceJson.get(key);
            if (value instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) value;
                Type actualType = type.getActualTypeArguments()[1];
                Object convertedValue = fromJson(jsonObject.toString(),
                        (Class<?>) actualType);
                map.put(key, convertedValue);
            } else {
                map.put(key, value);
            }
        }
        return map;
    }

    @SuppressWarnings("unused")
    private static boolean isPrimitive(Class<?> returnClass) {
        boolean status = false;
        if (returnClass.getName().equalsIgnoreCase("java.lang.Short")
                || returnClass.getName().equalsIgnoreCase("short")
                || returnClass.getName().equalsIgnoreCase("java.lang.Byte")
                || returnClass.getName().equalsIgnoreCase("byte")
                || returnClass.getName()
                .equalsIgnoreCase("java.lang.Character")
                || returnClass.getName().equalsIgnoreCase("char")
                || returnClass.getName().equalsIgnoreCase("java.lang.Integer")
                || returnClass.getName().equalsIgnoreCase("int")
                || returnClass.getName().equalsIgnoreCase("java.lang.Long")
                || returnClass.getName().equalsIgnoreCase("long")
                || returnClass.getName().equalsIgnoreCase("java.lang.Boolean")
                || returnClass.getName().equalsIgnoreCase("boolean")
                || returnClass.getName().equalsIgnoreCase("java.lang.Float")
                || returnClass.getName().equalsIgnoreCase("float")
                || returnClass.getName().equalsIgnoreCase("java.lang.Double")
                || returnClass.getName().equalsIgnoreCase("double")
                || returnClass.getName().equalsIgnoreCase("java.lang.String")) {
            status = true;
        }
        return status;
    }

    private static Object getValueFromJson(JSONObject jsonObject, String key,
                                           boolean isIgnoreException) {
        Object value = null;
        try {
            value = jsonObject.get(key);
        } catch (Exception e) {
            if (!isIgnoreException) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return value;
    }

    /**
     * Returns json string.
     *
     * @param sourceBean
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String toJson(Object sourceBean) {
        String output = null;
        if (sourceBean == null) {
            throw new RuntimeException("source bean is null");
        }
        try {
            if (sourceBean instanceof Collection) {
                JSONArray jsonArray = new JSONArray((Collection) sourceBean);
                output = jsonArray.toString();
            } else if (sourceBean instanceof Map<?, ?>) {
                Map<Object, Object> map = (Map<Object, Object>) sourceBean;
                Map<Object, Object> tempMap = new HashMap<Object, Object>();
                for (Map.Entry<Object, Object> entry : map.entrySet()) {
                    tempMap.put(JSONObject.wrap(entry.getKey()),
                            JSONObject.wrap(entry.getValue()));
                }
                JSONObject jsonObject = new JSONObject(tempMap);
                output = jsonObject.toString();
            } else {
                BeanInfo beanInfo = Introspector.getBeanInfo(
                        sourceBean.getClass(), Object.class);
                Map<String, Object> map = new HashMap<String, Object>();
                for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                    String name = pd.getName();
                    Object value = pd.getReadMethod().invoke(sourceBean
                    );
                    if (value != null) {
                        map.put(name, JSONObject.wrap(value));
                    }
                }
                JSONObject jsonObject = new JSONObject(map);
                output = jsonObject.toString();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return output;
    }
}