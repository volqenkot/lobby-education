package io.volqe.education.utils;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public interface PlayerUtils {
     String chatPrefix = "§a@LOBBY §8» §7";


     static void createNavigator(Player player) {
         ItemStack navigatorStack = new ItemStack(Material.COMPASS);
         ItemMeta navigatorMeta = navigatorStack.getItemMeta();
         navigatorMeta.setDisplayName("§cNavigator");
         navigatorMeta.setItemName("Navigator");
         List<String> lore = new ArrayList<>();
         lore.add(ChatColor.GRAY + "Click to open the navigator");
         lore.add(ChatColor.GRAY + "select the game mode and play!");
         navigatorMeta.setLore(lore);
         navigatorStack.setItemMeta(navigatorMeta);
         player.getInventory().setItem(0, navigatorStack);
     }


    static void createExtras(Player player) {
        ItemStack stack = new ItemStack(Material.CHEST_MINECART);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("§eExtras");
        meta.setItemName("Extras");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Click to open the extras menu");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        player.getInventory().setItem(4, stack);
    }

    static void createSettings(Player player) {
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();
        meta.setOwner(player.getName());
        meta.setDisplayName("§5Settings");
        meta.setItemName("Settings");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "You wanna change the settings of the player");
        lore.add(ChatColor.GRAY + "or something from the server?");
        meta.setLore(lore);
        stack.setItemMeta(meta);
        player.getInventory().setItem(8, stack);
    }
    static void getItems(Inventory inventory, Player player) {
        createNavigator(player);
        createExtras(player);
        createSettings(player);
     }

     static void getPlayerInventory(Player player, Inventory inventory) {
         player.getInventory().clear();
         getItems(inventory, player);
     }

     static void setPlayerData(Player player){
         World world = Bukkit.getServer().getWorld("world");
         Location location = new Location(world, 6.434F, 72.000F, -24.389, 153.9F, -5.7F);
         player.teleport(location);
         player.setLevel(0);
         player.setExp(0);
         player.setFoodLevel(20);
         player.setSaturation(20);
         player.setHealth(20);
         player.setGameMode(GameMode.SURVIVAL);
     }
}
