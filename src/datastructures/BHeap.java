package datastructures;

import java.util.Arrays;

public abstract class BHeap<T extends Comparable<T>> {
	
	public static int DEFAULT_SIZE = 1024;
	protected T[] heap;
	protected int size;
	
	protected static int getParentFor(int k) {
		if(k == 0)
			return -1;
		
		return (int)((k-1)/2);
	}
	
	protected static int getLeftChildFor(int k) {		
		return (k*2) + 1;
	}
	
	protected static int getRightChildFor(int k) {
		return (k*2) + 2;
	}
	
	protected void swap(int node1, int node2) {
		T temp = heap[node1];
		heap[node1] = heap[node2];
		heap[node2] = temp;
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(heap);
	}
	
	public abstract T pop();
	public abstract boolean insert(T elem);
}
