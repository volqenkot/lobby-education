package io.volqe.education.listener;

import io.volqe.education.Lobby;
import io.volqe.education.utils.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnectionHandler implements Listener {
    public PlayerConnectionHandler() {
        Lobby.getLobby().getServer().getPluginManager().registerEvents(this, Lobby.getLobby());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);
        player.sendMessage(PlayerUtils.chatPrefix + "Connected to Volqe!");
        PlayerUtils.setPlayerData(player);
        PlayerUtils.getPlayerInventory(player, player.getInventory());

    }
}
