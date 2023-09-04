package com.starxmind.bass.json.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Province {
    private int id;
    private String name;
    private List<City> cities;
}
