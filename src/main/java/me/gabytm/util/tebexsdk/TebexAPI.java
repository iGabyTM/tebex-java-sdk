package me.gabytm.util.tebexsdk;

import com.google.gson.Gson;
import me.gabytm.util.tebexsdk.endpoints.checkout.CheckoutEndpoint;
import me.gabytm.util.tebexsdk.endpoints.checkout.CheckoutResponse;
import me.gabytm.util.tebexsdk.endpoints.information.InformationEndpoint;
import me.gabytm.util.tebexsdk.endpoints.information.InformationResponse;
import me.gabytm.util.tebexsdk.endpoints.listing.ListingEndpoint;
import me.gabytm.util.tebexsdk.endpoints.listing.objects.Category;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class TebexAPI {

    private final String serverSecretKey;
    private final Gson gson;
    private final OkHttpClient okHttpClient;

    private final static Logger logger = Logger.getLogger(TebexAPI.class.getName());

    /**
     * @param serverSecretKey can be found <a href="https://server.tebex.io/settings/servers">here</a>
     * @param gson            the {@link Gson} instance used by the api
     * @param okHttpClient    the {@link OkHttpClient} instance used by the api
     */
    public TebexAPI(@NotNull final String serverSecretKey, @Nullable final Gson gson, @Nullable final OkHttpClient okHttpClient) {
        this.serverSecretKey = serverSecretKey;
        this.gson = gson == null ? new Gson() : gson;
        this.okHttpClient = okHttpClient == null ? new OkHttpClient().newBuilder().build() : okHttpClient;
    }

    /**
     * @param serverSecretKey can be found <a href="https://server.tebex.io/settings/servers">here</a>
     */
    public TebexAPI(@NotNull final String serverSecretKey) {
        this(serverSecretKey, null, null);
    }

    public static Logger getLogger() {
        return logger;
    }

    public String getServerSecretKey() {
        return serverSecretKey;
    }

    public Gson getGson() {
        return gson;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    /**
     * This endpoint returns general information about the authenticated account and server.
     *
     * @return {@link InformationResponse}
     */
    public Optional<InformationResponse> getServerInformation() {
        return InformationEndpoint.getServerInformation(serverSecretKey, gson, okHttpClient);
    }

    /**
     * Get the categories and packages which should be displayed to players in game.
     *
     * @return {@link InformationResponse}
     */
    public Optional<List<Category>> getListing() {
        return ListingEndpoint.getListing(serverSecretKey, gson, okHttpClient);
    }

    /**
     * Creates a URL which will redirect the player to the webstore and add the package to their basket.
     *
     * @param packageId the ID of the package the players want to purchase
     * @param username  the username of the player
     * @return {@link CheckoutResponse}
     */
    public Optional<CheckoutResponse> createCheckoutURL(final int packageId, @NotNull final String username) {
        return CheckoutEndpoint.createCheckoutURL(serverSecretKey, gson, okHttpClient, packageId, username);
    }
}
