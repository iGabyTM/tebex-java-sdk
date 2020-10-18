package me.gabytm.util.tebexsdk.endpoints.playerlookup.objects;

public class Player {

    private String id;
    private String createdAt;
    private String updatedAt;
    private String cacheExpire;
    private String username;
    private int pluginUsernameId;

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCacheExpire() {
        return cacheExpire;
    }

    public String getUsername() {
        return username;
    }

    public int getPluginUsernameId() {
        return pluginUsernameId;
    }
}
