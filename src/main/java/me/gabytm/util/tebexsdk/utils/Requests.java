package me.gabytm.util.tebexsdk.utils;

import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import okhttp3.Request;

public class Requests {

    private Requests() { }

    public static Request createGetRequest(final String serverSecretKey, final Endpoint endpoint) {
        return createGetRequest(serverSecretKey, endpoint.getUrl());
    }

    public static Request createGetRequest(final String serverSecretKey, final String url) {
        return new Request.Builder()
                .addHeader(Constant.TEBEX_SECRET, serverSecretKey)
                .url(url)
                .get()
                .build();
    }

}
