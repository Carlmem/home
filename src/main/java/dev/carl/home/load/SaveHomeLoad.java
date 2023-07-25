package dev.carl.home.load;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.carl.home.Home;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class SaveHomeLoad extends AbstractLoad {
    private final Map<UUID, Home> homeMap;

    public SaveHomeLoad(String fileName, Map<UUID, Home> homeMap) {
        super(fileName);
        this.homeMap = homeMap;
    }

    @Override
    protected void operation(ObjectMapper objectMapper, File file) throws IOException {
        objectMapper.writeValue(file, homeMap);
    }
}
