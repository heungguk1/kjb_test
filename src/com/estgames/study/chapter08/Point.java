package com.estgames.study.chapter08;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Point {

	private final int x;
	private final int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	public Point moveRightBy(int x){
		return new Point(this.x + x, this.y);
	}
	
	public final static Comparator<Point> compareByXAndThenY = 
			Comparator.comparing(Point::getX).thenComparing(Point::getY);
	
	public static List<Point> moveAllPointsRightBy(List<Point> points, int x) {
		return points.stream()
				.map(p -> new Point(p.getX() + x, p.getY()))
				.collect(Collectors.toList());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
