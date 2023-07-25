package dev.carl.home.load;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.carl.home.Config;
import lombok.Getter;

import java.io.File;
import java.io.IOException;

@Getter
public class ConfigLoad extends AbstractLoad {
    private Config config;

    public ConfigLoad(String fileName) {
        super(fileName);
    }

    @Override
    public void operation(ObjectMapper objectMapper, File file) throws IOException {
        if (file.length() != 0) {
            config = objectMapper.readValue(file, Config.class);
            return;
        }

        config = new Config(60, 5);
        objectMapper.writeValue(file, config);
    }
}
