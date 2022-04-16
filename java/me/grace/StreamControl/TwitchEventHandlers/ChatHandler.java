package me.grace.StreamControl.TwitchEventHandlers;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import me.grace.StreamControl.StreamControl;

public class ChatHandler {
    private final StreamControl twitchPlugin;
    private final StreamControl plugin;
    public final String ign;
    public final String prefix;

    public ChatHandler(StreamControl plugin) {
        this.twitchPlugin = plugin;
        this.plugin = StreamControl.getPlugin(StreamControl.class);
        this.ign = StreamControl.ign;
        this.prefix = StreamControl.prefix;
    }

    @EventSubscriber
    public void onMessageSend(ChannelMessageEvent event) {
        String message = event.getMessage();
        if(message.equals("!smc about")) {
            twitchPlugin.getTwitchClient().getChat().sendMessage(StreamControl.channel, "StreamControl made by @imgracings / Discord grace#2039 :)");
        }
    }
}
