package com.estgames.study.chapter03;

public class CapturingLambda {

	public static void main(String[] args){
		int portNumber = 1337;
		Runnable r = () -> System.out.println(portNumber);
	}
	
}
