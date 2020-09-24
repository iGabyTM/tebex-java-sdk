package me.gabytm.util.tebexsdk.endpoints.listing;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.listing.objects.Category;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import me.gabytm.util.tebexsdk.utils.Requests;
import me.gabytm.util.tebexsdk.utils.Responses;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ListingEndpoint {

    private static final Type LIST_OF_CATEGORIES = new TypeToken<List<Category>>(){}.getType();

    /**
     * Get the categories and packages which should be displayed to players in game.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param gson            {@link Gson}
     * @param client          {@link OkHttpClient}
     * @return list of {@link Category}
     */
    @NotNull
    public static TebexResponse<List<Category>> getListing(@NotNull final String serverSecretKey, @NotNull final Gson gson, @NotNull final OkHttpClient client) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.LISTING);

        try (final Response response = client.newCall(request).execute()) {
            return Responses.getList(response, LIST_OF_CATEGORIES, "categories");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TebexResponse.empty();
    }
}
