package com.estgames.study.chapter08;

public class SpellCheckerProcessing extends ProcessingObject<String> {

	@Override
	protected String handleWork(String text) {
		return text.replaceAll("labda", "lambda");
	}

}
