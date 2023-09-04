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
public class DeleteHttpCaller extends AbstractHttpCaller {
    public DeleteHttpCaller(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }

    @Override
    public String call(StarxRequest starxRequest) {
        RequestBody requestBody = super.getRequestBody(starxRequest);
        Request.Builder builder = new Request.Builder().url(starxRequest.getUrl());
        appendHeaders(builder, starxRequest.getHeaders());
        Request request = builder.delete(requestBody).build();
        return call(request);
    }
}
