package me.gabytm.util.tebexsdk.endpoints.commandqueue.objects;

import okhttp3.OkHttpClient;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
@SuppressWarnings("unused")
public class Player {

    private int id;
    private String name;
    private String uuid;

    /**
     * @see me.gabytm.util.tebexsdk.endpoints.commandqueue.CommandQueueEndpoint#getOnlineCommands(String, OkHttpClient, int)
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

}
