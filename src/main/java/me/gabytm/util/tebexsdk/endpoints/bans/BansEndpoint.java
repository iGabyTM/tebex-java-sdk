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
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class BansEndpoint {

    private static final Type LIST_OF_BANS = new TypeToken<List<Ban>>() {}.getType();

    private BansEndpoint() { }

    /**
     * Returns an array of all bans on your account.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @return list of {@link Ban}s
     * @see TebexAPI#getAllBans()
     * @since 0.0.1-BETA
     */
    @ApiStatus.Internal
    @NotNull
    public static TebexResponse<List<Ban>> getAllBans(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.BANS);
        return Responses.getList(request, client, LIST_OF_BANS, "data");
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
     * @since 0.0.1-BETA
     * @see TebexAPI#createBan(String, String, String)
     * @see TebexAPI#createBan(String) 
     */
    @ApiStatus.Internal
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

        return Responses.getObject(request, client, Ban.class);
    }

    /**
     * Create a ban against a player or/and IP address.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @param user            the username or UUID of the player to ban
     * @return {@link Ban}
     * @since 0.0.1-BETA
     * @see TebexAPI#createBan(String)
     * @see TebexAPI#createBan(String, String, String) 
     */
    @ApiStatus.Internal
    @NotNull
    public static TebexResponse<Ban> createBan(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client, @NotNull final String user) {
        return createBan(serverSecretKey, client, user, null, null);
    }
}
