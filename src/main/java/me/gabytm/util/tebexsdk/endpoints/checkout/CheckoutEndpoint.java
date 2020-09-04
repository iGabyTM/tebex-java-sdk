package me.gabytm.util.tebexsdk.endpoints.checkout;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Optional;

public class CheckoutEndpoint {

    public static Optional<CheckoutResponse> call(final String secret, final Gson gson, final OkHttpClient client, final int packageId, final String username) {

        final Request request = new Request.Builder()
                .addHeader("X-Tebex-Secret", secret)
                .url(Endpoint.CHECKOUT.getUrl())
                .post(
                        new FormBody.Builder()
                                .add("package_id", Integer.toString(packageId))
                                .add("username", username)
                                .build()
                )
                .build();

        try (final Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return Optional.empty();
            }

            return Optional.of(new CheckoutResponse(gson.fromJson(response.body().charStream(), JsonObject.class)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
