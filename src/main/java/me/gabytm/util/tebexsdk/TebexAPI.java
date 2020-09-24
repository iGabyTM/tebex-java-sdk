package me.gabytm.util.tebexsdk;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import me.gabytm.util.tebexsdk.endpoints.bans.BansEndpoint;
import me.gabytm.util.tebexsdk.endpoints.bans.objects.Ban;
import me.gabytm.util.tebexsdk.endpoints.checkout.CheckoutEndpoint;
import me.gabytm.util.tebexsdk.endpoints.checkout.objects.Checkout;
import me.gabytm.util.tebexsdk.endpoints.commandqueue.CommandQueueEndpoint;
import me.gabytm.util.tebexsdk.endpoints.commandqueue.objects.Command;
import me.gabytm.util.tebexsdk.endpoints.commandqueue.objects.DuePlayers;
import me.gabytm.util.tebexsdk.endpoints.commandqueue.objects.OfflineCommands;
import me.gabytm.util.tebexsdk.endpoints.information.InformationEndpoint;
import me.gabytm.util.tebexsdk.endpoints.information.objects.ServerInfo;
import me.gabytm.util.tebexsdk.endpoints.listing.ListingEndpoint;
import me.gabytm.util.tebexsdk.endpoints.listing.objects.Category;
import me.gabytm.util.tebexsdk.endpoints.sales.objects.Discount;
import me.gabytm.util.tebexsdk.endpoints.sales.objects.Effective;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TebexAPI {

    public static final String SECRET = "X-Tebex-Secret";
    private static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(new TypeToken<Discount.DiscountType>() {
            }.getType(), new Discount.DiscountTypeDeserializer())
            .registerTypeAdapter(new TypeToken<Effective.EffectiveType>() {
            }.getType(), new Effective.EffectiveTypeDeserializer())
            .create();

    private final String serverSecretKey;
    private final Gson gson;
    private final OkHttpClient okHttpClient;

    /**
     * @param serverSecretKey can be found <a href="https://server.tebex.io/settings/servers">here</a>
     * @param okHttpClient    the {@link OkHttpClient} instance used by the api
     */
    public TebexAPI(@NotNull final String serverSecretKey, @Nullable final OkHttpClient okHttpClient) {
        this.serverSecretKey = serverSecretKey;
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(new TypeToken<Discount.DiscountType>() {
                }.getType(), new Discount.DiscountTypeDeserializer())
                .registerTypeAdapter(new TypeToken<Effective.EffectiveType>() {
                }.getType(), new Effective.EffectiveTypeDeserializer())
                .create();
        this.okHttpClient = okHttpClient == null ? new OkHttpClient().newBuilder().build() : okHttpClient;
    }

    /**
     * @param serverSecretKey can be found <a href="https://server.tebex.io/settings/servers">here</a>
     */
    public TebexAPI(@NotNull final String serverSecretKey) {
        this(serverSecretKey, null);
    }

    @NotNull
    public static Gson getGson() {
        return GSON;
    }

    @NotNull
    public String getServerSecretKey() {
        return serverSecretKey;
    }

    @NotNull
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    /**
     * Returns an array of all bans on your account.
     *
     * @return list of {@link Ban}s
     */
    @NotNull
    public TebexResponse<List<Ban>> getAllBans() {
        return BansEndpoint.getAllBans(serverSecretKey, okHttpClient);
    }

    /**
     * Create a ban against a player or/and IP address.
     *
     * @param user   the username or UUID of the player to ban
     * @param ip     the IP address to also ban
     * @param reason the reason for the ban
     * @return {@link Ban}
     */
    @NotNull
    public TebexResponse<Ban> createBan(@NotNull final String user, @Nullable final String ip, @Nullable final String reason) {
        return BansEndpoint.createBan(serverSecretKey, okHttpClient, user, ip, reason);
    }

    /**
     * Create a ban against a player or/and IP address.
     *
     * @param user the username or UUID of the player to ban
     * @return {@link Ban}
     */
    @NotNull
    public TebexResponse<Ban> createBan(@NotNull final String user) {
        return BansEndpoint.createBan(serverSecretKey, okHttpClient, user);
    }

    /**
     * This endpoint returns general information about the authenticated account and server.
     *
     * @return {@link ServerInfo}
     */
    @NotNull
    public TebexResponse<ServerInfo> getServerInformation() {
        return InformationEndpoint.getServerInformation(serverSecretKey, gson, okHttpClient);
    }

    /**
     * List the players who have commands due to be executed when they next login to the game server. This
     * endpoint also returns any offline commands to be processed and the amount of seconds to wait before
     * performing the queue check again. All clients should strictly follow the response of next_check, failure to
     * do so would result in your secret key being revoked or IP address being banned from accessing the API.
     * <br><br>
     * NOTE: The <b>player.id</b> field is an internal ID, sometimes referred to as the <b>plugin username ID</b>. This is the
     * value to use to retrieve online commands for a player (see below). the <b>player.uuid</b> field is the actual
     * username ID value, depending on the username type (a Steam64ID for steam games, an XUID for Xbox Live
     * etc), and is the value that should be used to replace the <b>{id}</b> placeholder in commands.
     *
     * @return {@link DuePlayers}
     */
    @NotNull
    public TebexResponse<DuePlayers> getDuePlayers() {
        return CommandQueueEndpoint.getDuePlayers(serverSecretKey, okHttpClient);
    }

    /**
     * Get the offline commands which are due to be executed.
     * <br><br>
     * These commands should be executed immediately and no checks are required against the related players.
     * <br><br>
     * NOTE: The <b>player.id</b> field is an internal ID, sometimes referred to as the <b>plugin username ID</b>. This is the
     * value to use to retrieve online commands for a player (see below). the <b>player.uuid</b> field is the actual
     * username ID value, depending on the username type (a Steam64ID for steam games, an XUID for Xbox Live
     * etc), and is the value that should be used to replace the <b>{id}</b> placeholder in commands.
     *
     * @return {@link OfflineCommands}
     */
    @NotNull
    public TebexResponse<OfflineCommands> getOfflineCommands() {
        return CommandQueueEndpoint.getOfflineCommands(serverSecretKey, okHttpClient);
    }

    /**
     * List the due online commands for a specific player.
     * <br><br>
     * These commands should only be executed when the player is online and all the conditions have been met
     * (such as if the player has the required amount of inventory slots).
     *
     * @param playerId the ID of the player you want to retrieve the online commands for
     * @return {@link OfflineCommands}
     */
    @NotNull
    public TebexResponse<List<Command>> getOnlineCommands(final int playerId) {
        return CommandQueueEndpoint.getOnlineCommands(serverSecretKey, okHttpClient, playerId);
    }

    /**
     * Get the categories and packages which should be displayed to players in game.
     *
     * @return {@link ServerInfo}
     */
    @NotNull
    public TebexResponse<List<Category>> getListing() {
        return ListingEndpoint.getListing(serverSecretKey, gson, okHttpClient);
    }

    /**
     * Creates a URL which will redirect the player to the webstore and add the package to their basket.
     *
     * @param packageId the ID of the package the players want to purchase
     * @param username  the username of the player
     * @return {@link Checkout}
     */
    @NotNull
    public TebexResponse<Checkout> createCheckoutURL(final int packageId, @NotNull final String username) {
        return CheckoutEndpoint.createCheckoutURL(serverSecretKey, okHttpClient, packageId, username);
    }
}
