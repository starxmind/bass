package com.starxmind.bass.http.callers;

import com.starxmind.bass.http.ContentType;
import com.starxmind.bass.http.StarxHttpException;
import com.starxmind.bass.http.entities.StarxRequest;
import com.starxmind.bass.sugar.Asserts;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public abstract class AbstractHttpCaller {
    private OkHttpClient okHttpClient;

    public AbstractHttpCaller(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public String call(StarxRequest starxRequest) {
        byte[] bytes = callForBytes(starxRequest);
        return new String(bytes);
    }

    public byte[] callForBytes(StarxRequest starxRequest) {
        Request request = buildRequest(starxRequest);
        return callOkHttp(request);
    }

    protected byte[] callOkHttp(Request request) {
        try {
            Response response = okHttpClient.newCall(request).execute();
            Asserts.isTrue(response.isSuccessful(),
                    String.format("Unexpected http code: %d, error message: %s", response.code(), response.message()));
            return response.body().bytes();
        } catch (Exception e) {
            throw new StarxHttpException(e);
        }
    }

    protected abstract Request buildRequest(StarxRequest starxRequest);

    /**
     * Append headers
     *
     * @param builder Request builder
     * @param header  Header map
     */
    protected void appendHeaders(Request.Builder builder, Map<String, String> header) {
        if (MapUtils.isEmpty(header)) {
            return;
        }
        header.entrySet().stream()
                .filter(x -> x.getValue() != null)
                .forEach(x -> builder.addHeader(x.getKey(), x.getValue()));
    }

    @Nullable
    protected static RequestBody getRequestBody(StarxRequest starxRequest) {
        RequestBody requestBody = null;
        if (starxRequest.getContentType() == ContentType.JSON) {
            requestBody = RequestBody.create(
                    MediaType.parse(ContentType.JSON.getWellFormedMediaTypeString()),
                    starxRequest.getJsonBody() == null ? "" : starxRequest.getJsonBody());
        } else if (starxRequest.getContentType() == ContentType.FORM) {
            FormBody.Builder formBuilder = new FormBody.Builder();
            starxRequest.getFormBody().entrySet()
                    .stream()
                    .filter(x -> x.getValue() != null)
                    .forEach(x -> formBuilder.add(x.getKey(), x.getValue()));
            requestBody = formBuilder.build();
        }
        return requestBody;
    }
}
