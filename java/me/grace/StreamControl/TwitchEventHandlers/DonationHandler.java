package me.grace.StreamControl.TwitchEventHandlers;

import me.grace.StreamControl.StreamControl;

public class DonationHandler {
    private final StreamControl plugin;
    public final String ign;
    public final String prefix;

    public DonationHandler(StreamControl plugin) {
        this.plugin = StreamControl.getPlugin(StreamControl.class);
        this.ign = StreamControl.ign;
        this.prefix = StreamControl.prefix;
    }


}
