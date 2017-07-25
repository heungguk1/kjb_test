package com.estgames.study.chapter08;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class C04Debugging {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(2,3,4,5);
		List<Integer> result = numbers.stream()
				.peek(x -> System.out.println("from stream : " + x))
				.map(x -> x + 17)
				.peek(x -> System.out.println("from map : " + x))
				.filter(x -> x % 2 == 0)
				.peek(x -> System.out.println("from filter : " + x))
				.limit(3)
				.peek(x -> System.out.println("from limit : " + x))
				.collect(Collectors.toList());
		System.out.println(result);
	}
	
}
