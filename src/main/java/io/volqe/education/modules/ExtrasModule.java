package io.volqe.education.modules;

import io.volqe.education.Lobby;
import io.volqe.education.utils.GradientUtils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ExtrasModule implements Listener {
    public ExtrasModule() {
        Lobby.getLobby().getServer().getPluginManager().registerEvents(this, Lobby.getLobby());
    }

    private static Inventory extrasInventory;
    private static void setExtrasInventory(Player player) {
        extrasInventory = Bukkit.createInventory(null, 27, GradientUtils.applyGradient("Extras", net.md_5.bungee.api.ChatColor.GOLD, net.md_5.bungee.api.ChatColor.YELLOW));
        createItem(Material.GRAY_STAINED_GLASS_PANE, "§", ChatColor.DARK_PURPLE, "§");
        createItem(Material.BLACK_STAINED_GLASS_PANE, "§§", ChatColor.DARK_PURPLE, "§");
        for (int i = 0; i < 27; i++) {
            extrasInventory.setItem(i , extras.get("§§"));
        }
        int width = 9;
        int height = 3;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int slot = i * width + j;
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    extrasInventory.setItem(slot, ExtrasModule.extras.get("§"));
                }
            }
        }
        //11 13 15
        createItem(Material.LEATHER_BOOTS, "Boots", ChatColor.GOLD, "Here you can select special boots :o");
        createItem(Material.LEAD, "Pets", ChatColor.AQUA, "Buy one pet. :3");
        createItem(Material.NAME_TAG, "Nickname", ChatColor.GREEN, "Change your public nickname.");

        extrasInventory.setItem(11, extras.get("Boots"));
        extrasInventory.setItem(13, extras.get("Pets"));
        extrasInventory.setItem(15, extras.get("Nickname"));

        player.openInventory(extrasInventory);
    }

    private static HashMap<String, ItemStack> extras = new HashMap<>();
    private static void createItem(Material material, String name, ChatColor chatColor, String loreDescription) {
        List<String> lore = new ArrayList<>();
        ItemStack newItem = new ItemStack(material);
        ItemMeta meta = newItem.getItemMeta();
        meta.setDisplayName(chatColor + name);
        newItem.setItemMeta(meta);
        meta.setItemName(name);
        lore.add(ChatColor.GRAY + loreDescription);
        meta.setLore(lore);
        newItem.setItemMeta(meta);
        extras.put(name, newItem);

    }

    private static void createItemRGB(Material material, String name, net.md_5.bungee.api.ChatColor startColor, net.md_5.bungee.api.ChatColor endColor, String loreDescription) {
        List<String> lore = new ArrayList<>();
        ItemStack newItem = new ItemStack(material);
        ItemMeta meta = newItem.getItemMeta();
        meta.setDisplayName(GradientUtils.applyGradient(name, startColor, endColor));
        newItem.setItemMeta(meta);
        meta.setItemName(name);
        lore.add(ChatColor.GRAY + loreDescription);
        meta.setLore(lore);
        newItem.setItemMeta(meta);
        extras.put(name, newItem);

    }
    private static Inventory nickInventory;
    // Methode zum Öffnen des Anvil-GUIs
    public static void nickConstructor(Player player) {
        nickInventory = Bukkit.createInventory(null, 27, "§8Set your color gradient!");
        createItem(Material.GRAY_STAINED_GLASS_PANE, "§", ChatColor.DARK_PURPLE, "§");
        createItem(Material.BLACK_STAINED_GLASS_PANE, "§§", ChatColor.DARK_PURPLE, "§");
        for (int i = 0; i < 27; i++) {
            nickInventory.setItem(i , extras.get("§§"));
        }
        int width = 9;
        int height = 3;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int slot = i * width + j;
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    nickInventory.setItem(slot, ExtrasModule.extras.get("§"));
                }
            }
        }
        createItemRGB(Material.PAPER, "RedGreen", net.md_5.bungee.api.ChatColor.DARK_RED, net.md_5.bungee.api.ChatColor.GREEN, "Set you display name");
        createItemRGB(Material.PAPER, "OrangeBlack", net.md_5.bungee.api.ChatColor.GOLD, net.md_5.bungee.api.ChatColor.BLACK, "Set you display name");
        createItemRGB(Material.PAPER, "BluePurple", net.md_5.bungee.api.ChatColor.DARK_BLUE, net.md_5.bungee.api.ChatColor.LIGHT_PURPLE, "Set you display name");
        createItemRGB(Material.PAPER, "WhiteGray", net.md_5.bungee.api.ChatColor.WHITE, net.md_5.bungee.api.ChatColor.DARK_GRAY, "Set you display name");
        createItemRGB(Material.PAPER, "BlackWhite", net.md_5.bungee.api.ChatColor.BLACK, net.md_5.bungee.api.ChatColor.WHITE, "Set you display name");
        createItemRGB(Material.PAPER, "GoldRed", net.md_5.bungee.api.ChatColor.GOLD, net.md_5.bungee.api.ChatColor.RED, "Set you display name");
        createItemRGB(Material.PAPER, "Silversurfer", net.md_5.bungee.api.ChatColor.GRAY, net.md_5.bungee.api.ChatColor.DARK_GRAY, "Set you display name");
        nickInventory.setItem(10, extras.get("RedGreen"));
        nickInventory.setItem(11, extras.get("OrangeBlack"));
        nickInventory.setItem(12, extras.get("BluePurple"));
        nickInventory.setItem(13, extras.get("WhiteGray"));
        nickInventory.setItem(14, extras.get("BlackWhite"));
        nickInventory.setItem(15, extras.get("GoldRed"));
        nickInventory.setItem(16, extras.get("Silversurfer"));
        player.openInventory(nickInventory);

    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item == null) {
            event.setCancelled(true);
            return;
        }
        if(item.getItemMeta().getDisplayName().equalsIgnoreCase("§eExtras") && item.getItemMeta().getItemName().equalsIgnoreCase("Extras")) {
            setExtrasInventory(player);

        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if(event.getInventory().equals(extrasInventory)) {
            if(item != null) {
                if(item.getItemMeta().getItemName().equalsIgnoreCase("Nickname")) {
                    nickConstructor(player);
                }
            } else {
                event.setCancelled(true);
            }
        } else if(event.getInventory().equals(nickInventory)) {
            if(item != null) {
                if(item.getItemMeta().getItemName().equalsIgnoreCase("RedGreen")) {
                    player.closeInventory();
                    String prefix = GradientUtils.applyGradient(player.getName().toString(), net.md_5.bungee.api.ChatColor.DARK_RED, net.md_5.bungee.api.ChatColor.GREEN);
                    player.setPlayerListName(prefix);
                    player.setDisplayName(prefix);
                } else if(item.getItemMeta().getItemName().equalsIgnoreCase("OrangeBlack")) {
                    player.closeInventory();
                    String prefix = GradientUtils.applyGradient(player.getName().toString(), net.md_5.bungee.api.ChatColor.GOLD, net.md_5.bungee.api.ChatColor.BLACK);
                    player.setPlayerListName(prefix);
                    player.setDisplayName(prefix);
                }
            }
        }
    }
}
