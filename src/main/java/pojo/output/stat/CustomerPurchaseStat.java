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
public class CustomerPurchaseStat {

    private String name;
    private List<Purchase> purchases;
    private BigDecimal totalExpenses;
}
