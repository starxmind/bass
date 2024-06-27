package com.starxmind.bass.http;

import com.starxmind.bass.http.callers.DeleteHttpCaller;
import com.starxmind.bass.http.callers.GetHttpCaller;
import com.starxmind.bass.http.callers.PostHttpCaller;
import com.starxmind.bass.http.callers.PutHttpCaller;
import com.starxmind.bass.http.entities.XRequest;
import com.starxmind.bass.json.XJson;
import lombok.Getter;
import lombok.Setter;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Http client
 *
 * @author pizzalord
 * @since 1.0
 */
public class XHttp {
    /**
     * 超时秒数,默认为30
     */
    @Getter
    @Setter
    private long timeoutSeconds = 30;

    /**
     * 最大连接数
     */
    @Getter
    @Setter
    private int maxIdleConnections = 100;

    /**
     * 保持活动持续秒数
     */
    @Getter
    @Setter
    private long keepAliveDurationSeconds = 5;

    /**
     * Core of {@link OkHttpClient}
     */
    private OkHttpClient okHttpClient;

    private GetHttpCaller getHttpCaller;
    private PostHttpCaller postHttpCaller;
    private PutHttpCaller putHttpCaller;
    private DeleteHttpCaller deleteHttpCaller;

    /**
     * Get http caller
     *
     * @return {@link GetHttpCaller}
     */

    /**
     * Constructor
     */
    public XHttp() {
        init();
    }

    private void init() {
        this.okHttpClient = okHttpClient();
        this.getHttpCaller = new GetHttpCaller(this.okHttpClient);
        this.postHttpCaller = new PostHttpCaller(this.okHttpClient);
        this.putHttpCaller = new PutHttpCaller(this.okHttpClient);
        this.deleteHttpCaller = new DeleteHttpCaller(this.okHttpClient);
    }

    /**
     * Constructor with params
     *
     * @param timeoutSeconds           超时秒数
     * @param maxIdleConnections       最大连接数
     * @param keepAliveDurationSeconds 保持活动持续秒数
     */
    public XHttp(long timeoutSeconds, int maxIdleConnections, long keepAliveDurationSeconds) {
        this.timeoutSeconds = timeoutSeconds;
        this.maxIdleConnections = maxIdleConnections;
        this.keepAliveDurationSeconds = keepAliveDurationSeconds;
        init();
    }

