package service;

import dao.Repository;
import exception.WriteToFileException;
import lombok.AllArgsConstructor;
import pojo.output.search.Customer;
import pojo.output.stat.CustomerPurchaseStat;
import pojo.output.stat.Purchase;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class StatCustomersDataService implements DataService {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Repository repository;
    private final String outputFilePath;

    @Override
    public void printResult() {
        int totalDays = DateUtil.countWeekDays(startDate, endDate);

        Map<Customer, List<Purchase>> customersMap = repository.getCustomerPurchaseStats(startDate, endDate);
        List<CustomerPurchaseStat> customersList = new ArrayList<>();
        for (Customer customer : customersMap.keySet()) {
            CustomerPurchaseStat stat = new CustomerPurchaseStat();
            stat.setName(customer.getLastName() + " " + customer.getFirstName());
            stat.setPurchases(customersMap.get(customer));
            stat.setTotalExpenses();
            customersList.add(stat);
        }

        BigDecimal totalExpenses = customersList.stream()
                .map(CustomerPurchaseStat::getTotalExpenses)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal avgExpenses;
        if (totalExpenses.equals(BigDecimal.ZERO)) {
            avgExpenses = BigDecimal.ZERO;
        } else {
            avgExpenses = totalExpenses.divide(BigDecimal.valueOf(customersMap.size()),
                    2, RoundingMode.CEILING);
        }

        StatResult result = new StatResult();
        result.setType();
        result.setTotalDays(totalDays);
        result.setCustomers(customersList);
        result.setTotalExpenses(totalExpenses);
        result.setAvgExpenses(avgExpenses);

        Path path = Paths.get(outputFilePath);
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            bw.write(JsonUtil.writeJson(result));
        } catch (IOException e) {
            throw new WriteToFileException("Ошибка записи в файл");
        }
    }
}
