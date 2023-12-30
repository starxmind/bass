package com.starxmind.bass.http.callers;

import com.starxmind.bass.http.entities.StarxRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author pizzalord
 * @since 1.0
 */
public class GetHttpCaller extends AbstractHttpCaller {
    public GetHttpCaller(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }

    @Override
    protected Request buildRequest(StarxRequest starxRequest) {
        String url = starxRequest.getUrl();
        if (MapUtils.isNotEmpty(starxRequest.getQueryParams())) {
            Map<String, String> params = starxRequest.getQueryParams().entrySet().stream()
                    .filter(x -> x.getValue() != null)
                    .collect(Collectors.toMap(
                            x -> x.getKey(),
                            x -> x.getValue()
                    ));
            url = appendParamsAfterUrl(url, params);
        }
        Request.Builder builder = new Request.Builder().url(url);
        appendHeaders(builder, starxRequest.getHeaders());
        return builder.get().build();
    }

    /**
     * @param url    Request url
     * @param params Append params after url
     * @return Url with params
     */
    private String appendParamsAfterUrl(String url, Map<String, String> params) {
        StringBuffer urlWithParams = new StringBuffer(url);
        if (urlWithParams.indexOf("?") == -1) {
            urlWithParams.append("?");
        } else {
            urlWithParams.append("&");
        }
        for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
            urlWithParams.append(stringStringEntry.getKey());
            urlWithParams.append("=");
            urlWithParams.append(stringStringEntry.getValue());
            urlWithParams.append("&");
        }
        urlWithParams.setLength(urlWithParams.length() - 1);
        return urlWithParams.toString();
    }
}
