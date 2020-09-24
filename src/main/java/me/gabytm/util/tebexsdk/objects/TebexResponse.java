package me.gabytm.util.tebexsdk.objects;

import java.util.function.Consumer;

public class TebexResponse<T> {

    private final boolean successful;
    private final T data;
    private final String errorMessage;

    private TebexResponse(T data, String errorMessage) {
        this.successful = errorMessage == null;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public static <T> TebexResponse<T> of(final T data) {
        return new TebexResponse<>(data, null);
    }

    public static <T> TebexResponse<T> error(final String errorMessage) {
        return new TebexResponse<>(null, errorMessage);
    }

    public static <T> TebexResponse<T> empty() {
        return new TebexResponse<>(null, null);
    }

    public boolean isSuccessful() {
        return successful;
    }

    public T getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void ifSuccessful(final Consumer<T> data) {
        if (successful) {
            data.accept(this.data);
        }
    }

    public void ifNotSuccessful(final Consumer<String> errorMessage) {
        if (!successful) {
            errorMessage.accept(this.errorMessage);
        }
    }
}
