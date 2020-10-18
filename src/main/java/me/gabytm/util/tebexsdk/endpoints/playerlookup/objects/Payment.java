package me.gabytm.util.tebexsdk.endpoints.playerlookup.objects;

public class Payment {

    private String txnId;
    private long time;
    private double price;
    private String currency;
    private int status;

    public String getTxnId() {
        return txnId;
    }

    public long getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public int getStatus() {
        return status;
    }

}
