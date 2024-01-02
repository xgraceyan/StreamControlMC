package me.grace.StreamControl.TwitchEventHandlers;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.channel.GiftSubscriptionsEvent;
import com.github.twitch4j.chat.events.channel.SubscriptionEvent;
import com.github.twitch4j.common.enums.SubscriptionPlan;
import me.grace.StreamControl.StreamControl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class SubHandler {
    private final StreamControl plugin;
    private final String gameName;
    private final String prefix;

    public SubHandler(StreamControl plugin) {
        this.plugin = StreamControl.getPlugin(StreamControl.class);
        this.gameName = StreamControl.getGameName();
        this.prefix = StreamControl.getPrefix();
    }

    public int getInventoryFreeSlots(Player player) {
        int count = 0;
        for(ItemStack i : player.getInventory().getContents()) {
            if(i == null || i.getType() == Material.AIR) {
                count++;
            }
        }
        return count;
    }

    @EventSubscriber
    public void onSub(SubscriptionEvent event) {
        Player player = Bukkit.getPlayerExact(gameName);
        assert player != null;

        if(event.getSubPlan() == SubscriptionPlan.TIER1 || event.getSubPlan() == SubscriptionPlan.TWITCH_PRIME) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + " for the TIER 1 sub! Blaze spawned");
                    Blaze blaze = (Blaze) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.BLAZE);
                    blaze.setCustomName(event.getUser().getName());
                    blaze.setCustomNameVisible(true);
                }
            }.runTask(plugin);
        }

        else if(event.getSubPlan() == SubscriptionPlan.TIER2) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + " for the TIER 2 sub! Poppies given");
                    if(getInventoryFreeSlots(player) >= 5) {
                        player.getInventory().addItem(new ItemStack(Material.POPPY, 64));
                        player.getInventory().addItem(new ItemStack(Material.POPPY, 64));
                        player.getInventory().addItem(new ItemStack(Material.POPPY, 64));
                        player.getInventory().addItem(new ItemStack(Material.POPPY, 64));
                        player.getInventory().addItem(new ItemStack(Material.POPPY, 64));
                    }
                }
            }.runTask(plugin);
        }

        else if(event.getSubPlan() == SubscriptionPlan.TIER3) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + " for the TIER 3 sub! Bees spawned");
                    for(int i = 0; i <= 50; i++) {
                        player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.BEE);
                        Bee bee = (Bee) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.BEE);
                        bee.setAnger(1000);
                    }
                }
            }.runTask(plugin);
        }
    }

    @EventSubscriber
    public void onSubGift(GiftSubscriptionsEvent event) {
        int subs = event.getCount();

        Player player = Bukkit.getPlayerExact(gameName);
        assert player != null;

        if(subs >= 1 && subs < 3) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + " for the gifted sub! Item cleared");
                    player.getInventory().setItemInMainHand(null);
                }
            }.runTask(plugin);
        }

        else if(subs >= 3 && subs < 10) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + " for the gifted subs! Hotbar cleared");
                    for(int i = 0; i <= 9; i++) {
                        player.getInventory().setItem(i, null);
                    }
                }
            }.runTask(plugin);
        }

        else if(subs >= 10 && subs < 25) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + " for the gifted subs! Teleported");
                    player.teleport(new Location(player.getWorld(), player.getLocation().getX(), 265, player.getLocation().getY()));
                }
            }.runTask(plugin);
        }

        else if(subs >= 25 && subs < 40) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + " for the gifted subs! Ender Dragon spawned");
                    Location location = new Location(player.getWorld(), player.getLocation().getX(), 50, player.getLocation().getY());
                    location.getWorld().spawnEntity(location, EntityType.ENDER_DRAGON);
                }
            }.runTask(plugin);
        }

        else if(subs >= 40 && subs < 50) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + " for the gifted subs! Health set to half a heart");
                    player.setHealth(1);
                }
            }.runTask(plugin);
        }

        else if(subs >= 50) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getUser().getName() + " for the gifted subs! Player killed");
                    player.setHealth(0);
                }
            }.runTask(plugin);
        }
    }
}
