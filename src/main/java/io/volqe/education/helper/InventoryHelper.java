package io.volqe.education.helper;

import io.volqe.education.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import java.util.HashMap;
import java.util.Random;

public class InventoryHelper {
    private static InventoryHelper instance;
    private HashMap<String, Inventory> inventories = new HashMap<>();

    public static InventoryHelper getInstance() {
        if (instance == null) {
            instance = new InventoryHelper();
        }
        return instance;
    }

    public Inventory createInventory(int size, String title) {
        Inventory inventory = Bukkit.createInventory(null, size, title);
        inventories.put(title, inventory);
        return inventory;
    }

    public Inventory getInventory(String title) {
        if (title == null || title.isEmpty()) {
            Lobby.getLobby().getLogger().warning(ChatColor.RED + "Title is null or empty. Cannot fetch inventory.");
            return null;
        }
        if (!inventories.containsKey(title)) {
            Lobby.getLobby().getLogger().warning(ChatColor.RED + "Inventory does not exist. Will be created.");
            int[] validSizes = {9, 18, 27, 36, 45, 54};
            Random random = new Random();
            int size = validSizes[random.nextInt(validSizes.length)];
            createInventory(size, title);
        }
        return inventories.get(title);
    }



    public void openInventory(String title, Player player) {
        if(inventories.containsKey(title)) {
            player.openInventory(getInventory(title));
        } else {
            Lobby.getLobby().getLogger().warning(ChatColor.RED + "Inventory doesnt exist " + title);
        }
    }
}
