package io.volqe.education.utils;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface LobbyUtils {
     String chatPrefix = ChatColor.GREEN + "[LOBBY] §8| §7";

     static void getItems(Inventory inventory, Player player) {
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
