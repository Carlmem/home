package dev.carl.home;

import dev.carl.home.command.HomeCommand;
import dev.carl.home.command.SetHomeCommand;
import dev.carl.home.events.Join;
import dev.carl.home.load.ConfigLoad;
import dev.carl.home.load.HomeLoad;
import dev.carl.home.load.SaveHomeLoad;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Map;
import java.util.UUID;

public class Main extends JavaPlugin {
    private static Main instance;
    private Map<UUID, Home> homeMap;

    @Override
    public void onEnable() {
        instance = this;
        File file = getDataFolder();
        if (!file.exists()) {
            file.mkdir();
        }

        HomeLoad homeLoad = new HomeLoad("home.yaml");
        homeLoad.load();
        homeMap = homeLoad.getHomeMap();

        Bukkit.getPluginManager().registerEvents(new Join(homeMap), this);

        ConfigLoad configLoad = new ConfigLoad("cfg.yaml");
        configLoad.load();
        Config config = configLoad.getConfig();

        this.getCommand("sethome").setExecutor(new SetHomeCommand(homeMap, config.setHomeCoolDown()));
        this.getCommand("home").setExecutor(new HomeCommand(homeMap, config.homeTeleportCoolDown()));
    }

    @Override
    public void onDisable() {
        SaveHomeLoad saveHomeLoad = new SaveHomeLoad("home.yaml", homeMap);
        saveHomeLoad.load();
    }

    public static Main getInstance() {
        return instance;
    }
}