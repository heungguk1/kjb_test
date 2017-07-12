package com.estgames.study.chapter05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.estgames.study.chapter04.model.Dish;

public class C04Reducing {

	public static void main(String[] args){
		//int sum = Arrays.asList(1,2,3,4,5,6).stream().reduce(0, (a, b) ->{System.out.println("a:" + a + " b:" + b); return a + b;} );
		int sum1 = Arrays.asList(1,2,3,4,5,6).stream().reduce(0, (a, b) -> a + b );
		int sum2 = Arrays.asList(1,2,3,4,5,6).stream().reduce(0, Integer::sum);
		Optional<Integer> sum3 = Arrays.asList(1,2,3,4,5,6).stream().reduce((a,b) -> a+b);
		Optional<Integer> sum4 = Arrays.asList(1,2,3,4,5,6).stream().reduce(Integer::sum);
		
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
		// quiz 5-3 : map과 reduce를 이용해서 스트림의 요리 갯수를 계산
		int count = menu.stream()
				.map(d -> 1)
				.reduce(0, Integer::sum);
		
	}
	
}
