package com.prometheus;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtils {

	/**
	 * 将LocalDateTime 转为自定义的时间格式字符串
	 * 
	 * @param localDateTime
	 * @param format
	 * @return
	 */
	public static String localDateTimeToString(LocalDateTime localDateTime, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return localDateTime.format(formatter);
	}

	/**
	 * LocalDateTime to long
	 * 
	 * @param localDateTime
	 * @return
	 */
	public static long localDateTimeToTimestamp(LocalDateTime localDateTime) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		return instant.toEpochMilli();
	}

	/**
	 * long to LocalDateTime
	 * 
	 * @param timestamp
	 * @return
	 */
	public static LocalDateTime longToLocalDateTime(long timestamp) {
		Instant instant = Instant.ofEpochMilli(timestamp);
		ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone);
	}

	/**
	 * LocalDateTime to Date
	 * 
	 * @param localDateTime
	 * @return
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDateTime.atZone(zone).toInstant();
		return Date.from(instant);
	}

	/**
	 * 将时间字符串转为自定义格式的LocalDateTime
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static LocalDateTime parseStringToDateTime(String time, String format) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(time, df);
	}

	/**
	 * Date to LocalDateTime
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDateTime dateToLocalDateTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone);
	}

	/**
	 * Date to LocalDate
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDate dateToLocalDate(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime.toLocalDate();
	}

	/**
	 * LocalDate to Date
	 * 
	 * @param localDate
	 * @return
	 */
	public static Date localDateToDate(LocalDate localDate) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
		return Date.from(instant);
	}

	/**
	 * Date to LocalTime
	 * 
	 * @param date
	 * @return
	 */
	public static LocalTime dateToLocalTime(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime.toLocalTime();
	}

	/**
	 * 获取当前时间的前一小时的时间
	 * 
	 * @param localDateTime
	 * @param minusHours
	 * @return
	 */
	public static long minusHours(LocalDateTime localDateTime, long minusHours) {
		return localDateTimeToTimestamp(localDateTime.minusHours(minusHours));
	}

	/**
	 * 获取当前时间的前一天的时间
	 * 
	 * @param localDateTime
	 * @param minusDays
	 * @return
	 */
	public static long minusDays(LocalDateTime localDateTime, long minusDays) {
		return localDateTimeToTimestamp(localDateTime.minusDays(minusDays));
	}

	/**
	 * 获取当前时间的前一周的时间
	 * 
	 * @param localDateTime
	 * @param minusWeeks
	 * @return
	 */
	public static long minusWeeks(LocalDateTime localDateTime, long minusWeeks) {
		return localDateTimeToTimestamp(localDateTime.minusWeeks(minusWeeks));
	}

	/**
	 * 获取当前时间的前一个月的时间
	 * 
	 * @param localDateTime
	 * @param minusMonths
	 * @return
	 */
	public static long minusMonths(LocalDateTime localDateTime, long minusMonths) {
		return localDateTimeToTimestamp(localDateTime.minusMonths(minusMonths));
	}

	public static LocalDate anyDay(LocalDate localDate) {
		/**
		 * 当月第一天-写法1
		 */
		// 第一天传入1，第二天2，第三天3，第四天4，第五天5,......
		LocalDate dayOfMonth = localDate.withDayOfMonth(2);
		/**
		 * 当月第一天-写法2
		 */
//		LocalDate with = localDate.with(TemporalAdjusters.firstDayOfMonth());
//		// 当月最后一天
//		LocalDate lastDate = localDate.with(TemporalAdjusters.lastDayOfMonth());
//
//		// 下一天
//		LocalDate plusDays = dayOfMonth.plusDays(1);
//		// 前一天
//		LocalDate minusDays = dayOfMonth.minusDays(1);
//
//		// 取2014年1月第一个周一
//		LocalDate firstMondayOf2014 = LocalDate.parse("2014-01-01")
//				.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

		return dayOfMonth;
	}

	/**
	 * 转换Prometheus提供的UTC时间
	 *
	 */
	public static String getLocalTime(String UTCTime) {
		ZonedDateTime parse = ZonedDateTime.parse(UTCTime);
		ZonedDateTime zonedDateTime = parse.withZoneSameInstant(ZoneId.of("Asia/Shanghai"));// 中国标准时间 (北京)
		String result = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return result;
	}

//	public static void main(String[] args) {
////		2019-11-11T14:51:17Z
////		2019-11-07T06:13:47Z
////		2019-11-11T02:00:17Z
////		2019-11-12T03:44:47.588420806Z
//		
//		ZonedDateTime parse = ZonedDateTime.parse("2019-11-12T03:44:47.588420806Z");
//		ZonedDateTime zonedDateTime = parse.withZoneSameInstant(ZoneId.of("Asia/Shanghai"));//中国标准时间 (北京)
//		String result = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//		System.out.println(result);
//	}

}
