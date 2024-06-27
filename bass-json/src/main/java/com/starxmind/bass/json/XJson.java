package com.starxmind.bass.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starxmind.bass.json.exceptions.DeserializeException;
import com.starxmind.bass.json.exceptions.SerializeException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * JSON工具
 *
 * @author pizzalord
 * @since 1.0
 */
public class XJson {
    /**
     * core
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // config
    static {
        // ignore all unknown properties
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象序列化成JSON string
     *
     * @param Object 对象
     * @param <T>    对象类型
     * @return JSON
     */
    public static <T> String serializeAsString(T Object) {
        try {
            return objectMapper.writeValueAsString(Object);
        } catch (Exception e) {
            throw new SerializeException("Fatal: failed to convert the Object to a json string...", e);
        }
    }

    /**
     * 对象序列化成JSON bytes
     *
     * @param Object 对象
     * @param <T>    对象类型
     * @return JSON
     */
    public static <T> byte[] serializeAsBytes(T Object) {
        try {
            return objectMapper.writeValueAsBytes(Object);
        } catch (Exception e) {
            throw new SerializeException("Fatal: failed to convert the Object to json bytes...", e);
        }
    }

    /**
     * JSON反序列化成对象
     *
     * @param json  json
     * @param clazz 对象class
     * @param <T>   对象类型
     * @return
     */
    public static <T> T deserializeObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new DeserializeException("Fatal: failed to convert the json to an object...", e);
        }
    }

    public static <T> T deserializeObject(byte[] bytes, Class<T> clazz) {
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (Exception e) {
            throw new DeserializeException("Fatal: failed to convert the json to an object...", e);
        }
    }

    public static <T> T deserializeObject(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new DeserializeException("Fatal: failed to convert the json to an object...", e);
        }
    }

    public static <T> T deserializeObject(byte[] bytes, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(bytes, typeReference);
        } catch (Exception e) {
            throw new DeserializeException("Fatal: failed to convert the json to an object...", e);
        }
    }

    /**
     * JSON反序列化成Object对象
     *
     * @param json
     * @return
     */
    public static Object deserializeObject(String json) {
        return deserializeObject(json, Object.class);
    }

    /**
     * JSON反序列化成对象list
     *
     * @param json  json
     * @param clazz 集合中对象class
     * @param <T>   集合中对象类型
     * @return
     */
    public static <T> List<T> deserializeList(String json,
                                              Class<T> clazz) {
        try {
            return objectMapper.readValue(json, getCollectionType(List.class, clazz));
        } catch (Exception e) {
            throw new DeserializeException("Fatal: failed to convert the json to an object...", e);
        }
    }

    /**
     * 获取带泛型的集合类型
     *
     * @param collectionClass 集合class
     * @param elementClasses  集合中元素的class
     * @return 带泛型的集合类型
     */
    private static <C extends Collection> JavaType getCollectionType(Class<C> collectionClass,
                                                                     Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static Map objectToMap(Object o) {
        String json = serializeAsString(o);
        return deserializeObject(json, Map.class);
    }

    public static <T> T mapToObject(Map map, Class<T> clazz) {
        String json = serializeAsString(map);
        return deserializeObject(json, clazz);
    }

    public static <T> List<T> mapListToObjectList(List<Map> mapList, Class<T> clazz) {
        String json = serializeAsString(mapList);
        return deserializeList(json, clazz);
    }

    public static <T> List<Map> objectListToMapList(List<T> objectList) {
        String json = serializeAsString(objectList);
        return deserializeList(json, Map.class);
    }
}
