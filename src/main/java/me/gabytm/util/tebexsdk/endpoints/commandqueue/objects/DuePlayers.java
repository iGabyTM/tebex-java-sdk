package me.gabytm.util.tebexsdk.endpoints.commandqueue.objects;

import java.util.List;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
@SuppressWarnings("unused")
public class DuePlayers {

    private Meta meta;
    private List<Player> players;

    public Meta getMeta() {
        return meta;
    }

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * @author GabyTM
     * @since 0.0.1-BETA
     */
    public static class Meta {

        public boolean executeOffline;
        public int nextCheck;
        private boolean more;

        public boolean isExecuteOffline() {
            return executeOffline;
        }

        public int getNextCheck() {
            return nextCheck;
        }

        public boolean isMore() {
            return more;
        }
    }

}
