package me.gabytm.util.tebexsdk.endpoints.communitygoals.objects;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
@SuppressWarnings("unused")
public class CommunityGoal {

    private int id;
    private Date createdAt;
    private Date updatedAt;
    private int account;
    private String name;
    private String description;
    private String image;
    private double target;
    private double current;
    private int repeatable;
    private Date lastAchieved;
    private int timesAchieved;
    private String status;
    private int sale;

    public int getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public int getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    /**
     * Gets the description of the goal as entered on the control panel (contains HTML tags)
     *
     * @return the description of the goal
     */
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

    /**
     * @deprecated the value is actually a boolean but represented as an int, see {@link #isRepeatable}
     */
    @ApiStatus.ScheduledForRemoval(inVersion = "0.0.4-BETA")
    @Deprecated
    public int getRepeatable() {
        return repeatable;
    }

    public boolean isRepeatable() {
        return repeatable == 1;
    }

    @Nullable
    public Date getLastAchieved() {
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
