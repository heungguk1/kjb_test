package com.estgames.study.chapter03;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import com.estgames.study.chapter02.model.Apple;

public class MethodReference {

	public static void main(String[] args){
		Apple a = new Apple("red", 150);
		BiFunction<Apple, String, Boolean> test1 = (apple, color) -> apple.equalsColor(color);
		BiFunction<Apple, String, Boolean> test2 = Apple::equalsColor;
		Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);
		ToIntFunction<String> stringToInteger2 = Integer::parseInt;
	}
	
}

@FunctionalInterface
interface Test{
	boolean test(Apple apple, String color);
}