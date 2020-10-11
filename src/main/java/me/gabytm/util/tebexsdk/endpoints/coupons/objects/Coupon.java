package me.gabytm.util.tebexsdk.endpoints.coupons.objects;

import me.gabytm.util.tebexsdk.endpoints.sales.objects.Discount;
import me.gabytm.util.tebexsdk.endpoints.sales.objects.Effective;
import me.gabytm.util.tebexsdk.objects.TebexDate;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Coupon {

    public static final int APPLY_TO_EACH_PACKAGE = 0;
    public static final int APPLY_TO_BASKED_BEFORE_SALES = 1;
    public static final int APPLY_TO_BASKET_AFTER_SALES = 2;

    private Coupon() {}

    @ApiStatus.Experimental
    public static class Builder {

        private static final Pattern datePattern = Pattern.compile("^\\d{4}-[01]\\d-[0-3]\\d$");
        private final FormBody.Builder formBodyBuilder = new FormBody.Builder();

        private String code;
        private Effective.EffectiveType effectiveOn;
        private Integer[] packages;
        private Integer[] categories;
        private Discount.DiscountType discountType;
        private Integer discountAmount;
        private Integer discountPercentage;
        private Boolean redeemUnlimited;
        private Boolean expireNever;
        private String startDate;
        private BasketType basketType;
        private Double minimumBaskedValue;
        private Integer discountApplicationMethod;
        private String note;

        // Required values

        public Builder withCode(@NotNull String code) {
            this.formBodyBuilder.add("code", code);
            this.code = code;
            return this;
        }

        public Builder effectiveOn(@NotNull Effective.EffectiveType effectiveOn) {
            this.formBodyBuilder.add("effective_on", effectiveOn.name().toLowerCase());
            this.effectiveOn = effectiveOn;
            return this;
        }

        public Builder forPackages(@NotNull final Integer[] packages) {
            this.formBodyBuilder.add("packages", Arrays.toString(packages));
            this.packages = packages;
            return this;
        }

        public Builder forCategories(@NotNull final Integer[] categories) {
            this.formBodyBuilder.add("categories", Arrays.toString(categories));
            this.categories = categories;
            return this;
        }

        public Builder withDiscountType(@NotNull final Discount.DiscountType discountType) {
            this.formBodyBuilder.add("discount_type", discountType.name().toLowerCase());
            this.discountType = discountType;
            return this;
        }

        public Builder withDiscountAmount(@NotNull("A value for 'discount_amount' is required") final Integer discountAmount) {
            this.formBodyBuilder.add("discount_amount", Integer.toString(discountAmount));
            this.discountAmount = discountAmount;
            return this;
        }

        public Builder withDiscountPercentage(@NotNull final Integer discountPercentage) {
            this.formBodyBuilder.add("discount_percentage", Integer.toString(discountPercentage));
            this.discountPercentage = discountPercentage;
            return this;
        }

        public Builder redeemUnlimited(@NotNull final Boolean redeemUnlimited) {
            this.formBodyBuilder.add("redeem_unlimited", Boolean.toString(redeemUnlimited));
            this.redeemUnlimited = redeemUnlimited;
            return this;
        }

        public Builder expireNever(@NotNull final Boolean expireNever) {
            this.formBodyBuilder.add("expire_never", Boolean.toString(expireNever));
            this.expireNever = expireNever;
            return this;
        }

        public Builder withStartDate(@NotNull final String startDate) {
            if (startDate.length() != 10) {
                throw new IllegalArgumentException("The date '" + startDate + "' does not match the format yyyy-mm-dd");
            }

            if (!datePattern.matcher(startDate).find()) {
                throw new IllegalArgumentException("The date '" + startDate + "' does not match the regex " + datePattern.pattern());
            }

            this.formBodyBuilder.add("start_date", startDate);
            this.startDate = startDate;
            return this;
        }

        public Builder withStartDate(@NotNull final TebexDate startDate) {
            this.formBodyBuilder.add("start_date", startDate.toString());
            this.startDate = startDate.toString();
            return this;
        }

        public Builder withBasketType(@NotNull final BasketType basketType) {
            this.formBodyBuilder.add("basket_type", basketType.name().toLowerCase());
            this.basketType = basketType;
            return this;
        }

        public Builder withMinimumBasketValue(@NotNull final Double minimumBasketValue) {
            this.formBodyBuilder.add("minimum", Double.toString(minimumBasketValue));
            this.minimumBaskedValue = minimumBasketValue;
            return this;
        }

        public Builder withDiscountApplicationMethod(@MagicConstant(flagsFromClass = Coupon.class) final int method) {
            this.formBodyBuilder.add("discount_application_method", Integer.toString(method));
            this.discountApplicationMethod = method;
            return this;
        }

        public Builder withNote(@NotNull final String note) {
            this.formBodyBuilder.add("note", note);
            this.note = note;
            return this;
        }

        // Optional values

        public Builder withExpireLimit(final int expireLimit) {
            this.formBodyBuilder.add("expire_limit", Integer.toString(expireLimit));
            return this;
        }

        public Builder withExpireDate(@NotNull final String expireDate) {
            if (expireDate.length() != 10) {
                throw new IllegalArgumentException("The date '" + expireDate + "' does not match the format yyyy-mm-dd");
            }

            if (!datePattern.matcher(expireDate).find()) {
                throw new IllegalArgumentException("The date '" + expireDate + "' does not match the regex " + datePattern.pattern());
            }

            this.formBodyBuilder.add("expire_date", expireDate);
            return this;
        }

        public Builder withExpireDate(@NotNull final TebexDate expireDate) {
            this.formBodyBuilder.add("expire_date", expireDate.toString());
            return this;
        }

        public Builder forUser(@NotNull final String username) {
            if (username.isEmpty()) {
                return this;
            }

            this.formBodyBuilder.add("username", username);
            return this;
        }

        public RequestBody build() {
            if (this.code == null || this.code.isEmpty()) {
                throw new IllegalArgumentException("A value for 'code' is required");
            }

            if (this.effectiveOn == null) {
                throw new IllegalArgumentException("A value for 'effective_on' is required");
            }

            if (this.effectiveOn == Effective.EffectiveType.PACKAGE && (this.packages == null || this.packages.length == 0)) {
                throw new IllegalArgumentException("An array of 'packages' is required when 'effective_on' is 'PACKAGE'");
            }

            if (this.effectiveOn == Effective.EffectiveType.CATEGORY && (this.categories == null || this.categories.length == 0)) {
                throw new IllegalArgumentException("An array of 'categories' is required when 'effective_on' is 'CATEGORY'");
            }

            if (this.discountType == null) {
                throw new IllegalArgumentException("A value for 'discount_type' is required");
            }

            if (this.discountAmount == null) {
                throw new IllegalArgumentException("A value for 'discount_amount' is required");
            }

            if (this.discountPercentage == null) {
                throw new IllegalArgumentException("A value for 'discount_percentage' is required");
            }

            if (this.redeemUnlimited == null) {
                throw new IllegalArgumentException("A value for 'redeem_unlimited' is required");
            }

            if (this.expireNever == null) {
                throw new IllegalArgumentException("A value for 'expire_never' is required");
            }

            if (this.startDate == null) {
                throw new IllegalArgumentException("A value for 'start_date' is required");
            }

            if (this.basketType == null) {
                throw new IllegalArgumentException("A value for 'basket_type' is required");
            }

            if (this.minimumBaskedValue == null) {
                throw new IllegalArgumentException("A value for 'minimum' is required");
            }

            if (this.discountApplicationMethod == null || this.discountApplicationMethod < 0 || this.discountApplicationMethod > 3) {
                this.formBodyBuilder.add("discount_application_method", Integer.toString(Coupon.APPLY_TO_EACH_PACKAGE));
            }

            if (this.note == null || this.note.isEmpty()) {
                throw new IllegalArgumentException("A value for 'note' is required");
            }

            return this.formBodyBuilder.build();
        }
    }
}
