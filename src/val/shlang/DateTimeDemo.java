package val.shlang;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeDemo extends Demo {
    @Override
    public void demo() {
        //without timezone
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);

        //timezone
        ZonedDateTime zoned = ZonedDateTime.now(ZoneId.of("GMT-5"));
        //equals to:
        //ZonedDateTime zoned = ZonedDateTime.now(ZoneId.of("America/New_York"));
        
        System.out.println(zoned.format(DateTimeFormatter.RFC_1123_DATE_TIME));

    }
}
