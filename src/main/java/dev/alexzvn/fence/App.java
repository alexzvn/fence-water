package dev.alexzvn.fence;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import dev.alexzvn.fence.listeners.MakeBlockListener;

public class App extends JavaPlugin {

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        this.registerEvent(
            new MakeBlockListener(this.getConfig())
        );
    }

    @Override
    public void onDisable() {
        
    }

    protected void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);

        this.getServer().getPluginManager().registerEvents(listener, this);
    }
}
