package pojo.output.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pojo.input.search.criteria.Criteria;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchResult {

    private Criteria criteria;
    private List<Customer> results;
}
