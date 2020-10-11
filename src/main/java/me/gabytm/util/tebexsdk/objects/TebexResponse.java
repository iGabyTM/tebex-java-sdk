package me.gabytm.util.tebexsdk.objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Used as return type for API endpoints
 *
 * @param <T> the type of returned by each endpoint
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class TebexResponse<T> {

    private final boolean successful;
    private final T data;
    private final String errorMessage;

    private TebexResponse(@Nullable T data, @Nullable String errorMessage) {
        this.successful = errorMessage == null;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    /**
     * Create a response with data (successful)
     *
     * @param data data
     * @param <T>  T
     * @return {@link TebexResponse}
     */
    @NotNull
    public static <T> TebexResponse<T> of(@NotNull final T data) {
        return new TebexResponse<>(data, null);
    }

    /**
     * Create a response with an error message (not successful)
     *
     * @param errorMessage error message
     * @param <T>          T
     * @return {@link TebexResponse}
     */
    @NotNull
    public static <T> TebexResponse<T> error(@NotNull final String errorMessage) {
        return new TebexResponse<>(null, errorMessage);
    }

    /**
     * Create an empty response
     *
     * @param <T> T
     * @return empty {@link TebexResponse}
     */
    @NotNull
    public static <T> TebexResponse<T> empty() {
        return new TebexResponse<>(null, null);
    }

    /**
     * Check if the response was successful or not
     *
     * @return true if successful otherwise false
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Get the data or null if the response is not successful
     *
     * @return data or null
     * @see #isSuccessful()
     * @see #ifSuccessful(Consumer)
     */
    @Nullable
    public T getData() {
        return data;
    }

    /**
     * Get the error message or null if the response was successful
     *
     * @return error message or null
     * @see #isSuccessful()
     * @see #ifNotSuccessful(Consumer)
     */
    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Method run when the response is successful
     *
     * @param data T
     * @return {@link TebexResponse} for {@link #ifNotSuccessful(Consumer)} to be used without an if/else
     * @see #isSuccessful()
     */
    public TebexResponse<T> ifSuccessful(final Consumer<T> data) {
        if (isSuccessful()) {
            data.accept(this.data);
        }

        return this;
    }

    /**
     * Method run when the response is not successful
     *
     * @param errorMessage the error message
     * @see #isSuccessful()
     */
    public void ifNotSuccessful(final Consumer<String> errorMessage) {
        if (!isSuccessful()) {
            errorMessage.accept(this.errorMessage);
        }
    }
}
