package linkedlists;

import java.util.List;
import java.util.Random;

import datastructures.BLinkedNode;
import testing.Test;

//Partitions a list given a partition element P such that all elements < P come 
//before all elements > P in the new list.
public class ListPartition {
	
	//Performs the partition by building two new linked lists
	//One list will contain all elements < P
	//One list contains all elements > P
	//Then return the list where lessTail pointers to greaterHead.
	//Time: O(N)	Space: O(N)		Where N is length of list.
	public static <T extends Comparable<T>> BLinkedNode<T> partition(BLinkedNode<T> head, T partition) {
		BLinkedNode<T> lessList = null;
		BLinkedNode<T> less = null;
		BLinkedNode<T> greaterList = null;
		BLinkedNode<T> greater = null;
		
		while(head != null) {
			if(head.elem.compareTo(partition) < 0) {
				if(lessList == null) {
					lessList = new BLinkedNode<T>(head.elem);
					less = lessList;
				} else {
					less.next = new BLinkedNode<T>(head.elem);
					less = less.next;
				}
			} else {
				if(greaterList == null) {
					greaterList = new BLinkedNode<T>(head.elem);
					greater = greaterList;
				} else {
					greater.next = new BLinkedNode<T>(head.elem);
					greater = greater.next;
				}
			}
			
			head = head.next;
		}
		
		if(lessList == null)
			return greaterList;
		
		less.next = greaterList;
		return lessList;
	}
	
	//Performs the partition with a single linked list.
	//All elements larger than partition are inserted at back of list
	//All elements smaller are inserted at front of list
	//This guarantees the list will end up partitioned.
	//Time: O(N)	Space: O(N)		Where N is size of list
	public static <T extends Comparable<T>> BLinkedNode<T> partition2(BLinkedNode<T> head, T partition) {
		if(head == null)
			return null;
		
		BLinkedNode<T> partitionedFront = new BLinkedNode<T>(head.elem);
		BLinkedNode<T> partitionedBack = partitionedFront;
		head = head.next;
		
		while(head != null) {
			if(head.elem.compareTo(partition) > 0) {
				partitionedBack.next = new BLinkedNode<T>(head.elem);
				partitionedBack.next.prev = partitionedBack;
				partitionedBack = partitionedBack.next;
			} else {
				partitionedFront.prev = new BLinkedNode<T>(head.elem);
				partitionedFront.prev.next = partitionedFront;
				partitionedFront = partitionedFront.prev;
			}
			head = head.next;
		}
		
		return partitionedFront;
	}
	
	//helper method to verify if a given linked list is partitioned given the partition element
	private static <T extends Comparable<T>> boolean verifyPartition(BLinkedNode<T> head, T partition) {
		if(head == null)
			return false;
		
		boolean hitPartition = false;
		
		while(head != null) {
			
			if(head.elem.compareTo(partition) > 0) 
				hitPartition = true;
			
			if(head.elem.compareTo(partition) < 0 && hitPartition == true)
				return false;
			
			head = head.next;
		}
		
		return true;
	}
	
	public static void main(String[] arg) {
		Test.header("verifyPartition");
		Test.assertion(verifyPartition(BLinkedNode.createList(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)), Integer.valueOf(5)));
		Test.assertion(verifyPartition(BLinkedNode.createList(List.of(3, 2, 2, 5, 5, 9, 8, 6, 10)), Integer.valueOf(5)));
		Test.assertion(!verifyPartition(BLinkedNode.createList(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 1)), Integer.valueOf(5)));
		Test.assertion(!verifyPartition(BLinkedNode.createList(List.of(9, 1, 2, 3, 4, 5, 6, 7, 8, 9)), Integer.valueOf(5)));
		
		Test.header("partition");
		Random r = new Random();
		//100 trials
		for(int i = 0; i < 100; i++) {
			
			//generate random list of 10-50 elements
			int length = r.nextInt(50) + 1;
			BLinkedNode<Integer> head = new BLinkedNode<Integer>(r.nextInt(100));
			BLinkedNode<Integer> current = head;
			for(int j = 0; j < length-1; j++) { 
				current.next = new BLinkedNode<Integer>(r.nextInt(100));
				current.next.prev = current;
				current = current.next;
			}
			
			//partition with random partition
			int partition = r.nextInt(100);
			BLinkedNode<Integer> partitioned = partition(head, partition);
			BLinkedNode<Integer> partitioned2 = partition2(head, partition);
			
			//verify length matches (no elements were lost)
			BLinkedNode<Integer> n = partitioned;
			int l = 0;
			while(n != null) {
				l++;
				n = n.next;
			}
			Test.equals(length, l);
			
			n = partitioned2;
			l = 0;
			while(n != null) {
				l++;
				n = n.next;
			}
			Test.equals(length, l);
			
			//verify partition was successful
			Test.assertion(verifyPartition(partitioned, partition));
			Test.assertion(verifyPartition(partitioned2, partition));
		}
		
		Test.results();
	}

}
