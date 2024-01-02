package me.grace.StreamControl.TwitchEventHandlers;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import com.github.twitch4j.chat.events.channel.CheerEvent;
import me.grace.StreamControl.StreamControl;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

public class BitHandler {
    private final StreamControl plugin;
    private final String gameName;
    private final String prefix;

    public BitHandler(StreamControl plugin) {
        this.plugin = StreamControl.getPlugin(StreamControl.class);
        this.gameName = StreamControl.getGameName();
        this.prefix = StreamControl.getPrefix();
    }

    @EventSubscriber
    public void onCheer(CheerEvent event) {
        if(event.getBits() < 100 && event.getBits() >= 10) {
            // 10 bits - Axolotl spawn
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + "! Axolotl spawned");
                    Player player = Bukkit.getPlayerExact(gameName);
                    Axolotl axolotl = (Axolotl) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.AXOLOTL);
                    axolotl.setCustomName(event.getUser().getName());
                    axolotl.setCustomNameVisible(true);
                }
            }.runTask(plugin);
        } else if(event.getBits() >= 100 && event.getBits() < 1000) {
            // 100 bits - Spawn creeper
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + "! Creeper spawned");
                    Player player = Bukkit.getPlayerExact(gameName);
                    Creeper creeper = (Creeper) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                    creeper.setCustomName(event.getUser().getName());
                    creeper.setCustomNameVisible(true);
                }
            }.runTask(plugin);
        } else if(event.getBits() >= 1000 && event.getBits() < 3000) {
            // 1000 bits - 5 witches
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + "! Witches spawned");
                    Player player = Bukkit.getPlayerExact(gameName);
                    player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
                    player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
                    player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
                    player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
                    player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
                }
            }.runTask(plugin);
        } else if(event.getBits() >= 3000) {
            // 3000 bits - WITHER
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + "! WATCH OUT FOR THE WITHER :O");
                    Player player = Bukkit.getPlayerExact(gameName);
                    Wither wither = (Wither) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.WITHER);
                    wither.setCustomName(event.getUser().getName());
                    wither.setCustomNameVisible(true);
                }
            }.runTask(plugin);
        }
    }
}
