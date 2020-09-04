package me.gabytm.util.tebexsdk.endpoints.checkout;

import com.google.gson.JsonObject;

public class CheckoutResponse {

    private final String url;
    private final String expirationDate;

    public CheckoutResponse(final JsonObject json) {
        this.url = json.get("url").getAsString();
        this.expirationDate = json.get("expires").getAsString();
    }

    public String getUrl() {
        return url;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return "CheckoutEndpointResponse{" +
                "url='" + url + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
