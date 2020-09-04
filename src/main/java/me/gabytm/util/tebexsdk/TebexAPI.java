package me.gabytm.util.tebexsdk;

import com.google.gson.Gson;
import me.gabytm.util.tebexsdk.endpoints.checkout.CheckoutEndpoint;
import me.gabytm.util.tebexsdk.endpoints.checkout.CheckoutResponse;
import me.gabytm.util.tebexsdk.endpoints.information.InformationEndpoint;
import me.gabytm.util.tebexsdk.endpoints.information.InformationResponse;
import me.gabytm.util.tebexsdk.endpoints.listing.ListingEndpoint;
import me.gabytm.util.tebexsdk.endpoints.listing.objects.Category;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class TebexAPI {

    private final String xTebexSecret;
    private final Gson gson;
    private final OkHttpClient okHttpClient;

    public TebexAPI(@NotNull final String xTebexSecret, @Nullable final Gson gson, @Nullable final OkHttpClient okHttpClient) {
        this.xTebexSecret = xTebexSecret;
        this.gson = gson == null ? new Gson() : gson;
        this.okHttpClient = okHttpClient == null ? new OkHttpClient().newBuilder().build() : okHttpClient;
    }

    public TebexAPI(@NotNull final String xTebexSecret) {
        this(xTebexSecret, new Gson(), new OkHttpClient().newBuilder().build());
    }

    // /information
    public Optional<InformationResponse> getInformation() {
        return InformationEndpoint.call(xTebexSecret, gson, okHttpClient);
    }

    public Optional<List<Category>> getListing() {
        return ListingEndpoint.call(xTebexSecret, gson, okHttpClient);
    }

    public Optional<CheckoutResponse> createCheckoutURL(final int packagedId, @NotNull final String username) {
        return CheckoutEndpoint.call(xTebexSecret, gson, okHttpClient, packagedId, username);
    }
}
