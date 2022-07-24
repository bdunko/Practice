package recursiondynamic;

import java.util.LinkedList;
import java.util.List;

import testing.Test;

//Very simple pathfinding from top left to bottom right corner of grid
public class Pathfinding {

	private enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	
	public static List<Direction> findPath(boolean[][] grid) {
		if(grid.length == 0 || grid[0].length == 0)
			return null;
		return findPathHelper(grid, new boolean[grid.length][grid[0].length], 0, 0);
	}
	
	public static List<Direction> findPathHelper(boolean[][] grid, boolean[][] visited, int x, int y) {
		visited[x][y] = true;
		
		if(grid[x][y] == false)
			return null;
		
		if(x == grid.length-1 && y == grid[0].length-1)
			return new LinkedList<Direction>();
		
		//Try to go LEFT
		if(x-1 >= 0 && visited[x-1][y] != true) {
			System.out.println("Try LEFT");
			List<Direction> path = findPathHelper(grid, visited, x-1, y);
			if(path != null) {
				path.add(Direction.LEFT);
				return path;
			}
		}
		
		//Try to go RIGHT
		if(x+1 < grid.length && visited[x+1][y] != true) {
			List<Direction> path = findPathHelper(grid, visited, x+1, y);
			if(path != null) {
				path.add(Direction.RIGHT);
				return path;
			}
		}
		
		//Try to go UP
		if(y-1 >= 0 && visited[x][y-1] != true) {
			List<Direction> path = findPathHelper(grid, visited, x, y-1);
			if(path != null) {
				path.add(Direction.UP);
				return path;
			}
		}
		
		//Try to go DOWN
		if(y+1 < grid[0].length && visited[x][y+1] != true) {
			List<Direction> path = findPathHelper(grid, visited, x, y+1);
			if(path != null) {
				path.add(Direction.DOWN);
				return path;
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Test.header("Pathfinding");
		
		boolean[][] map1 = {
				{true, true, true},
				{true, true, true},
				{true, true, true},
		};
		
		Test.notNull(findPath(map1));
		
		boolean[][] map2 = {
				{true, true, true},
				{true, true, true},
				{true, true, true},
				{true, true, true},
				{true, true, true},
		};
		
		Test.notNull(findPath(map2));
		
		boolean[][] map3 = {
				{true,  false, true,  false},
				{true,  true,  true,  true},
				{false, false, true,  false},
				{true,  true,  true,  true},
				{true,  true,  false, false},
				{true,  true,  true,  true},
		};
		
		Test.notNull(findPath(map3));
		
		boolean[][] map4 = {
				{true,  false, true,  false},
				{true,  false, true,  true},
				{false, false, true,  false},
		};
		
		Test.isNull(findPath(map4));
		
		boolean[][] map5 = {
				{true, true, true},
				{true, true, true},
				{true, true, false},
		};
		
		Test.isNull(findPath(map5));
		
		boolean[][] map6 = {
				{false, true, true},
				{true, true, true},
				{true, true, true},
		};
		
		Test.isNull(findPath(map6));
		
		boolean[][] map7 = {
				{true,  false,  true,  true,  true},
				{true,  false,  true,  false, true},
				{true,  false,  true,  true,  true},
				{true,  false,  true,  false, false},
				{true,  false,  true,  true,  true},
				{true,  true,   true,  false, true},
		};
		
		Test.notNull(findPath(map7));
		
		Test.results();
	}
}
