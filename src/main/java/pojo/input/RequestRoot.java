package pojo.input;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pojo.input.search.SearchRequestRoot;
import pojo.input.stat.StatRequestRoot;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(SearchRequestRoot.class),
        @JsonSubTypes.Type(StatRequestRoot.class)
})
public abstract class RequestRoot {
}
