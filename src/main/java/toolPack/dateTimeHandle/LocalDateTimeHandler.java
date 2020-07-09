package toolPack.dateTimeHandle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

import net.sf.json.JSONObject;

public class LocalDateTimeHandler extends DateTimeUtilCommon {

	public LocalDateTime atStartOfDay(LocalDateTime date) {
		return date.with(LocalTime.MIN);
	}

	public LocalDateTime atEndOfDay(LocalDateTime date) {
		return date.with(LocalTime.MAX);
	}

	public String dateToStr(LocalDateTime date, String format, Locale locale) {
		if (date == null) {
			return null;
		}
		if (locale == null) {
			locale = Locale.US;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, locale);
		String formatDateTime = date.format(formatter);
		return formatDateTime;
	}

	public String dateToStr(LocalDateTime date) {
		return dateToStr(date, DateTimeUtilCommon.normalDateTimeFormat, null);
	}

	public String dateToStr(LocalDateTime date, String format) {
		return dateToStr(date, format, null);
	}

	/**
	 * 下策 LocalDateTime 转换至 json 默认的字符串输出比较特别, 因日常需求, 新建此方法
	 * 
	 * @param localDateTimeStr LocalDateTime 默认输出样例 { "nano": 0, "year": 2019,
	 *                         "monthValue": 12, "dayOfMonth": 2, "hour": 15,
	 *                         "minute": 0, "second": 11, "dayOfWeek": "MONDAY",
	 *                         "dayOfYear": 336, "month": "DECEMBER", "chronology":
	 *                         { "id": "ISO", "calendarType": "iso8601" } }
	 * @return
	 */
	public LocalDateTime localDateTimeJsonStrToLocalDateTime(String localDateTimeStr) {
		LocalDateTime ldt = null;
		try {
			JSONObject j = JSONObject.fromObject(localDateTimeStr);
			ldt = LocalDateTime.of(j.getInt("year"), j.getInt("monthValue"), j.getInt("dayOfMonth"), j.getInt("hour"),
					j.getInt("minute"), j.getInt("second"));
		} catch (Exception e) {
			return null;
		}
		return ldt;
	}

	public LocalDateTime stringToLocalDateTimeUnkonwFormat(String dateString) {
		return stringToLocalDateTimeUnkonwFormat(dateString, null);
	}

	public LocalDateTime stringToLocalDateTimeUnkonwFormat(String dateString, Locale locale) {
		if (locale == null) {
			locale = Locale.US;
		}
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(determineDateFormat(dateString), locale);

		if (dateFormat != null) {
			try {
				return LocalDateTime.parse(dateString, dateFormat);
			} catch (Exception e) {
				try {
					return LocalDate.parse(dateString, dateFormat).atStartOfDay();
				} catch (Exception e2) {
					return null;
				}
			}
		}
		return null;
	}

	public LocalDate findTheXWeekdayOfTheMonth(LocalDate date, int weekdayValue, int targetIndex) {
		if (date == null) {
			return null;
		}

		if (targetIndex < 1 || targetIndex > 5) {
			return null;
		}

		if (weekdayValue < 1 || weekdayValue > 7) {
			return null;
		}

		LocalDate firstDay = date.withDayOfMonth(1);
		LocalDate lastDay = findLastDayOfMonth(date);

		LocalDate tmpDay = firstDay;
		boolean matchWeekDay = false;
		int tmpIndex = 1;
		int stepLong = 1;

		for (; (tmpDay.isBefore(lastDay) || tmpDay.isEqual(lastDay))
				&& tmpIndex <= targetIndex;) {
			if(tmpDay.getDayOfWeek().getValue() == weekdayValue) {
				stepLong = 7;
				matchWeekDay = true;
				if(targetIndex == tmpIndex) {
					return tmpDay;
				}
			}
			tmpDay = tmpDay.plusDays(stepLong);
			if(matchWeekDay) {
				tmpIndex += 1;
			}
		}

		return null;
	}

	public LocalDate findLastDayOfMonth(LocalDate date) {
		if (date == null) {
			return null;
		}
		return date.with(TemporalAdjusters.lastDayOfMonth());
	}
	
}
