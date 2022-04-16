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
    public final String ign;
    public final String prefix;

    public BitHandler(StreamControl plugin) {
        this.plugin = StreamControl.getPlugin(StreamControl.class);
        this.ign = StreamControl.ign;
        this.prefix = StreamControl.prefix;
    }

    @EventSubscriber
    public void onCheer(CheerEvent event) {
        int bits = event.getBits();
        assert Bukkit.getPlayerExact(ign) != null;
        // 10 bits - spawns axolotl
        if(bits >= 10 && bits < 100) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + "! Axolotl spawned");
                    Player player = Bukkit.getPlayerExact(ign);
                    Axolotl axolotl = (Axolotl) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.AXOLOTL);
                    axolotl.setCustomName(event.getUser().getName());
                    axolotl.setCustomNameVisible(true);
                }
            }.runTask(plugin);
        }

        // 100 bits - spawns zombie
        else if(bits >= 100 && bits < 500) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + "! Zombie spawned");
                    Player player = Bukkit.getPlayerExact(ign);
                    Zombie zombie = (Zombie) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                    zombie.setCustomName(event.getUser().getName());
                    zombie.setCustomNameVisible(true);
                }
            }.runTask(plugin);
        }

        // 500 bits - spawns ghast
        else if(bits >= 500 && bits < 1000) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + "! Ghast spawned");
                    Player player = Bukkit.getPlayerExact(ign);
                    Ghast ghast = (Ghast) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.GHAST);
                    ghast.setCustomName(event.getUser().getName());
                    ghast.setCustomNameVisible(true);
                }
            }.runTask(plugin);
        }

        else if(bits >= 1000 && bits < 5000) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + "! Witches spawned");
                    Player player = Bukkit.getPlayerExact(ign);
                    player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
                    player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
                    player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
                    player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
                    player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
                }
            }.runTask(plugin);
        }

        else if(bits >= 5000 && bits < 10000) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + "! Wither spawned");
                    Player player = Bukkit.getPlayerExact(ign);
                    player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.WITHER);
                }
            }.runTask(plugin);
        }

        else if(bits >= 10000) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "@" + event.getUser().getName() + " committed murder O_O");
                    Player player = Bukkit.getPlayerExact(ign);
                    player.setHealth(0);
                }
            }.runTask(plugin);
        }
    }
}
