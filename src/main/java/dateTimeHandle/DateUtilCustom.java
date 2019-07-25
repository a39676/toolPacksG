package dateTimeHandle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public abstract class DateUtilCustom extends DateTimeUtilCommon {
	
	public static Date stringToDateUnkonwFormat(String dateString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(determineDateFormat(dateString));
		
		Date dateOutput = null;
		try {
			dateOutput = dateFormat.parse(dateString);
			return dateOutput;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean isNormalDate(String dateString) {
		if (determineDateFormat(dateString) == null) {
			return false; 
		} else {
			return true;
		}
	}
	
	public static boolean isDateValid(String dateString) {
		String dateFormat = determineDateFormat(dateString);
		if(dateFormat == null) {
			return false;
		}
	    try {
	        DateFormat df = new SimpleDateFormat(dateFormat);
	        df.setLenient(false);
	        df.parse(dateString);
	        return true;
	    } catch (ParseException e) {
	        return false;
	    }
	}
	
	public static Date dateDiffMonths(Date date, Integer manyMonths) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.add(Calendar.MONTH, manyMonths);
		return calendar.getTime();
	}
	
	public static Date dateDiffMonths(Integer manyMonths) {
		return dateDiffMonths(new Date(), manyMonths);
	}
	
	public static Date dateDiffDays(Date date, Integer days) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}
	
	public static Date dateDiffDays(Integer days) {
		return dateDiffDays(new Date(), days);
	}
	
	public static Date atStartOfDay(Date date) {
	    LocalDateTime localDateTime = dateToLocalDateTime(date);
	    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
	    return localDateTimeToDate(startOfDay);
	}

	public static Date atEndOfDay(Date date) {
	    LocalDateTime localDateTime = dateToLocalDateTime(date);
	    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
	    return localDateTimeToDate(endOfDay);
	}
	
}
