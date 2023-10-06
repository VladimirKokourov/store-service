package service;

import dao.Repository;
import exception.UnknownCommandException;
import pojo.input.search.SearchRequestRoot;
import pojo.input.search.criteria.Criteria;
import pojo.input.stat.StatRequestRoot;
import util.JsonUtil;

import java.util.List;

public class DataServiceFactory {

    private static final String SEARCH = "search";
    private static final String STAT = "stat";

    public static DataService getDataService(String command, String inputFilePath,
                                             String outputFilePath, Repository repository) {
        switch (command) {
            case SEARCH :
                SearchRequestRoot searchRequestRoot = (SearchRequestRoot) JsonUtil.readJson(inputFilePath);
                List<Criteria> criterias = searchRequestRoot.getCriterias();
                return new CriteriasDataService(criterias, repository, outputFilePath);
            case STAT:
                StatRequestRoot statRequestRoot = (StatRequestRoot) JsonUtil.readJson(inputFilePath);
                return new StatCustomersDataService(statRequestRoot.getStartDate(), statRequestRoot.getEndDate(),
                        repository, outputFilePath);
            default:
                throw new UnknownCommandException("Неизвестная команда: " + command);
        }
    }
}
