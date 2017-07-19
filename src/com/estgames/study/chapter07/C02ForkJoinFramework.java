package com.estgames.study.chapter07;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class C02ForkJoinFramework {

	public static void main(String[] args){
		forkJoinSum(100_000_000);
	}
	
	public static long forkJoinSum(long n) {
		long[] numbers = LongStream.rangeClosed(1, n).toArray();
		ForkJoinSumCalculator task = new ForkJoinSumCalculator(numbers);
		return new ForkJoinPool().invoke(task);
	}
	
}
