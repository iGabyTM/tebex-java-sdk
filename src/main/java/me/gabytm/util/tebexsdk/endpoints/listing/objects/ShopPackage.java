package me.gabytm.util.tebexsdk.endpoints.listing.objects;

import org.jetbrains.annotations.ApiStatus;

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
    private Sale sale;
    private int guiItem;

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

    public Sale getSale() {
        return sale;
    }

    /**
     * @see Sale#isActive()
     * @deprecated use {@link #getSale()}
     */
    @ApiStatus.ScheduledForRemoval(inVersion = "0.0.4-BETA")
    @Deprecated
    public boolean isSaleActive() {
        return sale.isActive();
    }

    /**
     * @see Sale#getDiscount()
     * @deprecated use {@link #getSale()}
     */
    @ApiStatus.ScheduledForRemoval(inVersion = "0.0.4-BETA")
    @Deprecated
    public double getSaleDiscount() {
        return sale.discount;
    }

    public static class Sale {

        private boolean active;
        private double discount;

        public boolean isActive() {
            return active;
        }

        public double getDiscount() {
            return discount;
        }

    }

}
