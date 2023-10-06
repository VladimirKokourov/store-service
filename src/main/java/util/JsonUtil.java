package util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.FileReadException;
import exception.JsonReadException;
import exception.JsonWriteException;
import pojo.input.RequestRoot;

import java.io.IOException;
import java.nio.file.Paths;

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper()
            .findAndRegisterModules()
            .enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);

    public static RequestRoot readJson(String path) {
        try {
            return mapper.readValue(Paths.get(path).toFile(), RequestRoot.class);
        } catch (JacksonException ex) {
            throw new JsonReadException("Некорректные данные в файле: " + path);
        } catch (IOException e) {
            throw new FileReadException("Не удалось прочитать файл: " + path);
        }
    }

    public static <T> String writeJson(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonWriteException("Не удается записать объект: " + object.getClass().getSimpleName());
        }
    }
}
