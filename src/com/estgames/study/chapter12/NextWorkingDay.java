package com.estgames.study.chapter12;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

import static java.time.temporal.TemporalAdjusters.*;

public class NextWorkingDay implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		temporal = temporal.plus(1, ChronoUnit.DAYS);
		DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
		if(dow == DayOfWeek.SATURDAY || dow == DayOfWeek.SUNDAY) {
			temporal = temporal.with(next(DayOfWeek.MONDAY));
		}
		return temporal;
	}

}
