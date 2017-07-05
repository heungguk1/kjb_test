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
			if(apple.getColor().equals(color)){ // �ٲ�� �κ�
				result.add(apple);
			}
		}
		return result;
	}
	
	public static List<Apple> filterAppleByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<Apple>();
		for(Apple apple : inventory) {
			if(apple.getWeight() > weight){ // �ٲ�� �κ� - �� �ܴ̿� ��� �ݺ��ǰ� ����
				result.add(apple);
			}
		}
		return result;
	}
	
	public static List<Apple> filterApple(List<Apple> inventory, ApplePredicate p) { // �������̽��� ���� ���
		List<Apple> result = new ArrayList<Apple>();
		for(Apple apple : inventory) {
			if(p.test(apple)){ // ���� ������ ����.
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
		//List<Apple> greenApple = filterApple(inventory, new AppleGreenColorPredicate()); // �Ź� ���ŷӰ� Ŭ���� ����, �ν��Ͻ�ȭ �ʿ�
		//List<Apple> heavyApple = filterApple(inventory, new AppleHeavyWeightPredicate());
		List<Apple> greenApple = filterApple(inventory, new ApplePredicate(){

			@Override
			public boolean test(Apple apple) {
				return "green".equals(apple.getColor());
			}
			
		}); // �͸�Ŭ����
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
