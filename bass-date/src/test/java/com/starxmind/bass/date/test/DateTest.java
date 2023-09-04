package com.starxmind.bass.date.test;

import com.starxmind.bass.date.DateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class DateTest {
    @Test
    public void todayZero() {
        Date date = DateUtils.todayZero();
        System.out.println(date);
    }

    @Test
    public void ageTest() {
        System.out.println(DateUtils.getAge("1989-08-07"));
    }
}
