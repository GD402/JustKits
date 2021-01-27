package io.github.gd402.justkits;

import org.bukkit.plugin.java.JavaPlugin;

public class JustKits extends JavaPlugin {
    @Override
    public void onEnable() {
        try {
            this.getCommand("kit").setExecutor(new CommandKit());
        } catch(Exception e) { getLogger().severe("Failed to Load Command 'Kit': " + e); }
    }
    @Override
    public void onDisable() {

    }
}
