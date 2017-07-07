package com.estgames.study.chapter02.model;

public class Apple {

	String color;
	int weight;
	
	public Apple(String color, int weight){
		this.color = color;
		this.weight = weight;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public boolean equalsColor(String color){
		return this.color.equals(color);
	}
	
}
