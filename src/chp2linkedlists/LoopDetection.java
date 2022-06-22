package chp2linkedlists;

import java.util.HashSet;
import java.util.Set;

import datastructures.BNode;
import testing.Test;

//Determine whether a linked list is circular (infinite/corrupted)
public class LoopDetection {

	//Add each node to a set until a node is repeated.
	//The repeated node is the start of the loop.
	//If we reach end of list, then list is not corrupted.
	//Time: O(N)	Space: O(N)		Where N is length of list before loop.
	public static <T> BNode<T> detectCircular(BNode<T> head) {
		Set<BNode<T>> seen = new HashSet<BNode<T>>();
		
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
		BNode<Integer> one = new BNode<Integer>(1);
		BNode<Integer> two = new BNode<Integer>(2);
		BNode<Integer> three = new BNode<Integer>(3);
		BNode<Integer> four = new BNode<Integer>(4);
		BNode<Integer> five = new BNode<Integer>(5);
		BNode<Integer> six = new BNode<Integer>(6);
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
