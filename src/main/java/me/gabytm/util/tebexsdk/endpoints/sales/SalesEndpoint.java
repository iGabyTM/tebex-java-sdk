package me.gabytm.util.tebexsdk.endpoints.sales;

import com.google.gson.reflect.TypeToken;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.sales.objects.Sale;
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
public class SalesEndpoint {

    private static final Type LIST_OF_SALES = new TypeToken<List<Sale>>() {}.getType();

    /**
     * Return an array of all active sales on your account.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @return list of {@link Sale}s
     * @see TebexAPI#getAllSales()
     * @since 0.0.1-BETA
     */
    @ApiStatus.Internal
    @NotNull
    public static TebexResponse<List<Sale>> getAllSales(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.SALES);
        return Responses.getList(request, client, LIST_OF_SALES);
    }
}
