package com.estgames.study.chapter07;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class C03Spliterator {
	
	public static void main(String[] args){
		C03Spliterator main = new C03Spliterator();
		final String s = " Nel  mezzo del cammin    di mostra vita "
				+ "mi    ritrovai in una  selva oscura "
				+ " ch la    dritta via era  smarrita ";
		System.out.println("FOund " + main.countWordsIteratively(s) + " words");
		
		Stream<Character> stream = IntStream.range(0, s.length())
				.mapToObj(s::charAt);
		System.out.println("FOund " + main.countWords(stream) + " words");
		Stream<Character> streamParallel = IntStream.range(0, s.length())
				.mapToObj(s::charAt)
				.parallel();
		System.out.println("FOund " + main.countWords(streamParallel) + " words");
	}

	public int countWordsIteratively(String s){
		int counter = 0;
		boolean lastSpace = true;
		for(char c : s.toCharArray()) {
			if(Character.isWhitespace(c)) {
				lastSpace = true;
			} else {
				if(lastSpace) {
					counter ++;
				}
				lastSpace = false;
			}
		}
		return counter;
	}
	
	public int countWords(Stream<Character> stream) {
		WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
				WordCounter::accumulate,
				WordCounter::combine);
		return wordCounter.getCounter();
	}
	
}
