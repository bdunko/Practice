package sortsearch;

import java.util.Arrays;
import java.util.Random;

import testing.Test;

//Given two sorted arrays A and B, where A has sufficient space at the end to store B, merge B into A in sorted order.
public class SortedMerge {

	private static void merge(int[] A, int[] B) {
		int aIndex = A.length-B.length-1;
		int bIndex = B.length-1;
		int mergeIndex = A.length-1;
		
		//repeatedly add larger element to back of A
		while(aIndex >= 0 && bIndex >= 0) {
			int elemA = A[aIndex];
			int elemB = B[bIndex];
			
			if(elemA > elemB) {
				aIndex--;
				A[mergeIndex] = elemA;
			} else {
				bIndex--;
				A[mergeIndex] = elemB;
			}
			
			mergeIndex--;
		}
		
		
		//leftovers in A are already in right spot, no need to copy
		
		//if leftovers in B
		while(bIndex >= 0) {
			A[mergeIndex] = B[bIndex];
			bIndex--;
			mergeIndex--;
		}
	}
	
	private static void verify(int[] array) {
		boolean success = true;
		
		int current = array[0];
		for(int i = 1; i < array.length; i++) {
			if(current > array[i]) {
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
	
	public static void main(String[] args) {
		Test.header("SortedMerge");
		
		int arraySize = 1000;
		
		for(int i = 0; i < 5; i++) {
			int[] smallA = new int[arraySize];
			int[] B = new int[arraySize];
			
			Random r = new Random();
			for(int j = 0; j < smallA.length; j++) {
				smallA[j] = r.nextInt();
				B[j] = r.nextInt();
			}
			Arrays.sort(smallA);
			Arrays.sort(B);
			
			verify(smallA);
			verify(B);
			
			int[] A = new int[arraySize * 2];
			System.arraycopy(smallA, 0, A, 0, smallA.length);
			
			merge(A, B);
			verify(A);
		}
		
		Test.results();
	}
}
