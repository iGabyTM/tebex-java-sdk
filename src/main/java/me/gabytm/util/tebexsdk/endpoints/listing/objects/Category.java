package me.gabytm.util.tebexsdk.endpoints.listing.objects;

import java.util.List;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class Category {

    private int id;
    private int order;
    private String name;
    private boolean onlySubcategories;
    private List<Category> subcategories;
    private List<ShopPackage> packages;

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
