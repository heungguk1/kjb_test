package com.estgames.study.chapter02.predicate;

import com.estgames.study.chapter02.model.Apple;

public class ApplePrintHeavyPredicate implements ApplePrintPredicate {

	@Override
	public String prettyPrintApple(Apple apple) {
		return "is heavy? " + (apple.getWeight() > 150);
	}

}
