package com.estgames.study.chapter02.predicate;

import com.estgames.study.chapter02.model.Apple;

public class AppleGreenColorPredicate implements ApplePredicate {

	@Override
	public boolean test(Apple apple) {
		return apple.getColor().equals("green");
	}

}
