package com.estgames.study.service;

import com.estgames.study.model.Apple;

public class AppleGreenColorPredicate implements ApplePredicate {

	@Override
	public boolean test(Apple apple) {
		return apple.getColor().equals("green");
	}

}
