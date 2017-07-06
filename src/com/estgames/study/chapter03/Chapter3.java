package com.estgames.study.chapter03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.estgames.study.chapter02.model.Apple;

import static java.util.Comparator.comparing;

public class Chapter3 {

	public static void main(String[] args){
		
		List<Apple> inventory = Arrays.asList(
				new Apple("red", 150),
				new Apple("blue", 110));
		
		inventory.sort(new AppleComparator()); // 1. 코드 전달
		
		inventory.sort(new Comparator<Apple>(){

			@Override
			public int compare(Apple o1, Apple o2) {
				return o1.getWeight().compareTo(o2.getWeight());
			}
			
		}); // 2. 익명클래스 사용
		
		inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight())); // 3. 람다 표현식 사용
		
		Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());
		inventory.sort(comparing((a) -> a.getWeight())); // comparing을 사용,,
		
		inventory.sort(comparing(Apple::getWeight)); // 4. 메서드 레퍼런스 사용
	}
	
}

class AppleComparator implements Comparator<Apple> {
	public int compare(Apple a1, Apple a2){
		return a1.getWeight().compareTo(a2.getWeight());
	}
}