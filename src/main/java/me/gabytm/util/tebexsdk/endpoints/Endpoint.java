package me.gabytm.util.tebexsdk.endpoints;

public enum Endpoint {

    /**
     * @see <a href="https://docs.tebex.io/plugin/endpoints/bans">Tebex Documentation</a>
     */
    BANS(),

    /**
     * @see <a href="https://docs.tebex.io/plugin/endpoints/checkout">Tebex Documentation</a>
     */
    CHECKOUT(),

    /**
     * @see <a href="https://docs.tebex.io/plugin/endpoints/command-queue">Tebex Documentation</a>
     */
    COMMAND_QUEUE__QUEUE("queue"),
    COMMAND_QUEUE__OFFLINE_COMMANDS("queue/offline-commands"),
    COMMAND_QUEUE__ONLINE_COMMANDS("queue/online-commands"),

    /**
     * @see <a href="https://docs.tebex.io/plugin/endpoints/community-goals">Tebex Documentation</a>
     */
    COMMUNITY_GOALS("community_goals"),

    /**
     * @see <a href="https://docs.tebex.io/plugin/endpoints/coupons">Tebex Documentation</a>
     */
    COUPONS(),

    /**
     * @see <a href="https://docs.tebex.io/plugin/endpoints/gift-cards">Tebex Documentation</a>
     */
    GIFT_CARDS("gift-cards"),

    /**
     * @see <a href="https://docs.tebex.io/plugin/endpoints/information">Tebex Documentation</a>
     */
    INFORMATION(),

    /**
     * @see <a href="https://docs.tebex.io/plugin/endpoints/listing">Tebex Documentation</a>
     */
    LISTING(),

    /**
     * @see <a href="https://docs.tebex.io/plugin/endpoints/packages">Tebex Documentation</a>
     */
    PACKAGE(),
    PACKAGES(),

    /**
     * @see <a href="https://docs.tebex.io/plugin/endpoints/payments">Tebex Documentation</a>
     */
    PAYMENTS(),
    PAYMENTS__PAGED("payments?paged=1"),

    /**
     * @see <a href="https://docs.tebex.io/plugin/endpoints/sales">Tebex Documentation</a>
     */
    SALES(),

    /**
     * @see <a href="https://docs.tebex.io/plugin/endpoints/player-lookup">Tebex Documentation</a>
     */
    USER();

    private final String url;

    Endpoint(String path) {
        this.url = "https://plugin.tebex.io/" + path;
    }

    Endpoint() {
        this.url = "https://plugin.tebex.io/" + name().toLowerCase();
    }

    public String getUrl() {
        return url;
    }
}
