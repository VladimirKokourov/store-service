package pojo.output.stat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatResult {

    private String type;
    private int totalDays;
    List<CustomerPurchaseStat> customers;
    private BigDecimal totalExpenses;
    private BigDecimal avgExpenses;

}
