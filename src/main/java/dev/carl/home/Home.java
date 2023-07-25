package dev.carl.home;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Home {
    private String world;
    private double x;
    private double y;
    private double z;
    private long lastTeleport;
    private long lastSetHome;

    @JsonIgnore
    public Location getLocation() {
        return world == null ? null : new Location(Bukkit.getWorld(world), x, y, z);
    }

    @JsonIgnore
    public void setLocation(Location location) {
        this.world = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
    }
}
