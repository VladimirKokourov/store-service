package pojo.input.search.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MinAndMaxExpensesCriteria extends Criteria {

    private BigDecimal minExpenses;
    private BigDecimal maxExpenses;
}
