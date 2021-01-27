package io.github.gd402.justkits;

import io.github.gd402.justkits.commands.CommandKit;
import io.github.gd402.justkits.commands.CommandKits;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class JustKits extends JavaPlugin {

    public static JustKits plugin;
    private static FileConfiguration configFileConfig; // My perfect variable naming shines here :)

    @Override
    public void onEnable() {
        plugin = this;
        generateConfig();

        try {
            Objects.requireNonNull(this.getCommand("kit")).setExecutor(new CommandKit());
            Objects.requireNonNull(this.getCommand("kits")).setExecutor(new CommandKits());
        } catch(Exception error) { getLogger().severe("Failed to Load Commands: " + error.getMessage()); }
    }
    @Override
    public void onDisable() {

    }

    private void generateConfig() {
        File configFile;

        configFile = new File(getDataFolder(), "config.yml");
        if(!configFile.exists()) {
            configFile.getParentFile().mkdirs(); // Creates directories for file location
            saveResource("config.yml", false); // Saves file as "config.yml" without replacing any existing files
        }

        configFileConfig = new YamlConfiguration();

        try {
            configFileConfig.load(configFile); // Tries to load plug-in config, gives config error and stack trace if it cannot be loaded
        } catch(IOException | InvalidConfigurationException error) { error.printStackTrace(); }
    }

    public static FileConfiguration getConfigFile() { return configFileConfig; } // Used for global access of plug-in config
}
