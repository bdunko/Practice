package uncategorized;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import testing.Test;

//Given a grid representing a plot of land, where values above 0 represent land and 0 represents water, report the size
//of each pond in the plot. 
//A pond is a group of water cells connected orthogonally and/or diagonally.
public class PondSizes {

	public static List<Integer> pondSizes(int[][] grid) {
		List<Integer> ponds = new ArrayList<Integer>();
		
		for(int x = 0; x < grid.length; x++) {
			for(int y = 0; y < grid[x].length; y++) {
				int pond = pondSize(grid, x, y);
				if(pond != 0)
					ponds.add(pond);
			}
		}
		
		return ponds;
	}
	
	private static int pondSize(int[][] grid, int x, int y) {
		if(x < 0 || x >= grid.length)
			return 0;
		if(y < 0 || y >= grid[x].length)
			return 0;
		if(grid[x][y] != 0)
			return 0;
		
		int size = 1;
		grid[x][y] = 1;
		
		//check all 8 directions
		//left/right
		size += pondSize(grid, x+1, y); 
		size += pondSize(grid, x-1, y); 
		//up/down
		size += pondSize(grid, x, y-1); 
		size += pondSize(grid, x, y+1); 
		//diagonals
		size += pondSize(grid, x+1, y+1); 
		size += pondSize(grid, x+1, y-1);
		size += pondSize(grid, x-1, y+1); 
		size += pondSize(grid, x-1, y-1);
		
		return size;
	}
	
	public static void main(String[] args) {
		Test.header("PondSizes");
		
		int[][][] inputs = {
				{
					{0, 2, 1, 0},
					{0, 1, 0, 1},
					{1, 1, 0, 1},
					{0, 1, 0, 1}
				},
				{
					{0, 0, 0},
					{0, 0, 1},
					{0, 1, 0}
				},
				{
					{0, 5, 0},
					{5, 0, 5},
					{0, 5, 0},
				}, 
				{
					{0, 1, 1, 0},
					{1, 1, 0, 0},
					{0, 1, 1, 1},
				}
		};
		

		List<List<Integer>> answers = new ArrayList<List<Integer>>();
		List<Integer> ans1 = new LinkedList<Integer>();
		ans1.add(2);
		ans1.add(4);
		ans1.add(1);
		List<Integer> ans2 = new LinkedList<Integer>();
		ans2.add(7);
		List<Integer> ans3 = new LinkedList<Integer>();
		ans3.add(5);
		List<Integer> ans4 = new LinkedList<Integer>();
		ans4.add(1);
		ans4.add(1);
		ans4.add(3);
		answers.add(ans1);
		answers.add(ans2);
		answers.add(ans3);
		answers.add(ans4);
		
		for(int i = 0; i < inputs.length; i++) {
			int[][] input = inputs[i];
			List<Integer> expected = answers.get(i);
			List<Integer> solution = pondSizes(input);
			
			for(Integer pond : solution) {
				if(expected.remove(pond) == false)
					Test.fail("Pond of size " + pond + " in solution, but doesn't exist.");
			}
			
			for(Integer pondNotRemoved : expected) {
				Test.fail("Pond of size " + pondNotRemoved + " expected, but not found.");
			}
			
			if(expected.size() == 0)
				Test.success("Ponds correct.");
		}
		
		Test.results();
	}
}
