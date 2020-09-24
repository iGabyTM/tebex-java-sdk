package me.gabytm.util.tebexsdk.endpoints.listing.objects;

public class ShopPackage {

    private int id;
    private int order;
    private String name;
    private double price;
    private String image;
    private int guiItem;

    private boolean saleActive;
    private double saleDiscount;

    /*
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
    }*/

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

    @Override
    public String toString() {
        return "ShopPackage{" +
                "id=" + id +
                ", order=" + order +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", guiItem=" + guiItem +
                ", saleActive=" + saleActive +
                ", saleDiscount=" + saleDiscount +
                '}';
    }
}
