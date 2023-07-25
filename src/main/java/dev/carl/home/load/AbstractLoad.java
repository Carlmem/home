package dev.carl.home.load;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import dev.carl.home.Main;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

@AllArgsConstructor
public abstract class AbstractLoad {
    private final String fileName;

    public void load() {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.findAndRegisterModules();

        try {
            File dataFolder = Main.getInstance().getDataFolder();
            File file = new File(dataFolder, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            operation(objectMapper, file);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.SEVERE, "IOException OCCURRED" + e);
        }
    }

    protected abstract void operation(ObjectMapper objectMapper, File file) throws IOException;
}
