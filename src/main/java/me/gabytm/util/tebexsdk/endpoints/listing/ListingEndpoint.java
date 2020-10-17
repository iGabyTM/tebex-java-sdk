package me.gabytm.util.tebexsdk.endpoints.listing;

import com.google.gson.reflect.TypeToken;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.listing.objects.Category;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import me.gabytm.util.tebexsdk.utils.Requests;
import me.gabytm.util.tebexsdk.utils.Responses;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class ListingEndpoint {

    private static final Type LIST_OF_CATEGORIES = new TypeToken<List<Category>>(){}.getType();

    private ListingEndpoint() { }

    /**
     * Get the categories and packages which should be displayed to players in game.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @return list of {@link Category}
     * @see TebexAPI#getListing()
     * @since 0.0.1-BETA
     */
    @ApiStatus.Internal
    @NotNull
    public static TebexResponse<List<Category>> getListing(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.LISTING);
        return Responses.getList(request, client, LIST_OF_CATEGORIES, "categories");
    }
}
