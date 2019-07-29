package dateTimeHandle;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeHandle extends DateTimeUtilCommon {

	public static LocalDateTime atStartOfDay(LocalDateTime date) {
	    return date.with(LocalTime.MIN);
	}

	public static LocalDateTime atEndOfDay(LocalDateTime date) {
	    return date.with(LocalTime.MAX);
	}

}
