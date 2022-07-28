package recursiondynamic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import testing.Test;

//Return all subsets of a set
public class PowerSet {
	
	public static <T> List<List<T>> powerSet(Set<T> set) {
		if(set.size() == 0)
			return null;
		
		List<List<T>> powerSet = new LinkedList<List<T>>();
		
		powerHelper(set, new LinkedList<T>(), powerSet);
		
		return powerSet;
	}
	
	private static <T> void powerHelper(Set<T> remaining, List<T> set, List<List<T>> powerSet) {
		if(remaining.size() == 0) {
			powerSet.add(set);
		}
		else {
			for(T elem : remaining) {
				List<T> newSet = new LinkedList<T>(set);
				newSet.add(elem);
				Set<T> newRemaining = new HashSet<T>(remaining);
				newRemaining.remove(elem);
				powerHelper(newRemaining, newSet, powerSet);
			}
		}
	}
	
	private static <T> boolean verify(List<List<T>> powerSet, int expectedSize) {
		if(powerSet == null)
			return false;
		if(powerSet.size() != expectedSize)
			return false;
		
		for(int i = 0; i < powerSet.size(); i++) {
			for(int j = i+1; j < powerSet.size(); j++) {
				List<T> set1 = powerSet.get(i);
				List<T> set2 = powerSet.get(j);
				if(set1.equals(set2)) 
					return false;
				if(set1.size() != set2.size())
					return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		Test.header("PowerSet");
		
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		Test.assertion(verify(powerSet(set), 1));
		
		set.add(2);
		Test.assertion(verify(powerSet(set), 2));
		
		set.add(3);
		Test.assertion(verify(powerSet(set), 6));
		
		set.add(4);
		Test.assertion(verify(powerSet(set), 24));
		
		set.add(5);
		Test.assertion(verify(powerSet(set), 24*5));
		
		set.add(10);
		Test.assertion(verify(powerSet(set), 24*5*6));
		
		Test.results();
	}
}
