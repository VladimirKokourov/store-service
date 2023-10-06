package pojo.input.search.criteria;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dao.Repository;
import pojo.output.search.SearchResult;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(LastNameCriteria.class),
        @JsonSubTypes.Type(ProductAndMinTimesCriteria.class),
        @JsonSubTypes.Type(MinAndMaxExpensesCriteria.class),
        @JsonSubTypes.Type(BadCustomersCriteria.class),
})
public abstract class Criteria {

    public abstract SearchResult getResult(Repository repository);
}
