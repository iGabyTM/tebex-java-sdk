package me.gabytm.util.tebexsdk.endpoints.listing;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.information.InformationResponse;
import me.gabytm.util.tebexsdk.endpoints.listing.objects.Category;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListingEndpoint {

    /**
     * Get the categories and packages which should be displayed to players in game.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}}
     * @param gson            {@link Gson}
     * @param client          {@link OkHttpClient}
     * @return {@link InformationResponse}
     */
    public static Optional<List<Category>> getListing(final String serverSecretKey, final Gson gson, final OkHttpClient client) {
        final Request request = new Request.Builder()
                .addHeader("X-Tebex-Secret", serverSecretKey)
                .url(Endpoint.LISTING.getUrl())
                .get()
                .build();

        try (final Response response = client.newCall(request).execute()) {
            final JsonObject json = gson.fromJson(response.body().charStream(), JsonObject.class);

            if (!response.isSuccessful()) {
                TebexAPI.getLogger().warning(json.get("error_message").getAsString());
                return Optional.empty();
            }

            return Optional.of(
                    Stream.of(json.getAsJsonArray("categories"))
                            .map(JsonElement::getAsJsonObject)
                            .map(Category::new)
                            .collect(Collectors.toList())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
