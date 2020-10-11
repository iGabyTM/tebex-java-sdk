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
public class Effective {

    private EffectiveType type;
    private int[] packages;
    private int[] categories;

    public EffectiveType getType() {
        return type;
    }

    public int[] getPackages() {
        return packages;
    }

    public int[] getCategories() {
        return categories;
    }

    public enum EffectiveType {
        CATEGORY, PACKAGE
    }

    public static class EffectiveTypeDeserializer implements JsonDeserializer<EffectiveType> {

        @Override
        public EffectiveType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return EffectiveType.valueOf(json.getAsString().toUpperCase());
        }
    }
}
