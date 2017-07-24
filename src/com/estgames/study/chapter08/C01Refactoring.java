package com.estgames.study.chapter08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.estgames.study.chapter02.model.Apple;
import com.estgames.study.chapter04.model.Dish;
import com.estgames.study.chapter06.C03Grouping;

public class C01Refactoring {

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
		List<Apple> inventory = Arrays.asList(
				new Apple("red", 150),
				new Apple("blue", 110));
		
		doSomething(new  Task() {
			public void execute(){
				System.out.println("Danger danger!!");
			}
		});
		doSomething((Task)() -> System.out.println("Danger danger!!"));
		
		Map<C03Grouping.CaloricLevel, List<Dish>> dishesByCaloricLevel = 
				menu.stream()
						.collect(Collectors.groupingBy(dish -> {
							if(dish.getCalories() <= 400) return C03Grouping.CaloricLevel.DIET;
							else if(dish.getCalories() <= 700) return C03Grouping.CaloricLevel.NORMAL;
							else return C03Grouping.CaloricLevel.FAT;
						}));
		Map<C03Grouping.CaloricLevel, List<Dish>> dishesByCaloricLevel2 = 
				menu.stream().collect(Collectors.groupingBy(Dish::getCaloricLevel));
		
		inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
		inventory.sort(Comparator.comparing(Apple::getWeight));
		
		int totalCalories = menu
				.stream()
				.map(Dish::getCalories)
				.reduce(0, (c1, c2) -> c1 + c2);
		int totalCalories2 = menu
				.stream()
				.collect(Collectors.summingInt(Dish::getCalories));
		
		List<String> dishNames = new ArrayList<>();
		for(Dish dish : menu) {
			if(dish.getCalories() > 300) {
				dishNames.add(dish.getName());
			}
		}
		
		List<String> dishNames2 = menu.stream()
				.filter(d -> d.getCalories() > 300)
				.map(Dish::getName)
				.collect(Collectors.toList());
		
		
	}
	
	public void lambdaTest(){
		int a = 10;
		Runnable r1 = () -> {
			//int a = 2;
			//System.out.println(a);
		};
		
		Runnable r2 = new Runnable() {
			public void run(){
				int a = 2; 
				System.out.println(a);
			}
		};
	}
	
	interface Task {
		public void execute();
	}
	
	public static void doSomething(Runnable r) {r.run();}
	public static void doSomething(Task r) {r.execute();}
	
}
