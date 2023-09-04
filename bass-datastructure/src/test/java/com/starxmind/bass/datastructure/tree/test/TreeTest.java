package com.starxmind.bass.datastructure.tree.test;

import com.google.common.collect.Lists;
import com.starxmind.bass.datastructure.tree.FlatArrayElement;
import com.starxmind.bass.datastructure.tree.TreeConverter;
import com.starxmind.bass.datastructure.tree.TreeNode;
import org.junit.Test;

import java.util.List;

/**
 * Test toTree and flat
 *
 * @author pizzalord
 * @since 1.0
 */
public class TreeTest {
    @Test
    public void toTreeTest() {
        List<FlatArrayElement<String, TreeConfig>> list = Lists.newArrayList();
        list.add(
                new FlatArrayElement<String, TreeConfig>().toBuilder()
                        .id("A01")
                        .parentId("A")
                        .name("xxx")
                        .config(
                                TreeConfig.builder()
                                        .type("AAA")
                                        .some("xasd21")
                                        .build()
                        )
                        .build()
        );
        list.add(
                new FlatArrayElement<String, TreeConfig>().toBuilder()
                        .id("A02")
                        .parentId("A")
                        .name("xxx")
                        .config(
                                TreeConfig.builder()
                                        .type("AAA")
                                        .some("xasd21")
                                        .build()
                        )
                        .build()
        );
        List<TreeNode<String, TreeConfig>> treeNodes = TreeConverter.toTree(list, "A", 1);
        System.out.println(treeNodes);
    }
}
