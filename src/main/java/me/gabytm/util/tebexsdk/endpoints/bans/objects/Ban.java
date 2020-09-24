package me.gabytm.util.tebexsdk.endpoints.bans.objects;

public class Ban {

    private int id;
    private String time;
    private String ip;
    private String paymentEmail;
    private String reason;
    private User user;

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getIp() {
        return ip;
    }

    public String getPaymentEmail() {
        return paymentEmail;
    }

    public String getReason() {
        return reason;
    }

    public User getUser() {
        return user;
    }
}
