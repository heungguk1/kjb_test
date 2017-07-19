package com.estgames.study.chapter07;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

	private static final long serialVersionUID = -7913434811694173197L;
	
	private final long[] numbers; // 더할 숫자 배열
	private final int start;
	private final int end; // 처리할 배열의 초기, 최종 위치
	public static final long THRESHOLD = 10_000; // 이보다 작은 태스크는 분할할 수 없다.
	
	public ForkJoinSumCalculator(long[] numbers) { // 메인 테스트 생성
		this(numbers, 0, numbers.length);
	}
	private ForkJoinSumCalculator(long[] numbers, int start, int end) { // 서브 태스크 생성
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Long compute(){
		int length = end-start; // 더할 배열의 길이
		if(length <= THRESHOLD){ // 기준 값보다 작거나 같으면
			return computeSequentially(); // 순차적으로 결과 계산
		}
		
		ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2); // 절반크리고 서브태스크 생성
		leftTask.fork(); // ForkJoinPool의 다른 스레드로 새로 생성한 태스크 비동기로 실행
		ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end); // 서브태스크 생성

		Long rightResult = rightTask.compute(); // 두번째 서브태스크 동기 실행
		Long leftResult = leftTask.join(); // 첫번째 서브태스크 결과 읽거나 기다림
		return leftResult + rightResult; // 두 태스크 결과 합침
	}
	
	private long computeSequentially(){ // 더 분할할 수 없을 때 결과를 계산해주는 알고리즘
		long sum = 0;
		for(int i = start; i < end; i++) {
			sum += numbers[i];
		}
		return sum;
	}
	
}
