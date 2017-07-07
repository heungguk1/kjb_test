package com.estgames.study.chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.estgames.study.chapter02.model.Apple;

public class Lamda {

	public static <T> List<T> filter(List<T> inventory, Predicate<T> p) {
		List<T> result = new ArrayList<T>();
		for(T e : inventory) {
			if(p.test(e)){ 
				result.add(e);
			}
		}
		return result;
	}
	public static void process(Runnable r){
		r.run();
	}
	
	public static void main(String[] args){
		List<Apple> inventory = Arrays.asList(
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
		List<Apple> greenApples = filter(inventory, (Apple a) -> "green".equals(a.getColor()));
		
		Runnable r1 = () -> System.out.println("Hello world 1");
		Runnable r2 = new Runnable(){

			@Override
			public void run() {
				System.out.println("Hello world 2");
			}
			
		};
		process(r1);
		process(r2);
		process(() -> System.out.println("Hello World 3"));
	}
	
}
