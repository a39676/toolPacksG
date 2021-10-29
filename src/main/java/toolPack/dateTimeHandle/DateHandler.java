package toolPack.dateTimeHandle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHandler extends DateTimeUtilCommon {
	
	public Date stringToDateUnkonwFormat(String dateString) {
		return stringToDateUnkonwFormat(dateString, null);
	}
	
	public Date stringToDateUnkonwFormat(String dateString, Locale locale) {
		if(locale == null) {
			locale = Locale.US;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(determineDateFormat(dateString), locale);
		
		Date dateOutput = null;
		try {
			dateOutput = dateFormat.parse(dateString);
			return dateOutput;
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean isNormalDate(String dateString) {
		if (determineDateFormat(dateString) == null) {
			return false; 
		} else {
			return true;
		}
	}
	
	public boolean isDateValid(String dateString) {
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
	
	public Date dateDiffMonths(Date date, Integer manyMonths) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.add(Calendar.MONTH, manyMonths);
		return calendar.getTime();
	}
	
	public Date dateDiffMonths(Integer manyMonths) {
		return dateDiffMonths(new Date(), manyMonths);
	}
	
	public Date dateDiffDays(Date date, Integer days) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}
	
	public Date dateDiffDays(Integer days) {
		return dateDiffDays(new Date(), days);
	}
	
	public Date atStartOfDay(Date date) {
	    LocalDateTime localDateTime = dateToLocalDateTime(date);
	    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
	    return localDateTimeToDate(startOfDay);
	}

	public Date atEndOfDay(Date date) {
	    LocalDateTime localDateTime = dateToLocalDateTime(date);
	    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
	    return localDateTimeToDate(endOfDay);
	}
	
	public Calendar toCalendar(LocalDate date) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 0, 0, 0);
		return calendar;
	}
	
	public Lunar toLunar(LocalDate date) {
		Solar s = new Solar();
		s.setSolarDay(date.getDayOfMonth());
		s.setSolarMonth(date.getMonthValue());
		s.setSolarYear(date.getYear());
		Lunar lunar = LunarSolarConverter.SolarToLunar(s);
		return lunar;
	}
}
