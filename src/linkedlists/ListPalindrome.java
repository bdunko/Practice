package linkedlists;

import java.util.List;

import datastructures.BLinkedNode;
import testing.Test;

//Determine whether a doubly-linked list is a palindrome
public class ListPalindrome {
	
	//First, walk the list until we reach the tail, so we have a head pointer and tail pointer
	//One at a time, until the pointers cross, check if head = tail.
	//Then move head up and tail back.
	//Continue until we've checked length/2 pairs. This handles if the palindrome is odd length.
	//Time: O(N)	Space: O(1)		Where N is length of list.
	public static <T> boolean palindrome(BLinkedNode<T> list) {
		if(list == null)
			return false;
		
		BLinkedNode<T> head = list;
		BLinkedNode<T> tail = list;
		
		int length = 1;
		while(tail.next != null) {
			tail = tail.next;
			length++;
		}
		

		int current = 0;
		while(current < length / 2) {
			if(!head.elem.equals(tail.elem))
				return false;
			current++;
			head = head.next;
			tail = tail.prev;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		Test.header("palindrome");
		Test.assertion(palindrome(BLinkedNode.createList(List.of(1, 2, 3, 4, 4, 3, 2, 1))));
		Test.assertion(palindrome(BLinkedNode.createList(List.of(1, 2, 3, 4, 5, 4, 3, 2, 1))));
		Test.assertion(palindrome(BLinkedNode.createList(List.of(1, 1))));
		Test.assertion(palindrome(BLinkedNode.createList(List.of(1, 2, 1))));
		Test.assertion(palindrome(BLinkedNode.createList(List.of(1, 2, 4, 1, 3, 1, 4, 2, 1))));
		Test.assertion(!palindrome(BLinkedNode.createList(List.of(1, 2, 3, 4, 4, 9, 3, 2, 1))));
		Test.assertion(!palindrome(null));
		Test.assertion(!palindrome(BLinkedNode.createList(List.of(1, 2, 3))));
		Test.assertion(!palindrome(BLinkedNode.createList(List.of(2, 4))));
		Test.assertion(!palindrome(BLinkedNode.createList(List.of(8, 2, 3, 4, 4, 3, 2, 1))));
		Test.assertion(!palindrome(BLinkedNode.createList(List.of(1, 2, 3, 2, 6))));
		Test.assertion(!palindrome(BLinkedNode.createList(List.of(1, 4, 3, 5, 1))));
		Test.assertion(!palindrome(BLinkedNode.createList(List.of(4, 5, 6, 4))));
		Test.results();
	}
}
