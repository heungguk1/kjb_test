package com.estgames.study.chapter07;

import java.util.stream.Stream;

public class C01ParallelStream {

	public static void main(String[] args) {
		
	}
	
	public static long sequentialSum(long n){
		return Stream.iterate(1L, i->i+1)
				.limit(n)
				.parallel()
				.reduce(0L, Long::sum);
	}
	
	public static long iterativeSum(int n){
		long result = 0;
		for(long l = 1L; l <= n; l++) {
			result += l;
		}
		return result;
	}
	
}
