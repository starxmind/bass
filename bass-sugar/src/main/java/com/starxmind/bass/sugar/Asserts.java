package com.starxmind.bass.sugar;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 断言工具
 *
 * @author pizzalord
 * @since 1.0
 */
public abstract class Asserts {
    /**
     * Assert the expression is true
     *
     * @param expression expression
     */
    public static void isTrue(boolean expression) {
        if (!expression) {
            throw new RuntimeException();
        }
    }

    /**
     * Assert the expression is true
     *
     * @param expression expression
     * @param error      Error message
     */
    public static void isTrue(boolean expression, String error) {
        if (!expression) {
            throw new RuntimeException(error);
        }
    }

    /**
     * Assert the expression is true
     *
     * @param expression expression
     * @param ex         Customized exception
     */
    public static void isTrue(boolean expression, RuntimeException ex) {
        if (!expression) {
            throw ex;
        }
    }

    /**
     * Determine an object is null
     *
     * @param object Input object
     */
    public static void isNull(Object object) {
        isTrue(object == null);
    }

    /**
     * Determine an object is null
     *
     * @param object Input object
     * @param error  customized error
     */
    public static void isNull(Object object, String error) {
        isTrue(object == null, error);
    }

    /**
     * Determine an object is not null
     *
     * @param object Input object
     */
    public static void notNull(Object object) {
        isTrue(object != null);
    }

    /**
     * Determine an object is not null
     *
     * @param object Input object
     * @param error  customized error
     */
    public static void notNull(Object object, String error) {
        isTrue(object != null, error);
    }

    /**
     * Assert a string is not empty
     *
     * @param string Input string
     */
    public static void notEmpty(String string) {
        isTrue(StringUtils.isNotEmpty(string));
    }

    /**
     * Assert a string is not empty
     *
     * @param string Input string
     * @param error  Error message
     */
    public static void notEmpty(String string, String error) {
        isTrue(StringUtils.isNotEmpty(string), error);
    }

    /**
     * Assert a string is not empty
     *
     * @param string           Input string
     * @param RuntimeException Customized exception
     */
    public static void notEmpty(String string, RuntimeException RuntimeException) {
        isTrue(StringUtils.isNotEmpty(string), RuntimeException);
    }

    /**
     * Assert a string contains another string
     *
     * @param string          Origin string
     * @param containedString Contained string
     */
    public static void contains(String string, String containedString) {
        isTrue(StringUtils.contains(string, containedString));
    }

    /**
     * Assert a string contains another string
     *
     * @param string          Origin string
     * @param containedString Contained string
     * @param error           Error message
     */
    public static void contains(String string, String containedString, String error) {
        isTrue(StringUtils.contains(string, containedString), error);
    }

    /**
     * Assert a string contains another string
     *
     * @param string           Origin string
     * @param containedString  Contained string
     * @param RuntimeException Customized exception
     */
    public static void contains(String string, String containedString, RuntimeException RuntimeException) {
        isTrue(StringUtils.contains(string, containedString), RuntimeException);
    }

    /**
     * Assert a string does not contain another string
     *
     * @param string          Origin string
     * @param containedString Contained string
     */
    public static void notContains(String string, String containedString) {
        isTrue(!StringUtils.contains(string, containedString));
    }

    /**
     * Assert a string does not contain another string
     *
     * @param string          Origin string
     * @param containedString Contained string
     * @param error           Error message
     */
    public static void notContains(String string, String containedString, String error) {
        isTrue(!StringUtils.contains(string, containedString), error);
    }

    /**
     * Assert a string does not contain another string
     *
     * @param string           Origin string
     * @param containedString  Contained string
     * @param RuntimeException Customized exception
     */
    public static void notContains(String string, String containedString, RuntimeException RuntimeException) {
        isTrue(!StringUtils.contains(string, containedString), RuntimeException);
    }

    /**
     * Assert a collection is not empty
     *
     * @param collection Input collection
     */
    public static void notEmpty(Collection collection) {
        isTrue(CollectionUtils.isNotEmpty(collection));
    }

    /**
     * Assert a collection is not empty
     *
     * @param collection Input collection
     * @param error      Error message
     */
    public static void notEmpty(Collection collection, String error) {
        isTrue(CollectionUtils.isNotEmpty(collection), error);
    }

    /**
     * Assert a collection is not empty
     *
     * @param collection       Input collection
     * @param RuntimeException Customized exception
     */
    public static void notEmpty(Collection collection, RuntimeException RuntimeException) {
        isTrue(CollectionUtils.isNotEmpty(collection), RuntimeException);
    }

    /**
     * Assert a map is not empty
     *
     * @param map Input map
     */
    public static void notEmpty(Map map) {
        isTrue(MapUtils.isNotEmpty(map));
    }

    /**
     * Assert a map is not empty
     *
     * @param map   Input map
     * @param error Error message
     */
    public static void notEmpty(Map map, String error) {
        isTrue(MapUtils.isNotEmpty(map), error);
    }

    /**
     * Assert a map is not empty
     *
     * @param map              Input map
     * @param RuntimeException Customized exception
     */
    public static void notEmpty(Map map, RuntimeException RuntimeException) {
        isTrue(MapUtils.isNotEmpty(map), RuntimeException);
    }

    /**
     * Assert two objects are equal
     *
     * @param o1 Object one
     * @param o2 Object two
     */
    public static void equals(Object o1, Object o2) {
        isTrue(Objects.equals(o1, o2));
    }

    /**
     * Assert two objects are equal
     *
     * @param o1    Object one
     * @param o2    Object two
     * @param error Error message
     */
    public static void equals(Object o1, Object o2, String error) {
        isTrue(Objects.equals(o1, o2), error);
    }

    /**
     * Assert two objects are equal
     *
     * @param o1 Object one
     * @param o2 Object two
     * @param ex Customized exception
     */
    public static void equals(Object o1, Object o2, RuntimeException ex) {
        isTrue(Objects.equals(o1, o2), ex);
    }
}
