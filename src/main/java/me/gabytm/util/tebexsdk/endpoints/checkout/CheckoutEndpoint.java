package me.gabytm.util.tebexsdk.endpoints.checkout;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Optional;

public class CheckoutEndpoint {

    /**
     * Creates a URL which will redirect the player to the webstore and add the package to their basket.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}}
     * @param gson            {@link Gson}
     * @param client          {@link OkHttpClient}
     * @param packageId       the ID of the package the players want to purchase
     * @param username        the username of the player
     * @return {@link CheckoutResponse}
     */
    public static Optional<CheckoutResponse> createCheckoutURL(final String serverSecretKey, final Gson gson, final OkHttpClient client, final int packageId, final String username) {
        final Request request = new Request.Builder()
                .addHeader("X-Tebex-Secret", serverSecretKey)
                .url(Endpoint.CHECKOUT.getUrl())
                .post(
                        new FormBody.Builder()
                                .add("package_id", Integer.toString(packageId))
                                .add("username", username)
                                .build()
                )
                .build();

        try (final Response response = client.newCall(request).execute()) {
            final JsonObject json = gson.fromJson(response.body().charStream(), JsonObject.class);

            if (!response.isSuccessful()) {
                TebexAPI.getLogger().warning(json.get("error_message").getAsString());
                return Optional.empty();
            }

            return Optional.of(new CheckoutResponse(json));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
