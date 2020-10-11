package me.gabytm.util.tebexsdk.endpoints.listing.objects;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class ShopPackage {

    private int id;
    private int order;
    private String name;
    private double price;
    private String image;
    private int guiItem;

    private boolean saleActive;
    private double saleDiscount;

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
