package chp2linkedlists;

import java.util.List;

import datastructures.BNode;
import testing.Test;

public class DeleteMiddle {
	
	public static <T> void deleteMiddle(BNode<T> head, BNode<T> toDelete) {
		//if trying to delete head
		if(toDelete == head) 
			return;
		
		//move head until it equals the node before the node to be deleted
		while(head != null && head.next != toDelete)
			head = head.next;
		
		//if toDelete wasn't in list; or trying to delete tail
		if(head == null || head.next == null) 
			return;
		
		//delete middle node by setting previous node's next to the node after deleted
		head.next = head.next.next;
	}

	public static void main(String[] args) {
		Test.header("deleteMiddle");
		BNode<Integer> list = BNode.createList(List.of(1, 2, 3, 4, 5, 6, 7, 8));
		
		deleteMiddle(list, list.next.next.next); //delete 4
		Test.assertion(BNode.listEquals(list, BNode.createList(List.of(1, 2, 3, 5, 6, 7, 8))));
		deleteMiddle(list, list.next); //delete 2
		Test.assertion(BNode.listEquals(list, BNode.createList(List.of(1, 3, 5, 6, 7, 8))));
		deleteMiddle(list, list.next.next.next.next); //delete 7
		Test.assertion(BNode.listEquals(list, BNode.createList(List.of(1, 3, 5, 6, 8))));
		deleteMiddle(list, list); //try delete head (nothing happens)
		Test.assertion(BNode.listEquals(list, BNode.createList(List.of(1, 3, 5, 6, 8))));
		deleteMiddle(list, list.next.next.next.next.next); //try delete tail (nothing happens)
		Test.assertion(BNode.listEquals(list, BNode.createList(List.of(1, 3, 5, 6, 8))));
		
		Test.results();
	}
}