package com.estgames.study.chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.estgames.study.chapter04.model.Dish;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;

public class C01Stream {
	
	public static void main(String[] args){
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
		
		List<String> dishNames = menu.stream()
				.map(Dish::getName)
				.collect(toList());
		
		List<String> words = Arrays.asList("Java", "Lambdas", "In", "Action");
		List<Integer> wordLengths = words.stream()
				.map(String::length)
				.collect(toList());
		
		List<Integer> dishNameLengths = menu.stream()
				.map(Dish::getName)
				.map(String::length)
				.collect(toList());
		
		words = Arrays.asList("Hello", "World");
		words.stream()
				.map(w -> w.split(""))
				.distinct()
				.forEach(System.out::println);
		words.stream()
				.map(w -> w.split(""))
				.map(Arrays::stream)
				.distinct()
				.forEach(System.out::println);
		words.stream()
				.map(w -> w.split(""))
				.flatMap(Arrays::stream)
				.distinct()
				.forEach(System.out::println);
		
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		numbers.stream()
				.map(i -> i*i)
				.forEach(System.out::println);
		
		List<List<Integer>> numberList = Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(3,4));
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for(Integer i : numberList.get(0))
			for(Integer j : numberList.get(1))
				result.add(Arrays.asList(i, j));
		List<int[]> pairs = numberList.get(0).stream()
				.flatMap(i -> numberList.get(1).stream()
								.map(j -> new int[]{i, j}))
				.collect(toList());
		
		List<int[]> pairsDiv3 = numberList.get(0).stream()
				.flatMap(i -> numberList.get(1).stream()
								.map(j -> new int[]{i, j}))
				.filter((i) -> (i[0] + i[1]) % 3 == 0)
				.collect(toList());
		
		if(menu.stream().anyMatch(Dish::isVegetarian)){
			System.out.println("The menu is (somewhat) vegetarian friendly !!");
		}
		
		boolean isHealthy1 = menu.stream().allMatch(d -> d.getCalories() < 1000);
		boolean isHealthy2 = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
		
		Optional<Dish> dish = menu.stream()
				.filter(Dish::isVegetarian)
				.findAny();
		dish.ifPresent(d -> System.out.println(d.getName())); // 값이 있으면 출력하고, 없으면 그냥 아무일도 일어나지 않는다.
		
		Optional<Integer> first = Arrays.asList(1,2,3,4,5,6,7,8).stream()
				.filter(i -> i % 3 == 0)
				.map(i -> i*i)
				.findFirst();
	}

}
