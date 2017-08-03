package com.estgames.study.chapter12;

import java.time.temporal.ChronoField;

import java.time.LocalDate;

public class C02Date {

	public void test(){
		LocalDate date1 = LocalDate.of(2017,7,3);
		LocalDate date2 = date1.withYear(2011);
		LocalDate date3 = date2.withDayOfMonth(25);
		LocalDate date4 =  date3.with(ChronoField.MONTH_OF_YEAR, 9);
	}
	
}
