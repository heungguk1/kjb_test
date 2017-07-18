package com.estgames.study.chapter06;

import java.util.Arrays;
import java.util.List;

import com.estgames.study.chapter04.model.Dish;

import static java.util.stream.Collectors.*;

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
	}

}
