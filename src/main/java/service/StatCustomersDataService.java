package service;

import dao.Repository;
import exception.WriteToFileException;
import lombok.AllArgsConstructor;
import pojo.output.stat.CustomerPurchaseStat;
import pojo.output.stat.StatResult;
import util.DateUtil;
import util.JsonUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class StatCustomersDataService implements DataService {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Repository repository;
    private final String outputFilePath;

    @Override
    public void printResult() {
        Path path = Paths.get(outputFilePath);

        int totalDays = DateUtil.countWeekDays(startDate, endDate);
        List<CustomerPurchaseStat> customers = repository.getCustomerPurchaseStats(startDate, endDate);
        BigDecimal totalExpenses = customers.stream()
                .map(CustomerPurchaseStat::getTotalExpenses)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal avgExpenses = totalExpenses.divide(BigDecimal.valueOf(customers.size()),
                18, RoundingMode.CEILING);

        StatResult result = new StatResult();
        result.setType("stat");
        result.setTotalDays(totalDays);
        result.setCustomers(customers);
        result.setTotalExpenses(totalExpenses);
        result.setAvgExpenses(avgExpenses);

        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            bw.write(JsonUtil.writeJson(result));
        } catch (IOException e) {
            throw new WriteToFileException("Ошибка записи в файл");
        }
    }
}
