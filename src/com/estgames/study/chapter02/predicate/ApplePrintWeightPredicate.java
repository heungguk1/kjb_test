package com.estgames.study.chapter02.predicate;

import com.estgames.study.chapter02.model.Apple;

public class ApplePrintWeightPredicate implements ApplePrintPredicate {

	@Override
	public String prettyPrintApple(Apple apple) {
		return "Apple color:" + apple.getColor();
	}

}
