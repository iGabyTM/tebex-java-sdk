package me.gabytm.util.tebexsdk.endpoints.listing.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Category {

    private final int id;
    private final int order;
    private final String name;
    private final boolean onlySubcategories;
    private final List<Category> subcategories;
    private final List<ShopPackage> packages;

    public Category(final JsonObject json) {
        this.id = json.get("id").getAsInt();
        this.order = json.get("order").getAsInt();
        this.name = json.get("name").getAsString();
        this.onlySubcategories = json.has("only_subcategories") && json.get("only_subcategories").getAsBoolean();

        // Check if the category it's or not a subcategory
        if (json.has("subcategories")) {
            this.subcategories = Stream.of(json.getAsJsonArray("subcategories"))
                    .map(JsonElement::getAsJsonObject)
                    .map(Category::new)
                    .collect(Collectors.toList());
        } else {
            this.subcategories = Collections.emptyList();
        }

        this.packages = Stream.of(json.getAsJsonArray("packages"))
                .map(JsonElement::getAsJsonObject)
                .map(ShopPackage::new)
                .collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public boolean isOnlySubcategories() {
        return onlySubcategories;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public List<ShopPackage> getPackages() {
        return packages;
    }
}
