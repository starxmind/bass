package com.starxmind.bass.io.yaml;

import com.starxmind.bass.sugar.ClassLoaderUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * Yaml格式阅读器
 *
 * @author pizzalord
 * @since 1.0
 */
public abstract class YamlReader {
    private static final Yaml YAML = new Yaml();

    public static Map<String, Object> readAsMap(String yamlFile) {
        return readAsMap(YamlReader.class, yamlFile);
    }

    public static Map<String, Object> readAsMap(Class<?> resourceClass, String yamlFile) {
        return readAsObject(resourceClass, yamlFile, Map.class);
    }

    public static <T> T readAsObject(String yamlFile, Class<T> returnClass) {
        return readAsObject(YamlReader.class, yamlFile, returnClass);
    }

    public static <T> T readAsObject(Class<?> resourceClass, String yamlFile, Class<T> returnClass) {
        return readAsObject(ClassLoaderUtils.getResourceStream(resourceClass, yamlFile), returnClass);
    }

    public static Map<String, Object> readAsMap(InputStream inputStream) {
        return readAsObject(inputStream, Map.class);
    }

    public static <T> T readAsObject(InputStream inputStream, Class<T> returnClass) {
        return YAML.loadAs(inputStream, returnClass);
    }
}
