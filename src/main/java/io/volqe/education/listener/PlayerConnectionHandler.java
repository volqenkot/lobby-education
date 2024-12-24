package io.volqe.education.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import io.volqe.education.Lobby;
import io.volqe.education.utils.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.nio.charset.StandardCharsets;

public class PlayerConnectionHandler implements Listener {

    public PlayerConnectionHandler() {
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);
        player.sendMessage(PlayerUtils.chatPrefix + "Connected to Volqe!");
        PlayerUtils.setPlayerData(player);
        PlayerUtils.getPlayerInventory(player, player.getInventory());
    }

    public void test() {
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        System.out.println("PacketListener wird initialisiert...");

        protocolManager.addPacketListener(new PacketAdapter(Lobby.getLobby(), PacketType.Play.Client.CUSTOM_PAYLOAD) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                try {
                    // Hole das DiscardedPayload-Objekt aus dem Paket
                    Object payload = event.getPacket().getModifier().read(0);

                    if (payload != null) {
                        // Extrahiere den Kanalnamen aus dem Payload
                        String channel = payload.toString();
                        System.out.println("Payload: " + channel);

                        if (channel.contains("id=")) {
                            String channelName = channel.substring(channel.indexOf("id=") + 3, channel.indexOf(", data="));
                            System.out.println("Channel Name: " + channelName);

                            // Erkenne LabyMod
                            if (channelName.startsWith("labymod")) {
                                System.out.println(event.getPlayer().getName() + " verwendet LabyMod!");
                            }
                            // Erkenne weitere Clients
                            else if (channelName.startsWith("minecraft")) {
                                System.out.println("Minecraft-spezifischer Kanal erkannt: " + channelName);
                            } else {
                                System.out.println("Unbekannter Kanal: " + channelName);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Fehler beim Verarbeiten eines Pakets: " + e.getMessage());
                    e.printStackTrace();
                }
            }



        });

        System.out.println("PacketListener erfolgreich registriert.");
    }



}
