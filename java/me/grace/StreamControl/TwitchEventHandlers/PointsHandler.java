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

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class PointsHandler {
    private final StreamControl plugin;
    public final String gameName;
    public final String prefix;

    public PointsHandler(StreamControl plugin) {
        this.plugin = StreamControl.getPlugin(StreamControl.class);
        this.gameName = StreamControl.getGameName();
        this.prefix = StreamControl.getPrefix();
    }

    @EventSubscriber
    public void onChannelPointRedeem(ChannelPointsRedemptionEvent event) {
        if(event.getRedemption().getReward().getTitle().equals("Spawn A Goat")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Goat spawned");
                    Player player = Bukkit.getPlayerExact(gameName);
                    Goat goat = (Goat) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.GOAT);
                    goat.setCustomName(event.getRedemption().getUser().getDisplayName());
                    goat.setCustomNameVisible(true);
                }
            }.runTask(plugin);
        } else if(event.getRedemption().getReward().getTitle().equals("Change To Day")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Time changed");
                    Player player = Bukkit.getPlayerExact(gameName);
                    player.getWorld().setTime(500);
                }
            }.runTask(plugin);
        } else if(event.getRedemption().getReward().getTitle().equals("Change To Night")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Time changed");
                    Player player = Bukkit.getPlayerExact(gameName);
                    player.getWorld().setTime(13000);
                }
            }.runTask(plugin);
        } else if(event.getRedemption().getReward().getTitle().equals("Jump")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Jumped");
                    Player player = Bukkit.getPlayerExact(gameName);
                    player.setVelocity(player.getVelocity().setY(0.7));
                }
            }.runTask(plugin);
        } else if(event.getRedemption().getReward().getTitle().equals("Give Me Iron")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Iron given");
                    Player player = Bukkit.getPlayerExact(gameName);
                    final ItemStack iron = new ItemStack(Material.IRON_INGOT, 20); // Your itemstack
                    final Map<Integer, ItemStack> map = player.getInventory().addItem(iron); // Attempt to add in inventory
                    if (!map.isEmpty()) { // If not empty, it means the player's inventory is full.
                        player.getWorld().dropItem(player.getLocation(), iron);
                    }
                }
            }.runTask(plugin);
        } else if(event.getRedemption().getReward().getTitle().equals("Give Me Potatoes")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Potatoes given");
                    Player player = Bukkit.getPlayerExact(gameName);
                    final ItemStack potato = new ItemStack(Material.POTATO, 64); // Your itemstack
                    final Map<Integer, ItemStack> map = player.getInventory().addItem(potato); // Attempt to add in inventory
                    if (!map.isEmpty()) { // If not empty, it means the player's inventory is full.
                        player.getWorld().dropItem(player.getLocation(), potato);
                    }
                }
            }.runTask(plugin);
        } else if(event.getRedemption().getReward().getTitle().equals("Random Potion Effect")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Random effect given");
                    Player player = Bukkit.getPlayerExact(gameName);
                    int rnd = ThreadLocalRandom.current().nextInt(PotionEffectType.values().length);
                    assert player != null;
                    player.addPotionEffect(new PotionEffect(PotionEffectType.values()[rnd], 600, 0));
                }
            }.runTask(plugin);
        } else if(event.getRedemption().getReward().getTitle().equals("Give Me Diamonds")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Diamonds given");
                    Player player = Bukkit.getPlayerExact(gameName);
                    final ItemStack diamond = new ItemStack(Material.DIAMOND, 20); // Your itemstack
                    final Map<Integer, ItemStack> map = player.getInventory().addItem(diamond); // Attempt to add in inventory
                    if (!map.isEmpty()) { // If not empty, it means the player's inventory is full.
                        player.getWorld().dropItem(player.getLocation(), diamond);
                    }
                }
            }.runTask(plugin);
        } else if(event.getRedemption().getReward().getTitle().equals("Give Me Gold")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Gold given");
                    Player player = Bukkit.getPlayerExact(gameName);
                    final ItemStack gold = new ItemStack(Material.GOLD_INGOT, 20); // Your itemstack
                    final Map<Integer, ItemStack> map = player.getInventory().addItem(gold); // Attempt to add in inventory
                    if (!map.isEmpty()) { // If not empty, it means the player's inventory is full.
                        player.getWorld().dropItem(player.getLocation(), gold);
                    }
                }
            }.runTask(plugin);
        } else if(event.getRedemption().getReward().getTitle().equals("Instant Health II")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! Instant health II given");
                    Player player = Bukkit.getPlayerExact(gameName);
                    assert player != null;
                    if(player.getHealth() >= 12) {
                        player.setHealth(20);
                    } else {
                        player.setHealth(player.getHealth() + 8);
                    }
                }
            }.runTask(plugin);
        } else if(event.getRedemption().getReward().getTitle().equals("Notch Apple")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getServer().broadcastMessage(prefix + "Thanks @" + event.getRedemption().getUser().getDisplayName() + "! God apple given");
                    Player player = Bukkit.getPlayerExact(gameName);
                    assert player != null;
                    final ItemStack godApple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1); // Your itemstack
                    final Map<Integer, ItemStack> map = player.getInventory().addItem(godApple); // Attempt to add in inventory
                    if (!map.isEmpty()) { // If not empty, it means the player's inventory is full.
                        player.getWorld().dropItem(player.getLocation(), godApple);
                    }
                }
            }.runTask(plugin);
        }
    }
}
