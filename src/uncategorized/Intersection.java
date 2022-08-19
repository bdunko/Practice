package uncategorized;

import testing.Test;

//calculate the point of intersection given the start and end of two line segments, if there is one
public class Intersection {

	private static class Point {
		public double x, y;
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static class Line {
		public double slope, yintercept;
		public Point start, end;
		
		public Line(Point start, Point end) {
			this.start = start;
			this.end = end;
			this.slope = (end.y - start.y) / (end.x - start.x); //y1-y2 = m(x1-x2); m = (x1-x2)/(y1-y2)
			this.yintercept = end.y - slope * end.x; //y = mx + b, 0 = mx + b - y, -b = mx -y; b = -mx + y= y - mx
		}
	}
	
	private static void swap(Point a, Point b) {
		double xtemp = a.x;
		double ytemp = a.y;
		a.x = b.x;
		a.y = b.y;
		b.x = xtemp;
		b.y = ytemp;
	}
	
	private static boolean isBetween(double start, double middle, double end) {
		return middle >= start && middle <= end;
	}
	
	private static boolean isBetween(Point start, Point middle, Point end) {
		return isBetween(start.x, middle.x, end.x) && isBetween(start.y, middle.y, end.y);
	}
	
	private static Point getIntersection(Point start1, Point end1, Point start2, Point end2) {
		
		//reorganize points such that start is before end and point 1 is before point 2
		if(start1.x > end1.x)
			swap(start1, end1);
		if(start2.x > end2.x)
			swap(start2, end2);
		if(start1.x > start2.x) {
			swap(start1, start2);
			swap(end1, end2);
		}
		
		//make lines
		Line line1 = new Line(start1, end1);
		Line line2 = new Line(start2, end2);
		
		//if parallel lines, check if same line
		if(line1.slope == line2.slope) {
			//if shared intercept and part of line overlaps
			if(line1.yintercept == line2.yintercept && isBetween(start1, start2, end1)) 
				return start2;
			return null; //not same line
		}
		
		//otherwise, compute point of intersection
		//y = mx + b and y = Mx + B
		//mx + b = Mx + B
		//mx = Mx + B - b
		//mx - Mx = B - b
		//(m-M)x = B - b
		//x = (B-b)/(m-M)
		double x = (line2.yintercept - line1.yintercept) / (line1.slope - line2.slope);
		//plug back into either line for y
		//y = mx + b
		double y = line1.slope * x + line1.yintercept;
		
		Point intersection = new Point(x, y);
		
		//now check if intersection is within both lines
		if(isBetween(line1.start, intersection, line1.end) &&
				isBetween(line2.start, intersection, line2.end))
			return intersection;
		
		return null;
	}
	
	public static void main(String[] args) {
		Test.header("Intersection");
		
		Point result = getIntersection(new Point(0, 0), new Point(3, 6), new Point(-1, 4), new Point(4, 4));
		Test.equals(result.x, 2.0D);
		Test.equals(result.y, 4.0D);
		
		result = getIntersection(new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(4, 6));
		Test.isNull(result);
		
		result = getIntersection(new Point(-1, -1), new Point(10, 10), new Point(8, 8), new Point(12, 12));
		Test.notNull(result);
		Test.assertion(result.x >= 8.0D && result.x <= 12.0D);
		Test.assertion(result.y >= 8.0D && result.y <= 12.0D);
		
		Test.results();
	}
}
