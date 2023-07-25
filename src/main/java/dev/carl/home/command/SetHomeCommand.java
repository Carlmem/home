package dev.carl.home.command;

import dev.carl.home.Home;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class SetHomeCommand extends AbstractHomeCommand {

    public SetHomeCommand(Map<UUID, Home> homeMap, int cooldown) {
        super(cooldown, homeMap);
    }

    @Override
    protected void commandUse(Player player, Home home) {
        long lastUse = home.getLastSetHome();

        if (hasCoolDown(lastUse)) {
            player.sendMessage(Component.text("Вы сможете создать новый дом через " + secondsLater(lastUse) + " Секунд."));
            return;
        }

        home.setLastSetHome(System.currentTimeMillis());
        home.setLocation(player.getLocation());
    }
}
