package me.gabytm.util.tebexsdk.endpoints.information.objects;

import com.google.gson.annotations.SerializedName;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class Currency {

    @SerializedName("iso_4217") private String iso;
    private String symbol;

    public String getIso() {
        return iso;
    }

    public String getSymbol() {
        return symbol;
    }

}
