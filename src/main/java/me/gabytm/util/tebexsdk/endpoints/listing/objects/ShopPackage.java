package me.gabytm.util.tebexsdk.endpoints.listing.objects;

import com.google.gson.JsonObject;

public class ShopPackage {

    private final int id;
    private final int order;
    private final String name;
    private final double price;
    private final String image;
    private final int guiItem;

    private final boolean saleActive;
    private final double saleDiscount;

    public ShopPackage(JsonObject json) {
        this.id = json.get("id").getAsInt();
        this.order = json.get("order").getAsInt();
        this.name = json.get("name").getAsString();
        this.price = json.get("price").getAsDouble();
        this.image = json.get("image").getAsString();
        this.guiItem = json.get("gui_item").getAsInt();

        final JsonObject sale = json.getAsJsonObject("sale");

        this.saleActive = sale.get("active").getAsBoolean();
        this.saleDiscount = sale.get("discount").getAsDouble();
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

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public int getGuiItem() {
        return guiItem;
    }

    public boolean isSaleActive() {
        return saleActive;
    }

    public double getSaleDiscount() {
        return saleDiscount;
    }
}
