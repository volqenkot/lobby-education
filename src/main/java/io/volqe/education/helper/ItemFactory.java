package io.volqe.education.helper;

import io.volqe.education.utils.GradientUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ItemFactory {
    private HashMap<String, ItemStack> item = new HashMap<>();
    public ItemStack createItem(Material material, String name, ChatColor chatColor, String loreDescription) {
        List<String> lore = new ArrayList<>();
        ItemStack newItem = new ItemStack(material);
        ItemMeta meta = newItem.getItemMeta();
        meta.setDisplayName(chatColor + name);
        newItem.setItemMeta(meta);
        lore.add(ChatColor.GRAY + loreDescription);
        meta.setLore(lore);
        newItem.setItemMeta(meta);
        item.put(name, newItem);
        return newItem;
    }

    public void createGradientItem(Material material, String name, net.md_5.bungee.api.ChatColor startColor, net.md_5.bungee.api.ChatColor endColor, String loreDescription) {
        List<String> lore = new ArrayList<>();
        ItemStack newItem = new ItemStack(material);
        ItemMeta meta = newItem.getItemMeta();
        meta.setDisplayName(GradientUtils.applyGradient(name, startColor, endColor));
        newItem.setItemMeta(meta);
        meta.setItemName(name);
        lore.add(ChatColor.GRAY + loreDescription);
        meta.setLore(lore);
        newItem.setItemMeta(meta);
        item.put(name, newItem);
    }

    public Optional<ItemStack> getItem(String name) {
        return Optional.ofNullable(item.get(name));
    }

}
