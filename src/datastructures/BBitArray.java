package datastructures;

import testing.Test;

public class BBitArray {

	private long[] chunks;
	private int size;
	
	private static int DEFAULT_SIZE = 64;
	
	public BBitArray() {
		this(DEFAULT_SIZE);
	}
	
	public BBitArray(int size) {
		int arraySize = ((size-1) / 64) + 1;
		
		this.chunks = new long[arraySize];
		this.size = size;
	}
	
	public void set(int bit, boolean to) {
		if(bit >= size || bit < 0)
			throw new ArrayIndexOutOfBoundsException();
		
		int arrayIndex = bit / 64;
		int bitIndex = bit % 64;
		
		if(to) {
			long mask = 1L << bitIndex;
			chunks[arrayIndex] |= mask;
		} else {
			long mask = ~(1L << bitIndex);
			chunks[arrayIndex] &= mask;
		}
	}
	
	public boolean get(int bit) {
		if(bit >= size || bit < 0)
			throw new ArrayIndexOutOfBoundsException();
		
		int arrayIndex = bit / 64;
		int bitIndex = bit % 64;
		
		long mask = 1L << bitIndex;
		
		return (chunks[arrayIndex] & mask) != 0;
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int b = 0; b < size; b++) {
			sb.append(get(b) ? 1 : 0);
		}
		return sb.toString();
	}
	
	private static void verifyException(BBitArray ba, int bit) {
		try {
			ba.get(bit);
			Test.fail("Did not cause expectedexception");
		} catch (Exception e) {
			Test.success("Caused exception.");
		}
		try {
			ba.set(bit, true);
			Test.success("Caused exception.");
		} catch (Exception e) {
			Test.success("Caused exception.");
		}
	}
	
	public static void main(String[] args) {
		Test.header("BBitArray");
		
		Test.header("Constructors");
		//test default constructor
		BBitArray ba = new BBitArray();
		Test.equals(ba.size(), DEFAULT_SIZE);
		
		//test with length of multiple longs
		ba = new BBitArray(4 * DEFAULT_SIZE); //4 longs = 256 bits
		
		//verify all bits start at 0
		boolean success = true;
		for(int i = 0; i < ba.size(); i++) {
			if(ba.get(i))
				success = false;
		}
		
		if(success)
			Test.success("All bits are 0!");
		else
			Test.fail("Not all bits are 0!");
		
		Test.equals(ba.toString(), "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		
		Test.header("flip bits");
		ba.set(12, true);
		System.out.println(ba);
		Test.equals(ba.get(12), true);
		Test.equals(ba.get(11), false);
		Test.equals(ba.get(13), false);
		Test.equals(ba.get(0), false);
		ba.set(12, false);
		Test.equals(ba.get(12), false);
		Test.equals(ba.get(11), false);
		Test.equals(ba.get(13), false);
		Test.equals(ba.get(0), false);
		
		Test.header("Edges");
		//check edges
		boolean alternate = true;
		for(int i = 0; i < ba.size() / 64; alternate = !alternate) {
			ba.set(i, true);
			Test.equals(ba.get(i), true);
			ba.set(i, false);
			Test.equals(ba.get(i), false);
			
			if(alternate)
				i += 63;
			else
				i += 1;
		}
		
		Test.header("Set all to true");
		//set all bits to true
		for(int i = -1; ++i < ba.size(); ba.set(i, true));
		
		success = true;
		//verify they're all true
		for(int i = 0; i < ba.size; i++)
			if(!ba.get(i)) 
				success = false;

		Test.equals(ba.toString(), "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
		
		if(success)
			Test.success("All values were 1s");
		else
			Test.fail("Some values were not 1s.");
		
		//now false one chunk and check
		//for(int i = 12; ++i < 64; ba.set(i, false));
		
		Test.header("False one chunk");
		for(int i = 0; i < 64; i++)  {
			ba.set(i, false);
		}
		
		Test.equals(ba.get(0), false);
		Test.equals(ba.get(1), false);
		Test.equals(ba.get(10), false);
		Test.equals(ba.get(20), false);
		Test.equals(ba.get(62), false);
		Test.equals(ba.get(63), false);
		Test.equals(ba.get(64), true);
		Test.equals(ba.get(65), true);
		Test.equals(ba.get(70), true);
		Test.equals(ba.get(127), true);
		Test.equals(ba.get(250), true);
		Test.equals(ba.get(140), true);
		Test.equals(ba.get(130), true);
		Test.equals(ba.get(80), true);
		
		System.out.println(ba.toString());
		Test.equals(ba.toString(), "0000000000000000000000000000000000000000000000000000000000000000111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
		
		Test.header("Weird size");
		//try a weird size
		ba = new BBitArray(12);
		Test.equals(ba.get(0), false);
		Test.equals(ba.get(11), false);
		verifyException(ba, 12);
		verifyException(ba, 63);
		for(int i = 0; i < ba.size(); i++)
			ba.set(i, true);
		Test.equals(ba.get(0), true);
		Test.equals(ba.get(11), true);
		verifyException(ba, 12);
		verifyException(ba, 63);
		
		Test.results();
	}
}
