package dev.alexzvn.fence;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import dev.alexzvn.fence.listeners.MakeBlockListener;

public class App extends JavaPlugin {

    @Override
    public void onEnable() {
        // TODO Auto-generated method stub

        this.registerEvent(new MakeBlockListener());
    }

    @Override
    public void onDisable() {
        // TODO Auto-generated method stub

        
    }

    protected void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
        this.getServer().getPluginManager().registerEvents(listener, this);
    }
}
