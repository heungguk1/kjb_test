package com.estgames.study.chapter10;

import java.util.Optional;
import java.util.Properties;

public class C04Example {

	public static void main(String...args) {
		
	}
	
	public int readDuration(Properties props, String name){
		return Optional.ofNullable(props.getProperty("name"))
				.flatMap(s -> stringToInt(s))
				.filter(i -> i > 0)
				.orElse(0);
		
		
	}
	
	public Optional<Integer> stringToInt(String s) {
		try {
			return Optional.of(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}
	
}
