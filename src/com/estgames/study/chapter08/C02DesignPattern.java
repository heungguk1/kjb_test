package com.estgames.study.chapter08;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class C02DesignPattern {

	public static void main(String[] args){
		Validator numericValidator = new Validator((String s) -> s.matches("[a-z]+"));
		boolean b1 = numericValidator.validate("aaaa");
		Validator lowerCaseValidator = new Validator((String s) -> s.matches("\\d+"));
		boolean b2 = lowerCaseValidator.validate("bbbb");
		System.out.println(b1 + " " + b2);
		
		Feed f = new Feed();
		f.registerObserver(new NYTimes());
		f.registerObserver(new Gardian());
		f.registerObserver(new LeMonde());
		f.notifyObservers("The queen said her favourite book is Java 8 in Action!");
		
		Feed f2 = new Feed();
		f2.registerObserver((String tweet) -> {
			if(tweet != null && tweet.contains("money")){
				System.out.println("Breaking news in NY! " + tweet);
			}
		});
		f2.registerObserver((String tweet) -> {
			if(tweet != null && tweet.contains("queen")) {
				System.out.println("Yet another news in London... " + tweet);
			}
		});
		f2.notifyObservers("The queen said her favourite book is Java 8 in Action!");
		
		ProcessingObject<String> p1 = new HeaderTextProcessing();
		ProcessingObject<String> p2 = new SpellCheckerProcessing();
		p1.setSuccessor(p2);
		
		String result = p1.handle("Aren't labdas really sexy?!!");
		System.out.println(result);
		
		UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan : " + text;
		UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
		Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
		String result1 = pipeline.apply("Aren't labdas really sexy?!!");
	}
	
}

interface Observer {
	void notify(String tweet);
}

class NYTimes implements Observer {

	@Override
	public void notify(String tweet) {
		if(tweet != null && tweet.contains("money")) {
			System.out.println("Breaking news in NY! " + tweet);
		}
	}
	
}

class Gardian implements Observer {

	@Override
	public void notify(String tweet) {
		if(tweet != null && tweet.contains("queen")) {
			System.out.println("Yet another news in London... " + tweet);
		}
	}
	
}

class LeMonde implements Observer {

	@Override
	public void notify(String tweet) {
		if(tweet != null && tweet.contains("wine")) {
			System.out.println("Today cheese, wine and news! " + tweet);
		}
	}
	
}

interface Subject {
	void registerObserver(Observer o);
	void notifyObservers(String tweet);
}

class Feed implements Subject {

	private final List<Observer> observers = new ArrayList<>();
	
	@Override
	public void registerObserver(Observer o) {
		this.observers.add(o);
	}

	@Override
	public void notifyObservers(String tweet) {
		observers.forEach(o -> o.notify(tweet));
	}
	
}