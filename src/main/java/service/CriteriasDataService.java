package service;

import dao.Repository;
import lombok.AllArgsConstructor;
import pojo.input.search.criteria.Criteria;

import java.util.List;

@AllArgsConstructor
public class CriteriasDataService implements DataService {

    private final List<Criteria> criterias;
    private final Repository repository;
    private final String outputFilePath;

    @Override
    public void printResult() {

    }
}
