package io.volqe.education.modules;

import io.volqe.education.Lobby;
import io.volqe.education.utils.GradientUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatModule implements Listener {
    public ChatModule() {
        Lobby.getLobby().getServer().getPluginManager().registerEvents(this, Lobby.getLobby());
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if(player.isOp()){
            event.setFormat(player.getPlayerListName() + " §8» §f" + message + "§8.");
            String newFormat = event.getFormat().replace("&", "§");
            event.setFormat(newFormat);
        } else {
            event.setFormat(player.getPlayerListName() + " §8» §f" + message + "§8.");
        }
    }
}
