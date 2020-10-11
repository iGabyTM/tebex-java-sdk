package me.gabytm.util.tebexsdk.endpoints.communitygoals;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.communitygoals.objects.CommunityGoal;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import me.gabytm.util.tebexsdk.utils.Requests;
import me.gabytm.util.tebexsdk.utils.Responses;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class CommunityGoalsEndpoint {

    private static final Type LIST_OF_COMMUNITY_GOALS = new TypeToken<List<CommunityGoal>>() {}.getType();

    /**
     * Retrieve all community goals created on your account.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @return list of {@link CommunityGoal}s
     * @since 0.0.1-BETA
     * @see TebexAPI#getAllGoals()
     */
    @ApiStatus.Internal
    @NotNull
    public static TebexResponse<List<CommunityGoal>> getAllGoals(final String serverSecretKey, final OkHttpClient client) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.COMMUNITY_GOALS);
        return Responses.getList(request, client, LIST_OF_COMMUNITY_GOALS);
    }

    /**
     * Retrieve an individual community goal on your account
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @param goalId          the ID of a community goal
     * @return {@link CommunityGoal}
     * @since 0.0.1-BETA
     * @see TebexAPI#getGoal(int)
     */
    @ApiStatus.Internal
    @NotNull
    public static TebexResponse<CommunityGoal> getGoal(final String serverSecretKey, final OkHttpClient client, final int goalId) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.COMMUNITY_GOALS.getUrl() + "/" + goalId);
        return Responses.getObject(request, client, CommunityGoal.class);
    }
}
