package chp2linkedlists;

import java.util.List;

import datastructures.BNode;
import testing.Test;

//Delete a node from a singly-linked list
//Deny requests to delete head or tail.
public class DeleteMiddle {
	
	//Deletes a middle node by iterating over the list until reaching the node before that node
	//Then setting the next pointer to the node after the deleted node.
	//Time: O(N)	Space: O(1)		Where N is length of list
	public static <T> void deleteMiddle(BNode<T> head, BNode<T> toDelete) {
		//if trying to delete head or tail
		if(toDelete == head || toDelete.next == null) 
			return;
		
		//move head until it equals the node before the node to be deleted
		while(head != null && head.next != toDelete)
			head = head.next;
		
		//if toDelete wasn't in list; or trying to delete tail
		if(head == null) 
			return;
		
		//delete middle node by setting previous node's next to the node after deleted
		head.next = head.next.next;
	}
	
	//Deletes a middle node of singly linked list given only the middle node by copying over the next node, then deleting it.
	//Time: O(1)	Space: O(1)
	public static <T> void deleteMiddleGivenMiddle(BNode<T> middle) {
		if(middle == null || middle.next == null)
			return;
		
		//copy over next element
		middle.elem = middle.next.elem;
		
		//remove next node
		middle.next = middle.next.next;
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
		deleteMiddle(list, list.next.next.next.next); //try delete tail (nothing happens)
		Test.assertion(BNode.listEquals(list, BNode.createList(List.of(1, 3, 5, 6, 8))));
		deleteMiddle(list, new BNode<Integer>(1000)); //try delete element not in list (nothing happens)
		Test.assertion(BNode.listEquals(list, BNode.createList(List.of(1, 3, 5, 6, 8))));
		
		Test.header("deleteMiddleGivenMiddle");
		list = BNode.createList(List.of(1, 2, 3, 4, 5, 6, 7, 8));
		deleteMiddleGivenMiddle(list.next.next.next); //delete 4
		Test.assertion(BNode.listEquals(list, BNode.createList(List.of(1, 2, 3, 5, 6, 7, 8))));
		deleteMiddleGivenMiddle(list.next); //delete 2
		Test.assertion(BNode.listEquals(list, BNode.createList(List.of(1, 3, 5, 6, 7, 8))));
		deleteMiddleGivenMiddle(list.next.next.next.next); //delete 7
		Test.assertion(BNode.listEquals(list, BNode.createList(List.of(1, 3, 5, 6, 8))));
		deleteMiddleGivenMiddle(list.next.next.next.next); //try delete tail (nothing happens)
		Test.assertion(BNode.listEquals(list, BNode.createList(List.of(1, 3, 5, 6, 8))));
		
		Test.results();
	}
}
