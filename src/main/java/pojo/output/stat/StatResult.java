package pojo.output.stat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StatResult {

    private String type;
    private int totalDays;
    List<CustomerPurchaseStat> customers;
    private BigDecimal totalExpenses;
    private BigDecimal avgExpenses;

    public void setType() {
        this.type = "stat";
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public void setCustomers(List<CustomerPurchaseStat> customers) {
        this.customers = customers;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public void setAvgExpenses(BigDecimal avgExpenses) {
        this.avgExpenses = avgExpenses;
    }
}
