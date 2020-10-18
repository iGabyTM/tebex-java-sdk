package me.gabytm.util.tebexsdk.endpoints.playerlookup;

import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.playerlookup.objects.PlayerLookup;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import me.gabytm.util.tebexsdk.utils.Requests;
import me.gabytm.util.tebexsdk.utils.Responses;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * @author GabyTM
 * @since 0.0.2-BETA
 */
public class PlayerLookupEndpoint {

    private PlayerLookupEndpoint() { }

    /**
     * Returns player lookup information (similar to player lookup in control panel). Available on Ultimate and above plans.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @param player          the UUID or username of a player
     * @return {@link PlayerLookup}
     * @see TebexAPI#playerLookup(String)
     * @since 0.0.2-BETA
     */
    @ApiStatus.Internal
    @NotNull
    public static TebexResponse<PlayerLookup> playerLookup(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client, @NotNull final String player) {
        if (player.isEmpty()) {
            return TebexResponse.error("'player' can not be empty.");
        }

        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.PLAYER_LOOKUP.getUrl() + player);
        return Responses.getObject(request, client, PlayerLookup.class);
    }

}
