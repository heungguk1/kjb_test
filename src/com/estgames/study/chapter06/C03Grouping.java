package com.estgames.study.chapter06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import com.estgames.study.chapter04.model.Dish;

import static java.util.stream.Collectors.*;

public class C03Grouping {
	
	public enum CaloricLevel { DIET, NORMAL, FAT; }

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
		
		Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
		System.out.println(dishesByType);
		
		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(groupingBy(d -> {
			if(d.getCalories() <= 400){
				return CaloricLevel.DIET;
			} else if(d.getCalories() <= 700){
				return CaloricLevel.NORMAL;
			} else {
				return CaloricLevel.FAT;
			}
		}));
		System.out.println(dishesByCaloricLevel);
		
		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeAndCaloricLevel = menu
				.stream().
				collect(groupingBy(Dish::getType
						, groupingBy(d -> {
							if(d.getCalories() <= 400){
								return CaloricLevel.DIET;
							} else if(d.getCalories() <= 700){
								return CaloricLevel.NORMAL;
							} else {
								return CaloricLevel.FAT;
							}
						})));
		System.out.println(dishesByTypeAndCaloricLevel);
		
		Map<Dish.Type, Long> dishesCountByType = menu.stream().collect(groupingBy(Dish::getType, counting()));
		System.out.println(dishesCountByType);
		
		Map<Dish.Type, Optional<Dish>> dishHighCaloriesByType = menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
		System.out.println(dishHighCaloriesByType);
		
		Map<Dish.Type, Dish> mostCaloricByType = menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
		System.out.println(mostCaloricByType);
		
		Map<Dish.Type, Integer> sumCaloriesByType = menu
				.stream()
				.collect(
						groupingBy(
								Dish::getType, 
								summingInt(Dish::getCalories)));
		System.out.println(sumCaloriesByType);
		
		/*List<CaloricLevel> allLevel = menu.stream()
				.map(d -> {
							if(d.getCalories() <= 400){
								return CaloricLevel.DIET;
							} else if(d.getCalories() <= 700){
								return CaloricLevel.NORMAL;
							} else {
								return CaloricLevel.FAT;
							}
						})
				.collect(toList());*/
		Map<Dish.Type, Set<CaloricLevel>> calLevelByType = menu
				.stream()
				.collect(groupingBy(Dish::getType, mapping(d -> {
							if(d.getCalories() <= 400){
								return CaloricLevel.DIET;
							} else if(d.getCalories() <= 700){
								return CaloricLevel.NORMAL;
							} else {
								return CaloricLevel.FAT;
							}
						}, toSet())));
		System.out.println(calLevelByType);
		
		Map<Dish.Type, Set<CaloricLevel>> calLevelByType2 = menu
				.stream()
				.collect(groupingBy(Dish::getType, mapping(d -> {
							if(d.getCalories() <= 400){
								return CaloricLevel.DIET;
							} else if(d.getCalories() <= 700){
								return CaloricLevel.NORMAL;
							} else {
								return CaloricLevel.FAT;
							}
						}, toCollection(HashSet::new))));
		System.out.println(calLevelByType2);
		
		Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(groupingBy(Dish::isVegetarian));
		Map<Boolean, List<Dish>> partitionedMenu2 = menu.stream().collect(partitioningBy(Dish::isVegetarian));
		System.out.println(partitionedMenu);
		System.out.println(partitionedMenu2);
		List<Dish> vagetarianDishes2 = partitionedMenu2.get(true);
		List<Dish> vegetarianDishes3 = menu.stream().filter(Dish::isVegetarian).collect(toList());
		System.out.println(vagetarianDishes2);
		
		Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishedByType = menu
				.stream()
				.collect(
						partitioningBy(Dish::isVegetarian,
								groupingBy(Dish::getType)));
		System.out.println(vegetarianDishedByType);
		
		Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = 
				menu
				.stream()
				.collect(
						partitioningBy(Dish::isVegetarian,
								collectingAndThen(
										maxBy(Comparator.comparingInt(Dish::getCalories)),
										Optional::get)));
		System.out.println(mostCaloricPartitionedByVegetarian);
		
		Map<Boolean, Map<Boolean, List<Dish>>> quiz1 = menu
				.stream()
				.collect(
						partitioningBy(Dish::isVegetarian,
								partitioningBy(d -> d.getCalories() > 500)));
		Map<Boolean, Map<Dish.Type, List<Dish>>> quiz2 = menu
				.stream()
				.collect(
						partitioningBy(Dish::isVegetarian,
								groupingBy(Dish::getType)));
		Map<Boolean, Long> quiz3 = menu
				.stream()
				.collect(
						partitioningBy(Dish::isVegetarian,
								counting()));
		System.out.println(quiz1);
		System.out.println(quiz2);
		System.out.println(quiz3);
		
	}
	
	public static Map<Boolean, List<Integer>> partitionedIsPrime(int n){
		return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(i -> isPrime(i)));
	}
	
	public static boolean isPrime(int value){
		int valueRoot = (int) Math.sqrt((double) value);
		return IntStream.rangeClosed(2, valueRoot).noneMatch(i -> value % i == 0);
	}
	
}
