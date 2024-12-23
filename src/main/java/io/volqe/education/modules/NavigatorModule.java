package io.volqe.education.modules;

import io.volqe.education.Lobby;
import io.volqe.education.utils.GradientUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.ToolComponent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class NavigatorModule implements Listener {
    public NavigatorModule() {
        Lobby.getLobby().getServer().getPluginManager().registerEvents(this, Lobby.getLobby());
    }

    private static Inventory navigatorInventory;
    private static void createNavigator(Player player) {
        navigatorInventory = Bukkit.createInventory(null, 54, GradientUtils.applyGradient("Navigator", net.md_5.bungee.api.ChatColor.RED, net.md_5.bungee.api.ChatColor.DARK_RED));
        createItem(Material.BLACK_STAINED_GLASS_PANE, "§", ChatColor.DARK_PURPLE, "§");
        createItem(Material.GRAY_STAINED_GLASS_PANE, "§§", ChatColor.DARK_PURPLE, "§");
        createItem(Material.BLAZE_POWDER, "Spawn", ChatColor.GOLD, "Go back to the spawn location.");
        createItem(Material.CHEST, "Mode-1", ChatColor.RED, "Click to go to the mode.");
        createItem(Material.SHIELD, "Mode-2", ChatColor.RED, "Click to go to the mode 2.");
        createItem(Material.BLACK_BED, "Mode-3", ChatColor.RED, "Click to go to the mode 3.");
        createItem(Material.LAVA_BUCKET, "Mode-4", ChatColor.RED, "Click to go to the mode 4.");
        for (int i = 0; i < 54; i++) {
            navigatorInventory.setItem(i , navigator.get("§§"));
        }
        int width = 9;
        int height = 6;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int slot = i * width + j;
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    navigatorInventory.setItem(slot, NavigatorModule.navigator.get("§"));
                }
            }
        }
        navigatorInventory.setItem(40, navigator.get("Spawn"));
        navigatorInventory.setItem(11, navigator.get("Mode-1"));
        navigatorInventory.setItem(15, navigator.get("Mode-2"));
        navigatorInventory.setItem(37, navigator.get("Mode-3"));
        navigatorInventory.setItem(43, navigator.get("Mode-4"));

        player.openInventory(navigatorInventory);
    }

    private static HashMap<String, ItemStack> navigator = new HashMap<>();
    private static void createItem(Material material, String name, ChatColor chatColor, String loreDescription) {
        List<String> lore = new ArrayList<>();
        ItemStack newItem = new ItemStack(material);
        ItemMeta meta = newItem.getItemMeta();
        meta.setDisplayName(chatColor + name);
        newItem.setItemMeta(meta);
        lore.add(ChatColor.GRAY + loreDescription);
        meta.setLore(lore);
        newItem.setItemMeta(meta);
        navigator.put(name, newItem);

    }

    private static boolean isCon = false;
    public static void playerInteractConstructor(Player player, Location teleportLocation) {
        int fiveSeconds = 60;
        Location originalLocation = player.getLocation();
        final float[] yaw = {originalLocation.getYaw()};

        new BukkitRunnable() {
            int ticks = 0;
            @Override
            public void run() {
                if (ticks >= fiveSeconds) {
                    Vector upwardVelocity = new Vector(0, 2, 0);
                    player.setVelocity(upwardVelocity);

                    Bukkit.getScheduler().runTaskLater(
                            Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(Lobby.getLobby().getName())),
                            () -> player.teleport(teleportLocation),
                            10 // 1 Sekunde Verzögerung
                    );
                    cancel();
                    return;
                }
                yaw[0] += 36;
                if (yaw[0] > 360) {
                    yaw[0] -= 360;
                }
                player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 35, 1));
                Location newLocation = originalLocation.clone();
                newLocation.setYaw(yaw[0]); // Ändere nur die Richtung
                player.teleport(newLocation);
                ticks++;
            }
        }.runTaskTimer(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(Lobby.getLobby().getName())), 0L, 1L);
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(isCon){
            event.setCancelled(true);


        } else {
            event.setCancelled(false);
        }

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item == null) {
            event.setCancelled(true);
            return;
        }
        if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§cNavigator") && item.getItemMeta().getItemName().equalsIgnoreCase("Navigator")) {
            createNavigator(player);

        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if(event.getInventory().equals(navigatorInventory)) {
            if(item != null) {
                if (item.equals(navigator.get("Spawn"))) {
                    Location location = new Location(player.getWorld(), 6.434F, 72.000F, -24.389, 153.9F, -5.7F);
                    playerInteractConstructor(player, location);
                    player.closeInventory();
                } else if (item.equals(navigator.get("Mode-1"))) {
                    Location location = new Location(player.getWorld(), 6.434F+10, 72.000F, -24.389, 153.9F, -5.7F);
                    playerInteractConstructor(player, location);
                    player.closeInventory();
                } else if (item.equals(navigator.get("Mode-2"))) {
                    Location location = new Location(player.getWorld(), 6.434F+20, 72.000F, -24.389, 153.9F, -5.7F);
                    playerInteractConstructor(player, location);
                    player.closeInventory();
                } else if (item.equals(navigator.get("Mode-3"))) {
                    Location location = new Location(player.getWorld(), 6.434F+30, 72.000F, -24.389, 153.9F, -5.7F);
                    playerInteractConstructor(player, location);
                    player.closeInventory();
                } else if (item.equals(navigator.get("Mode-4"))) {
                    Location location = new Location(player.getWorld(), 6.434F+35, 72.000F, -24.389, 153.9F, -5.7F);
                    playerInteractConstructor(player, location);
                    player.closeInventory();
                } else {
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }
}
