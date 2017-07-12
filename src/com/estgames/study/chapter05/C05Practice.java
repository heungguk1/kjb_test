package com.estgames.study.chapter05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.estgames.study.chapter05.model.Trader;
import com.estgames.study.chapter05.model.Transaction;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class C05Practice {

	public static void main(String[] args){
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		List<Transaction> transactions = Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000),
				new Transaction(brian, 2011, 300),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950));
		
		List<Transaction> no1 = transactions.stream()
				.filter(t -> t.getYear() == 2011)
				.sorted(comparing(Transaction::getValue))
				.collect(toList());
		
		List<String> no2 = transactions.stream()
				.map(t -> t.getTrader().getCity())
				.distinct()
				.collect(toList());
		
		List<Trader> no3 = transactions.stream()
				.map(t -> t.getTrader())
				.filter(t -> t.getCity().equals("Cambridge"))
				.distinct()
				.sorted(comparing(Trader::getName))
				.collect(toList());
		
		List<Trader> no4 = transactions.stream()
				.map(Transaction::getTrader)
				.distinct()
				.sorted(comparing(Trader::getName))
				.collect(toList());
		
		String no4_1 = transactions.stream()
				.map(t -> t.getTrader().getName())
				.distinct()
				.sorted()
				.reduce("", (n1, n2) -> n1 + n2);
		
		boolean no5 = transactions.stream()
				.anyMatch(t -> t.getTrader().getCity().equals("Milan"));
		
		transactions.stream()
		.filter(t -> t.getTrader().getCity().equals("Cambridge"))
		.forEach(System.out::println);
		
		Optional<Integer> no7 = transactions.stream()
				.map(Transaction::getValue)
				.reduce(Integer::max);
		
		Optional<Integer> no8 = transactions.stream()
				.map(Transaction::getValue)
				.reduce(Integer::min);
	}
	
}
