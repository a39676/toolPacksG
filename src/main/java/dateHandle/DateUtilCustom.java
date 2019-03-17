package dateHandle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class DateUtilCustom {

	private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() ;
	
	public static String determineDateFormat(String dateString) {
	    for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
	        if (dateString.toLowerCase().matches(regexp)) {
	            return DATE_FORMAT_REGEXPS.get(regexp);
	        }
	    }
	    return null; // Unknown format.
	}
	
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
	
	/**
	 * 根据系统默认时区将 java.util.Date 转换成 java.time.LocalDateTime
	 * @param dateInput
	 * @return
	 */
	public static LocalDateTime dateToLocalDateTime(Date dateInput) {
		if(dateInput != null) {
			return LocalDateTime.ofInstant(dateInput.toInstant(), ZoneId.systemDefault());
		} else {
			return null;
		}
	}
	
	/**
	 * 按系统默认时区将 java.time.LocalDateTime 转换成 java.util.Date
	 * @param inputLocalDateTime
	 * @return
	 */
	public static Date localDateTimeToDate(LocalDateTime inputLocalDateTime) {
		return Date.from(inputLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
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
	
	static {{
	    DATE_FORMAT_REGEXPS.put("^\\d{8}$", "yyyyMMdd");
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
	    DATE_FORMAT_REGEXPS.put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
	    DATE_FORMAT_REGEXPS.put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
	    DATE_FORMAT_REGEXPS.put("^\\d{12}$", "yyyyMMddHHmm");
	    DATE_FORMAT_REGEXPS.put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
	    DATE_FORMAT_REGEXPS.put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
	    DATE_FORMAT_REGEXPS.put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
	    DATE_FORMAT_REGEXPS.put("^\\d{14}$", "yyyyMMddHHmmss");
	    DATE_FORMAT_REGEXPS.put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
	    DATE_FORMAT_REGEXPS.put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
	    DATE_FORMAT_REGEXPS.put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
	}}
	
}
