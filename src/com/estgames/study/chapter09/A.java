package com.estgames.study.chapter09;

public interface A {
	default void hello(){
		System.out.println("Hello from A");
	}
}
