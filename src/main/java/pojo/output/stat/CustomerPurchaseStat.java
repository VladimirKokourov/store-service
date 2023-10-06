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
public class CustomerPurchaseStat {

    private String name;
    private List<Purchase> purchases;
    private BigDecimal totalExpenses;

    public void setName(String name) {
        this.name = name;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void setTotalExpenses() {
        totalExpenses = purchases.stream()
                .map(Purchase::getExpenses)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
