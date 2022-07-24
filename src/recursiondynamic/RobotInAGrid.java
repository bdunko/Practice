package recursiondynamic;

import testing.Test;

//A Robot is in a grid, and wishes to move from the top left to bottom right corner.
//The Robot can only move to the right or down.
//Some grid spaces are impassable.
//Determine if there is a legal path.
public class RobotInAGrid {

	public static boolean findPath(boolean[][] grid) {
		if(grid.length == 0 || grid[0].length == 0)
			return false;
		return pathHelper(grid, 0, 0);
	}
	
	public static boolean pathHelper(boolean[][] grid, int x, int y) {
		if(grid[y][x] == false)
			return false;
		
		if(y == grid.length-1 && x == grid[0].length-1)
			return true;
		
		//try to move right
		if(x+1 < grid[0].length && pathHelper(grid, x+1, y))
			return true;
		
		//try to move down
		if(y+1 < grid.length && pathHelper(grid, x, y+1))
			return true;
		
		return false;
	}
	
	public static void main(String[] args) {
		Test.header("RobotInAGrid");
		
		boolean[][] map1 = {
				{true, true, true},
				{true, true, true},
				{true, true, true}
		};
		Test.assertion(findPath(map1));
		
		boolean[][] map2 = {
				{true},
				{true},
				{true}
		};
		Test.assertion(findPath(map2));
		
		boolean[][] map3 = {
				{true, true, true}
		};
		Test.assertion(findPath(map3));
		
		boolean[][] map4 = {
				{true, true, true},
				{true, true, true},
				{true, true, false}
		};
		Test.assertion(!findPath(map4));
		
		boolean[][] map5 = {
				{false, true, true},
				{true, true, true},
				{true, true, true}
		};
		Test.assertion(!findPath(map5));
		
		boolean[][] map6 = {
				{true, false, true},
				{true, true, true},
				{false, false, true},
				{true, false, true}
		};
		Test.assertion(findPath(map6));
		
		boolean[][] map7 = {
				{true, false, true},
				{true, true, true},
				{false, false, false},
				{true, false, true}
		};
		Test.assertion(!findPath(map7));
		
		boolean[][] map8 = {
				{true, true, true},
				{true, true, true},
				{true, true, false},
				{true, false, true}
		};
		Test.assertion(!findPath(map8));
		
		boolean[][] map9 = {
				{true, true, true, false},
				{true, true, true, true},
				{false, true, false, true},
				{true, false, false, true}
		};
		Test.assertion(findPath(map9));
		
		boolean[][] map10 = {
				{true, true, true, false},
				{true, true, false, true},
				{false, true, false, true},
				{true, false, false, true}
		};
		Test.assertion(!findPath(map10));
		
		Test.results();
	}
}
