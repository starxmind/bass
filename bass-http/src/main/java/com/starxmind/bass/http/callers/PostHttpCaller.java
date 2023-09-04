package com.starxmind.bass.http.callers;

import com.starxmind.bass.http.entities.StarxRequest;
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
    public String call(StarxRequest starxRequest) {
        RequestBody requestBody = super.getRequestBody(starxRequest);
        Request.Builder builder = new Request.Builder().url(starxRequest.getUrl());
        appendHeaders(builder, starxRequest.getHeaders());
        Request request = builder.post(requestBody).build();
        return call(request);
    }
}
