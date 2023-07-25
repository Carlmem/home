package dev.carl.home.command;

import dev.carl.home.Home;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;


public class HomeCommand extends AbstractHomeCommand {
    public HomeCommand(Map<UUID, Home> homeMap, int cooldown) {
        super(cooldown, homeMap);
    }

    @Override
    protected void commandUse(Player player, Home home) {
        Location location = home.getLocation();

        if (location == null) {
            player.sendMessage(Component.text("Установите Дом: /sethome"));
            return;
        }

        long lastUse = home.getLastTeleport();

        if (hasCoolDown(lastUse)) {
            player.sendMessage(Component.text("Вы сможете телепортироваться домой через " + secondsLater(lastUse) + " Секунд."));
            return;
        }

        home.setLastTeleport(System.currentTimeMillis());
        player.teleport(location);
    }
}
