package com.estgames.study.chapter12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class C01NewDate {
	
	public void test1(){
		LocalDate date = LocalDate.of(2017, 8, 3); // 2017-08-03
		int year = date.getYear();
		Month month = date.getMonth();
		int day = date.getDayOfMonth();
		DayOfWeek dow = date.getDayOfWeek();
		int lne = date.lengthOfMonth(); // 31 : 8월의 일 수
		boolean leap = date.isLeapYear(); // 윤년여부
	}

}
