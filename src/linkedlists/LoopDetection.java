package linkedlists;

import java.util.HashSet;
import java.util.Set;

import datastructures.BLinkedNode;
import testing.Test;

//Determine whether a linked list is circular (infinite/corrupted)
public class LoopDetection {

	//Add each node to a set until a node is repeated.
	//The repeated node is the start of the loop.
	//If we reach end of list, then list is not corrupted.
	//Time: O(N)	Space: O(N)		Where N is length of list before loop.
	public static <T> BLinkedNode<T> detectCircular(BLinkedNode<T> head) {
		Set<BLinkedNode<T>> seen = new HashSet<BLinkedNode<T>>();
		
		while(head != null) {
			if(seen.contains(head))
				return head;
			seen.add(head);
			
			head = head.next;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Test.header("detectCircular");
		
		//create circular list 	1->2->3->4->5->3->...
		BLinkedNode<Integer> one = new BLinkedNode<Integer>(1);
		BLinkedNode<Integer> two = new BLinkedNode<Integer>(2);
		BLinkedNode<Integer> three = new BLinkedNode<Integer>(3);
		BLinkedNode<Integer> four = new BLinkedNode<Integer>(4);
		BLinkedNode<Integer> five = new BLinkedNode<Integer>(5);
		BLinkedNode<Integer> six = new BLinkedNode<Integer>(6);
		one.next = two;
		two.next = three;
		three.next = four;
		four.next = five;
		five.next = three;
		
		Test.equals(detectCircular(one), three);
		
		//non-circular list
		five.next = six;
		Test.isNull(detectCircular(one));
		
		//circular at head
		one.next = one;
		Test.equals(detectCircular(one), one);
		
		//circular at head->next
		one.next = two;
		two.next = two;
		Test.equals(detectCircular(one), two);
		
		Test.results();
	}
}
