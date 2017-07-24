package com.estgames.study.chapter08;

import static org.junit.Assert.*;

import org.junit.Test;

public class C03LambdaTesting {

	@Test
	public void test() {
		Point p1 = new Point(5, 5);
		Point p2 = p1.moveRightBy(10);
		
		assertEquals(15, p2.getX());
		assertEquals(5, p2.getY());
	}

}
