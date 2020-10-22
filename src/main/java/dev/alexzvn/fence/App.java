package dev.alexzvn.fence;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import dev.alexzvn.fence.listeners.MakeBlockListener;
import world.bentobox.bentobox.api.addons.Addon;

public class App extends Addon {

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
        Bukkit.getPluginManager().registerEvents(listener, this.getPlugin());
    }
}
