package com.starxmind.bass.http.test;

import com.starxmind.bass.http.XHttp;
import com.starxmind.bass.http.test.domain.UserListResp;
import org.junit.Test;

/**
 * 使用 reqres.in 网站公开的API进行测试
 *
 * @author pizzalord
 * @since 1.0
 */
public class ReqresTest {
    @Test
    public void testGet() {
        XHttp simpleXHttp = new XHttp();
        UserListResp resp = simpleXHttp.getForObject("https://reqres.in/api/users?page=1&per_page=5", UserListResp.class);
        System.out.println(resp);
    }
}
