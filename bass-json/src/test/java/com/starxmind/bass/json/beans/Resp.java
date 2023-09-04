package com.starxmind.bass.json.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class Resp<T> {
    private int code;
    private String msg;
    private T data;
}
