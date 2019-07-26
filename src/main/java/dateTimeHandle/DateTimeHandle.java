package dateTimeHandle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandle extends DateTimeUtilCommon {

	public static void main(String[] args) {
		DateTimeHandle d = new DateTimeHandle();
		d.tmpDateTimeTrans("2018-04-11 18:00", 2);

		String format = DateUtilCustom.determineDateFormat("2018-01-02 12:45:00");
		System.out.println(format);
		format = DateUtilCustom.determineDateFormat("2018-01-02T12:45:00");
		System.out.println(format);
		format = DateUtilCustom.determineDateFormat("2018-01-02 12:45");
		System.out.println(format);
	}

	public void tmpDateTimeTrans(String dateTimeInput, Integer duration) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(normalDateTimeFormat);
		LocalDateTime ldt = LocalDateTime.parse(dateTimeInput, formatter);
		System.out.println(ldt + " , " + ldt.plusHours(duration));

		System.out.println(ldt.plusHours(15) + " , " + ldt.plusHours(15).plusHours(duration));

		LocalDateTime ldt2 = LocalDateTime.parse("2018-12-31T23:59:59");
		System.out.println(ldt2);
	}

}
