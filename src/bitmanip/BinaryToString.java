package bitmanip;

import testing.Test;

public class BinaryToString {

	private static String binaryToString(int bin) {
		if(bin == 0)
			return "0";
		
		StringBuilder sb = new StringBuilder();
		
		int n = (int) bin;
		while(n != 0) {
			sb.insert(0, n % 2);
			n = n / 2;
		}
		
		return sb.toString();
	}
	
	public static String binaryToString(double bin) {
		String beforeDecimal = binaryToString((int)bin);
		
		//get just the decimal part
		bin = bin - (int)bin;
		
		StringBuilder sb = new StringBuilder();
		double frac = 0.5;
		while(bin > 0) {
			if(sb.length() >= 32)
				return "ERROR";
			
			if(bin >= frac) {
				sb.append(1);
				bin -= frac;
			} else {
				sb.append(0);
			}
			
			frac = frac/2;
		}
		
		return sb.insert(0, ".").insert(0, beforeDecimal).toString();
	}
	
	public static void main(String[] args) {
		Test.header("BinaryToString");
		
		Test.equals(binaryToString(6), "110");
		Test.equals(binaryToString(0), "0");
		Test.equals(binaryToString(1), "1");
		Test.equals(binaryToString(7), "111");
		Test.equals(binaryToString(8), "1000");
		Test.equals(binaryToString(8.5), "1000.1");
		Test.equals(binaryToString(8.625), "1000.101");
		Test.equals(binaryToString(9.75), "1001.11");
		Test.equals(binaryToString(0.828125), "0.110101");
		Test.equals(binaryToString(1.0/3), "ERROR");
		
		Test.results();
	}
}
