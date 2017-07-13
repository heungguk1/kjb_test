package com.estgames.study.chapter05;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.estgames.study.chapter04.model.Dish;

public class C06NumberStream {

	public static void main(String[] args) {
		List<Dish> menu = Arrays.asList(
				new Dish("port", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chichen", false, 400, Dish.Type.MEAT),
				new Dish("french", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH)
				);
		
		int calories1 = menu.stream()
				.map(Dish::getCalories)
				.reduce(0, Integer::sum);
		
		int calories2 = menu.stream()
				.mapToInt(Dish::getCalories)
				.sum();
		
		IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
		Stream<Integer> stream = intStream.boxed();
		
		OptionalInt maxCalories = menu.stream()
				.mapToInt(Dish::getCalories)
				.max();
		int max = maxCalories.orElse(1);
		
		IntStream evenNumbers1 = IntStream.range(1,  100).filter(n -> n % 2 == 0); // 1,100 안 포함
		IntStream evenNumbers2 = IntStream.rangeClosed(1,  100).filter(n -> n % 2 == 0); // 1,100 포함
		
		Stream<Stream<int[]>> result_X = IntStream.rangeClosed(1, 100)
				.boxed()
				.map(a -> IntStream.rangeClosed(a, 100)
						.filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
						.mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a*a + b*b)}));
		
		Stream<int[]> result = IntStream.rangeClosed(1, 100)
				.boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100)
						.filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
						.mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a*a + b*b)}));
		result.limit(5)
		.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
		
		Stream<int[]> result_more_good = IntStream.rangeClosed(1, 100)
				.boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100)
						.mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a*a + b*b)})
						.filter(t -> t[2] % 1 == 0));
	}
	
}
