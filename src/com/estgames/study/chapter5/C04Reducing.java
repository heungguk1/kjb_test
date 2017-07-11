package com.estgames.study.chapter5;

import java.util.Arrays;
import java.util.Optional;

public class C04Reducing {

	public static void main(String[] args){
		//int sum = Arrays.asList(1,2,3,4,5,6).stream().reduce(0, (a, b) ->{System.out.println("a:" + a + " b:" + b); return a + b;} );
		int sum1 = Arrays.asList(1,2,3,4,5,6).stream().reduce(0, (a, b) -> a + b );
		int sum2 = Arrays.asList(1,2,3,4,5,6).stream().reduce(0, Integer::sum);
		Optional<Integer> sum3 = Arrays.asList(1,2,3,4,5,6).stream().reduce((a,b) -> a+b);
		Optional<Integer> sum4 = Arrays.asList(1,2,3,4,5,6).stream().reduce(Integer::sum);
	}
	
}
