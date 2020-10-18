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
import me.gabytm.util.tebexsdk.endpoints.communitygoals.CommunityGoalsEndpoint;
import me.gabytm.util.tebexsdk.endpoints.communitygoals.objects.CommunityGoal;
import me.gabytm.util.tebexsdk.endpoints.giftcards.GiftCardsEndpoint;
import me.gabytm.util.tebexsdk.endpoints.giftcards.objects.GiftCard;
import me.gabytm.util.tebexsdk.endpoints.information.InformationEndpoint;
import me.gabytm.util.tebexsdk.endpoints.information.objects.GeneralInformation;
import me.gabytm.util.tebexsdk.endpoints.listing.ListingEndpoint;
import me.gabytm.util.tebexsdk.endpoints.listing.objects.Category;
import me.gabytm.util.tebexsdk.endpoints.playerlookup.PlayerLookupEndpoint;
import me.gabytm.util.tebexsdk.endpoints.playerlookup.objects.PlayerLookup;
import me.gabytm.util.tebexsdk.endpoints.sales.SalesEndpoint;
import me.gabytm.util.tebexsdk.endpoints.sales.objects.Discount;
import me.gabytm.util.tebexsdk.endpoints.sales.objects.Effective;
import me.gabytm.util.tebexsdk.endpoints.sales.objects.Sale;
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
    private final OkHttpClient okHttpClient;

    /**
     * @param serverSecretKey can be found <a href="https://server.tebex.io/settings/servers">here</a>
     * @param okHttpClient    the {@link OkHttpClient} instance used by the api
     */
    public TebexAPI(@NotNull final String serverSecretKey, @Nullable final OkHttpClient okHttpClient) {
        this.serverSecretKey = serverSecretKey;
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
     * @see BansEndpoint#getAllBans(String, OkHttpClient)
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
     * @see BansEndpoint#createBan(String, OkHttpClient, String, String, String)
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
     * @see BansEndpoint#createBan(String, OkHttpClient, String)
     */
    @NotNull
    public TebexResponse<Ban> createBan(@NotNull final String user) {
        return BansEndpoint.createBan(serverSecretKey, okHttpClient, user);
    }

    /**
     * Creates a URL which will redirect the player to the webstore and add the package to their basket.
     *
     * @param packageId the ID of the package the players want to purchase
     * @param username  the username of the player
     * @return {@link Checkout}
     * @see CheckoutEndpoint#createCheckoutURL(String, OkHttpClient, int, String)
     */
    @NotNull
    public TebexResponse<Checkout> createCheckoutURL(final int packageId, @NotNull final String username) {
        return CheckoutEndpoint.createCheckoutURL(serverSecretKey, okHttpClient, packageId, username);
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
     * @see CommandQueueEndpoint#getDuePlayers(String, OkHttpClient)
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
     * Delete one or more commands which have been executed on the game server.
     * <br><br>
     * An empty response with the status code of <b>204 No Content</b> will be returned on completion.
     *
     * @param commands an array of one or more command IDs to delete
     * @see CommandQueueEndpoint#deleteCommands(String, OkHttpClient, int[])
     */
    public void deleteCommands(@NotNull int[] commands) {
        CommandQueueEndpoint.deleteCommands(serverSecretKey, okHttpClient, commands);
    }

    /**
     * Retrieve all community goals created on your account.
     *
     * @return list of {@link CommunityGoal}s
     * @see CommunityGoalsEndpoint#getAllGoals(String, OkHttpClient)
     */
    @NotNull
    public TebexResponse<List<CommunityGoal>> getAllGoals() {
        return CommunityGoalsEndpoint.getAllGoals(serverSecretKey, okHttpClient);
    }

    /**
     * Retrieve an individual community goal on your account
     *
     * @param goalId the ID of a community goal
     * @return {@link CommunityGoal}
     * @see CommunityGoalsEndpoint#getGoal(String, OkHttpClient, int)
     */
    @NotNull
    public TebexResponse<CommunityGoal> getGoal(final int goalId) {
        return CommunityGoalsEndpoint.getGoal(serverSecretKey, okHttpClient, goalId);
    }

    /**
     * Return an array of all gift cards on your account.
     *
     * @return list of {@link GiftCard}
     * @see GiftCardsEndpoint#getAllGiftCards(String, OkHttpClient)
     */
    @NotNull
    public TebexResponse<List<GiftCard>> getAllGiftCards() {
        return GiftCardsEndpoint.getAllGiftCards(serverSecretKey, okHttpClient);
    }

    /**
     * Retrieve a gift card by ID.
     *
     * @param id the ID of a gift card
     * @return {@link GiftCard}
     * @see GiftCardsEndpoint#getGiftCardById(String, OkHttpClient, int)
     */
    @NotNull
    public TebexResponse<GiftCard> getGiftCardById(final int id) {
        return GiftCardsEndpoint.getGiftCardById(serverSecretKey, okHttpClient, id);
    }

    /**
     * Create a gift card of a specified amount.
     *
     * @param amount the currency value of the gift card should have upon creation
     * @param note   the note that will be stored against the gift card
     * @return {@link GiftCard}
     * @see GiftCardsEndpoint#createGiftCard(String, OkHttpClient, double, String)
     * @since 0.0.1-BETA
     */
    @NotNull
    public TebexResponse<GiftCard> createGiftCard(final double amount, @Nullable final String note) {
        return GiftCardsEndpoint.createGiftCard(serverSecretKey, okHttpClient, amount, note);
    }

    /**
     * This endpoint returns general information about the authenticated account and server.
     *
     * @return {@link GeneralInformation}
     * @see InformationEndpoint#getGeneralInformation(String, OkHttpClient)
     */
    @NotNull
    public TebexResponse<GeneralInformation> getGeneralInformation() {
        return InformationEndpoint.getGeneralInformation(serverSecretKey, okHttpClient);
    }

    /**
     * Get the categories and packages which should be displayed to players in game.
     *
     * @return {@link GeneralInformation}
     * @see ListingEndpoint#getListing(String, OkHttpClient)
     */
    @NotNull
    public TebexResponse<List<Category>> getListing() {
        return ListingEndpoint.getListing(serverSecretKey, okHttpClient);
    }

    /**
     * Return an array of all active sales on your account.
     *
     * @return list of {@link Sale}s
     * @see SalesEndpoint#getAllSales(String, OkHttpClient)
     */
    @NotNull
    public TebexResponse<List<Sale>> getAllSales() {
        return SalesEndpoint.getAllSales(serverSecretKey, okHttpClient);
    }

    /**
     * Returns player lookup information (similar to player lookup in control panel). Available on Ultimate and above plans.
     *
     * @param player the UUID or username of a player
     * @return {@link PlayerLookup}
     * @see PlayerLookupEndpoint#playerLookup(String, OkHttpClient, String)
     */
    @NotNull
    public TebexResponse<PlayerLookup> playerLookup(@NotNull final String player) {
        return PlayerLookupEndpoint.playerLookup(serverSecretKey, okHttpClient, player);
    }
}
