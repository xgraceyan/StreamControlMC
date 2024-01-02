package me.grace.StreamControl;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.helix.domain.UserList;
import me.grace.StreamControl.TwitchEventHandlers.BitHandler;
import me.grace.StreamControl.TwitchEventHandlers.ChatHandler;
import me.grace.StreamControl.TwitchEventHandlers.PointsHandler;
import me.grace.StreamControl.TwitchEventHandlers.SubHandler;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collections;

public class StreamControl extends JavaPlugin {

    private String token = "token";
    private String clientId = "clientId";
    private String clientSecret = "clientSecret";
    private TwitchClient client;
    private static String channel = "twitch_acc";
    private static String gameName = "game_username";
    private static String prefix = ChatColor.translateAlternateColorCodes('&', "&b&lStreamControl &r&7» &f");

    @Override
    public void onEnable() {
        this.getLogger().info("StreamControl v1.17 by Grace - Activated!");
        OAuth2Credential credential = new OAuth2Credential("twitch", token);

        client = TwitchClientBuilder.builder()
                .withClientId(clientId)
                .withClientSecret(clientSecret)
                .withEnableChat(true)
                .withChatAccount(credential)
                .withEnableHelix(true)
                .withDefaultAuthToken(credential)
                .withEnablePubSub(true)
                .build();
        client.getChat().joinChannel(channel);
        client.getClientHelper().enableStreamEventListener(channel);
        client.getClientHelper().enableFollowEventListener(channel);

        UserList resultList = client.getHelix().getUsers(null, null, Collections.singletonList(channel)).execute();

        client.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(new BitHandler(this));
        client.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(new SubHandler(this));
        client.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(new PointsHandler(this));
        client.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(new ChatHandler(this));
        client.getPubSub().listenForChannelPointsRedemptionEvents(credential, resultList.getUsers().get(0).getId());
    }
    public TwitchClient getTwitchClient() {
        return client;
    }

    public static String getChannel() { return channel; }
    public static String getGameName() {return gameName; }
    public static String getPrefix() {return prefix;}
}
