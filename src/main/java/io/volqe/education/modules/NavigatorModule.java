package io.volqe.education.modules;

import io.volqe.education.Lobby;
import io.volqe.education.helper.InventoryHelper;
import io.volqe.education.helper.ItemFactory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class NavigatorModule implements Listener {
    private InventoryHelper helper = new InventoryHelper();
    private ItemFactory factory;
    public NavigatorModule() {
        helper = new InventoryHelper();
        factory = new ItemFactory();
        Lobby.getLobby().getServer().getPluginManager().registerEvents(this, Lobby.getLobby());
    }

    private void setNavigatorInventory(Player player) {

        helper.createInventory( 54, "Your navigator");
        helper.getInventory("Your navigator").setItem(0, factory.createItem(Material.BLAZE_POWDER,
                "TestDebug", ChatColor.GOLD, "This is Debug Test."));
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {
            return;
        }
        if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§cNavigator")) {
            setNavigatorInventory(player);
            helper.openInventory("Your navigator", player);

        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        if (item == null || !item.hasItemMeta()) {
            return;
        }
    }
}
