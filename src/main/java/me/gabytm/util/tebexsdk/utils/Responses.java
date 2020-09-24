package me.gabytm.util.tebexsdk.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;

public class Responses {

    /**
     * Get a list of objects from json.
     *
     * @param response the api response
     * @param type     the type of objects
     * @param array    the array from where data is read to generate the list
     * @return {@link TebexResponse}
     */
    @NotNull
    public static <T> TebexResponse<T> getList(@NotNull final Response response, @NotNull final Type type, @Nullable final String array) {
        final Gson gson = TebexAPI.getGson();
        final JsonObject json = gson.fromJson(response.body().charStream(), JsonObject.class);

        if (!response.isSuccessful()) {
            return TebexResponse.error(json.get("error_message").getAsString());
        }

        return TebexResponse.of(gson.fromJson(array == null ? json.getAsJsonArray() : json.getAsJsonArray(array), type));
    }

    /**
     * Get a list of objects from json.
     *
     * @param response the api response
     * @param type     the type of objects
     * @return {@link TebexResponse}
     */
    @NotNull
    public static <T> TebexResponse<T> getList(@NotNull final Response response, @NotNull final Type type) {
        return getList(response, type, null);
    }

    public static <T> TebexResponse<T> getObject(@NotNull final Response response, @NotNull final Class<T> clazz) {
        final Gson gson = TebexAPI.getGson();
        final JsonObject json = gson.fromJson(response.body().charStream(), JsonObject.class);

        if (!response.isSuccessful()) {
            return TebexResponse.error(json.get("error_message").getAsString());
        }

        return TebexResponse.of(gson.fromJson(json, clazz));
    }
}
