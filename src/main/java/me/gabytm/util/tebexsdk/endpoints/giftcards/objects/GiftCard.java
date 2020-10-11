package me.gabytm.util.tebexsdk.endpoints.giftcards.objects;

import com.google.gson.annotations.SerializedName;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class GiftCard {

    private int id;
    private String code;
    private Balance balance;
    private String note;
    @SerializedName("void") private boolean isVoid;

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Balance getBalance() {
        return balance;
    }

    public String getNote() {
        return note;
    }

    public boolean isVoid() {
        return isVoid;
    }

}
