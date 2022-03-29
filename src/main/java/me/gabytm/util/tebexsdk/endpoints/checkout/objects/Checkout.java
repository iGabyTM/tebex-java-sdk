package me.gabytm.util.tebexsdk.endpoints.checkout.objects;

import com.google.gson.annotations.SerializedName;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
@SuppressWarnings("unused")
public class Checkout {

    private String url;
    @SerializedName("expires") private String expirationDate;

    public String getUrl() {
        return url;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
}
