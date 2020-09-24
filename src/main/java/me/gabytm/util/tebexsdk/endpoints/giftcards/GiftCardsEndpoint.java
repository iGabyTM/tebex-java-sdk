package me.gabytm.util.tebexsdk.endpoints.giftcards;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.giftcards.objects.GiftCard;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import me.gabytm.util.tebexsdk.utils.Requests;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class GiftCardsEndpoint {

    private static final Type LIST_OF_GIFT_CARDS = new TypeToken<List<GiftCard>>() {
    }.getType();

    /**
     * Return an array of all gift cards on your account.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param gson            {@link Gson}
     * @param client          {@link OkHttpClient}
     * @return list of {@link GiftCard}
     */
    @NotNull
    public static TebexResponse<List<GiftCard>> getAllGiftCards(@NotNull final String serverSecretKey, @NotNull final Gson gson, @NotNull final OkHttpClient client) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.GIFT_CARDS);

        try (final Response response = client.newCall(request).execute()) {
            final JsonObject json = gson.fromJson(response.body().charStream(), JsonObject.class);

            if (!response.isSuccessful()) {
                return TebexResponse.error(json.get("error_message").getAsString());
            }

            return TebexResponse.of(gson.fromJson(json.getAsJsonArray("data"), LIST_OF_GIFT_CARDS));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TebexResponse.empty();
    }

    /**
     * Retrieve a gift card by ID.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param gson            {@link Gson}
     * @param client          {@link OkHttpClient}
     * @param id              the ID of a gift card
     * @return {@link GiftCard}
     */
    @NotNull
    public static TebexResponse<GiftCard> getGiftCardById(@NotNull final String serverSecretKey, @NotNull final Gson gson, @NotNull final OkHttpClient client, final int id) {
        if (id < 0) {
            return TebexResponse.error("ID can not be smaller than 0 (value: " + id + ')');
        }

        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.GIFT_CARDS.getUrl() + "/" + id);

        try (final Response response = client.newCall(request).execute()) {
            final JsonObject json = gson.fromJson(response.body().charStream(), JsonObject.class);

            if (!response.isSuccessful()) {
                return TebexResponse.error(json.get("error_message").getAsString());
            }

            return TebexResponse.of(gson.fromJson(json.getAsJsonObject("data"), GiftCard.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TebexResponse.empty();
    }

    /**
     * Create a gift card of a specified amount.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param gson            {@link Gson}
     * @param client          {@link OkHttpClient}
     * @param amount          the currency value of the gift card should have upon creation
     * @param note            the note that will be stored against the gift card
     * @return {@link GiftCard}
     */
    @NotNull
    public static TebexResponse<GiftCard> createGiftCard(@NotNull final String serverSecretKey, @NotNull final Gson gson, @NotNull final OkHttpClient client, final double amount, @Nullable final String note) {
        if (amount <= 0d) {
            return TebexResponse.error("Amount can not be smaller or equal to 0 (value: " + amount + ')');
        }

        final FormBody.Builder formBodyBuilder = new FormBody.Builder().add("amount", Double.toString(amount));

        if (note != null && !note.isEmpty()) {
            formBodyBuilder.add("note", note);
        }

        final Request request = new Request.Builder()
                .header(TebexAPI.SECRET, serverSecretKey)
                .url(Endpoint.GIFT_CARDS.getUrl())
                .post(formBodyBuilder.build())
                .build();

        try (final Response response = client.newCall(request).execute()) {
            final JsonObject json = gson.fromJson(response.body().charStream(), JsonObject.class);

            if (!response.isSuccessful()) {
                return TebexResponse.error(json.get("error_message").getAsString());
            }

            return TebexResponse.of(gson.fromJson(json.getAsJsonObject("data"), GiftCard.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TebexResponse.empty();
    }

    /**
     * Create a gift card of a specified amount.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}}
     * @param gson            {@link Gson}
     * @param client          {@link OkHttpClient}
     * @param amount          the currency value of the gift card should have upon creation
     * @return {@link GiftCard}
     */
    @NotNull
    public static TebexResponse<GiftCard> createGiftCard(@NotNull final String serverSecretKey, @NotNull final Gson gson, @NotNull final OkHttpClient client, final double amount) {
        return createGiftCard(serverSecretKey, gson, client, amount, null);
    }
}
