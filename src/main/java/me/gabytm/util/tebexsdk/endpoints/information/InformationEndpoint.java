package me.gabytm.util.tebexsdk.endpoints.information;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.information.objects.ServerInfo;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import me.gabytm.util.tebexsdk.utils.Requests;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class InformationEndpoint {

    /**
     * This endpoint returns general information about the authenticated account and server.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param gson            {@link Gson}
     * @param client          {@link OkHttpClient}
     * @return {@link ServerInfo}
     */
    @NotNull
    public static TebexResponse<ServerInfo> getServerInformation(@NotNull final String serverSecretKey, @NotNull final Gson gson, @NotNull final OkHttpClient client) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.INFORMATION);

        try (final Response response = client.newCall(request).execute()) {
            final JsonObject json = gson.fromJson(response.body().charStream(), JsonObject.class);

            if (!response.isSuccessful()) {
                return TebexResponse.error(json.get("error_message").getAsString());
            }

            return TebexResponse.of(new ServerInfo(json));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TebexResponse.empty();
    }
}
