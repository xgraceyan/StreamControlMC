package me.grace.StreamControl.TwitchEventHandlers;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.pubsub.events.ChannelPointsRedemptionEvent;
import me.grace.StreamControl.StreamControl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Goat;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ThreadLocalRandom;

public class PointsHandler {
    private final StreamControl plugin;
    public final String ign;
    public final String prefix;

    public PointsHandler(StreamControl plugin) {
        this.plugin = StreamControl.getPlugin(StreamControl.class);
        this.ign = StreamControl.ign;
        this.prefix = StreamControl.prefix;
    }

    public EntityType pickRandomEntity() {
        int rnd = ThreadLocalRandom.current().nextInt(EntityType.values().length);
        if(EntityType.values()[rnd] == EntityType.ENDER_DRAGON || EntityType.values()[rnd] == EntityType.WITHER) {
            pickRandomEntity();
        } else {
            return EntityType.values()[rnd];
        }
        return null;
    }

    @EventSubscriber
    public void onChannelPointRedeem(ChannelPointsRedemptionEvent event) {
        String title = event.getRedemption().getReward().getTitle();

        Player player = Bukkit.getPlayerExact(ign);
        assert player != null;

        if(title.equals("Spawn A Goat")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Goat spawned");
                    Goat goat = (Goat) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.GOAT);
                    goat.setCustomName(event.getRedemption().getUser().getDisplayName());
                    goat.setCustomNameVisible(true);
                }
            }.runTask(plugin);
        }

        else if(title.equals("16 Wood Logs")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Wood given");
                    if(player.getInventory().firstEmpty() == -1) {
                        player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.OAK_LOG, 16));
                    } else {
                        player.getInventory().addItem(new ItemStack(Material.OAK_LOG, 16));
                    }
                }
            }.runTask(plugin);
        }

        else if(title.equals("Set Time to Day")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Wood given");
                    player.getWorld().setTime(500);
                }
            }.runTask(plugin);
        }

        else if(title.equals("Set Time to Night")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Wood given");
                    player.getWorld().setTime(13000);
                }
            }.runTask(plugin);
        }

        else if(title.equals("64 Baked Potatoes")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Wood given");
                    if(player.getInventory().firstEmpty() == -1) {
                        player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.BAKED_POTATO, 64));
                    } else {
                        player.getInventory().addItem(new ItemStack(Material.BAKED_POTATO, 64));
                    }
                }
            }.runTask(plugin);
        }

        else if(title.equals("Random Potion Effect")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Random effect given");
                    int rnd = ThreadLocalRandom.current().nextInt(PotionEffectType.values().length);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.values()[rnd], 600, 0));
                }
            }.runTask(plugin);
        }

        else if(title.equals("32 Diamonds")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Diamonds given");
                    if(player.getInventory().firstEmpty() == -1) {
                        player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.DIAMOND, 32));
                    } else {
                        player.getInventory().addItem(new ItemStack(Material.DIAMOND, 32));
                    }
                }
            }.runTask(plugin);
        }

        else if(title.equals("32 Gold")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Gold given");
                    if(player.getInventory().firstEmpty() == -1) {
                        player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.GOLD_INGOT, 32));
                    } else {
                        player.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 32));
                    }
                }
            }.runTask(plugin);
        }

        else if(title.equals("Spawn Random Mob")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Random mob spawned");
                    player.getLocation().getWorld().spawnEntity(player.getLocation(), pickRandomEntity());
                }
            }.runTask(plugin);
        }

        else if(title.equals("Spawn Lava")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Lava spawned");
                    Location location = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
                    location.getBlock().setType(Material.LAVA);
                }
            }.runTask(plugin);
        }
    }
}
