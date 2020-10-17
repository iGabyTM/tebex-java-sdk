package me.gabytm.util.tebexsdk.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * A collection of methods used inside the API
 *
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class Responses {

    private Responses() { }

    /**
     * Process the {@link Request} and return a {@link TebexResponse} with the given
     * list of objects type.
     * This code is used by most endpoints so it was turned into an util,
     * it has no utility outside the API.
     *
     * @param request request
     * @param client  client
     * @param type    the list type
     * @param array   the array from where data is took, a null value will indicate that the entire json should be used
     * @param <T>     T
     * @return {@link TebexResponse}
     * @see Responses#getList(Request, OkHttpClient, Type)
     * @since 0.0.1-BETA
     */
    @ApiStatus.Internal
    @NotNull
    public static <T> TebexResponse<T> getList(@NotNull final Request request, @NotNull final OkHttpClient client, @NotNull final Type type, @Nullable final String array) {
        try (final Response response = client.newCall(request).execute()) {
            final Gson gson = TebexAPI.getGson();
            final JsonObject json = gson.fromJson(response.body().charStream(), JsonObject.class);

            if (!response.isSuccessful()) {
                return TebexResponse.error(json.get("error_message").getAsString());
            }

            return TebexResponse.of(gson.fromJson(array == null ? json.getAsJsonArray() : json.getAsJsonArray(array), type));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TebexResponse.empty();
    }

    /**
     * Process the {@link Request} and return a {@link TebexResponse} with the given
     * list of objects type.
     * This code is used by most endpoints so it was turned into an util,
     * it has no utility outside the API.
     *
     * @param request request
     * @param client  client
     * @param type    the list type
     * @param <T>     T
     * @return {@link TebexResponse}
     * @see Responses#getList(Request, OkHttpClient, Type, String)
     * @since 0.0.1-BETA
     */
    @ApiStatus.Internal
    @NotNull
    public static <T> TebexResponse<T> getList(@NotNull final Request request, @NotNull final OkHttpClient client, @NotNull final Type type) {
        return getList(request, client, type, null);
    }

    /**
     * Process the {@link Request} and return a {@link TebexResponse} with the given object type.
     * This code is used by most endpoints so it was turned into an util,
     * it has no utility outside the API.
     *
     * @param request request
     * @param client  client
     * @param clazz   returned object class
     * @param <T>     T
     * @return {@link TebexResponse}
     * @since 0.0.1-BETA
     */
    @ApiStatus.Internal
    @NotNull
    public static <T> TebexResponse<T> getObject(@NotNull final Request request, @NotNull final OkHttpClient client, @NotNull final Class<T> clazz) {
        try (final Response response = client.newCall(request).execute()) {
            final Gson gson = TebexAPI.getGson();
            final JsonObject json = gson.fromJson(response.body().charStream(), JsonObject.class);

            if (!response.isSuccessful()) {
                return TebexResponse.error(json.get("error_message").getAsString());
            }

            return TebexResponse.of(gson.fromJson(json, clazz));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return TebexResponse.empty();
    }
}
