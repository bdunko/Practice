package linkedlists;

import java.util.HashSet;
import java.util.Set;

import datastructures.BLinkedNode;
import testing.Test;

//Determine whether two singly linked lists intersect
//Return the intersecting node
public class ListIntersection {
	
	//Iterate over list1, adding all elements to a HashSet
	//Iterate over list2, checking if each node is in the set. If it is, this is the point of
	//intersection and we return it.
	//Time: O(N)	Space: O(N)		Where N is the length of the longer list
	public static <T> BLinkedNode<T> intersection(BLinkedNode<T> list1, BLinkedNode<T> list2) {
		Set<BLinkedNode<T>> set = new HashSet<BLinkedNode<T>>();
		
		while(list1 != null) {
			set.add(list1);
			list1 = list1.next;
		}
		
		while(list2 != null) {
			if(set.contains(list2))
				return list2;
			list2 = list2.next;
		}
		
		return null;
	}
	
	//If lists intersect, they must have the same last element. ex: 1->2->3   4->5->2->3
	//Walk both lists, recording length. Check if tail of each matches.
	//If not, no intersection. Otherwise, the intersection is the same distance from the tail of each list
	//If lists are different sizes, move the longer list forward until the distance to tail is same for both lists. ex: 1->2->3  5->2->3
	//Now walk both lists until one node matches, that node must be the intersection. ex: return 2
	//Time: O(N)	Space: O(1)		Where N is length of longer list
	public static <T> BLinkedNode<T> intersectionNoSpace(BLinkedNode<T> list1, BLinkedNode<T> list2) {
		BLinkedNode<T> n = list1;
		int list1Length = 0;
		BLinkedNode<T> list1Tail = null;
		while(n != null) {
			if(n.next == null)
				list1Tail = n;
			list1Length++;
			n = n.next;
		}
		
		n = list2;
		int list2Length = 0;
		BLinkedNode<T> list2Tail = null;
		while(n != null) {
			if(n.next == null)
				list2Tail = n;
			list2Length++;
			n = n.next;
		}
		
		//if list1Tail != list2Tail, lists do not intersect!
		if(!list1Tail.equals(list2Tail))
			return null;
		
		//if list1 is longer than list2, move forward until same length
		while(list1Length > list2Length) {
			list1 = list1.next;
			list1Length--;
		}
		//if list2 is longer than list1, move forward until same length
		while(list2Length > list1Length) {
			list2 = list2.next;
			list2Length--;
		}
		
		//now walk the list until a node matches. This is the intersection.
		while(!list1.equals(list2)) {
			list1 = list1.next;
			list2 = list2.next;
		}
		
		return list1;
	}
	
	public static void main(String[] args) {
		Test.header("intersection");
		BLinkedNode<Integer> one = new BLinkedNode<Integer>(1);
		BLinkedNode<Integer> two = new BLinkedNode<Integer>(2);
		BLinkedNode<Integer> three = new BLinkedNode<Integer>(3);
		BLinkedNode<Integer> four = new BLinkedNode<Integer>(4);
		BLinkedNode<Integer> five = new BLinkedNode<Integer>(5);
		BLinkedNode<Integer> six = new BLinkedNode<Integer>(6);
		
		Test.isNull(intersection(one, two));
		Test.isNull(intersectionNoSpace(one, two));
		
		//two non-intersecting lists
		//list 1		1->2->3->4
		one.next = two;
		two.next = three;
		three.next = four;
		//list 2		5->6
		five.next = six;
		
		Test.isNull(intersection(one, five));
		Test.isNull(intersectionNoSpace(one, five));
		
		//two intersecting lists 	1->2->3->4->5->6 and 5->6
		four.next = five;
		Test.equals(intersection(one, five), five);
		Test.equals(intersectionNoSpace(one, five), five);
		
		//intersection at the end
		Test.equals(intersection(one, six), six);
		Test.equals(intersectionNoSpace(one, six), six);
		
		//intersection at the head
		Test.equals(intersection(one, one), one);
		Test.equals(intersectionNoSpace(one, one), one);
		
		Test.results();
	}
}
