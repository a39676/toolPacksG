package dateTimeHandle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandle {
	
	public static void main(String[] args) {
		DateTimeHandle d = new DateTimeHandle();
		d.tmpDateTimeTrans("2018-04-11 18:00", 2);
	}
	
	public void tmpDateTimeTrans(String dateTimeInput, Integer duration) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime ldt = LocalDateTime.parse(dateTimeInput, formatter);
		System.out.println(ldt + " , " + ldt.plusHours(duration));
		
		System.out.println(ldt.plusHours(15) + " , " + ldt.plusHours(15).plusHours(duration));
	}

}
