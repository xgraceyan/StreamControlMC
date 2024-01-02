package me.grace.StreamControl.TwitchEventHandlers;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import me.grace.StreamControl.StreamControl;

public class ChatHandler {
    private final StreamControl twitchPlugin;

    private final StreamControl plugin;
    private final String gameName;
    private final String prefix;

    public ChatHandler(StreamControl plugin) {
        this.twitchPlugin = plugin;
        this.plugin = StreamControl.getPlugin(StreamControl.class);
        this.gameName = StreamControl.getGameName();
        this.prefix = StreamControl.getPrefix();
    }

    @EventSubscriber
    public void onMessageSend(ChannelMessageEvent event) {
        String message = event.getMessage();
        if(message.equals("!smc about")) {
            twitchPlugin.getTwitchClient().getChat().sendMessage(StreamControl.getChannel(), "StreamControl made by @imgracings / Discord grace#2039 :)");
        }
    }
}
