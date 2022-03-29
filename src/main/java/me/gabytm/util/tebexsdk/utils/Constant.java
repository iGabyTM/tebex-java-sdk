package me.gabytm.util.tebexsdk.utils;

import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.objects.TebexResponse;

/**
 * Constants used inside the API
 *
 * @author GabyTM
 * @since 0.0.2-BETA
 */
public class Constant {

    /**
     * Path of the error message provided if the request wasn't successful
     *
     * @see TebexResponse#error(String)
     * @see <a href="https://docs.tebex.io/plugin/error-handling">Error Handling</a>
     * @since 0.0.2-BETA
     */
    public static final String ERROR_MESSAGE = "error_message";

    /**
     * Header for {@link TebexAPI#getServerSecretKey()}}
     *
     * @since 0.0.2-BETA
     */
    public static final String TEBEX_SECRET = "X-Tebex-Secret";

    /**
     * The date format used by Tebex
     *
     * @see com.google.gson.GsonBuilder#setDateFormat(String)
     * @see 0.0.3-BETA
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

}
