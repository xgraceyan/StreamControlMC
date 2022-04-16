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

public class StreamControl extends JavaPlugin {
    public static TwitchClient client;
    public static String channel = "Mr_PotatoLIVE";
    public static String ign = "Mr_PotatoLIVE";
    public static String prefix = ChatColor.translateAlternateColorCodes('&', "&b&lStreamControl &r&7» &f");

    @Override
    public void onEnable() {
        this.getLogger().info("StreamControl v1.18 by gracings - Activated!");
        String token = "ipuh0g7fq2exkh5h3qihnzo9xstn5a";
        OAuth2Credential credential = new OAuth2Credential("twitch", token);

        client = TwitchClientBuilder.builder()
                .withClientId("v5thjlgflhoxegl4wcv7155ptxygm0")
                .withClientSecret("by5e4dvzkt3p6giennszz1h8kh9p7p")
                .withEnableChat(true)
                .withChatAccount(credential)
                .withEnableHelix(true)
                .withDefaultAuthToken(credential)
                .withEnablePubSub(true)
                .build();
        client.getChat().joinChannel(channel);
        client.getClientHelper().enableStreamEventListener(channel);
        client.getClientHelper().enableFollowEventListener(channel);

        UserList resultList = client.getHelix().getUsers(null, null, Arrays.asList(channel)).execute();

        client.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(new BitHandler(this));
        client.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(new SubHandler(this));
        client.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(new PointsHandler(this));
        client.getEventManager().getEventHandler(SimpleEventHandler.class).registerListener(new ChatHandler(this));
        client.getPubSub().listenForChannelPointsRedemptionEvents(credential, resultList.getUsers().get(0).getId());
    }
    public TwitchClient getTwitchClient() {
        return client;
    }
}
