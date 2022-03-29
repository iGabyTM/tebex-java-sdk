package me.gabytm.util.tebexsdk.endpoints.commandqueue.objects;

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
     * @see me.gabytm.util.tebexsdk.TebexAPI#getOnlineCommands(int)
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
