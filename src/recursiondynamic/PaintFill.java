package recursiondynamic;

import testing.Test;

//Implement the 'paint fill' operation of an image editing program
//  User has a chosen color and clicks a given pixel
//  Color all connecting pixels of the same color as the original pixel
public class PaintFill {
	
	private static class Color {
		private int r, g, b;
		
		public Color(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("(").append(r).append(", ").append(g).append(", ").append(b).append(")");
			return sb.toString();
		}
		
		@Override
		public boolean equals(Object o) {
			if(o == null)
				return false;
			if(!(o instanceof Color))
				return false;
			Color c = (Color)(o);
			
			return c.r == r && c.b == b & c.g == g;
		}
	}

	public static void paintFill(Color[][] screen, Color fillColor, int x, int y) {
		if(x < 0 || y < 0 || x >= screen.length || y >= screen[x].length)
			return;
		
		fillHelper(screen, fillColor, screen[x][y], x, y);
	}
	
	private static void fillHelper(Color[][] screen, Color fillColor, Color startColor, int x, int y) {
		if(x < 0 || y < 0 || x >= screen.length || y >= screen[x].length) //pixel outside of screen
			return;
		
		if(screen[x][y] == fillColor) //pixel already equals fill color
			return;
		
		if(screen[x][y] == startColor) {
			screen[x][y] = fillColor; //color this pixel
			//attempt to color adjacent pixels
			fillHelper(screen, fillColor, startColor, x-1, y);
			fillHelper(screen, fillColor, startColor, x+1, y);
			fillHelper(screen, fillColor, startColor, x, y-1);
			fillHelper(screen, fillColor, startColor, x, y+1);
		}		
	}
	
	private static Color[][] createScreen(int width, int height, Color base) {
		Color[][] screen = new Color[width][height];
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				screen[x][y] = base;
			}
		}
		
		return screen;
	}
	
	private static String screenToString(Color[][] screen) {
		StringBuilder sb = new StringBuilder();
		
		for(int x = 0; x < screen.length; x++) {
			for(int y = 0; y < screen[x].length; y++) {
				sb.append(screen[x][y]);
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Test.header("PaintFill");
		
		Color[][] screen = createScreen(5, 5, new Color(0, 0, 0));
		Test.equals(screenToString(screen), 
				  "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n");
		
		paintFill(screen, new Color(1, 1, 1), -1, 0);
		paintFill(screen, new Color(1, 1, 1), 0, -1);
		paintFill(screen, new Color(1, 1, 1), 100, 0);
		paintFill(screen, new Color(1, 1, 1), 0, 100);
		paintFill(screen, new Color(1, 1, 1), -1, -1);
		paintFill(screen, new Color(1, 1, 1), 100, 100);
		
		Test.equals(screenToString(screen), 
				  "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n");
		
		//color whole screen
		screen = createScreen(5, 5, new Color(0, 0, 0));
		paintFill(screen, new Color(8, 7, 6), 0, 0);
		Test.equals(screenToString(screen), 
				  "(8, 7, 6)(8, 7, 6)(8, 7, 6)(8, 7, 6)(8, 7, 6)\n"
				+ "(8, 7, 6)(8, 7, 6)(8, 7, 6)(8, 7, 6)(8, 7, 6)\n"
				+ "(8, 7, 6)(8, 7, 6)(8, 7, 6)(8, 7, 6)(8, 7, 6)\n"
				+ "(8, 7, 6)(8, 7, 6)(8, 7, 6)(8, 7, 6)(8, 7, 6)\n"
				+ "(8, 7, 6)(8, 7, 6)(8, 7, 6)(8, 7, 6)(8, 7, 6)\n");
		
		//color part of screen
		screen = createScreen(5, 5, new Color(0, 0, 0));
		screen[2][0] = new Color(1, 1, 1);
		screen[2][1] = new Color(1, 1, 1);
		screen[2][2] = new Color(1, 1, 1);
		screen[2][3] = new Color(1, 1, 1);
		screen[2][4] = new Color(1, 1, 1);
		
		Test.equals(screenToString(screen), 
				  "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(1, 1, 1)(1, 1, 1)(1, 1, 1)(1, 1, 1)(1, 1, 1)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n");
		
		paintFill(screen, new Color(1, 1, 1), 1, 1);
		Test.equals(screenToString(screen), 
				  "(1, 1, 1)(1, 1, 1)(1, 1, 1)(1, 1, 1)(1, 1, 1)\n"
				+ "(1, 1, 1)(1, 1, 1)(1, 1, 1)(1, 1, 1)(1, 1, 1)\n"
				+ "(1, 1, 1)(1, 1, 1)(1, 1, 1)(1, 1, 1)(1, 1, 1)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n");
		
		paintFill(screen, new Color(2, 2, 2), 4, 3);
		Test.equals(screenToString(screen), 
				  "(1, 1, 1)(1, 1, 1)(1, 1, 1)(1, 1, 1)(1, 1, 1)\n"
				+ "(1, 1, 1)(1, 1, 1)(1, 1, 1)(1, 1, 1)(1, 1, 1)\n"
				+ "(1, 1, 1)(1, 1, 1)(1, 1, 1)(1, 1, 1)(1, 1, 1)\n"
				+ "(2, 2, 2)(2, 2, 2)(2, 2, 2)(2, 2, 2)(2, 2, 2)\n"
				+ "(2, 2, 2)(2, 2, 2)(2, 2, 2)(2, 2, 2)(2, 2, 2)\n");
		
		
		//color only single pixel
		screen = createScreen(5, 5, new Color(0, 0, 0));
		screen[3][4] = new Color(5, 5, 5);
		screen[4][3] = new Color(5, 5, 5);
		
		Test.equals(screenToString(screen), 
				  "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(5, 5, 5)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(5, 5, 5)(0, 0, 0)\n");
		
		paintFill(screen, new Color(255, 255, 255), 4, 4);
		Test.equals(screenToString(screen), 
				  "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(0, 0, 0)(5, 5, 5)\n"
				+ "(0, 0, 0)(0, 0, 0)(0, 0, 0)(5, 5, 5)(255, 255, 255)\n");
		
		Test.results();
	}
}
