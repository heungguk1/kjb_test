package com.estgames.study.chapter02.predicate;

import com.estgames.study.chapter02.model.Apple;

public class AppleRedAndHeavyColorPredicate implements ApplePredicate {

	@Override
	public boolean test(Apple apple) {
		return apple.getClass().equals("red") &&
				apple.getWeight() > 150;
	}

}
