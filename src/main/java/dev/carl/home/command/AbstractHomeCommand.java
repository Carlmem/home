package dev.carl.home.command;

import dev.carl.home.Home;
import lombok.AllArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public abstract class AbstractHomeCommand implements CommandExecutor {
    private final int coolDown;
    private final Map<UUID, Home> homeMap;
    private final static int TO_MILLISECONDS = 1000;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.execute(sender, args);
        return true;
    }

    protected abstract void commandUse(Player player, Home home);

    protected boolean hasCoolDown(long lastUse) {
        return lastUse != 0 && coolDown(lastUse) >= 0;
    }

    protected long secondsLater(long lastUse) {
        return coolDown(lastUse) / 1000;
    }

    private void execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof Player player)) {
            return;
        }

        if (args.length != 0) {
            return;
        }

        Home home = homeMap.get(player.getUniqueId());
        if (home == null) {
            return;
        }

        commandUse(player, home);
    }

    private long coolDown(long lastUse) {
        return (lastUse + (long) coolDown * TO_MILLISECONDS) - System.currentTimeMillis();
    }
}
