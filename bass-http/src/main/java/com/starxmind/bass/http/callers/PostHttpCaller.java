package com.starxmind.bass.http.callers;

import com.starxmind.bass.http.entities.XRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class PostHttpCaller extends AbstractHttpCaller {
    public PostHttpCaller(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }

    @Override
    public Request buildRequest(XRequest xRequest) {
        RequestBody requestBody = super.getRequestBody(xRequest);
        Request.Builder builder = new Request.Builder().url(xRequest.getUrl());
        appendHeaders(builder, xRequest.getHeaders());
        return builder.post(requestBody).build();
    }
}
