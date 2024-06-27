package com.starxmind.bass.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.starxmind.bass.json.beans.City;
import com.starxmind.bass.json.beans.Person;
import com.starxmind.bass.json.beans.Province;
import com.starxmind.bass.json.beans.Resp;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * json test
 *
 * @author pizzalord
 * @since 1.0
 */
public class XJsonTest {
    Province js = new Province(1, "江苏省",
            Lists.newArrayList(
                    new City(11, "南京市"),
                    new City(12, "扬州市")
            ));

    Province zj = new Province(2, "浙江省",
            Lists.newArrayList(
                    new City(21, "杭州市"),
                    new City(22, "湖州市")
            ));

    List<Province> provinces = Lists.newArrayList(js, zj);

    String oneProvinceJson = XJson.serializeAsString(js);
    String provincesJson = XJson.serializeAsString(provinces);

    @Test
    public void test1() {
        Province province = XJson.deserializeObject(oneProvinceJson, Province.class);
        System.out.println(province);
    }

    @Test
    public void test2() {
        List list = XJson.deserializeObject(provincesJson, List.class);
        System.out.println(list);
    }

    @Test
    public void test3() {
        Map map = XJson.deserializeObject(oneProvinceJson, Map.class);
        System.out.println(map);
    }

    @Test
    public void test4() {
        List<Province> provinces = XJson.deserializeList(provincesJson, Province.class);
        System.out.println(provinces);
    }

    @Test
    public void test5() {
        Map map = XJson.objectToMap(js);
        System.out.println(map);

        Province province = XJson.mapToObject(map, Province.class);
        System.out.println(province);
    }

    @Test
    public void test6() {
        List<Map> maps = XJson.objectListToMapList(provinces);
        System.out.println(maps);

        List<Province> provinces = XJson.mapListToObjectList(maps, Province.class);
        System.out.println(provinces);
    }

    @Test
    public void jsonWithGeneric() {
        Resp<Person> resp = new Resp<Person>().toBuilder()
                .code(0)
                .msg("OK")
                .data(Person.builder()
                        .name("Jackson")
                        .age(100)
                        .build())
                .build();
        String json = XJson.serializeAsString(resp);
        Resp<Person> object = XJson.deserializeObject(json, new TypeReference<Resp<Person>>() {
        });
        System.out.println(object.getData());
    }

    @Test
    public void testIgnoreUnknownProperties() {
        String cityJson = "{\"name\":\"xxx\",\"id\":100,\"desc\":\"abc\"}";
        City city = XJson.deserializeObject(cityJson, City.class);
        System.out.println(city);
    }

}
