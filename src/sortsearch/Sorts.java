package sortsearch;

import java.util.Arrays;
import java.util.Random;

import testing.Test;

public class Sorts {

	public static <T extends Comparable<T>> void bubbleSort(T[] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array.length-1; j++) {
				if(array[j].compareTo(array[j+1]) > 0) {
					T temp = array[j+1];
					array[j+1] = array[j];
					array[j] = temp;
				} 
			}
		}
	}
	
	public static <T extends Comparable<T>> void insertionSort(T[] array) {
		//iterate over array
		for(int i = 0; i < array.length; i++) {
			T current = array[i];
			
			//now iterate backwards over sorted sub array until finding spot to place current element
			int j = i - 1;
			//while sorted element is larger, move down (and move that element up an index to make space for new element)
			while(j >= 0 && array[j].compareTo(current) > 0) {
				array[j+1] = array[j];
				j = j - 1;
			}
			//place new element into sorted subarray
			array[j+1] = current;
		}
	}
	
	public static <T extends Comparable<T>> void selectionSort(T[] array) {
		//find smallest element and swap it with element at current index
		for(int i = 0; i < array.length; i++) {
			T min = array[i];
			int minIndex = i;
			
			for(int j = i+1; j < array.length; j++) {
				if(array[j].compareTo(min) < 0) {
					min = array[j];
					minIndex = i;
				}
			}
			
			array[minIndex] = array[i];
			array[i] = min;
		}
	}
	
	
	
	public static <T extends Comparable<T>> void mergeSort(T[] array) {
		//can improve performance slightly by creating temp array here
		//and passing it down, reusing it for each merge
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Comparable[array.length];
		
		mergeSort(array, 0, array.length-1, temp);
	}
	
	private static <T extends Comparable<T>> void mergeSort(T[] array, int start, int end, T[] temp) {
		if(start >= end) //base case
			return;
		
		int middle = (start + end) / 2;
		
		mergeSort(array, start, middle, temp); //sort array from start to middle
		mergeSort(array, middle+1, end, temp); //sort array from middle+1 to end
		merge(array, start, middle, end, temp); //merge the sorted arrays
	}
	
	//merges subarray from start-middle with subarray from (middle+1)-end, inclusive
	private static <T extends Comparable<T>> void merge(T[] array, int start, int middle, int end, T[] temp) {
		//copy over elements from array
		for(int i = start; i <= end; i++) 
			temp[i] = array[i];
		
		//perform the merge
		int leftSubarray = start;
		int rightSubarray = middle+1;
		int mergeIndex = start;
		
		//while both are in bounds, merge
		while(leftSubarray <= middle && rightSubarray <= end) {
			//determine if we should merge element from left or right, whichever is smaller
			T leftElem = temp[leftSubarray];
			T rightElem = temp[rightSubarray];
			
			//if left is smaller (or equal), merge it
			if(leftElem.compareTo(rightElem) <= 0) {
				array[mergeIndex] = leftElem;
				leftSubarray++;
			} else { //if right is smaller, merge it
				array[mergeIndex] = rightElem;
				rightSubarray++;
			}
			mergeIndex++;
		}
		
		//if there are any leftover elements in left subarray, merge them in
		while(leftSubarray <= middle) {
			array[mergeIndex] = temp[leftSubarray];
			leftSubarray++;
			mergeIndex++;
		}

		//note - not needed
		//if there are any leftover elements in right subarray, merge them in
//		while(rightSubarray <= end) {
//			array[mergeIndex] = temp[rightSubarray];
//			rightSubarray++;
//			mergeIndex++;
//		}

	}
	
	public static <T extends Comparable<T>> void quickSort(T[] array) {
		quickSort(array, 0, array.length-1);
	}
	
	private static <T extends Comparable<T>> void quickSort(T[] array, int left, int right) {
		if(left < right) {
			int index = partition(array, left, right);
			quickSort(array, left, index); //sort left of pivot
			quickSort(array, index+1, right); //sort right of pivot
		}
	}
	
	//Sorts the subarray from left to right such that all elements less than the element at returned index are to the left
	//and all elements greater than the element at returned index are to the right
	private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
		T pivot = array[(left + right) / 2]; //select pivot point
		
		//rearrange all elements such that elements smaller than the pivot end up to the left
		//and elements larger than pivot end up on the right
		while(left <= right) {
			//find an element on the left that should be on the right
			while(pivot.compareTo(array[left]) > 0)
				left++;
			
			//find an element on the right that should be on the left
			while(pivot.compareTo(array[right]) < 0)
				right--;
			
			//swap them
			if(left <= right) {
				T temp = array[left];
				array[left] = array[right];
				array[right] = temp;
				left++;
				right--;
			}
		}
		
		//return the ending index of the pivot value
		return left-1;
	}

	private static int max(int[] array) {
		if(array.length == 0)
			return Integer.MIN_VALUE;
		int max = array[0];
		
		for(int i = 1; i < array.length; i++)
			max = Math.max(array[i], max);
		
		return max;
	}
	
	private static int min(int[] array) {
		if(array.length == 0)
			return Integer.MAX_VALUE;
		int min = array[0];
		
		for(int i = 1; i < array.length; i++)
			min = Math.min(array[i], min);
		
		return min;
	}
	
	public static void countingSort(int[] array) {
		if(array.length == 0)
			return;
		
		countingSort(array, min(array), max(array));
	}
	
	public static void countingSort(int[] array, int min, int max) {
		//create array with one slot per different possible value
		int[] counts = new int[(max-min) + 1];
		Arrays.fill(counts, 0);
		
		//populate counts array
		for(int num : array) {
			int index = num - min;
			counts[index]++;
		}
		
		//modify counts array so that counts[i] stores the index where i should go in the sorted array
		int numBefore = 0;
		for(int i = 0; i < counts.length; i++) {
			int temp = counts[i];
			counts[i] = numBefore;
			numBefore += temp;
		}
		
		//create empty sorted array, temp storage
		int[] sorted = new int[array.length];
		
		//iterate over each element in original unsorted array
		//for each, look up the index to place it at using counts
		//then increment counts so that the next time we look up
		//that element, it goes to the next slot in the output array
		for(int i = 0; i < array.length; i++) {
			int elem = array[i];
			sorted[counts[elem-min]] = elem;
			counts[elem-min]++;
		}
		
		//copy over the output array back into the input array
		for(int i = 0; i < array.length; i++) {
			array[i] = sorted[i];
		}
	}
	
	public static void radixCountingSort(int[] array, int n, int exp) {
        int counts[] = new int[10];
        Arrays.fill(counts, 0);
  
        for(int i = 0; i < n; i++) {
        	int digit = (array[i]/exp) % 10; //get digit
            counts[digit]++; //increment count for that digit
        }
  
        //modify counts array so that counts[i] stores the index where i should go in the sorted array
        int numBefore = 0;
        for(int i = 0; i < 10; i++) {
        	int temp = counts[i];
            counts[i] = numBefore;
            numBefore += temp;
        }
  
        // Build the output array
        int sorted[] = new int[n]; //output array
        
        for(int i = 0; i < array.length; i++) {
        	int elem = array[i];
        	int elemDigit = (elem / exp) % 10;
        	sorted[counts[elemDigit]] = elem;
        	counts[elemDigit]++;
        }
  
        //copy back to original array
        for(int i = 0; i < n; i++)
        	array[i] = sorted[i];
	  }
	
	public static void radixSort(int[] array) {
		int max = max(array);
		for(int place = 1; max / place > 0; place *= 10)
			radixCountingSort(array, array.length, place);
	}
	
	private static <T extends Comparable<T>> void verify(T[] array) {
		boolean success = true;
		
		T current = array[0];
		for(int i = 1; i < array.length; i++) {
			if(current.compareTo(array[i]) > 0) {
				success = false;
				break;
			}
			current = array[i];
		}
		
		if(success)
			Test.success("Array was sorted.");
		else
			Test.fail("Array was not sorted.");
	}
	
	private static void verify(int[] array) {
		verify(intToInteger(array));
	}
	
	private static Integer[] generateUnsorted(int size) {
		Integer[] a = new Integer[size];
		Random r = new Random();
		
		for(int i = 0; i < size; i++) {
			a[i] = r.nextInt(1000);
		}
		
		return a;
	}
	
	private static int[] integerToInt(Integer[] array) {
		int[] a = new int[array.length];
		for(int i = 0; i < array.length; i++) 
			a[i] = array[i];
		return a;
	}
	
	private static Integer[] intToInteger(int[] array) {
		Integer[] a = new Integer[array.length];
		for(int i = 0; i < array.length; i++)
			a[i] = array[i];
		return a;
	}
	
	public static void main(String[] args) {
		Test.header("Sorts");
		
		Integer[] a0 = {1, 2, 3, 4, 5, 5, 6, 7, 7, 8, 9};
		verify(a0);
		
		Integer[] a1 = generateUnsorted(250);
		bubbleSort(a1);
		verify(a1);
		
		Integer[] a2 = generateUnsorted(250);
		insertionSort(a2);
		verify(a2);
		
		Integer[] a3 = generateUnsorted(250);
		selectionSort(a3);
		verify(a3);
		
		Integer[] a4 = generateUnsorted(250);
		quickSort(a4);
		verify(a4);
		
		Integer[] a5 = generateUnsorted(250);
		mergeSort(a5);
		verify(a5);
		
		int[] a6 = integerToInt(generateUnsorted(250));
		radixSort(a6);
		verify(a6);
		
		int[] a7 = integerToInt(generateUnsorted(250));
		countingSort(a7);
		verify(a7);
		
		Test.results();
	}
}
