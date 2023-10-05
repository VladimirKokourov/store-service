package pojo.input.search.criteria;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(LastNameCriteria.class),
        @JsonSubTypes.Type(ProductAndMinTimesCriteria.class),
        @JsonSubTypes.Type(MinAndMaxExpensesCriteria.class),
        @JsonSubTypes.Type(BadCustomersCriteria.class),
})
public abstract class Criteria {
}
