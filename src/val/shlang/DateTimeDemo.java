package val.shlang;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;

public class DateTimeDemo extends Demo {
    @Override
    public void demo() {
        //without timezone
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);

        time = time.plusDays(33);
        System.out.println("+ 33 days = " + time);
        
        //timezone
        ZonedDateTime zoned = ZonedDateTime.now(ZoneId.of("GMT-5"));
        //equals to:
        //ZonedDateTime zoned = ZonedDateTime.now(ZoneId.of("America/New_York"));
        
        System.out.println(zoned.format(DateTimeFormatter.RFC_1123_DATE_TIME));

    }
}
