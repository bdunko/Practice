package chp2linkedlists;

import java.util.List;

import datastructures.BNode;
import testing.Test;

public class Palindrome {
	
	public static <T> boolean palindrome(BNode<T> list) {
		if(list == null)
			return false;
		
		BNode<T> head = list;
		BNode<T> tail = list;
		
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
		Test.assertion(palindrome(BNode.createList(List.of(1, 2, 3, 4, 4, 3, 2, 1))));
		Test.assertion(palindrome(BNode.createList(List.of(1, 2, 3, 4, 5, 4, 3, 2, 1))));
		Test.assertion(palindrome(BNode.createList(List.of(1, 1))));
		Test.assertion(palindrome(BNode.createList(List.of(1, 2, 1))));
		Test.assertion(palindrome(BNode.createList(List.of(1, 2, 4, 1, 3, 1, 4, 2, 1))));
		Test.assertion(!palindrome(BNode.createList(List.of(1, 2, 3, 4, 4, 9, 3, 2, 1))));
		Test.assertion(!palindrome(null));
		Test.assertion(!palindrome(BNode.createList(List.of(1, 2, 3))));
		Test.assertion(!palindrome(BNode.createList(List.of(2, 4))));
		Test.assertion(!palindrome(BNode.createList(List.of(8, 2, 3, 4, 4, 3, 2, 1))));
		Test.assertion(!palindrome(BNode.createList(List.of(1, 2, 3, 2, 6))));
		Test.assertion(!palindrome(BNode.createList(List.of(1, 4, 3, 5, 1))));
		Test.assertion(!palindrome(BNode.createList(List.of(4, 5, 6, 4))));
		Test.results();
	}
}
