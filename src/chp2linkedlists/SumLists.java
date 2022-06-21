package chp2linkedlists;

import java.util.List;

import datastructures.BNode;
import testing.Test;

public class SumLists {

	//sums list where each list contains the digit in reverse order, returns answer is reverse order
	//ex: input: (7, 1, 6) (5, 9, 2)  output: (2, 1, 9)
	//617 + 295 = 912
	public static BNode<Integer> sumListsReversed(BNode<Integer> list1, BNode<Integer> list2) {
		int carry = 0;
		BNode<Integer> answerHead = null;
		BNode<Integer> answerTail = null;
		
		while(list1 != null || list2 != null) {
			int elem1 = (list1 == null ? 0 : list1.elem);
			int elem2 = (list2 == null ? 0 : list2.elem);
			int sum = elem1 + elem2 + carry;
			
			if(sum >= 10) {
				carry = 1;
				sum -= 10;
			} else {
				carry = 0;
			}
			
			if(answerHead == null) {
				answerHead = new BNode<Integer>(sum);
				answerTail = answerHead;
			} else {
				answerTail.next = new BNode<Integer>(sum);
				answerTail.next.prev = answerTail;
				answerTail = answerTail.next;
			}
			
			
			if(list1 != null)
				list1 = list1.next;
			if(list2 != null)
				list2 = list2.next;
		}
		
		//ex: adding 9 + 8 = 17, we need to add the 1 here
		if(carry == 1)
			answerTail.next = new BNode<Integer>(carry);
		
		return answerHead;
	}
	
	public static BNode<Integer> sumLists(BNode<Integer> list1, BNode<Integer> list2) {
		BNode<Integer> answerHead = null;
		BNode<Integer> answerTail = null;
		
		while(list1 != null  || list2 != null) {
			int elem1 = (list1 == null ? 0 : list1.elem);
			int elem2 = (list2 == null ? 0 : list2.elem);
			int sum = elem1 + elem2;
			
			if(sum >= 10) {
				BNode<Integer> n = answerTail;
				//traverse the list in reverse order from the tail
				//find first non-9 previous node and increase it by 1
				//(no point in incrementing a 9, since that would also carry another 1)
				//carry is successful once we find a single non-9 node and increment it
				while(n != null) {
					if(n.elem == 9) {
						n = n.prev;
					} else {
						n.elem += 1;
						break;
					}
				}
				
				//if we couldn't add 1 to any previous node, create a new node of 1 at head
				//ex: input of 7, 8 requires inserting a 1 before the head to make 15.
				if(n == null) {
					if(answerHead == null) {
						answerHead = new BNode<Integer>(1);
						answerTail = answerHead;
					} else {
						answerHead.prev = new BNode<Integer>(1);
						answerHead.prev.next = answerHead;
						answerHead = answerHead.prev;
					}
				}
				
				sum -= 10;
			}
			
			if(answerHead == null) {
				answerHead = new BNode<Integer>(sum);
				answerTail = answerHead;
			} else {
				answerTail.next = new BNode<Integer>(sum);
				answerTail.next.prev = answerTail;
				answerTail = answerTail.next;
			}
			
			if(list1 != null)
				list1 = list1.next;
			if(list2 != null) 
				list2 = list2.next;
		}
		
		return answerHead;
	}
	
	public static void testCase(List<Integer> list1, List<Integer> list2, List<Integer> answer) {
		BNode<Integer> l1 = BNode.createList(list1);
		BNode<Integer> l2 = BNode.createList(list2);
		BNode<Integer> ans = BNode.createList(answer);
		Test.assertion(BNode.listEquals(sumLists(l1, l2), ans));
	}
	
	public static void testCaseReversed(List<Integer> list1, List<Integer> list2, List<Integer> answer) {
		BNode<Integer> l1 = BNode.createList(list1);
		BNode<Integer> l2 = BNode.createList(list2);
		BNode<Integer> ans = BNode.createList(answer);
		Test.assertion(BNode.listEquals(sumListsReversed(l1, l2), ans));
	}
	
	public static void main(String[] args) {
		Test.header("sumListsReversed");
		testCaseReversed(List.of(7, 1, 6), List.of(5, 9, 2), List.of(2, 1, 9));
		testCaseReversed(List.of(8), List.of(9), List.of(7, 1));
		testCaseReversed(List.of(1, 2, 3), List.of(1), List.of(2, 2, 3));
		testCaseReversed(List.of(5, 7), List.of(1), List.of(6, 7));
		testCaseReversed(List.of(9, 9, 9), List.of(9, 9, 9), List.of(8, 9, 9, 1));
		Test.header("sumLists");
		testCase(List.of(6, 1, 7), List.of(2, 9, 5), List.of(9, 1, 2));
		testCase(List.of(8), List.of(9), List.of(1, 7));
		testCase(List.of(1, 5), List.of(2, 6), List.of(4, 1));
		testCase(List.of(3, 2, 1), List.of(1), List.of(4, 2, 1));
		testCase(List.of(7, 5), List.of(0, 1), List.of(7, 6));
		testCase(List.of(9, 9, 9), List.of(9, 9, 9), List.of(1, 9, 9, 8));
		Test.results();
	}
}
