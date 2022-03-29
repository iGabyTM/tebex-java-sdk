package me.gabytm.util.tebexsdk.endpoints.commandqueue.objects;

import com.google.gson.annotations.SerializedName;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
@SuppressWarnings("unused")
public class Command {

    private int id;
    private String command;
    private int payment;
    @SerializedName("package") private int pakage;
    private Player player;

    /**
     * @see me.gabytm.util.tebexsdk.TebexAPI#deleteCommands(int[])
     */
    public int getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public int getPayment() {
        return payment;
    }

    public int getPackage() {
        return pakage;
    }

    public Player getPlayer() {
        return player;
    }
}