    /**
     * To build a okHttpClient
     *
     * @return OkHttpClient
     */
    private OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectionPool(pool())
                .connectTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .readTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .writeTimeout(timeoutSeconds, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Create a new connection pool with tuning parameters appropriate for a single-user application.
     * The tuning parameters in this pool are subject to change in future OkHttp releases. Currently
     */
    private ConnectionPool pool() {
        return new ConnectionPool(maxIdleConnections, keepAliveDurationSeconds, TimeUnit.SECONDS);
    }

    protected <T> T json2Object(String json,
                                Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return XJson.deserializeObject(json, clazz);
    }

    // GET START ------------------------------------------------------------------------------------------------------
    public String get(String url) {
        return get(url, null, null);
    }

    public String get(String url,
                      Map<String, String> headers) {
        return get(url, headers, null);
    }

    public String get(String url,
                      Map<String, String> headers,
                      Map<String, String> queryParams) {
        return getHttpCaller.call(
                XRequest.builder()
                        .url(url)
                        .headers(headers)
                        .queryParams(queryParams)
                        .build()
        );
    }

    public <T> T getForObject(String url,
                              Class<T> clazz) {
        String response = get(url);
        return json2Object(response, clazz);
    }

    public <T> T getForObject(String url,
                              Map<String, String> headers,
                              Class<T> clazz) {
        String response = get(url, headers);
        return json2Object(response, clazz);
    }

    public <T> T getForObject(String url,
                              Map<String, String> headers,
                              Map<String, String> queryParams,
                              Class<T> clazz) {
        String response = get(url, headers, queryParams);
        return json2Object(response, clazz);
    }

    public byte[] getForBytes(String url) {
        return getForBytes(url, null, null);
    }

    public byte[] getForBytes(String url,
                              Map<String, String> headers) {
        return getForBytes(url, headers, null);
    }

    public byte[] getForBytes(String url,
                              Map<String, String> headers,
                              Map<String, String> queryParams) {
        return getHttpCaller.callForBytes(
                XRequest.builder()
                        .url(url)
                        .headers(headers)
                        .queryParams(queryParams)
                        .build()
        );
    }
    // GET END --------------------------------------------------------------------------------------------------------

    // POST START -----------------------------------------------------------------------------------------------------
    public String post(String url,
                       Map<String, String> headers,
                       String jsonBody) {
        return postHttpCaller.call(
                XRequest.builder()
                        .url(url)
                        .headers(headers)
                        .jsonBody(jsonBody)
                        .contentType(ContentType.JSON)
                        .build()
        );
    }

    public String post(String url,
                       Map<String, String> headers,
                       Map<String, String> formBody) {
        return postHttpCaller.call(
                XRequest.builder()
                        .url(url)
                        .headers(headers)
                        .formBody(formBody)
                        .contentType(ContentType.FORM)
                        .build()
        );
    }

    public <T> T postForObject(String url,
                               Map<String, String> headers,
                               String jsonBody,
                               Class<T> clazz) {
        String response = post(url, headers, jsonBody);
        return json2Object(response, clazz);
    }

    public <T> T postForObject(String url,
                               Map<String, String> headers,
                               Map<String, String> formBody,
                               Class<T> clazz) {
        String response = post(url, headers, formBody);
        return json2Object(response, clazz);
    }

    public byte[] postForBytes(String url,
                               Map<String, String> headers,
                               String jsonBody) {
        return postHttpCaller.callForBytes(
                XRequest.builder()
                        .url(url)
                        .headers(headers)
                        .jsonBody(jsonBody)
                        .contentType(ContentType.JSON)
                        .build()
        );
    }

    public byte[] postForBytes(String url,
                               Map<String, String> headers,
                               Map<String, String> formBody) {
        return postHttpCaller.callForBytes(
                XRequest.builder()
                        .url(url)
                        .headers(headers)
                        .formBody(formBody)
                        .contentType(ContentType.FORM)
                        .build()
        );
    }
    // POST END -------------------------------------------------------------------------------------------------------

    // PUT START ------------------------------------------------------------------------------------------------------
    public String put(String url,
                      Map<String, String> headers,
                      String jsonBody) {
        return putHttpCaller.call(
                XRequest.builder()
                        .url(url)
                        .headers(headers)
                        .jsonBody(jsonBody)
                        .contentType(ContentType.JSON)
                        .build()
        );
    }

    public String put(String url,
                      Map<String, String> headers,
                      Map<String, String> formBody) {
        return putHttpCaller.call(
                XRequest.builder()
                        .url(url)
                        .headers(headers)
                        .formBody(formBody)
                        .contentType(ContentType.FORM)
                        .build()
        );
    }

    public <T> T putForObject(String url,
                              Map<String, String> headers,
                              String jsonBody,
                              Class<T> clazz) {
        String response = put(url, headers, jsonBody);
        return json2Object(response, clazz);
    }

    public <T> T putForObject(String url,
                              Map<String, String> headers,
                              Map<String, String> formBody,
                              Class<T> clazz) {
        String response = put(url, headers, formBody);
        return json2Object(response, clazz);
    }
    // PUT END --------------------------------------------------------------------------------------------------------

    // DELETE START ---------------------------------------------------------------------------------------------------
    public String delete(String url,
                         Map<String, String> headers,
                         String jsonBody) {
        return deleteHttpCaller.call(
                XRequest.builder()
                        .url(url)
                        .headers(headers)
                        .jsonBody(jsonBody)
                        .contentType(ContentType.JSON)
                        .build()
        );
    }

    public String delete(String url,
                         Map<String, String> headers,
                         Map<String, String> formBody) {
        return deleteHttpCaller.call(
                XRequest.builder()
                        .url(url)
                        .headers(headers)
                        .formBody(formBody)
                        .contentType(ContentType.FORM)
                        .build()
        );
    }

    public <T> T deleteForObject(String url,
                                 Map<String, String> headers,
                                 String jsonBody,
                                 Class<T> clazz) {
        String response = delete(url, headers, jsonBody);
        return json2Object(response, clazz);
    }

    public <T> T deleteForObject(String url,
                                 Map<String, String> headers,
                                 Map<String, String> formBody,
                                 Class<T> clazz) {
        String response = delete(url, headers, formBody);
        return json2Object(response, clazz);
    }
    // DELETE END -----------------------------------------------------------------------------------------------------
}
