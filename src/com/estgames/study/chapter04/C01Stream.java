package com.estgames.study.chapter04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.estgames.study.chapter04.model.Dish;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class C01Stream {

	public static void java7Code(List<Dish> menu){
		List<Dish> lowCaloricDishes = new ArrayList<Dish>(); // 가비지 변수
		
		for(Dish d : menu){
			if(d.getCalories() < 400){
				lowCaloricDishes.add(d);
			}
		}
		
		Collections.sort(lowCaloricDishes, new Comparator<Dish>(){

			@Override
			public int compare(Dish o1, Dish o2) {
				return Integer.compare(o1.getCalories(), o2.getCalories());
			}
			
		});
		
		//Collections.sort(lowCaloricDishes, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories())); // lambda(java8)
		
		List<String> lowCaloricDishesName = new ArrayList<String>();
		for(Dish d : lowCaloricDishes){
			lowCaloricDishesName.add(d.getName());
		}
	}
	
	public static void java8(List<Dish> menu){
		List<String> lowCaloricDishesName = menu.parallelStream()
				.filter(d -> d.getCalories() < 400)
				.sorted(comparing(Dish::getCalories))
				.map(Dish::getName)
				.collect(toList());
		
		List<String> threeHighCaloricDishNames = menu.stream()
				.filter(d -> d.getCalories() > 300)
				.map(Dish::getName)
				.limit(3)
				.collect(toList());
		System.out.println(threeHighCaloricDishNames); 
		
		long count = menu.stream()
				.filter(d -> d.getCalories() > 300)
				.distinct()
				.limit(3)
				.count();
		System.out.println(count);
		
		long count1 = menu.stream()
				.filter(d -> d.getCalories() > 300)
				.count();
		long count2 = menu.stream()
				.filter(d -> d.getCalories() > 300)
				.distinct()
				.count();
		System.out.println(count1 + " " + count2);
	}
	
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
		
		Dish d = new Dish("port", false, 800, Dish.Type.MEAT);
		List<Dish> menu2 = Arrays.asList(
				new Dish("port", false, 800, Dish.Type.MEAT),d,d,d
				);
		java8(menu2);
	}
	
}
