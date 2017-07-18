package com.estgames.study.chapter06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;

public class PrimeNumbersCollector implements 	Collector<Integer, 
												Map<Boolean, List<Integer>>, 
												Map<Boolean, List<Integer>>> {

	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		/*return () -> {
			Map<Boolean, List<Integer>> result = new HashMap<Boolean, List<Integer>>();
			result.put(true, new ArrayList<Integer>());
			result.put(false, new ArrayList<Integer>());
			return result;
		};*/
		return () -> new HashMap<Boolean, List<Integer>>() {{
			put(true, new ArrayList<Integer>());
			put(false, new ArrayList<Integer>());
		}};
	}

	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (map, ele) -> 
			map.get(isPrime(map.get(true), ele))
			.add(ele);
	}

	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (map1, map2) -> {
			map1.get(true).addAll(map2.get(true));
			map1.get(false).addAll(map2.get(false));
			return map1;
		};
	}

	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
	}
	
	public boolean isPrime(List<Integer> prime, int value) {
		return takeWhile(prime, i -> i*i <= value)
				.stream()
				.noneMatch(i -> value % i == 0);
	}
	
	public <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
		int i = 0;
		for(A item : list){
			if(!p.test(item))
				return list.subList(0, i);
		}
		return list;
	}

}
