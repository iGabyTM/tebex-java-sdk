package me.gabytm.util.tebexsdk.endpoints.information;

import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.information.objects.GeneralInformation;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import me.gabytm.util.tebexsdk.utils.Requests;
import me.gabytm.util.tebexsdk.utils.Responses;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class InformationEndpoint {

    /**
     * This endpoint returns general information about the authenticated account and server.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @return {@link GeneralInformation}
     * @see TebexAPI#getGeneralInformation()
     * @since 0.0.1-BETA
     */
    @ApiStatus.Internal
    @NotNull
    public static TebexResponse<GeneralInformation> getGeneralInformation(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.INFORMATION);
        return Responses.getObject(request, client, GeneralInformation.class);
    }
}
