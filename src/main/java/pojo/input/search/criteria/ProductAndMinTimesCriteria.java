package pojo.input.search.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductAndMinTimesCriteria extends Criteria {

    private String productName;
    private int minTimes;
}
