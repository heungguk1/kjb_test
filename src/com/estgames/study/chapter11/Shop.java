package com.estgames.study.chapter11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class Shop {
	String name;
	
	public Shop(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public String getPrice(String product){
		double price = calculatePrice(product);
		Discount.Code code = Discount.Code.GOLD;
		return String.format("%s:%.2f:%s", name, price, code);
	}
	
	public void delay(){
		try {
			Thread.sleep(1000L);
		} catch(InterruptedException e){
			throw new RuntimeException(e);
		}
	}
	
	public double calculatePrice(String product){
		delay();
		return 0.23432432 * product.charAt(0) + product.charAt(1);
	}
	
	public Future<Double> getPriceAsync(String product) {
		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
		/*CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		new Thread(() -> {
			try {
				double price = calculatePrice(product);
				futurePrice.complete(price);
			} catch (Exception e) {
				futurePrice.completeExceptionally(e);
			}
		}).start();
		return futurePrice;*/
	}
	
	public static void main(String...args) {
		/*Shop shop = new Shop("BestPrice");
		long start = System.nanoTime();
		Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Invocation returned after " + invocationTime + " msecs");
		
		doSometingElse();
		try {
			double price = futurePrice.get();
			System.out.printf("Price is %.2f%n", price);
		} catch(Exception e){
			throw new RuntimeException(e);
		}
		long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Price returned after " + retrievalTime + " msecs");*/
		
		List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
				new Shop("LetsSaveBig"),
				new Shop("MyFavoriteShop"),
				new Shop("BuyItAll"));
	}
	
	public List<String> findPrices(String product) {
		List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
				new Shop("LetsSaveBig"),
				new Shop("MyFavoriteShop"),
				new Shop("BuyItAll"));
		/*return shops.stream()
				.map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
				.collect(Collectors.toList());*/
		/*return shops.stream()
				.map(shop -> shop.getPrice(product))
				.map(Quote::parse)
				.map(Discount::applyDiscount)
				.collect(Collectors.toList());*/
		Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
				new ThreadFactory(){
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setDaemon(true);
				return t;
			}
		});
		List<CompletableFuture<String>> priceFutures = 
				shops.stream()
						.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
						.map(future -> future.thenApply(Quote::parse))
						.map(future -> future.thenCompose(quote -> 
							CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
						.collect(Collectors.toList());
		return priceFutures.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
	}

	private static void doSometingElse() {
		// TODO Auto-generated method stub
		
	}
}
