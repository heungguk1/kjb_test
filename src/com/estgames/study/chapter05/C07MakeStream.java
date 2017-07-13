package com.estgames.study.chapter05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class C07MakeStream {

	public static void main(String[] args){
		Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action"); // Stream.of를 이용하여 스트림을 만듦
		stream.map(String::toUpperCase).forEach(System.out::println);
		Stream<String> emptyStream = Stream.empty(); // 스트림 비움
		
		int[] numbers = {2,3,4,5,7,11,13};
		int sum = Arrays.stream(numbers).sum();
		
		long uniqueWorlds = 0;
		try(Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
			uniqueWorlds = lines.flatMap(line -> Arrays.stream(line.split(" ")))
					.distinct()
					.count();
		} catch(IOException e){}
		
		Stream.iterate(0,  n->n+2)
		.limit(5)
		.forEach(System.out::println);
		
		Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
		.limit(20)
		.forEach(n -> System.out.println("(" + n[0] + ", " + n[1] + ")"));
		
		Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
		.limit(20)
		.map(n -> n[0])
		.forEach(System.out::println);
		
		Stream.generate(Math::random)
		.limit(5)
		.forEach(System.out::println);
		
		IntSupplier fib = new IntSupplier(){
			private int previous = 0;
			private int current = 1;
			public int getAsInt(){
				int oldPrevious = this.previous;
				int nextValue = this.previous + this.current;
				this.previous = this.current;
				this.current = nextValue;
				return oldPrevious;
			}
		};
		IntStream.generate(fib).limit(10).forEach(System.out::println);
	}
	
}
