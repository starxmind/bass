package com.starxmind.bass.http.callers;

import com.starxmind.bass.http.ContentType;
import com.starxmind.bass.http.XHttpException;
import com.starxmind.bass.http.entities.XRequest;
import com.starxmind.bass.sugar.Asserts;
import com.starxmind.bass.sugar.Sugar;
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
    private final OkHttpClient okHttpClient;

    public AbstractHttpCaller(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public String call(XRequest xRequest) {
        byte[] bytes = callForBytes(xRequest);
        return new String(bytes);
    }

    public byte[] callForBytes(XRequest xRequest) {
        Request request = buildRequest(xRequest);
        return callOkHttp(request);
    }

    protected byte[] callOkHttp(Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            Asserts.isTrue(response.isSuccessful(),
                    String.format("Unexpected http code: %d, error message: %s", response.code(), response.message()));
            assert response.body() != null;
            return response.body().bytes();
        } catch (Exception e) {
            throw new XHttpException(e);
        } finally {
            Sugar.closeQuietly(response);
        }
    }

    protected abstract Request buildRequest(XRequest xRequest);

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
    protected static RequestBody getRequestBody(XRequest xRequest) {
        RequestBody requestBody = null;
        if (xRequest.getContentType() == ContentType.JSON) {
            requestBody = RequestBody.create(
                    MediaType.parse(ContentType.JSON.getWellFormedMediaTypeString()),
                    xRequest.getJsonBody() == null ? "" : xRequest.getJsonBody());
        } else if (xRequest.getContentType() == ContentType.FORM) {
            FormBody.Builder formBuilder = new FormBody.Builder();
            xRequest.getFormBody().entrySet()
                    .stream()
                    .filter(x -> x.getValue() != null)
                    .forEach(x -> formBuilder.add(x.getKey(), x.getValue()));
            requestBody = formBuilder.build();
        }
        return requestBody;
    }
}
