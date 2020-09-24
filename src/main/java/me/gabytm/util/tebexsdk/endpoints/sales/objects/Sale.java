package me.gabytm.util.tebexsdk.endpoints.sales.objects;

public class Sale {

    private int id;
    private Effective effective;
    private Discount discount;
    private long start;
    private long expire;
    private int order;

    public int getId() {
        return id;
    }

    public Effective getEffective() {
        return effective;
    }

    public Discount getDiscount() {
        return discount;
    }

    public long getStart() {
        return start;
    }

    public long getExpire() {
        return expire;
    }

    public int getOrder() {
        return order;
    }
}
