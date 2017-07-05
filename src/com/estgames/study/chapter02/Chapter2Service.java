package com.estgames.study.chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.estgames.study.chapter02.model.Apple;
import com.estgames.study.chapter02.predicate.AppleGreenColorPredicate;
import com.estgames.study.chapter02.predicate.AppleHeavyWeightPredicate;
import com.estgames.study.chapter02.predicate.ApplePredicate;
import com.estgames.study.chapter02.predicate.ApplePrintPredicate;

public class Chapter2Service {

	public static List<Apple> filterAppleByColor(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList<Apple>();
		for(Apple apple : inventory) {
			if(apple.getColor().equals(color)){ // 바뀌는 부분
				result.add(apple);
			}
		}
		return result;
	}
	
	public static List<Apple> filterAppleByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<Apple>();
		for(Apple apple : inventory) {
			if(apple.getWeight() > weight){ // 바뀌는 부분 - 이 이외는 계속 반복되고 있음
				result.add(apple);
			}
		}
		return result;
	}
	
	public static List<Apple> filterApple(List<Apple> inventory, ApplePredicate p) { // 인터페이스를 만들어서 사용
		List<Apple> result = new ArrayList<Apple>();
		for(Apple apple : inventory) {
			if(p.test(apple)){ // 이제 변하지 않음.
				result.add(apple);
			}
		}
		return result;
	}
	
	public static void prettyPrintApple(List<Apple> inventory, ApplePrintPredicate p) {
		for(Apple apple : inventory) {
			String output = p.prettyPrintApple(apple);
			System.out.println(output);
		}
	}
	
	public static void main(String[] args) {
		List<Apple> inventory = getAppleListData();
		//List<Apple> greenApple = filterApple(inventory, new AppleGreenColorPredicate()); // 매번 번거롭게 클래스 생성, 인스턴스화 필요
		//List<Apple> heavyApple = filterApple(inventory, new AppleHeavyWeightPredicate());
		List<Apple> greenApple = filterApple(inventory, new ApplePredicate(){

			@Override
			public boolean test(Apple apple) {
				return "green".equals(apple.getColor());
			}
			
		}); // 익명클래스
		List<Apple> heavyApple = filterApple(inventory, new ApplePredicate(){

			@Override
			public boolean test(Apple apple) {
				return apple.getWeight() > 150;
			}
			
		});
	}

	private static List<Apple> getAppleListData() {
		return Arrays.asList(
				new Apple("red", 10),
				new Apple("red", 20),
				new Apple("yellow", 30),
				new Apple("red", 10),
				new Apple("blue", 20),
				new Apple("red", 100),
				new Apple("green", 60),
				new Apple("red", 20),
				new Apple("black", 30),
				new Apple("green", 10));
	}
	
}
