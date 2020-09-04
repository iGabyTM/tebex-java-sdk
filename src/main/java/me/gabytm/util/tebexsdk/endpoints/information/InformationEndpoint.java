package me.gabytm.util.tebexsdk.endpoints.information;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Optional;

public class InformationEndpoint {

    public static Optional<InformationResponse> call(final String secret, final Gson gson, final OkHttpClient client) {
        final Request request = new Request.Builder()
                .addHeader("X-Tebex-Secret", secret)
                .url(Endpoint.INFORMATION.getUrl())
                .get()
                .build();

        try (final Response response = client.newCall(request).execute()){
            if (!response.isSuccessful()) {
                return Optional.empty();
            }

            return Optional.of(new InformationResponse(gson.fromJson(response.body().charStream(), JsonObject.class)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
