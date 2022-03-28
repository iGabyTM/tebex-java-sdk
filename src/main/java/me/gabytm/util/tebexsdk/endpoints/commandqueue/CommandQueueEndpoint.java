package me.gabytm.util.tebexsdk.endpoints.commandqueue;

import com.google.gson.reflect.TypeToken;
import me.gabytm.util.tebexsdk.TebexAPI;
import me.gabytm.util.tebexsdk.endpoints.Endpoint;
import me.gabytm.util.tebexsdk.endpoints.commandqueue.objects.Command;
import me.gabytm.util.tebexsdk.endpoints.commandqueue.objects.DuePlayers;
import me.gabytm.util.tebexsdk.endpoints.commandqueue.objects.OfflineCommands;
import me.gabytm.util.tebexsdk.objects.TebexResponse;
import me.gabytm.util.tebexsdk.utils.Constant;
import me.gabytm.util.tebexsdk.utils.Requests;
import me.gabytm.util.tebexsdk.utils.Responses;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author GabyTM
 * @since 0.0.1-BETA
 */
public class CommandQueueEndpoint {

    private static final Type LIST_OF_COMMANDS = new TypeToken<List<Command>>() {}.getType();

    private CommandQueueEndpoint() { }

    /**
     * List the players who have commands due to be executed when they next login to the game server. This
     * endpoint also returns any offline commands to be processed and the amount of seconds to wait before
     * performing the queue check again. All clients should strictly follow the response of next_check, failure to
     * do so would result in your secret key being revoked or IP address being banned from accessing the API.
     * <br><br>
     * NOTE: The <b>player.id</b> field is an internal ID, sometimes referred to as the <b>plugin username ID</b>. This is the
     * value to use to retrieve online commands for a player (see below). the <b>player.uuid</b> field is the actual
     * username ID value, depending on the username type (a Steam64ID for steam games, an XUID for Xbox Live
     * etc), and is the value that should be used to replace the <b>{id}</b> placeholder in commands.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @return {@link DuePlayers}
     * @since 0.0.1-BETA
     * @see TebexAPI#getDuePlayers()
     */
    @ApiStatus.Internal
    @NotNull
    public static TebexResponse<DuePlayers> getDuePlayers(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.COMMAND_QUEUE__QUEUE);
        return Responses.getObject(request, client, DuePlayers.class);
    }

    /**
     * Get the offline commands which are due to be executed.
     * <br><br>
     * These commands should be executed immediately and no checks are required against the related players.
     * <br><br>
     * NOTE: The <b>player.id</b> field is an internal ID, sometimes referred to as the <b>plugin username ID</b>. This is the
     * value to use to retrieve online commands for a player (see below). the <b>player.uuid</b> field is the actual
     * username ID value, depending on the username type (a Steam64ID for steam games, an XUID for Xbox Live
     * etc), and is the value that should be used to replace the <b>{id}</b> placeholder in commands.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @return {@link OfflineCommands}
     * @since 0.0.1-BETA
     * @see TebexAPI#getOfflineCommands()
     */
    @ApiStatus.Internal
    @NotNull
    public static TebexResponse<OfflineCommands> getOfflineCommands(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.COMMAND_QUEUE__OFFLINE_COMMANDS);
        return Responses.getObject(request, client, OfflineCommands.class);
    }

    /**
     * List the due online commands for a specific player.
     * <br><br>
     * These commands should only be executed when the player is online and all the conditions have been met
     * (such as if the player has the required amount of inventory slots).
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @param playerId        the ID of the player you want to retrieve the online commands for
     * @return list of {@link Command}s
     * @since 0.0.1-BETA
     * @see TebexAPI#getOnlineCommands(int)
     */
    @ApiStatus.Internal
    @NotNull
    public static TebexResponse<List<Command>> getOnlineCommands(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client, final int playerId) {
        final Request request = Requests.createGetRequest(serverSecretKey, Endpoint.COMMAND_QUEUE__ONLINE_COMMANDS.getUrl() + playerId);
        return Responses.getList(request, client, LIST_OF_COMMANDS, "commands");
    }

    /**
     * Delete one or more commands which have been executed on the game server.
     * <br><br>
     * An empty response with the status code of <b>204 No Content</b> will be returned on completion.
     *
     * @param serverSecretKey {@link TebexAPI#getServerSecretKey()}
     * @param client          {@link OkHttpClient}
     * @param commands        an array of one or more command IDs to delete
     * @since 0.0.1-BETA
     * @see TebexAPI#deleteCommands(int[])
     */
    @ApiStatus.Internal
    public static void deleteCommands(@NotNull final String serverSecretKey, @NotNull final OkHttpClient client, int @NotNull[] commands) {
        if (commands.length == 0) {
            return;
        }

        final Request request = new Request.Builder()
                .addHeader(Constant.TEBEX_SECRET, serverSecretKey)
                .url(Endpoint.COMMAND_QUEUE__QUEUE.getUrl())
                .delete(
                        new FormBody.Builder()
                                .add("ids", Arrays.toString(commands))
                                .build()
                )
                .build();

        try {
            client.newCall(request).execute().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
