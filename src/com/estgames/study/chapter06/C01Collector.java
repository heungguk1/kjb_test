package com.estgames.study.chapter06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Stream;

import com.estgames.study.chapter04.model.Dish;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;

public class C01Collector {
	
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
		Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
		Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));
		
		int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
		double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
		IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
		
		String shortMenu = menu.stream().map(Dish::getName).collect(joining());
		
		int totalCalories2 = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
		Optional<Dish> mostCalorieDish1 = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
		
		Stream<Integer> stream = Arrays.asList(1,2,3,4,5,6).stream();
		List<Integer> numbers = stream.reduce(
				new ArrayList<>()
				, (List<Integer> l, Integer e) -> {
					l.add(e);
					return l;
				}
				, (List<Integer> l1, List<Integer> l2) -> {
					l1.addAll(l2);
					return l1;
				});
		System.out.println(numbers);
		
		int totalCalories1 = menu.stream().collect(reducing(0,
				Dish::getCalories,
				Integer::sum));
		int totoalCalories2 = menu.stream().map(Dish::getCalories).reduce(Integer::sum).orElse(0);
		
		String shortMenu0 = menu.stream().map(Dish::getName).collect(joining());
		String shortMenu1 = menu.stream().map(Dish::getName).collect(reducing((d1, d2) -> d1 + d2)).orElse("");
		//String shortMenu2 = menu.stream().collect(reducing((d1, d2) -> d1.getName() + d2.getName())).orElse("");
		String shortMenu3 = menu.stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2));
	}
	
	public static <T> Collector<T, ?, Long> counting(){
		return reducing(0L, e->1L, Long::sum);
	}
	
}
