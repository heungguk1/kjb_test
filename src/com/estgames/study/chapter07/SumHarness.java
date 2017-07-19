package com.estgames.study.chapter07;

import java.util.function.Function;

public class SumHarness {
	
	public static void main(String[] args){
		SumHarness harness = new SumHarness();
		//System.out.println("Sequential sum done in : " + harness.measureSumPerf(C01ParallelStream::sequentialSum, 10_000_000) + " msecs");
		//System.out.println("Iterative sum done in : " + harness.measureSumPerf(C01ParallelStream::iterativeSum, 10_000_000) + " msecs");
		//System.out.println("Parallel sum done in : " + harness.measureSumPerf(C01ParallelStream::parallelSum, 10_000_000) + " msecs");
		
		//System.out.println("Ranged sum done in : " + harness.measureSumPerf(C01ParallelStream::rangedSum, 10_000_000) + " msecs");
		//System.out.println("Ranged Parallel sum done in : " + harness.measureSumPerf(C01ParallelStream::parallelRangedSum, 10_000_000) + " msecs");
		
		//System.out.println("SideEffect Parallel sum done in : " + harness.measureSumPerf(C01ParallelStream::sideEffectParallelSum, 10_000_000) + " msecs");
		
		System.out.println("ForkJoin sum done in : " + harness.measureSumPerf(C02ForkJoinFramework::forkJoinSum, 10_000_000) + " msecs");
	}

	public long measureSumPerf(Function<Long, Long> adder, long n){
		long fastest = Long.MAX_VALUE;
		for(int i = 0; i < 10; i++){
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Result:" + sum + ", Duration:" + duration);
			if(fastest > duration) fastest = duration;
		}
		return fastest;
	}
	
}
