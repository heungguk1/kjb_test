package com.estgames.study.chapter08;

public class HeaderTextProcessing extends ProcessingObject<String> {

	@Override
	protected String handleWork(String text) {
		return "From Roul, Mario and Alan : " + text; 
	}

}
