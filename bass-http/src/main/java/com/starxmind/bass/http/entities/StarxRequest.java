package com.starxmind.bass.http.entities;

import com.starxmind.bass.http.ContentType;
import com.starxmind.bass.http.RequestMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class StarxRequest {
    private String url;
    private RequestMethod requestMethod;
    private ContentType contentType;
    private Map<String, String> headers;
    private String jsonBody;
    private Map<String, String> formBody;
    private Map<String, String> queryParams;
}
