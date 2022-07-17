package bitmanip;

import testing.Test;

//A screen is stored as an array of bytes, so that 8 pixels can be stores in one byte. Given the width in bits of the screen, draw a line from (x1, y) to (x2, y)
public class DrawLine {

	public static byte[] drawLine(byte[] screen, int width, int x1, int x2, int y) {
		//what is the starting index in the byte array?
		int bytesPerLine = width/8;
		int screenIndex = bytesPerLine * y; //move to correct y
		screenIndex += (x1 / 8); //move forward on y to starting byte
		
		int bitsLeft = (x2 - x1) + 1; //how many bits total need to be flipped?
		int bitOffset = x1 % 8; //what if the first byte's starting bit is not 0?
		
		while(bitsLeft != 0) {
			
			//how many bits need to be flipped in this current byte?
			int bitsToFlip = Math.min(bitsLeft, 8-bitOffset);
			
			//create mask to flip that many bits
			//example: if we need to flip 5 bits, we create the mask 11111000
			//however, say we need to flip 5 but offset is 1. Then we would need to do 11111100
			//the next mask will remove the one bit from the front giving 01111100 as intended
			int byteMask = (0b11111111 << (8-bitsToFlip-bitOffset));
			
			//if offset is not 0, we will need to remove some 1s from front
			//example: if offset is 2, we need to create the mask 00111111 and use &
			//so we take 1, shift it over 6 places to get 01000000, then subtract 1 to get 00111111
			//if offset is 0, we create the mask 11111111, which does nothing when we use &
			int offsetMask = ((1 << (8-bitOffset))-1);
			byteMask &= offsetMask; //apply offset mask
			
			//apply mask to byte
			screen[screenIndex] |= (byte) byteMask;
			
			bitOffset = 0;
			bitsLeft -= bitsToFlip;
			screenIndex++;
		}
		
		return screen;
	}
	
	private static boolean get(byte b, int index) {
		int mask = 1 << index;
		return (b & mask) != 0;
	}
	
	private static String toBinaryString(byte b) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 8; i++) {
			sb.insert(0, get(b, i) ? 1 : 0);
		}
		return sb.toString();
	}
	
	public static String screenToString(byte[] screen, int width) {
		int bitsOnLine = 0;
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < screen.length; i++) {
			sb.append(toBinaryString(screen[i]));
			bitsOnLine += 8;
			if(bitsOnLine == width) {
				sb.append("\n");
				bitsOnLine = 0;
			}
		}
		
		return sb.toString();
	}
	
	private static byte[] screen() {
		byte[] screen = { (byte) 0b00000000, (byte) 0b00000000, (byte) 0b00000000, (byte)0b00000000, (byte)0b00000000, (byte)0b00000000, (byte)0b00000000, (byte)0b00000000 , (byte)0b00000000 , (byte)0b00000000 };
		return screen;
	}
	
	public static void main(String[] args) {
		Test.header("DrawLine");
		
		Test.equals(screenToString(drawLine(screen(), 16, 0, 7, 2), 16), "0000000000000000\n0000000000000000\n1111111100000000\n0000000000000000\n0000000000000000\n");
		Test.equals(screenToString(drawLine(screen(), 16, 0, 15, 2), 16), "0000000000000000\n0000000000000000\n1111111111111111\n0000000000000000\n0000000000000000\n");
		Test.equals(screenToString(drawLine(screen(), 16, 0, 7, 0), 16), "1111111100000000\n0000000000000000\n0000000000000000\n0000000000000000\n0000000000000000\n");
		Test.equals(screenToString(drawLine(screen(), 16, 0, 15, 4), 16), "0000000000000000\n0000000000000000\n0000000000000000\n0000000000000000\n1111111111111111\n");
		
		Test.equals(screenToString(drawLine(screen(), 16, 0, 13, 4), 16), "0000000000000000\n0000000000000000\n0000000000000000\n0000000000000000\n1111111111111100\n");
		Test.equals(screenToString(drawLine(screen(), 16, 2, 15, 4), 16), "0000000000000000\n0000000000000000\n0000000000000000\n0000000000000000\n0011111111111111\n");
		Test.equals(screenToString(drawLine(screen(), 16, 3, 14, 4), 16), "0000000000000000\n0000000000000000\n0000000000000000\n0000000000000000\n0001111111111110\n");
		Test.equals(screenToString(drawLine(screen(), 16, 2, 4, 4), 16), "0000000000000000\n0000000000000000\n0000000000000000\n0000000000000000\n0011100000000000\n");
		Test.equals(screenToString(drawLine(screen(), 16, 1, 6, 0), 16), "0111111000000000\n0000000000000000\n0000000000000000\n0000000000000000\n0000000000000000\n");
		Test.equals(screenToString(drawLine(screen(), 16, 3, 3, 0), 16), "0001000000000000\n0000000000000000\n0000000000000000\n0000000000000000\n0000000000000000\n");
		Test.equals(screenToString(drawLine(screen(), 16, 0, 0, 0), 16), "1000000000000000\n0000000000000000\n0000000000000000\n0000000000000000\n0000000000000000\n");
		
		Test.equals(screenToString(drawLine(screen(), 8, 2, 7, 0), 8), "00111111\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n");
		Test.equals(screenToString(drawLine(screen(), 40, 1, 27, 0), 40), "0111111111111111111111111111000000000000\n0000000000000000000000000000000000000000\n");
		Test.equals(screenToString(drawLine(screen(), 40, 17, 18, 0), 40), "0000000000000000011000000000000000000000\n0000000000000000000000000000000000000000\n");
		
		Test.results();
	}
}
