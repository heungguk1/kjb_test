package com.estgames.study.chapter07;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

	private static final long serialVersionUID = -7913434811694173197L;
	
	private final long[] numbers; // ���� ���� �迭
	private final int start;
	private final int end; // ó���� �迭�� �ʱ�, ���� ��ġ
	public static final long THRESHOLD = 10_000; // �̺��� ���� �½�ũ�� ������ �� ����.
	
	public ForkJoinSumCalculator(long[] numbers) { // ���� �׽�Ʈ ����
		this(numbers, 0, numbers.length);
	}
	private ForkJoinSumCalculator(long[] numbers, int start, int end) { // ���� �½�ũ ����
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Long compute(){
		int length = end-start; // ���� �迭�� ����
		if(length <= THRESHOLD){ // ���� ������ �۰ų� ������
			return computeSequentially(); // ���������� ��� ���
		}
		
		ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2); // ����ũ���� �����½�ũ ����
		leftTask.fork(); // ForkJoinPool�� �ٸ� ������� ���� ������ �½�ũ �񵿱�� ����
		ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end); // �����½�ũ ����

		Long rightResult = rightTask.compute(); // �ι�° �����½�ũ ���� ����
		Long leftResult = leftTask.join(); // ù��° �����½�ũ ��� �аų� ��ٸ�
		return leftResult + rightResult; // �� �½�ũ ��� ��ħ
	}
	
	private long computeSequentially(){ // �� ������ �� ���� �� ����� ������ִ� �˰���
		long sum = 0;
		for(int i = start; i < end; i++) {
			sum += numbers[i];
		}
		return sum;
	}
	
}
