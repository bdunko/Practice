package chp2linkedlists;

import java.util.List;
import java.util.Random;

import datastructures.BNode;
import testing.Test;

public class Partition {
	
	public static <T extends Comparable<T>> BNode<T> partition(BNode<T> head, T partition) {
		BNode<T> lessList = null;
		BNode<T> less = null;
		BNode<T> greaterList = null;
		BNode<T> greater = null;
		
		while(head != null) {
			if(head.elem.compareTo(partition) < 0) {
				if(lessList == null) {
					lessList = new BNode<T>(head.elem);
					less = lessList;
				} else {
					less.next = new BNode<T>(head.elem);
					less = less.next;
				}
			} else {
				if(greaterList == null) {
					greaterList = new BNode<T>(head.elem);
					greater = greaterList;
				} else {
					greater.next = new BNode<T>(head.elem);
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
	
	private static <T extends Comparable<T>> boolean verifyPartition(BNode<T> head, T partition) {
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
		Test.assertion(verifyPartition(BNode.createList(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)), Integer.valueOf(5)));
		Test.assertion(verifyPartition(BNode.createList(List.of(3, 2, 2, 5, 5, 9, 8, 6, 10)), Integer.valueOf(5)));
		Test.assertion(!verifyPartition(BNode.createList(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 1)), Integer.valueOf(5)));
		Test.assertion(!verifyPartition(BNode.createList(List.of(9, 1, 2, 3, 4, 5, 6, 7, 8, 9)), Integer.valueOf(5)));
		
		Test.header("partition");
		Random r = new Random();
		//100 trials
		for(int i = 0; i < 100; i++) {
			
			//generate random list of 10-50 elements
			int length = r.nextInt(50) + 1;
			BNode<Integer> head = new BNode<Integer>(r.nextInt(100));
			BNode<Integer> current = head;
			for(int j = 0; j < length-1; j++) { 
				current.next = new BNode<Integer>(r.nextInt(100));
				current.next.prev = current;
				current = current.next;
			}
			
			//partition with random partition
			int partition = r.nextInt(100);
			BNode<Integer> partitioned = partition(head, partition);
			BNode<Integer> n = partitioned;
			int l = 0;
			while(n != null) {
				l++;
				n = n.next;
			}
			Test.equals(length, l);
			
			//verify partition was successful
			Test.assertion(verifyPartition(partitioned, partition));
		}
		
		Test.results();
	}

}
