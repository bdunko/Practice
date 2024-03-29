package utility;

public class Util {

	public static class Pair {
		public int n, m;
		
		public Pair(int n, int m) {
			this.n = n;
			this.m = m;
		}
		
		@Override
		public boolean equals(Object other) {
			if(other == null)
				return false;
			if(!(other instanceof Pair))
				return false;
			
			Pair otherPair = (Pair) other;
			
			return n == otherPair.n && m == otherPair.m;
		}
		
		@Override
		public String toString() {
			return "(" + n + ", " + m + ")";
		}
	}
	
	public static int log2(int x) {
		return log(x, 2);
	}
	
	public static int log(int x, int base) {
		return (int) (Math.log(x) / Math.log(base));
	}
	
	//Returns the array index that the parent of the kth node in a binary tree structure would be at
	//Returns -1 if k is 0 (the root)
	public static int binaryParentFor(int k) {
		if(k == 0)
			return -1;
		
		return (int)((k-1)/2);
	}
	
	//Returns the array index that the left child of the kth node in a binary tree structure would be at
	public static int binaryLeftChildFor(int k) {		
		return (k*2) + 1;
	}
	
	//Returns the array index that the right child of the kth node in a binary tree structure would be at
	public static int binaryRightChildFor(int k) {
		return (k*2) + 2;
	}
	
	//sleeps current thread for given amount of time
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
