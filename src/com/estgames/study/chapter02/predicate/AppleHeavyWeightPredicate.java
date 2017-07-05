package com.estgames.study.chapter02.predicate;

import com.estgames.study.chapter02.model.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {

	@Override
	public boolean test(Apple apple) {
		return apple.getWeight() > 150;
	}

}
