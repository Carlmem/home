package dev.carl.home.events;

import dev.carl.home.Home;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class Join implements Listener {
    private final Map<UUID, Home> homeMap;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        Home home = homeMap.get(uuid);
        if (home != null) {
            return;
        }

        homeMap.put(uuid, new Home());
    }
}
