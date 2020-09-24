package me.gabytm.util.tebexsdk.endpoints.communitygoals;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.communitygoals.objects.CommunityGoal;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import me.gabytm.util.tebexsdk.utils.Requests;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommunityGoalsEndpoint {

    /**
     * Retrieve all community goals created on your account.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param gson            {@link Gson}
     * @param client          {@link OkHttpClient}
     * @return list of {@link CommunityGoal}s
     */
    @NotNull
    public static TebexResponse<List<CommunityGoal>> getAllGoals(final String serverSecretKey, final Gson gson, final OkHttpClient client) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.COMMUNITY_GOALS);

        try (final Response response = client.newCall(request).execute()) {
            final JsonObject json = gson.fromJson(response.body().charStream(), JsonObject.class);

            if (!response.isSuccessful()) {
                return TebexResponse.error(json.get("error_message").getAsString());
            }

            return TebexResponse.of(
                    Stream.of(json.getAsJsonArray())
                            .map(JsonElement::getAsJsonObject)
                            .map(CommunityGoal::new)
                            .collect(Collectors.toList())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TebexResponse.empty();
    }

    /**
     * Retrieve all community goals created on your account.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param gson            {@link Gson}
     * @param client          {@link OkHttpClient}
     * @param goalId          the ID of a community goal
     * @return {@link CommunityGoal}
     */
    @NotNull
    public static TebexResponse<CommunityGoal> getGoal(final String serverSecretKey, final Gson gson, final OkHttpClient client, final int goalId) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.COMMUNITY_GOALS.getUrl() + "/" + goalId);

        try (final Response response = client.newCall(request).execute()) {
            final JsonObject json = gson.fromJson(response.body().charStream(), JsonObject.class);

            if (!response.isSuccessful()) {
                return TebexResponse.error(json.get("error_message").getAsString());
            }

            return TebexResponse.of(new CommunityGoal(json));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TebexResponse.empty();
    }
}
