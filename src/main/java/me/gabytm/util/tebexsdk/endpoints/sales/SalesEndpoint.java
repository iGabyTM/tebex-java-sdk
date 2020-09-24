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
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class SalesEndpoint {

    private static final Type LIST_OF_SALES = new TypeToken<List<Sale>>() {}.getType();

    /**
     * Return an array of all active sales on your account.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @return list of {@link Sale}s
     */
    @NotNull
    public static TebexResponse<List<Sale>> getAllSales(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.SALES);

        try (final Response response = client.newCall(request).execute()) {
            return Responses.getList(response, LIST_OF_SALES);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TebexResponse.empty();
    }
}
