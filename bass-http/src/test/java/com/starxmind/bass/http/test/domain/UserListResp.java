package com.starxmind.bass.http.test.domain;

import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
@Data
public class UserListResp {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<User> data;
    private Support support;

    @Data
    public static class User {
        private int id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
    }

    @Data
    public static class Support {
        private String url;
        private String text;
    }
}
