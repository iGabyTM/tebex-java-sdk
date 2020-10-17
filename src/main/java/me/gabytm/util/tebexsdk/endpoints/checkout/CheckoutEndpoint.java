package me.gabytm.util.tebexsdk.endpoints.checkout;

import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.checkout.objects.Checkout;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import me.gabytm.util.tebexsdk.utils.Responses;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class CheckoutEndpoint {

    private CheckoutEndpoint() { }

    /**
     * Creates a URL which will redirect the player to the webstore and add the package to their basket.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @param packageId       the ID of the package the players want to purchase
     * @param username        the username of the player
     * @return {@link Checkout}
     * @since 0.0.1-BETA
     * @see CheckoutEndpoint#createCheckoutURL(String, OkHttpClient, int, String)
     */
    @ApiStatus.Internal
    @NotNull
    public static TebexResponse<Checkout> createCheckoutURL(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client, final int packageId, @NotNull final String username) {
        final Request request = new Request.Builder()
                .addHeader(TebexAPI.SECRET, serverSecretKey)
                .url(Endpoint.CHECKOUT.getUrl())
                .post(
                        new FormBody.Builder()
                                .add("package_id", Integer.toString(packageId))
                                .add("username", username)
                                .build()
                )
                .build();

        return Responses.getObject(request, client, Checkout.class);
    }

}
