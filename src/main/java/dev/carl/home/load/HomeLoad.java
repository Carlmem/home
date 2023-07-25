package dev.carl.home.load;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.carl.home.Home;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class HomeLoad extends AbstractLoad {
    private Map<UUID, Home> homeMap;

    public HomeLoad(String fileName) {
        super(fileName);
    }

    @Override
    protected void operation(ObjectMapper objectMapper, File file) throws IOException {
        homeMap = file.length() != 0 ? objectMapper.readValue(file, new TypeReference<>() {}) : new HashMap<>();
    }
}