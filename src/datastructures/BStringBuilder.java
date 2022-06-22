package datastructures;

import testing.Test;

//Simple stringbuilder implementation
public class BStringBuilder {
	
	private static int RESIZE_FACTOR = 2;
	private char[] buffer;
	private int length;
	
	public BStringBuilder() {
		buffer = new char[1];
		length = 0;
	}
	
	public BStringBuilder append(BStringBuilder toAppend) {
		for(int i = 0; i < toAppend.length; i++) {
			append(toAppend.buffer[i]);
		}
		return this;
	}
	
	public BStringBuilder append(String toAppend) {
		//one at a time, add each character to the buffer
		for(int i = 0; i < toAppend.length(); i++) {
			append(toAppend.charAt(i));
		}
		return this;
	}
	
	public BStringBuilder append(char toAppend) {
		checkBufferAndGrowIfNeeded();
		buffer[length] = toAppend;
		length++;
		return this;
	}
	
	public BStringBuilder insert(String toInsert, int index) {
		checkBufferAndGrowIfNeeded(toInsert.length());
		
		//copy part of the existing string to later in the buffer to make space for insertion
		copyOver(index, length, toInsert.length());
		
		//copy the inserted string into buffer at given index
		for(int c = 0; c < toInsert.length(); c++) {
			char insertionChar = toInsert.charAt(c);
			int insertionIndex = index + c;
			buffer[insertionIndex] = insertionChar;
		}
		
		//increment length
		length += toInsert.length();
		
		return this;
	}
	
	public BStringBuilder insert(char toInsert, int index) {
		checkBufferAndGrowIfNeeded();
		
		copyOver(index, length, 1);
		buffer[index] = toInsert;
		length++;
		
		return this;
	}
	
	public int length() {
		return length;
	}
	
	private void copyOver(int startIndex, int endIndex, int spacesMoved) {
		for(int i = endIndex; i != startIndex-1; i--) 
		{
			buffer[i + spacesMoved] = buffer[i];
		}
	}
	
	private void checkBufferAndGrowIfNeeded() {
		checkBufferAndGrowIfNeeded(1);
	}
	
	private void checkBufferAndGrowIfNeeded(int increase) {
		if(length+increase >= buffer.length) {
			//if so, create new larger buffer and copy over old buffer
			char[] largerBuffer = new char[buffer.length * RESIZE_FACTOR];
			for(int j = 0; j < buffer.length; j++) {
				largerBuffer[j] = buffer[j];
			}
			buffer = largerBuffer;
		}
	}
	
	@Override
	public String toString() {
		return new String(buffer).substring(0, length);
	}
	
	public static void main(String[] args) {
		BStringBuilder b = new BStringBuilder();
		
		b.append("ABCDE");
		Test.equals(b.toString(), "ABCDE");
		Test.equals(b.length(), 5);
		
		b.append("FGH").append('Z');
		Test.equals(b.toString(), "ABCDEFGHZ");
		Test.equals(b.length(), 9);
		
		b.insert('3', 2);
		Test.equals(b.toString(), "AB3CDEFGHZ");
		Test.equals(b.length(), 10);
		
		b.insert('S', 0);
		Test.equals(b.toString(), "SAB3CDEFGHZ");
		Test.equals(b.length(), 11);
		
		b.insert('E', 11);
		Test.equals(b.toString(), "SAB3CDEFGHZE");
		Test.equals(b.length(), 12);
		
		b.insert("MID", 5);
		Test.equals(b.toString(), "SAB3CMIDDEFGHZE");
		Test.equals(b.length(), 15);
		
		b.insert("END", 15);
		Test.equals(b.toString(), "SAB3CMIDDEFGHZEEND");
		Test.equals(b.length(), 18);
		
		b.insert("START", 0);
		Test.equals(b.toString(), "STARTSAB3CMIDDEFGHZEEND");
		Test.equals(b.length(), 23);
		
		Test.results();
	}
}
