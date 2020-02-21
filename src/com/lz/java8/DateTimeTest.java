package com.lz.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/**
 *
 * @author Leowy Zhuang
 */
public class DateTimeTest {

	public static void main(String[] args) {
		
		//获取当前的日期时间
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		//获取当前的日期
		LocalDate ld = ldt.toLocalDate();
		System.out.println(ld);
		
		//获取当前的时间
		LocalTime lt = ldt.toLocalTime();
		System.out.println(lt);

		//日期时间格式转换
		String dateTime = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(dateTime);
		
		//获取日期时间值
		int t = ldt.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
		System.out.println(t);



		Month month = ldt.getMonth();
		int day = ldt.getDayOfMonth();
		int seconds = ldt.getSecond();

		System.out.println("月: " + month + ", 日: " + day + ", 秒: " + seconds);

		LocalDateTime date2 = ldt.withDayOfMonth(10).withYear(2012);
		System.out.println("date2: " + date2);

		// 12 december 2014
		LocalDate date3 = LocalDate.of(2019, Month.MAY, 5);
		System.out.println("date3: " + date3);

		// 22 小时 15 分钟
		LocalTime date4 = LocalTime.of(17, 15);
		System.out.println("date4: " + date4);

		// 解析字符串
		LocalTime date5 = LocalTime.parse("17:15:30");
		System.out.println("date5: " + date5);
		
		//时区
		ZonedDateTime zdt = ZonedDateTime.now();
		System.out.println(zdt);
		
		ZoneId zi1 = ZoneId.systemDefault();
		System.out.println(zi1);
		ZoneId zi2 = ZoneId.of("America/Los_Angeles");
		System.out.println(zi2);
		
	}

}
