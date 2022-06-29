package linkedlists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import datastructures.BLinkedNode;
import testing.Test;

//Remove all duplicate elemnts from an unsorted linked list.
public class RemoveDuplicates {
	
	//Adds all elements to a hashset as we iterate over list
	//If element is already in hashset, delete this node.
	//Time: O(N)	Space: O(N)		Where N is length of list
	public static <T> BLinkedNode<T> removeDups(BLinkedNode<T> head) {
		Set<T> uniques = new HashSet<T>();
		
		BLinkedNode<T> n = head;
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
	
	//Removes duplicates without using space for storage.
	//Iterate over elements in list; for each, walk the rest of the list and 
	//delete all nodes that duplicate that element.
	//Time: O(N^2) 	Space: O(1)		Where N is length of linked list
	public static <T> BLinkedNode<T> removeDupsNoStorage(BLinkedNode <T> head) {
		BLinkedNode<T> current = head;
		
		//iterate over all unique elements in list
		while(current != null) {
			//delete all nodes in list which are duplicates
			T toDelete = current.elem;
			BLinkedNode<T> n = current.next;
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
		BLinkedNode<String> list1 = BLinkedNode.createList(List.of("one", "two", "one", "three", "four", "three"));
		BLinkedNode<String> list2 = BLinkedNode.createList(List.of("1", "1", "1", "2", "3", "1"));
		BLinkedNode<Integer> list3 = BLinkedNode.createList(List.of(1, 2, 3, 4));
		Test.assertion(BLinkedNode.listEquals(removeDups(list1), BLinkedNode.createList(List.of("one", "two", "three", "four"))));
		Test.assertion(BLinkedNode.listEquals(removeDups(list2), BLinkedNode.createList(List.of("1", "2", "3"))));
		Test.assertion(BLinkedNode.listEquals(removeDups(list3), BLinkedNode.createList(List.of(1, 2, 3, 4))));
		
		Test.header("removeDupsNoStorage");
		BLinkedNode<String> list4 = BLinkedNode.createList(List.of("one", "two", "one", "three", "four", "three"));
		BLinkedNode<String> list5 = BLinkedNode.createList(List.of("1", "1", "1", "2", "3", "1"));
		BLinkedNode<Integer> list6 = BLinkedNode.createList(List.of(1, 2, 3, 4));
		Test.assertion(BLinkedNode.listEquals(removeDupsNoStorage(list4), BLinkedNode.createList(List.of("one", "two", "three", "four"))));
		Test.assertion(BLinkedNode.listEquals(removeDupsNoStorage(list5), BLinkedNode.createList(List.of("1", "2", "3"))));
		Test.assertion(BLinkedNode.listEquals(removeDupsNoStorage(list6), BLinkedNode.createList(List.of(1, 2, 3, 4))));
		
		Test.results();
	}
}
