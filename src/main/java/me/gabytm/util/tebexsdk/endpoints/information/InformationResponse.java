package me.gabytm.util.tebexsdk.endpoints.information;

import com.google.gson.JsonObject;

public class InformationResponse {

    // Account
    private final int accountId;
    private final String domain;
    private final String name;
    private final String currencyIso;
    private final String currencySymbol;
    private final boolean onlineMode;
    private final String gameType;
    private final boolean logEvents;

    // Server
    private final int serverId;
    private final String serverName;

    public InformationResponse(final JsonObject json) {
        final JsonObject account = json.getAsJsonObject("account");

        this.accountId = account.get("id").getAsInt();
        this.domain = account.get("domain").getAsString();
        this.name = account.get("name").getAsString();

        final JsonObject currency = account.getAsJsonObject("currency");

        this.currencyIso = currency.get("iso_4217").getAsString();
        this.currencySymbol = currency.get("symbol").getAsString();

        this.onlineMode = account.get("online_mode").getAsBoolean();
        this.gameType = account.get("game_type").getAsString();
        this.logEvents = account.get("log_events").getAsBoolean();

        final JsonObject server = json.getAsJsonObject("server");

        this.serverId = server.get("id").getAsInt();
        this.serverName = server.get("name").getAsString();
    }

    public int getAccountId() {
        return accountId;
    }

    public String getDomain() {
        return domain;
    }

    public String getName() {
        return name;
    }

    public String getCurrencyIso() {
        return currencyIso;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
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

    public int getServerId() {
        return serverId;
    }

    public String getServerName() {
        return serverName;
    }
}
