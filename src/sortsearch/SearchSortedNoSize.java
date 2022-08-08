package sortsearch;

import testing.Test;

public class SearchSortedNoSize {

	private static class Listy {
		private int[] elems;
		
		public Listy(int[] elems) {
			this.elems = elems;
		}
		
		public int elementAt(int i) {
			if(i < 0 || i >= elems.length)
				return -1;
			return elems[i];
		}
	}
	
	private static int searchListy(Listy listy, int target) {
		return binSearchListy(listy, 0, Integer.MAX_VALUE, target);
	}
	
	private static int binSearchListy(Listy listy, int min, int max, int target) {
		if(min > max)
			return -1;
		
		int middle = (min + max) / 2;
		
		int elem = listy.elementAt(middle);
		
		if(target == elem)
			return middle;
		
		//if outside of list range, or within range and target is to the left, search left
		if(elem == -1 || target < elem) {
			return binSearchListy(listy, min, middle-1, target);
		} else { //otherwise target is to the right
			return binSearchListy(listy, middle+1, max, target);
		}
	}
		
	
	public static void main(String[] args) {
		Test.header("SearchSortedNoSize");
		
		int[] l1 = {1, 3, 6, 8, 12, 15, 17, 20, 23, 27, 30, 40, 45, 46, 51, 57, 64, 90, 102};
		Listy listy = new Listy(l1);
		
		for(int i = 0; i < l1.length; i++) {
			int searchFor = listy.elementAt(i);
			Test.equals(searchListy(listy, searchFor), i);
		}
		
		Test.equals(searchListy(listy, -23), -1);
		Test.equals(searchListy(listy, 2), -1);
		Test.equals(searchListy(listy, 19), -1);
		Test.equals(searchListy(listy, 105), -1);
		
		Test.results();
	}
	
}
