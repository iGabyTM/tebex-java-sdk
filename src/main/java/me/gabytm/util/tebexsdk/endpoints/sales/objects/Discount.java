package me.gabytm.util.tebexsdk.endpoints.sales.objects;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class Discount {

    private DiscountType type;
    private int percentage;
    private int value;

    public DiscountType getType() {
        return type;
    }

    public int getPercentage() {
        return percentage;
    }

    public int getValue() {
        return value;
    }

    public enum DiscountType {
        PERCENTAGE
    }

    public static class DiscountTypeDeserializer implements JsonDeserializer<DiscountType> {

        @Override
        public DiscountType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return DiscountType.valueOf(json.getAsString().toUpperCase());
        }
    }

}
