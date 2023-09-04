package com.starxmind.bass.io.yaml.test;

import com.starxmind.bass.io.yaml.YamlReader;
import com.starxmind.bass.io.yaml.test.objects.TestObject;
import org.junit.Test;

import java.util.Map;

/**
 * 测试阅读YAML配置文件
 *
 * @author pizzalord
 * @since 1.0
 */
public class YamlTest {
    @Test
    public void readAsMapTest() {
        Map<String, Object> map = YamlReader.readAsMap("test.yml");
        System.out.println(map);
    }

    @Test
    public void readAsObjectTest() {
        TestObject object = YamlReader.readAsObject("test.yml", TestObject.class);
        System.out.println(object);
    }
}
