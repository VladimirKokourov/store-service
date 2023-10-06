package util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    public static int countWeekDays(LocalDate start, LocalDate end) {
        // https://stackoverflow.com/a/44942039/642706
        end = end.plusDays(1L); // add for including endDate

        DayOfWeek startW = start.getDayOfWeek();
        DayOfWeek stopW = end.getDayOfWeek();

        int days = (int) ChronoUnit.DAYS.between(start, end);
        int daysWithoutWeekends = days - 2 * ((days + startW.getValue()) / 7);

        return daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (stopW == DayOfWeek.SUNDAY ? 1 : 0);
    }
}
