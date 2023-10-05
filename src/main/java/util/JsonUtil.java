package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.input.RequestRoot;

import java.io.IOException;
import java.nio.file.Paths;

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    public static RequestRoot readJson(String path) {
        try {
            return mapper.readValue(Paths.get(path).toFile(), RequestRoot.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String writeJson(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
