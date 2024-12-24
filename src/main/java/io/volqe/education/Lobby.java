package io.volqe.education;

import io.volqe.education.listener.PlayerCancelHandler;
import io.volqe.education.listener.PlayerConnectionHandler;
import io.volqe.education.modules.ChatModule;
import io.volqe.education.modules.ExtrasModule;
import io.volqe.education.modules.NavigatorModule;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lobby extends JavaPlugin {
    private static PluginManager pluginManager;

    @Override
    public void onEnable() {
        lobby = this;
        pluginManager = getServer().getPluginManager();
        pluginLoader();
        getLogger().info(ChatColor.GREEN + "[EDUCATION] | Plugin enabled");

        new PlayerConnectionHandler().test();


    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.RED + "[EDUCATION] | Plugin enabled");
    }

    private void pluginLoader(){

        getLogger().info(ChatColor.GOLD + "[EDUCATION] | Plugin loaded.");
        PlayerConnectionHandler playerConnectionHandler = new PlayerConnectionHandler();
        PlayerCancelHandler playerCancelHandler = new PlayerCancelHandler();
        NavigatorModule navigatorModule = new NavigatorModule();
        ChatModule chatModule = new ChatModule();
        ExtrasModule extrasModule = new ExtrasModule();
    }

    private static Lobby lobby;

    public static Lobby getLobby() {
        return lobby;
    }

}
