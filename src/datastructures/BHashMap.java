package datastructures;

import java.util.ArrayList;
import java.util.List;

import testing.Test;

//Generic hashmap implementation
public class BHashMap<K, V> {

	private class BHashMapEntry {
		public K key;
		public V value;
		
		public BHashMapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			return sb.append("(").append(key.toString()).append(", ").append(value.toString()).append(")").toString();
		}
	}
	
	private static int NUM_BUCKETS = 1024;
	private List<List<BHashMapEntry>> buckets;
	int size;
	
	public BHashMap() {
		clear();
	}
	
	public V put(K key, V value) {
		List<BHashMapEntry> bucket = getBucketForKey(key);
		
		V previousValue = null;
		
		for(BHashMapEntry hme : bucket) {
			if(hme.key.equals(key)) {
				previousValue = hme.value;
				hme.value = value;
			}
		}
		
		if(previousValue == null) {
			bucket.add(new BHashMapEntry(key, value));
			size++;
		}
		
		return previousValue;
	}
	
	private List<BHashMapEntry> getBucketForKey(K key) {
		int bucketNumber = Math.abs(key.hashCode() % NUM_BUCKETS);
		return buckets.get(bucketNumber);
	}
	
	public V get(K key) {
		List<BHashMapEntry> bucket = getBucketForKey(key);
		
		for(BHashMapEntry hme : bucket) {
			if(hme.key.equals(key)) {
				return hme.value;
			}
		}
		
		return null;
	}
	
	public int size() {
		return size;
	}
	
	public boolean containsKey(K key) {
		List<BHashMapEntry> bucket = getBucketForKey(key);
		
		for(BHashMapEntry hme : bucket) {
			if(hme.key.equals(key))
				return true;
		}
		
		return false;
	}
	
	public boolean containsValue(V value) {
		for(List<BHashMapEntry> bucket : buckets) {
			for(BHashMapEntry hme : bucket) {
				if(hme.value.equals(value))
					return true;
			}
		}
		
		return false;
	}
	
	public V remove(K key) {
		List<BHashMapEntry> bucket = getBucketForKey(key);
		
		BHashMapEntry toRemove = null;
		
		for(BHashMapEntry hme : bucket) {
			if(hme.key == key) {
				toRemove = hme;
				break;
			}
		}
		
		if(toRemove != null) {
			bucket.remove(toRemove);
			size--;
			return toRemove.value;
		}
		
		return null;
	}
	
	public void clear() {
		buckets = new ArrayList<List<BHashMapEntry>>();
		for(int i = 0; i < NUM_BUCKETS; i++) {
			buckets.add(new ArrayList<BHashMapEntry>());
		}
		size = 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("HashMap - Size = ").append(size).append(" - [ ");
		
		for(List<BHashMapEntry> bucket : buckets) {
			for(BHashMapEntry hme : bucket) {
				sb.append(hme.toString()).append(",");
			}
		}
		
		if(sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		sb.append(" ]");
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Test.header("constructors");
		BHashMap<Integer, String> hm = new BHashMap<Integer, String>();
		Test.equals(hm.size(), 0);
		
		Test.header("put & get");
		hm.put(121, "OneTwoOne");
		hm.put(456, "FourFiveSix");
		Test.equals(hm.size(), 2);
		Test.equals(hm.get(121), "OneTwoOne");
		Test.equals(hm.put(121, "OVERWRITE!"), "OneTwoOne");
		Test.equals(hm.get(121), "OVERWRITE!");
		Test.equals(hm.size(), 2);
		hm.put(123234, "bignum");
		Test.equals(hm.get(123234), "bignum");
		Test.equals(hm.size(), 3);
		Test.isNull(hm.get(0));
		Test.print(hm.toString());
		
		Test.header("containsKey/containsValue");
		Test.assertion(hm.containsKey(121));
		Test.assertion(hm.containsKey(456));
		Test.assertion(!hm.containsKey(348959));
		Test.assertion(hm.containsValue("OVERWRITE!"));
		Test.assertion(hm.containsValue("bignum"));
		Test.assertion(!hm.containsValue("not here"));
		
		Test.header("remove");
		Test.equals(hm.remove(121), "OVERWRITE!");
		Test.equals(hm.size(), 2);
		Test.isNull(hm.remove(121));
		Test.isNull(hm.remove(56));
		Test.equals(hm.size(), 2);
		Test.print(hm.toString());
		
		Test.header("clear");
		hm.clear();
		Test.equals(hm.size(), 0);
		Test.isNull(hm.get(123234));
		Test.assertion(!hm.containsValue("FourFiveSix"));
		
		Test.print(hm.toString());
		
		Test.results();
	}
}
