package recursiondynamic;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import testing.Test;

public class StackOfBoxes {

	private static class Box {
		int width;
		int height;
		int depth;
		
		public Box(int width, int height, int depth) {
			this.width = width;
			this.height = height;
			this.depth = depth;
		}
		
		public boolean canStackOnTopOf(Box below) {
			return below.height > height && below.width > width && below.depth > depth;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[").append(width).append(",").append(height).append(",").append(depth).append("]");
			return sb.toString();
		}
	}
	
	private static class BoxComparator implements Comparator<Box> {
		@Override
		public int compare(Box box1, Box box2) {
			return box2.height - box1.height;
		}
	}
	
	public static int tallestStack(List<Box> boxes) {
		//create dynamic memo
		HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();
		
		//sort the boxes
		boxes.sort(new BoxComparator());
		
		int tallestStack = -1;
		
		//try with each box as the bottom
		for(int i = 0; i < boxes.size(); i++) {
			tallestStack = Math.max(tallestStack, stackHelper(boxes, i, memo)); 
		}
		
		return tallestStack;
	}
	
	//Calculates the maximum possible height of a stack of boxes beginning with the box at index
	//Assume that boxes is sorted from largest to smallest
	private static int stackHelper(List<Box> boxes, int index, HashMap<Integer, Integer> memo) {
		
		//if we've already calced the largest possible stack from this index, just return it
		if(memo.containsKey(index)) 
			return memo.get(index);
		
		int highestStack = 0;
		
		//get box
		Box box = boxes.get(index);
		
		//attempt to stack all other boxes on top of this box
		for(int i = index+1; i < boxes.size(); i++) {
			Box next = boxes.get(i);
			
			//if this box can go on top, try it
			if(next.canStackOnTopOf(box)) {
				//see if the stack with this new box is largest
				highestStack = Math.max(highestStack, stackHelper(boxes, i, memo));
			}
		}
		
		//return the height of this box, plus the stack of boxes on top of it
		int stackHeight = box.height + highestStack;
		
		//add to memo
		memo.put(index, stackHeight);
		
		return stackHeight;
	}
	
	public static void main(String[] args) {
		Test.header("Stack of Boxes");
		
		List<Box> boxes = new LinkedList<Box>();
		boxes.add(new Box(1, 1, 1));
		boxes.add(new Box(2, 2, 2));
		boxes.add(new Box(3, 3, 3));
		
		Test.equals(tallestStack(boxes), 6); //tallest is 333 222 111 = 6
		
		boxes.add(new Box(2, 6, 2)); 
		Test.equals(tallestStack(boxes), 7); //tallest is 252 111 = 7
		
		boxes.add(new Box(4, 4, 4)); 
		Test.equals(tallestStack(boxes), 10); //tallest is 444 333 222 111 = 10
		
		boxes.clear();
		boxes.add(new Box(3, 3, 3));
		boxes.add(new Box(4, 2, 2));
		boxes.add(new Box(2, 2, 4));
		Test.equals(tallestStack(boxes), 3); //tallest is 333, no boxes stack = 3
		
		boxes.add(new Box(5, 5, 5)); //tallest is 555 333 = 8
		Test.equals(tallestStack(boxes), 8); //tallest is 333, no boxes stack = 3
		
		Test.results();
	}
	
}
