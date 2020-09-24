package me.gabytm.util.tebexsdk.endpoints.bans;

import com.google.gson.reflect.TypeToken;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.bans.objects.Ban;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import me.gabytm.util.tebexsdk.utils.Requests;
import me.gabytm.util.tebexsdk.utils.Responses;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class BansEndpoint {

    private static final Type LIST_OF_BANS = new TypeToken<List<Ban>>() {
    }.getType();

    /**
     * Returns an array of all bans on your account.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @return list of {@link Ban}s
     */
    @NotNull
    public static TebexResponse<List<Ban>> getAllBans(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.BANS);

        try (final Response response = client.newCall(request).execute()) {
            return Responses.getList(response, LIST_OF_BANS, "data");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TebexResponse.empty();
    }

    /**
     * Create a ban against a player or/and IP address.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @param user            the username or UUID of the player to ban
     * @param ip              the IP address to also ban
     * @param reason          the reason for the ban
     * @return {@link Ban}
     */
    @NotNull
    public static TebexResponse<Ban> createBan(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client, @NotNull final String user, @Nullable final String ip, @Nullable final String reason) {
        final FormBody.Builder formBodyBuilder = new FormBody.Builder().add("user", user);

        if (ip != null) {
            formBodyBuilder.add("ip", ip);
        }

        if (reason != null) {
            formBodyBuilder.add("reason", reason);
        }

        final Request request = new Request.Builder()
                .addHeader(TebexAPI.SECRET, serverSecretKey)
                .url(Endpoint.BANS.getUrl())
                .post(formBodyBuilder.build())
                .build();

        try (final Response response = client.newCall(request).execute()) {
            return Responses.getObject(response, Ban.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TebexResponse.empty();
    }

    /**
     * Create a ban against a player or/and IP address.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @param user            the username or UUID of the player to ban
     * @return {@link Ban}
     */
    @NotNull
    public static TebexResponse<Ban> createBan(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client, @NotNull final String user) {
        return createBan(serverSecretKey, client, user, null, null);
    }
}
