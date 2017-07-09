package com.estgames.study.chapter04.model;

public class Dish {

	String name;
	boolean vegetarian;
	int calories;
	Type type;
	
	public Dish(String name, int calories){
		this.name = name;
		this.calories = calories;
	}
	
	public Dish(String name, boolean vegetarian, int calories, Type type){
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}
	
	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public enum Type{
		MEAT, FISH, OTHER;
	}
	
}
