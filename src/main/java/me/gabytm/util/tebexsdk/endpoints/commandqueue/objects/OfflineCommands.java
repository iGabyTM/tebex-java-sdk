package me.gabytm.util.tebexsdk.endpoints.commandqueue.objects;

import java.util.List;

public class OfflineCommands {

    private Meta meta;
    private List<Command> commands;

    public Meta getMeta() {
        return meta;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public static class Meta {

        private boolean limited;

        public boolean isLimited() {
            return limited;
        }
    }
}
