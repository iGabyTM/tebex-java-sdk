package me.gabytm.util.tebexsdk.endpoints.commandqueue.objects;

import java.util.List;

public class DuePlayers {

    private Meta meta;
    private List<Player> players;

    public Meta getMeta() {
        return meta;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "DuePlayers @ {" +
                "meta=" + meta +
                ", players=" + players +
                '}';
    }

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

        @Override
        public String toString() {
            return "Meta @ {" +
                    "executeOffline=" + executeOffline +
                    ", nextCheck=" + nextCheck +
                    ", more=" + more +
                    '}';
        }
    }
}