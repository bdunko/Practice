package chp2linkedlists;

import java.util.HashSet;
import java.util.Set;

import datastructures.BNode;
import testing.Test;

public class ListIntersection {
	
	public static <T> BNode<T> intersection(BNode<T> list1, BNode<T> list2) {
		Set<BNode<T>> set = new HashSet<BNode<T>>();
		
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
	
	public static void main(String[] args) {
		Test.header("intersection");
		BNode<Integer> one = new BNode<Integer>(1);
		BNode<Integer> two = new BNode<Integer>(2);
		BNode<Integer> three = new BNode<Integer>(3);
		BNode<Integer> four = new BNode<Integer>(4);
		BNode<Integer> five = new BNode<Integer>(5);
		BNode<Integer> six = new BNode<Integer>(6);
		
		Test.isNull(intersection(one, two));
		
		//two non-intersecting lists
		//list 1		1->2->3->4
		one.next = two;
		two.next = three;
		three.next = four;
		//list 2		5->6
		five.next = six;
		
		Test.isNull(intersection(one, five));
		
		//two intersecting lists 	1->2->3->4->5->6 and 5->6
		four.next = five;
		Test.equals(intersection(one, five), five);
		
		//intersection at the end
		Test.equals(intersection(one, six), six);
		
		//intersection at the head
		Test.equals(intersection(one, one), one);
		
		Test.results();
	}
}
