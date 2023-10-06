package service;

import dao.Repository;
import exception.WriteToFileException;
import lombok.AllArgsConstructor;
import pojo.input.search.criteria.Criteria;
import util.JsonUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@AllArgsConstructor
public class CriteriasDataService implements DataService {

    public static final String BEGIN = "{\"type\": \"search\",\"results\": [";
    public static final String END = "]}";

    private final List<Criteria> criterias;
    private final Repository repository;
    private final String outputFilePath;

    @Override
    public void printResult() {
        Path path = Paths.get(outputFilePath);
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            bw.write(BEGIN);
            for (Criteria criteria : criterias) {
                bw.append(JsonUtil.writeJson(criteria.getResult(repository)));
            }
            bw.append(END);
        } catch (IOException e) {
            throw new WriteToFileException("Ошибка записи в файл");
        }
    }
}
