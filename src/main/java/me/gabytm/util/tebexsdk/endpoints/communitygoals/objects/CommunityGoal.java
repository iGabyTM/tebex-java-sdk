package me.gabytm.util.tebexsdk.endpoints.communitygoals.objects;

/**
 * @author GabyTM
 * @see 0.0.1-BETA
 */
public class CommunityGoal {

    private int id;
    private String createdAt;
    private String updatedAt;
    private int account;
    private String name;
    private String description;
    private String image;
    private double target;
    private double current;
    private int repeatable;
    private String lastAchieved;
    private int timesAchieved;
    private String status;
    private int sale;

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
