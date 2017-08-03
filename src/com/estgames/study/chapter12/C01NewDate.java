package com.estgames.study.chapter12;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;

public class C01NewDate {
	
	public void test1(){
		LocalDate date = LocalDate.of(2017, 8, 3); // 2017-08-03
		int year = date.getYear();
		Month month = date.getMonth();
		int day = date.getDayOfMonth();
		DayOfWeek dow = date.getDayOfWeek();
		int lne = date.lengthOfMonth(); // 31 : 8월의 일 수
		boolean leap = date.isLeapYear(); // 윤년여부
		
		int year2 = date.get(ChronoField.YEAR);
		int month2 = date.get(ChronoField.MONTH_OF_YEAR);
		int day2 = date.get(ChronoField.DAY_OF_MONTH);
	}
	
	public void test2(){
		LocalTime time = LocalTime.of(13, 45, 20);
		time.getHour();
		time.getMinute();
		time.getSecond();
	}
	
	public void test3(){
		LocalDate date = LocalDate.parse("2017-01-01");
		LocalTime time = LocalTime.parse("18:22:22");
		LocalDateTime dt1 = LocalDateTime.of(date, time);
		LocalDate.now();
		LocalTime.now();
		Instant.ofEpochSecond(4, -1_000_000_000);
	}

}
