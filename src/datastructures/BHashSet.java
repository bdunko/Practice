package datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import testing.Test;

public class BHashSet<T> {

	private static int NUM_BUCKETS = 1000;
	List<List<T>> buckets;
	int size;
	
	public BHashSet() {
		clear();
	}
	
	public void add(T elem) {
		if(contains(elem))
			return;
		
		int hash = elem.hashCode() % NUM_BUCKETS;
		
		buckets.get(hash).add(elem);
		size++;
	}
	
	public boolean contains(T elem) {
		int hash = elem.hashCode() % NUM_BUCKETS;
		
		for(T e : buckets.get(hash)) {
			if(e.equals(elem))
				return true;
		}
		
		return false;
	}
	
	public boolean remove(T elem) {
		int hash = Math.abs(elem.hashCode() % NUM_BUCKETS);
		Test.print(hash);
		
		List<T> bucket = buckets.get(hash);
		boolean didRemove = bucket.remove(elem);
		
		if(didRemove)
			size--;
		
		return didRemove;
	}
	
	public int size() {
		return size;
	}
	
	public void clear() {
		buckets = new ArrayList<List<T>>();
		for(int i = 0; i < NUM_BUCKETS; i++) {
			buckets.add(new LinkedList<T>());
		}
		size = 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("HashSet - Size = ").append(size).append(" - [ ");
		for(List<T> bucket : buckets) {
			for(T elem : bucket) {
				sb.append(elem.toString()).append(",");
			}
		}
				
		if(sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		
		sb.append(" ]");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Test.header("BHashSet");
		Test.header("create/size");
		BHashSet<String> set = new BHashSet<String>();
		Test.equals(set.size(), 0);
		
		Test.header("add");
		set.add("ben");
		Test.equals(set.size(), 1);
		set.add("jake");
		Test.equals(set.size(), 2);
		set.add("fionn");
		Test.equals(set.size(), 3);
		set.add("ben");
		Test.equals(set.size(), 3);
		
		Test.header("contains");
		Test.assertion(set.contains("ben"));
		Test.assertion(set.contains("jake"));
		Test.assertion(set.contains("fionn"));
		Test.assertion(set.contains("ben"));
		Test.assertion(!set.contains("ZZZ"));
		Test.equals(set.size, 3);
		
		Test.header("remove");
		Test.assertion(!set.remove("zzz"));
		Test.assertion(set.remove("ben"));
		Test.equals(set.size(), 2);
		Test.assertion(!set.contains("ben"));
		set.add("ben");
		Test.assertion(set.contains("ben"));
		Test.equals(set.size(), 3);
		set.remove("ben");
		Test.assertion(!set.remove("ben"));
		Test.assertion(set.remove("fionn"));
		Test.equals(set.size(), 1);
		Test.assertion(!set.contains("fionn"));
		Test.assertion(set.contains("jake"));
		Test.assertion(set.remove("jake"));
		Test.equals(set.size(), 0);
		Test.assertion(!set.contains("jake"));
		Test.assertion(!set.remove("nobody"));
		
		Test.header("clear");
		set.add("ben");
		set.clear();
		Test.assertion(!set.contains("ben"));
		Test.equals(set.size(), 0);
		
		Test.results();
	}
}
