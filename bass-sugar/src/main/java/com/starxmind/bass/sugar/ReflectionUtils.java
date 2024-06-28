package com.starxmind.bass.sugar;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Reflection utils
 *
 * @author pizzalord
 * @since 1.0
 */
public final class ReflectionUtils {
    /**
     * Transfer an object to a map
     *
     * @param object Any object
     * @return
     */
    public static Map<String, Object> objectToMap(Object object) {
        Map<String, Object> reMap = Maps.newHashMap();
        if (object == null) {
            return null;
        }
        List<Field> fields = Lists.newArrayList();
        Class tempClass = object.getClass();
        while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
        }
        List<String> fieldNames = Arrays.stream(object.getClass().getDeclaredFields())
                .map(x -> x.getName())
                .collect(Collectors.toList());
        try {
            for (Field field : fields) {
                Field declaredField = fieldNames.contains(field.getName()) ?
                        object.getClass().getDeclaredField(field.getName()) :
                        object.getClass().getSuperclass().getDeclaredField(field.getName());
                declaredField.setAccessible(true);
                reMap.put(field.getName(), declaredField.get(object));
            }
            return reMap;
        } catch (Throwable throwable) {
            throw new RuntimeException("Fatal: an error occurred when the object was converted to map", throwable);
        }
    }

    /**
     * Transfer a map to an object
     *
     * @param map   Input map
     * @param clazz Target class
     * @param <T>   Target generic type
     * @return Object
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        try {
            T object = clazz.newInstance();
            Field[] declaredFields = object.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
//            field.set(obj, map.get(field.getName())); // 这种方式只有一层,不能递归

                // 这种方式可以递归
                Object value = map.get(field.getName());
                // 如果值还是Map,并且它的类型没有实现Map接口,那么需要再次转Object
                if (value instanceof Map && !hasInterface(field.getType(), Map.class)) {
                    value = mapToObject((Map) value, field.getType());
                }
                field.set(object, value);
            }
            return object;
        } catch (Throwable throwable) {
            throw new RuntimeException("Fatal: an error occurred when the map was converted to object", throwable);
        }
    }

    public static <T> T strMapToObject(Map<String, String> map, Class<T> clazz) {
        Map<String, Object> objMap = map.entrySet().stream()
                .collect(Collectors.toMap(
                        x -> x.getKey(),
                        x -> x.getValue()
                ));
        return mapToObject(objMap, clazz);
    }

    /**
     * Determine whether the class contains the specified interface
     *
     * @param type           Source class
     * @param interfaceClass Interface class
     * @return boolean
     */
    public static <T, I> boolean hasInterface(Class<T> type, Class<I> interfaceClass) {
        Class<?>[] interfaces = type.getInterfaces();
        for (Class<?> i : interfaces) {
            if (i.equals(interfaceClass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Transfer map list to object list
     *
     * @param list        Data list
     * @param targetClass Target object class
     * @param <T>         Class type
     * @return List of objects
     */
    public static <T> List<T> mapListToObjectList(List<Map<String, Object>> list, Class<T> targetClass) {
        List<T> retList = Lists.newArrayList();
        for (Map<String, Object> each : list) {
            T obj = mapToObject(each, targetClass);
            retList.add(obj);
        }
        return retList;
    }

    /**
     * Transfer object list to map list
     *
     * @param list Data list
     * @param <T>  The object type for data list
     * @return List List of map elements
     */
    public static <T> List<Map<String, Object>> objectListToMapList(List<T> list) {
        List<Map<String, Object>> retList = Lists.newArrayList();
        for (T obj : list) {
            Map<String, Object> map = objectToMap(obj);
            retList.add(map);
        }
        return retList;
    }

    /**
     * @param clazz Super class
     * @param index Generic index
     * @return Generic type
     */
    public static Class getSuperClassGenericType(Class clazz, int index) {
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        if (index > params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * Get the super or itself suitable constructor by parameter types
     *
     * @param clazz class
     * @param superOrSelfConstructorParameterTypes constructor parameter types
     * @return The suitable constructor
     */
    public static Constructor<?> getSuitableConstructor(Class clazz, List<Class<?>> superOrSelfConstructorParameterTypes) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (int i = 0; i < superOrSelfConstructorParameterTypes.size(); i++) {
                if (!parameterTypes[i].equals(superOrSelfConstructorParameterTypes.get(i))) {
                    break; // 构造器不匹配，继续匹配下一个
                }
                // 匹配，则返回
                return constructor;
            }
        }
        throw new IllegalArgumentException("No suitable constructor found for " + clazz.getName());
    }
}
