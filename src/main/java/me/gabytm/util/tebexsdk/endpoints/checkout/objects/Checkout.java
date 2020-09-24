package me.gabytm.util.tebexsdk.endpoints.checkout.objects;

import com.google.gson.annotations.SerializedName;

public class Checkout {

    private String url;

    @SerializedName("expires")
    private String expirationDate;

    public String getUrl() {
        return url;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
}
