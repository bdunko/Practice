package chp2linkedlists;

import java.util.List;

import datastructures.BNode;
import testing.Test;

//Return the kth to last element of singly linked list
public class KthToLast {

	//Returns kth to last element by using two pointers
	//First, move the front pointer up k spaces (if this reaches end of list, return null)
	//Back pointer starts at head of list.
	//Move both pointers together until front reaches the tail
	//Then return element at back, which is k behind front.
	//Time: O(N)	Space: O(1)		Where N is length of list
	public static <T> T kthToLast(BNode<T> head, int k) {
		BNode<T> front = head;
		for(int i = 0; i < k; i++) {
			if(front == null)
				return null;
			front = front.next;
		}
			
		BNode<T> back = head;
		
		while(front.next != null) {
			front = front.next;
			back = back.next;
		}
		
		return back.elem;
	}
	
	public static void main(String[] args) {
		Test.header("kthToLast");
		
		Test.equals(kthToLast(BNode.createList(List.of(1, 2, 3, 4, 5)), 0), Integer.valueOf(5));
		Test.equals(kthToLast(BNode.createList(List.of(1, 2, 3, 4, 5)), 4), Integer.valueOf(1));
		Test.isNull(kthToLast(BNode.createList(List.of(1, 2, 3, 4, 5)), 6));
		Test.equals(kthToLast(BNode.createList(List.of(1, 2, 3, 4, 5)), 2), Integer.valueOf(3));
		
		Test.results();
	}
}
