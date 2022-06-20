package ds;

import testing.Test;

public class BArrayList<E> {

	private static int RC_ERROR = -1;
	private static int DEFAULT_CAPACITY = 2;
	private static int RESIZE_FACTOR = 2;
	
	private Object[] array;
	private int size;
	
	public BArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	public BArrayList(int capacity) {
		if(capacity < DEFAULT_CAPACITY)
			capacity = DEFAULT_CAPACITY;
		
		array = new Object[capacity];
		size = 0;
	}
	
	private void ensureCapacity() {
		if(size == array.length) {
			Object[] biggerArray = new Object[array.length * RESIZE_FACTOR];
			for(int i = 0; i < array.length; i++) {
				biggerArray[i] = array[i];
			}
			array = biggerArray;
		}
	}
	
	public void add(E elem) {
		ensureCapacity();
		array[size] = elem;
		size++;
	}
	
	public boolean insert(E elem, int index) {
		if(index > size || index < 0 || elem == null)
			return false;
		
		ensureCapacity();
		for(int i = size; i != index; i--) {
			array[i] = array[i-1];
		}
		array[index] = elem;
		size++;
		
		return true;
	}
	
	public boolean remove(int index) {
		if(index > size || index < 0)
			return false;
		
		for(int i = index; i < size-1; i++) {
			array[i] = array[i+1];
		}
		size--;
		
		return true;
	}
	
	public boolean remove(E elem) {
		if(elem == null)
			return false;
		
		return remove(indexOf(elem));
	}
	
	public void clear() {
		for(int i = 0; i < size; i++) {
			array[i] = null;
		}
		size = 0;
	}
	
	public boolean contains(E elem) {
		return indexOf(elem) != RC_ERROR;
	}
	
	public int indexOf(E elem) {
		if(elem == null)
			return RC_ERROR;
		
		for(int i = 0; i < size; i++) {
			if(elem.equals(array[i])) {
				return i;
			}
		}
		return RC_ERROR;
	}
	
	@SuppressWarnings("unchecked")
	public E get(int index) {
		if(index > size || index < 0)
			return null;
		
		return (E)array[index];
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public int capacity() {
		return array.length;
	}
	
	public boolean set(E elem, int index) {
		if(index > size || index < 0 || elem == null)
			return false;
	
		array[index] = elem;
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("BArrayList - Size = ").append(size).append(" - [ ");
		for(int i = 0; i < array.length; i++) {
			if(array[i] == null)
				sb.append("(NULL),");
			else
				sb.append(array[i].toString()).append(",");
		}
		if(sb.charAt(sb.length()-1) == ',')
			sb.deleteCharAt(sb.length()-1);
		sb.append(" ]");
		
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof BArrayList)) 
			return false;
		
		@SuppressWarnings("unchecked")
		BArrayList<E> other = (BArrayList<E>) o;
		
		if(this.size != other.size)
			return false;
		
		for(int i = 0; i < this.size; i++) {
			if(!this.array[i].equals(other.array[i]))
				return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		//Test constructors
		Test.header("constructors");
		BArrayList<String> bar = new BArrayList<String>(10);
		Test.equals(bar.size(), 0);
		Test.equals(bar.capacity(), 10);
		
		bar = new BArrayList<String>();
		Test.equals(bar.size(), 0);
		Test.equals(bar.capacity(), DEFAULT_CAPACITY);
		
		//Test isEmpty
		Test.header("isEmpty");
		Test.assertion(bar.isEmpty());
		
		//Test add
		Test.header("add");
		for(int i = 0; i < 100; i++) {
			bar.add("" + i);
			Test.equals(bar.size(), i+1);
		}
		Test.equals(bar.capacity(), 128);
		Test.assertion(!bar.isEmpty());
		
		//Test get
		Test.header("get");
		Test.equals(bar.get(50), "50");
		Test.isNull(bar.get(-100));
		Test.isNull(bar.get(134343));
		
		//Test indexOf
		Test.header("indexOf");
		Test.equals(bar.indexOf("68"), 68);
		Test.equals(bar.indexOf("nothing"), RC_ERROR);
		Test.equals(bar.indexOf(null), RC_ERROR);
		
		//Test contains
		Test.header("contains");
		Test.assertion(bar.contains("0"));
		Test.assertion(!bar.contains("hello"));
		Test.assertion(!bar.contains(null));
		
		//Test insert
		Test.header("insert");
		bar.insert("WORLD", 0);
		Test.equals(bar.size(), 101);
		Test.equals(bar.indexOf("0"), 1);
		Test.equals(bar.indexOf("WORLD"), 0);
		Test.equals(bar.indexOf("99"), 100);
		Test.logp(bar.toString());
		
		//Test remove
		Test.header("remove");
		bar.remove(0);
		Test.equals(bar.size(), 100);
		Test.equals(bar.get(54), "54");
		Test.assertion(!bar.contains("WORLD"));
		Test.assertion(!bar.remove(-1));
		Test.assertion(!bar.remove(18912189));
		
		Test.header("remove2");
		bar.remove("72");
		Test.equals(bar.size(), 99);
		Test.assertion(!bar.contains("72"));
		Test.equals(bar.get(71), "71");
		Test.equals(bar.get(72), "73");
		Test.equals(bar.get(0), "0");
		Test.equals(bar.indexOf("99"), 98);
		
		//Test set
		Test.header("set");
		bar.set("HI", 5);
		Test.equals(bar.indexOf("HI"), 5);
		Test.equals(bar.size(), 99);
		Test.assertion(!bar.contains("5"));
		Test.logp(bar.toString());
		
		//Test clear
		Test.header("clear");
		bar.clear();
		Test.assertion(bar.isEmpty());
		
		Test.results();
	}
}
