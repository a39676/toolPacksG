package toolPack.dateTimeHandle;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class DateTimeUtilCommon {
	
	/** yyyy-MM-dd */
	public static final String normalDateFormat = "yyyy-MM-dd";
	/** HH:mm:ss */
	public static final String normalTimeFormat = "HH:mm:ss";
	/** yyyy-MM-dd HH:mm:ss */
	public static final String normalDateTimeFormat = normalDateFormat + " " + normalTimeFormat;
	/** yyyy-MM-ddTHH:mm:ss */
	public static final String localDateTimeFormatLong = normalDateFormat + "T" + normalTimeFormat;
	/** yyyy-MM-dd HH:mm */
	public static final String normalDateTimeFormatShort = normalDateFormat + " " + "HH:mm";
	/** yyyy-MM-ddTHH:mm */
	public static final String localDateTimeFormatShort = normalDateFormat + "T" + "HH:mm";
	/** yyyyMMddHHmmss */
	public static final String dateTimeFormatNoSymbol = "yyyyMMddHHmmss";
	/** yyyyMMdd */
	public static final String dateFormatNoSymbol = "yyyyMMdd";

	public static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() ;
	
	public String determineDateFormat(String dateString) {
	    for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
	        if (dateString.toLowerCase().matches(regexp)) {
	            return DATE_FORMAT_REGEXPS.get(regexp);
	        }
	    }
	    return null; // Unknown format.
	}
	
	/**
	 * 根据系统默认时区将 java.util.Date 转换成 java.time.LocalDateTime
	 * @param dateInput
	 * @return
	 */
	public LocalDateTime dateToLocalDateTime(Date dateInput) {
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
	public Date localDateTimeToDate(LocalDateTime inputLocalDateTime) {
		return Date.from(inputLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	static {{
	    DATE_FORMAT_REGEXPS.put("^\\d{8}$", dateFormatNoSymbol);
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
	    DATE_FORMAT_REGEXPS.put("^\\d{4}-\\d{1,2}-\\d{1,2}$", normalDateFormat);
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
	    DATE_FORMAT_REGEXPS.put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", normalDateTimeFormat);
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
	    DATE_FORMAT_REGEXPS.put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
	    DATE_FORMAT_REGEXPS.put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
	    DATE_FORMAT_REGEXPS.put("^\\d{4}-\\d{1,2}-\\d{1,2}t\\d{1,2}:\\d{2}:\\d{2}$", localDateTimeFormatLong);
	    DATE_FORMAT_REGEXPS.put("^\\d{4}-\\d{1,2}-\\d{1,2}t\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
	}}
	
}
