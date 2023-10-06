package pojo.input.search.criteria;

import dao.Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pojo.output.search.SearchResult;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BadCustomersCriteria extends Criteria {

    private int badCustomers;

    @Override
    public SearchResult getResult(Repository repository) {
        return new SearchResult(this, repository.getBadCustomers(badCustomers));
    }
}
