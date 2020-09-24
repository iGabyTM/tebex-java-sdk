package me.gabytm.util.tebexsdk.endpoints.bans.objects;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("ign")
    private String inGameName;
    private String uuid;

    public String getInGameName() {
        return inGameName;
    }

    public String getUuid() {
        return uuid;
    }
}
