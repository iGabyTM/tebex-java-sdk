package me.gabytm.util.tebexsdk.endpoints.communitygoals.objects;

import com.google.gson.JsonObject;

public class CommunityGoal {

    private final int id;
    private final String createdAt;
    private final String updatedAt;
    private final int account;
    private final String name;
    private final String description;
    private final String image;
    private final double target;
    private final double current;
    private final int repeatable;
    private final String lastAchieved;
    private final int timesAchieved;
    private final String status;
    private final int sale;

    public CommunityGoal(final JsonObject json) {
        this.id = json.get("id").getAsInt();
        this.createdAt = json.get("created_at").getAsString();
        this.updatedAt = json.get("updated_at").getAsString();
        this.account = json.get("account").getAsInt();
        this.name = json.get("name").getAsString();
        this.description = json.get("description").getAsString();
        this.image = json.get("image").getAsString();
        this.target = json.get("target").getAsDouble();
        this.current = json.get("current").getAsDouble();
        this.repeatable = json.get("repeatable").getAsInt();
        this.lastAchieved = json.has("last_achieved") ? json.get("last_achieved").getAsString() : null;
        this.timesAchieved = json.get("times_achieved").getAsInt();
        this.status = json.get("status").getAsString();
        this.sale = json.get("sale").getAsInt();
    }

    public int getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public double getTarget() {
        return target;
    }

    public double getCurrent() {
        return current;
    }

    public int getRepeatable() {
        return repeatable;
    }

    public String getLastAchieved() {
        return lastAchieved;
    }

    public int getTimesAchieved() {
        return timesAchieved;
    }

    public String getStatus() {
        return status;
    }

    public int getSale() {
        return sale;
    }
}
