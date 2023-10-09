import dao.JdbcRepository;
import dao.Repository;
import exception.ApplicationException;
import exception.WriteToFileException;
import pojo.ErrorMessage;
import service.DataService;
import service.DataServiceFactory;
import util.ArgsValidator;
import util.JsonUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StoreDataApplication {
    public static void main(String[] args) {
        StoreDataApplication storeDataApplication = new StoreDataApplication();
        storeDataApplication.run(args);
    }

    public void run(String[] args) {
        try {
            ArgsValidator.validate(args);
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        String inputCommand = args[0].toLowerCase();
        String inputPath = args[1];
        String outputPath = args[2];

        try {
            Repository repository = new JdbcRepository();
            DataService dataService = DataServiceFactory.getDataService(inputCommand,
                    inputPath, outputPath, repository);
            dataService.printResult();
        } catch (ApplicationException e) {
            Path path = Paths.get(outputPath);
            try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
                ErrorMessage error = new ErrorMessage();
                error.setType("error");
                error.setMessage(e.getMessage());
                bw.write(JsonUtil.writeJson(error));
            } catch (IOException ex) {
                throw new WriteToFileException("Ошибка записи в файл");
            }
        }
    }
}
