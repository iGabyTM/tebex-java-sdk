package me.gabytm.util.tebexsdk.endpoints.playerlookup.objects;

import java.util.List;

public class PlayerLookup {

    private Player player;
    private int banCount;
    private int chargebackRate;
    private List<Payment> payments;
    //TODO add purchaseTotals

    public Player getPlayer() {
        return player;
    }

    public int getBanCount() {
        return banCount;
    }

    public int getChargebackRate() {
        return chargebackRate;
    }

    public List<Payment> getPayments() {
        return payments;
    }

}
