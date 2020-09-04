package me.gabytm.util.tebexsdk.endpoints.listing;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.listing.objects.Category;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListingEndpoint {

    public static Optional<List<Category>> call(final String secret, final Gson gson, final OkHttpClient client) {
        final Request request = new Request.Builder()
                .addHeader("X-Tebex-Secret", secret)
                .url(Endpoint.LISTING.getUrl())
                .get()
                .build();

        try (final Response response = client.newCall(request).execute()){
            if (!response.isSuccessful()) {
                return Optional.empty();
            }

            final JsonArray categoriesArray = gson.fromJson(response.body().charStream(), JsonObject.class).getAsJsonArray("categories");
            final List<Category> categories = new ArrayList<>(categoriesArray.size());

            categoriesArray.forEach(category -> categories.add(new Category(category.getAsJsonObject())));
            return Optional.of(categories);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
