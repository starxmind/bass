package com.starxmind.bass.io.yaml.test.objects;

import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
@Data
public class TestObject {
    private String key1;
    private Key2 key2;
    private List<Key3Element> key3;
}
