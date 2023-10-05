package pojo.input.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pojo.input.RequestRoot;
import pojo.input.search.criteria.Criteria;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchRequestRoot extends RequestRoot {

    List<Criteria> criterias;
}
