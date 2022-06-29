package datastructures;

import java.util.Random;

import testing.Test;

@SuppressWarnings("unchecked")
public class BMinHeap<T extends Comparable<T>> extends BHeap<T> {
	
	public BMinHeap() {
		this.heap = (T[]) new Comparable[DEFAULT_SIZE];
		this.size = 0;
	}
	
	public BMinHeap(int size) {
		this.heap = (T[]) new Comparable[size];
		this.size = 0;
	}
	
	//Inserts an element into the min heap.
	//Returns true if successful or false if heap is full.
	@Override
	public boolean insert(T elem) {
		if(size == heap.length)
			return false;
		
		//insert new element at furthest open slot to preserve complete tree propery
		heap[size] = elem;
		
		//'bubble' up
		int current = size;
		int parent = getParentFor(size);
		//while current is less than its parent (and we aren't at the top of the heap), swap them
		while(parent != -1 && heap[current].compareTo(heap[parent]) < 0) {
			swap(current, parent);
			current = parent;
			parent = getParentFor(current);
		}
		
		size++;
		
		return true;
	}
	
	//Fetches the smallest element in the heap, or null if the heap is empty.
	@Override
	public T pop() {
		if(size == 0)
			return null;
		
		//get the minimum value from top of heap
		T min = heap[0];
		
		//move last element in heap to top
		heap[0] = heap[size-1];
		heap[size-1] = null;
		size--;
				
		//'bubble' down
		int current = 0;
		int left = getLeftChildFor(0);
		int right = getRightChildFor(0);
		
		while(left < size || right < size) {
			T currentElem = heap[current];
			T leftElem = heap[left];
			T rightElem = heap[right];
			
			//if current is smaller than both left and right, we've found the spot and exit
			if((left >= size || currentElem.compareTo(leftElem) <= 0) && (right >= size || currentElem.compareTo(rightElem) <= 0))
				break;
			
			//otherwise, if left is smaller, swap with left (and if there is only a left, swap with left)
			if(right >= size || leftElem.compareTo(rightElem) < 0) {
				swap(current, left);
				current = left;
			}
			else { //otherwise swap with right
				swap(current, right);
				current = right;
			}
			
			left = getLeftChildFor(current);
			right = getRightChildFor(current);
		} 
		
		return min;
	}
	
	public static void main(String[] args) {
		Test.header("getParentFor");
		Test.equals(0, BMinHeap.getParentFor(1));
		Test.equals(0, BMinHeap.getParentFor(2));
		Test.equals(1, BMinHeap.getParentFor(3));
		Test.equals(1, BMinHeap.getParentFor(4));
		Test.equals(2, BMinHeap.getParentFor(5));
		Test.equals(2, BMinHeap.getParentFor(6));
		Test.equals(3, BMinHeap.getParentFor(7));
		Test.equals(6, BMinHeap.getParentFor(14));
		Test.equals(13, BMinHeap.getParentFor(28));
		Test.header("getLeftChildFor/getRightChildFor");
		Test.equals(1, BMinHeap.getLeftChildFor(0));
		Test.equals(2, BMinHeap.getRightChildFor(0));
		Test.equals(3, BMinHeap.getLeftChildFor(1));
		Test.equals(4, BMinHeap.getRightChildFor(1));
		Test.equals(5, BMinHeap.getLeftChildFor(2));
		Test.equals(6, BMinHeap.getRightChildFor(2));
		Test.equals(13, BMinHeap.getLeftChildFor(6));
		Test.equals(14, BMinHeap.getRightChildFor(6));
		Test.equals(9, BMinHeap.getLeftChildFor(4));
		Test.equals(7, BMinHeap.getLeftChildFor(3));
		
		Test.header("BHeap");
		BMinHeap<Integer> heap = new BMinHeap<Integer>();
		Test.equals(heap.size(), 0);
		Test.isNull(heap.pop());
		
		heap.insert(1);
		heap.insert(7);
		heap.insert(3);
		heap.insert(9);
		heap.insert(2);
		heap.insert(4);
		heap.insert(8);
		heap.insert(5);
		heap.insert(6);
		
		Test.equals(heap.size(), 9);
		
		for(int i = 1; i <= 9; i++) {
			Test.equals(heap.pop(), i);
		}
		Test.equals(heap.size(), 0);
		Test.isNull(heap.pop());
		
		heap = new BMinHeap<Integer>(100);
		Random r = new Random();
		for(int i = 0; i < 100; i++) {
			heap.insert(r.nextInt());
		}
		int prev = heap.pop();
		while(heap.size() != 0) {
			int curr = heap.pop();
			Test.assertion(prev < curr);
			prev = curr;
		}
		
		Test.results();
	}
}
