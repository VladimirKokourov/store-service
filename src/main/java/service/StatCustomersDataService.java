package service;

import dao.Repository;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class StatCustomersDataService implements DataService {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Repository repository;
    private final String outputFilePath;

    @Override
    public void printResult() {

    }
}
