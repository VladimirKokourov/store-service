package pojo.input.search.criteria;

import dao.Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pojo.output.search.SearchResult;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MinAndMaxExpensesCriteria extends Criteria {

    private BigDecimal minExpenses;
    private BigDecimal maxExpenses;

    @Override
    public SearchResult getResult(Repository repository) {
        return new SearchResult(this, repository.getCustomersByMinAndMaxExpenses(minExpenses, maxExpenses));
    }
}
