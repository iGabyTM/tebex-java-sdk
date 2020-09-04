package me.gabytm.util.tebexsdk.endpoints.information;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Optional;

public class InformationEndpoint {

    /**
     * This endpoint returns general information about the authenticated account and server.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}}
     * @param gson            {@link Gson}
     * @param client          {@link OkHttpClient}
     * @return {@link InformationResponse}
     */
    public static Optional<InformationResponse> getServerInformation(final String serverSecretKey, final Gson gson, final OkHttpClient client) {
        final Request request = new Request.Builder()
                .addHeader("X-Tebex-Secret", serverSecretKey)
                .url(Endpoint.INFORMATION.getUrl())
                .get()
                .build();

        try (final Response response = client.newCall(request).execute()) {
            final JsonObject json = gson.fromJson(response.body().charStream(), JsonObject.class);

            if (!response.isSuccessful()) {
                TebexAPI.getLogger().warning(json.get("error_message").getAsString());
                return Optional.empty();
            }

            return Optional.of(new InformationResponse(json));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
