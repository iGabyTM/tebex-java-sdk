package me.gabytm.util.tebexsdk.endpoints.giftcards;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.giftcards.objects.GiftCard;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import me.gabytm.util.tebexsdk.utils.Constant;
import me.gabytm.util.tebexsdk.utils.Requests;
import me.gabytm.util.tebexsdk.utils.Responses;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class GiftCardsEndpoint {

    private static final Type LIST_OF_GIFT_CARDS = new TypeToken<List<GiftCard>>() {}.getType();

    private GiftCardsEndpoint() { }

    /**
     * Return an array of all gift cards on your account.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @return list of {@link GiftCard}
     * @see TebexAPI#getAllGiftCards()
     * @since 0.0.1-BETA
     */
    @ApiStatus.Internal
    @NotNull
    public static TebexResponse<List<GiftCard>> getAllGiftCards(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.GIFT_CARDS);
        return Responses.getList(request, client, LIST_OF_GIFT_CARDS, "data");
    }

    /**
     * Retrieve a gift card by ID.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @param id              the ID of a gift card
     * @return {@link GiftCard}
     * @see TebexAPI#getGiftCardById(int)
     * @since 0.0.1-BETA
     */
    @ApiStatus.Internal
    @NotNull
    public static TebexResponse<GiftCard> getGiftCardById(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client, final int id) {
        if (id < 0) {
            return TebexResponse.error("ID can not be smaller than 0 (value: " + id + ')');
        }

        return getGiftCardFromResponse(client, Requests.createGetRequest(serverSecretKey, Endpoint.GIFT_CARDS.getUrl() + "/" + id));
    }

    /**
     * Create a gift card of a specified amount.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @param amount          the currency value of the gift card should have upon creation
     * @param note            the note that will be stored against the gift card
     * @return {@link GiftCard}
     * @see TebexAPI#createGiftCard(double, String)
     * @since 0.0.1-BETA
     */
    @NotNull
    public static TebexResponse<GiftCard> createGiftCard(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client, final double amount, @Nullable final String note) {
        if (amount <= 0d) {
            return TebexResponse.error("Amount can not be smaller or equal to 0 (value: " + amount + ')');
        }

        final FormBody.Builder formBodyBuilder = new FormBody.Builder().add("amount", Double.toString(amount));

        if (note != null && !note.isEmpty()) {
            formBodyBuilder.add("note", note);
        }

        final Request request = new Request.Builder()
                .header(Constant.TEBEX_SECRET, serverSecretKey)
                .url(Endpoint.GIFT_CARDS.getUrl())
                .post(formBodyBuilder.build())
                .build();

        return getGiftCardFromResponse(client, request);
    }

    private static TebexResponse<GiftCard> getGiftCardFromResponse(@NotNull final OkHttpClient client, @NotNull final Request request) {
        try (final Response response = client.newCall(request).execute()) {
            final JsonObject json = TebexAPI.getGson().fromJson(response.body().charStream(), JsonObject.class);

            if (!response.isSuccessful()) {
                return TebexResponse.error(json.get("error_message").getAsString());
            }

            return TebexResponse.of(TebexAPI.getGson().fromJson(json.getAsJsonObject("data"), GiftCard.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TebexResponse.empty();
    }
}
