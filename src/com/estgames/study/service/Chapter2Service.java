package com.estgames.study.service;

import java.util.ArrayList;
import java.util.List;

import com.estgames.study.model.Apple;

public class Chapter2Service {

	public static List<Apple> filterAppleByColor(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList<Apple>();
		for(Apple apple : inventory) {
			if(apple.getColor().equals(color)){
				result.add(apple);
			}
		}
		return result;
	}
	
	public static List<Apple> filterAppleByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<Apple>();
		for(Apple apple : inventory) {
			if(apple.getWeight() > weight){
				result.add(apple);
			}
		}
		return result;
	}
	
	public static List<Apple> filterApple(List<Apple> inventory, String color, int weight, boolean flag) {
		List<Apple> result = new ArrayList<Apple>();
		for(Apple apple : inventory) {
			if((flag && apple.getColor().equals(color))
					|| (!flag && apple.getWeight() > weight)){
				result.add(apple);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		List<Apple> inventory = getAppleListData();
		List<Apple> greenApple = filterAppleByColor(inventory, "green");
		List<Apple> redApple = filterAppleByColor(inventory, "red");
	}

	private static List<Apple> getAppleListData() {
		List<Apple> result = new ArrayList<Apple>();
		result.add(new Apple("red", 10));
		result.add(new Apple("red", 20));
		result.add(new Apple("yellow", 30));
		result.add(new Apple("red", 10));
		result.add(new Apple("blue", 20));
		result.add(new Apple("red", 100));
		result.add(new Apple("green", 60));
		result.add(new Apple("red", 20));
		result.add(new Apple("black", 30));
		result.add(new Apple("green", 10));
		return result;
	}
	
}
