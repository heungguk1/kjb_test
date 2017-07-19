package com.estgames.study.chapter06;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import com.estgames.study.chapter04.model.Dish;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;

public class C05CollectorInterface {
	
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
		
		List<Dish> dishes = menu.stream()
				.collect(new ToListCollector<Dish>());
		List<Dish> dishesByFactoryMethod = menu.stream()
				.collect(toList());
		List<Dish> dishesByCustom = menu.stream()
				.collect(ArrayList::new,
						List::add,
						List::addAll);
		
		Map<Boolean, List<Integer>> primePartitioned = IntStream.rangeClosed(2, 100)
				.boxed()
				.collect(partitioningBy(i -> C03Grouping.isPrime(i)));
		System.out.println(primePartitioned);
		
		Map<Boolean, List<Integer>> primeByCustom = partitionPrimesWithCustomCollector(100);
		System.out.println(primeByCustom);
	}
	
	public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
		return IntStream.rangeClosed(2, n)
				.boxed()
				.collect(partitioningBy(i -> C03Grouping.isPrime(i)));
	}
	
	public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
		return IntStream
				.rangeClosed(2, n)
				.boxed()
				.collect(new PrimeNumbersCollector());
	}
	
	public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector2(int n) {
		return IntStream
				.rangeClosed(2, n)
				.boxed()
				.collect(
						() -> new HashMap<Boolean, List<Integer>>() {{
							put(true, new ArrayList<Integer>());
							put(false, new ArrayList<Integer>());
						}},
						(map, ele) -> 
							map.get(isPrime(map.get(true), ele))
							.add(ele),
						(map1, map2) -> {
							map1.get(true).addAll(map2.get(true));
							map1.get(false).addAll(map2.get(false));
						});
	}
	
	public static boolean isPrime(List<Integer> prime, int value) {
		return takeWhile(prime, i -> i*i <= value)
				.stream()
				.noneMatch(i -> value % i == 0);
	}
	
	public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
		int i = 0;
		for(A item : list){
			if(!p.test(item))
				return list.subList(0, i);
		}
		return list;
	}

}
