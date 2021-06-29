package toolPack.dateTimeHandle;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

	public LocalDate findTheXWeekdayOfTheMonth(LocalDate date, int weekdayValue, int targetWeekCount) {
		if (date == null) {
			return null;
		}

		if (targetWeekCount < 1 || targetWeekCount > 5) {
			return null;
		}

		if (weekdayValue < 1 || weekdayValue > 7) {
			return null;
		}

		LocalDate firstDay = date.withDayOfMonth(1);
		LocalDate lastDay = findLastDayOfMonth(date);

		LocalDate tmpDay = firstDay;
		boolean matchWeekDay = false;
		int weekCounting = 1;
		int stepLong = 1;

		for (; (tmpDay.isBefore(lastDay) || tmpDay.isEqual(lastDay)) && weekCounting <= targetWeekCount;) {
			if (tmpDay.getDayOfWeek().getValue() == weekdayValue) {
				stepLong = 7;
				matchWeekDay = true;
				if (targetWeekCount == weekCounting) {
					return tmpDay;
				}
			}
			tmpDay = tmpDay.plusDays(stepLong);
			if (matchWeekDay) {
				weekCounting += 1;
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

	public LocalDateTime findNextDayOfWeek(LocalDateTime datetime, DayOfWeek dayOfWeek) {
		while(!dayOfWeek.equals(datetime.getDayOfWeek())) {
			datetime = datetime.plusDays(1);
		}
		return datetime.withHour(0).withMinute(0).withSecond(0).withNano(0);
	}
	
	public LocalDateTime findLastDayOfWeek(LocalDateTime datetime, DayOfWeek dayOfWeek) {
		while(!dayOfWeek.equals(datetime.getDayOfWeek())) {
			datetime = datetime.minusDays(1);
		}
		return datetime.withHour(0).withMinute(0).withSecond(0).withNano(0);
	}
	
	/**
	 * please input US date
	 */
	public boolean isUSWinterTime(LocalDate usDate) {
		if (usDate == null) {
			return false;
		}

		int monthValue = usDate.getMonthValue();
		if (monthValue > 11 || monthValue < 3) {
			return true;
		} else if (monthValue < 11 && monthValue > 3) {
			return false;
		} else if (monthValue == 11) {
			LocalDate winterTimeStart = findTheXWeekdayOfTheMonth(usDate, 7, 1);
			return !usDate.isBefore(winterTimeStart);
		} else if (monthValue == 3) {
			LocalDate winterTimeEnd = findTheXWeekdayOfTheMonth(usDate, 7, 2);
			return !usDate.isAfter(winterTimeEnd);
		}
		
		return false;
	}
	
	public boolean isUSWinterTime() {
		return isUSWinterTime(LocalDate.now());
	}
	
	public LocalDateTime jsonStrToLocalDateTime(String jsonStr) {
		try {
			JSONObject j = JSONObject.fromObject(jsonStr);
			LocalDateTime l = LocalDateTime.of(j.getInt("year"), j.getInt("monthValue"), j.getInt("dayOfMonth"), j.getInt("hour"), j.getInt("minute"), j.getInt("second"), j.getInt("nano"));
			return l;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unused")
	private void lodalDateTimeCalDemo() {
//		ChronoUnit
		LocalDateTime fromDate = null;
		LocalDateTime toDate = null;
		long minutes = ChronoUnit.MINUTES.between(fromDate, toDate);
		long hours = ChronoUnit.HOURS.between(fromDate, toDate);
	}
}
