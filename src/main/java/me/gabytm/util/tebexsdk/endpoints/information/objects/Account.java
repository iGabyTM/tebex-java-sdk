package me.gabytm.util.tebexsdk.endpoints.information.objects;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class Account {

    private int id;
    private String domain;
    private String name;
    private Currency currency;
    private boolean onlineMode;
    private String gameType;
    private boolean logEvents;

    public int getId() {
        return id;
    }

    public String getDomain() {
        return domain;
    }

    public String getName() {
        return name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public boolean isOnlineMode() {
        return onlineMode;
    }

    public String getGameType() {
        return gameType;
    }

    public boolean isLogEvents() {
        return logEvents;
    }

}
