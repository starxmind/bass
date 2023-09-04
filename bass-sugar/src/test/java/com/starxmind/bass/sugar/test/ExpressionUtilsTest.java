package com.starxmind.bass.sugar.test;

import com.google.common.collect.ImmutableMap;
import com.starxmind.bass.sugar.ExpressionUtils;
import com.starxmind.bass.sugar.test.beans.City;
import com.starxmind.bass.sugar.test.beans.County;
import com.starxmind.bass.sugar.test.beans.Province;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class ExpressionUtilsTest {
    @Test
    public void evaluateExpressionTest() {
        Map<String, String> variables = ImmutableMap.of("a", "AAA", "b", "BBB");
        String result = ExpressionUtils.evaluateExpression("Variable is ${c} ${a} ${a} ${b}", variables);
        System.out.println(result);
    }

    @Test
    public void extractVariablesTest() {
        String expression = "Hello ${key1} ${key1} ${key1} ${key2:default} ${key3}";
        Set<String> variables = ExpressionUtils.extractVariables(expression);
        System.out.println(variables);
    }

    @Test
    public void retrieveValueTest() {
        Province province = new Province();
        City city = new City();
        County county = new County();
        county.setName("歙县");
        city.setCounty(county);
        province.setCity(city);
        Object o = ExpressionUtils.retrieveValue(province, "city.county.name");
        System.out.println(o);
    }
}
