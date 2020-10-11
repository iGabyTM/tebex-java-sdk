package me.gabytm.util.tebexsdk.endpoints.bans.objects;

import com.google.gson.annotations.SerializedName;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class User {

    @SerializedName("ign") private String inGameName;
    private String uuid;

    public String getInGameName() {
        return inGameName;
    }

    public String getUuid() {
        return uuid;
    }
}
