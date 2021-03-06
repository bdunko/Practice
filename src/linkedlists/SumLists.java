package linkedlists;

import java.util.List;

import datastructures.BLinkedNode;
import testing.Test;

//Sums two lists where each list represents digits of a number
public class SumLists {

	//sums list where each list contains the digit in reverse order, returns answer is reverse order
	//ex: input: (7, 1, 6) (5, 9, 2)  output: (2, 1, 9)
	//617 + 295 = 912
	public static BLinkedNode<Integer> sumListsReversed(BLinkedNode<Integer> list1, BLinkedNode<Integer> list2) {
		int carry = 0;
		BLinkedNode<Integer> answerHead = null;
		BLinkedNode<Integer> answerTail = null;
		
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
				answerHead = new BLinkedNode<Integer>(sum);
				answerTail = answerHead;
			} else {
				answerTail.next = new BLinkedNode<Integer>(sum);
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
			answerTail.next = new BLinkedNode<Integer>(carry);
		
		return answerHead;
	}
	
	//sums list where each list contains the digit in order, returns answer in order
	//ex: input: (6, 1, 7) (2, 9, 5)  output: (9, 1, 2)
	//617 + 295 = 912
	public static BLinkedNode<Integer> sumLists(BLinkedNode<Integer> list1, BLinkedNode<Integer> list2) {
		BLinkedNode<Integer> answerHead = null;
		BLinkedNode<Integer> answerTail = null;
		
		//if one of the lists is shorter than the other, we must pad with 0s in front
		int length1 = list1.length();
		int length2 = list2.length();
		while(length1 > length2) {
			list2.prev = new BLinkedNode<Integer>(0);
			list2.prev.next = list2;
			list2 = list2.prev;
			length2++;
		}
		while(length2 > length1) {
			list1.prev = new BLinkedNode<Integer>(0);
			list1.prev.next = list1;
			list1 = list1.prev;
			length1++;
		}
		
		while(list1 != null  || list2 != null) {
			int elem1 = (list1 == null ? 0 : list1.elem);
			int elem2 = (list2 == null ? 0 : list2.elem);
			int sum = elem1 + elem2;
			
			if(sum >= 10) {
				BLinkedNode<Integer> n = answerTail;
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
						answerHead = new BLinkedNode<Integer>(1);
						answerTail = answerHead;
					} else {
						answerHead.prev = new BLinkedNode<Integer>(1);
						answerHead.prev.next = answerHead;
						answerHead = answerHead.prev;
					}
				}
				
				sum -= 10;
			}
			
			if(answerHead == null) {
				answerHead = new BLinkedNode<Integer>(sum);
				answerTail = answerHead;
			} else {
				answerTail.next = new BLinkedNode<Integer>(sum);
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
	
	//Helper method for a normal test case
	private static void testCase(List<Integer> list1, List<Integer> list2, List<Integer> answer) {
		BLinkedNode<Integer> l1 = BLinkedNode.createList(list1);
		BLinkedNode<Integer> l2 = BLinkedNode.createList(list2);
		BLinkedNode<Integer> ans = BLinkedNode.createList(answer);
		Test.assertion(BLinkedNode.listEquals(sumLists(l1, l2), ans));
	}
	
	//Helper method for a reversed test case
	private static void testCaseReversed(List<Integer> list1, List<Integer> list2, List<Integer> answer) {
		BLinkedNode<Integer> l1 = BLinkedNode.createList(list1);
		BLinkedNode<Integer> l2 = BLinkedNode.createList(list2);
		BLinkedNode<Integer> ans = BLinkedNode.createList(answer);
		Test.assertion(BLinkedNode.listEquals(sumListsReversed(l1, l2), ans));
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
		testCase(List.of(3, 2, 1), List.of(1), List.of(3, 2, 2));
		testCase(List.of(7, 5), List.of(1, 0), List.of(8, 5));
		testCase(List.of(9, 9, 9), List.of(9, 9, 9), List.of(1, 9, 9, 8));
		Test.results();
	}
}
