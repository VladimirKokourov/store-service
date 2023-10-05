package pojo.input.stat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pojo.input.RequestRoot;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatRequestRoot extends RequestRoot {

    private LocalDate startDate;
    private LocalDate endDate;
}
