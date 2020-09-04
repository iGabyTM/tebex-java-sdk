package me.gabytm.util.tebexsdk.endpoints.listing.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Category {

    private final int id;
    private final int order;
    private final String name;
    private final boolean onlySubcategories;
    private final List<Category> subcategories;
    private final List<ShopPackage> packages;

    public Category(final JsonObject json) {
        System.out.println("json.toString() = " + json.toString());
        this.id = json.get("id").getAsInt();
        this.order = json.get("order").getAsInt();
        this.name = json.get("name").getAsString();
        this.onlySubcategories = json.has("only_subcategories") && json.get("only_subcategories").getAsBoolean();

        if (json.has("subcategories")) {
            final JsonArray subcategoriesArray = json.getAsJsonArray("subcategories");
            final List<Category> subcategories = new ArrayList<>(subcategoriesArray.size());

            subcategoriesArray.forEach(subcategory -> subcategories.add(new Category(subcategory.getAsJsonObject())));
            this.subcategories = subcategories;
        } else {
            this.subcategories = Collections.emptyList();
        }

        final JsonArray packagesArray = json.getAsJsonArray("packages");
        final List<ShopPackage> packages = new ArrayList<>(packagesArray.size());

        packagesArray.forEach(packageObject -> packages.add(new ShopPackage(packageObject.getAsJsonObject())));
        this.packages = packages;
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

    @Override
    public String toString() {
        return "Category @ {" +
                "\n\tid=" + id +
                ", \n\torder=" + order +
                ", \n\tname='" + name + '\'' +
                ", \n\tonlySubcategories=" + onlySubcategories +
                ", \n\tsubcategories=" + subcategories +
                ", \n\tpackages=" + packages +
                '}';
    }
}
