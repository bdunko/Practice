package chp2linkedlists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import datastructures.BNode;
import testing.Test;

public class RemoveDuplicates {
	
	public static <T> BNode<T> removeDups(BNode<T> head) {
		Set<T> uniques = new HashSet<T>();
		
		BNode<T> n = head;
		while(n != null) {
			if(uniques.contains(n.elem)) { //if we already saw this elem, delete this node
				n.prev.next = n.next;
				if(n.next != null)
					n.next.prev = n.prev;
			}	
			uniques.add(n.elem);
			n = n.next;
		}
		
		return head;
	}
	
	public static <T> BNode<T> removeDupsNoStorage(BNode <T> head) {
		BNode<T> current = head;
		
		//iterate over all unique elements in list
		while(current != null) {
			//delete all nodes in list which are duplicates
			T toDelete = current.elem;
			BNode<T> n = current.next;
			while(n != null) {
				if(n.elem == toDelete) {
					n.prev.next = n.next;
					if(n.next != null)
						n.next.prev = n.prev;
				}
				n = n.next;
			}
			
			current = current.next;
		}
		
		return head;
	}
	
	public static void main(String[] args) {
		Test.header("removeDups");
		BNode<String> list1 = BNode.createList(List.of("one", "two", "one", "three", "four", "three"));
		BNode<String> list2 = BNode.createList(List.of("1", "1", "1", "2", "3", "1"));
		BNode<Integer> list3 = BNode.createList(List.of(1, 2, 3, 4));
		Test.assertion(BNode.listEquals(removeDups(list1), BNode.createList(List.of("one", "two", "three", "four"))));
		Test.assertion(BNode.listEquals(removeDups(list2), BNode.createList(List.of("1", "2", "3"))));
		Test.assertion(BNode.listEquals(removeDups(list3), BNode.createList(List.of(1, 2, 3, 4))));
		
		Test.header("removeDupsNoStorage");
		BNode<String> list4 = BNode.createList(List.of("one", "two", "one", "three", "four", "three"));
		BNode<String> list5 = BNode.createList(List.of("1", "1", "1", "2", "3", "1"));
		BNode<Integer> list6 = BNode.createList(List.of(1, 2, 3, 4));
		Test.assertion(BNode.listEquals(removeDupsNoStorage(list4), BNode.createList(List.of("one", "two", "three", "four"))));
		Test.assertion(BNode.listEquals(removeDupsNoStorage(list5), BNode.createList(List.of("1", "2", "3"))));
		Test.assertion(BNode.listEquals(removeDupsNoStorage(list6), BNode.createList(List.of(1, 2, 3, 4))));
		
		Test.results();
	}
}
