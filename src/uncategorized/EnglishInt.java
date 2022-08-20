package uncategorized;

import java.util.Map;

import testing.Test;
import static java.util.Map.entry;


//convert any integer into a human-readable string
public class EnglishInt {
	
	private static int leadingDigit(int x) {
		while(x >= 10)
			x /= 10;
		return x;
	}
	
	private static String threeDigitToString(int x, Map<Integer, String> belowTwentyMap, Map<Integer, String> tensMap) {
		StringBuilder sb = new StringBuilder();
		
		if(x >= 100) {
			sb.append(belowTwentyMap.get(leadingDigit(x)));
			sb.append(" hundred ");
			x = x % 100; //get tens
		}
		
		//nineteen and below are special cases
		if(x <= 19 && x != 0) {
			sb.append(belowTwentyMap.get(x));
		} 
		else if (x != 0) { 
			//otherwise we need twenty, thirty, fourty... etc
			//divide by 10 * 10 gets to just 20, 30, 40...
			sb.append(tensMap.get((x/10)*10)).append(" ");
			
			x = x % 10; //get ones
			if(x != 0) { 
				sb.append(belowTwentyMap.get(x));
			}
		}
		
		if(sb.charAt(sb.length()-1) == ' ')
			sb.deleteCharAt(sb.length()-1);
		
		return sb.toString();
	}
	
	private static String intToEnglish(int n) {
		if(n == 0)
			return "zero";
		if(n == Integer.MIN_VALUE)
			return "negative two billion one hundred fourty seven million four hundred eighty three thousand six hundred fourty eight";
		
		//create maps
		Map<Integer, String> belowTwentyMap = Map.ofEntries(
				entry(1, "one"),
				entry(2, "two"),
				entry(3, "three"),
				entry(4, "four"),
				entry(5, "five"),
				entry(6, "six"),
				entry(7, "seven"),
				entry(8, "eight"),
				entry(9, "nine"),
				entry(10, "ten"),
				entry(11, "eleven"),
				entry(12, "twelve"),
				entry(13, "thirteen"),
				entry(14, "fourteen"),
				entry(15, "fifteen"),
				entry(16, "sixteen"),
				entry(17, "seventeen"),
				entry(18, "eighteen"),
				entry(19, "nineteen")
		);
		
		Map<Integer, String> tensMap = Map.ofEntries(
				entry(20, "twenty"),
				entry(30, "thirty"),
				entry(40, "fourty"),
				entry(50, "fifty"),
				entry(60, "sixty"),
				entry(70, "seventy"),
				entry(80, "eighty"),
				entry(90, "ninety")
		);
		
		StringBuilder sb = new StringBuilder();
		
		//handle negatives
		if(n < 0) {
			sb.append("negative ");
			n *= -1;
		}
		
		int billions = n / 1_000_000_000;
		n = n % 1_000_000_000;
		int millions = n / 1_000_000;
		n = n % 1_000_000;
		int thousands = n / 1_000;
		n = n % 1_000;
		int rest = n;
		
		if(billions != 0) {
			sb.append(threeDigitToString(billions, belowTwentyMap, tensMap));
			sb.append(" billion ");
		}
		
		if(millions != 0) {
			sb.append(threeDigitToString(millions, belowTwentyMap, tensMap));
			sb.append(" million ");
		}
		
		if(thousands != 0) {
			sb.append(threeDigitToString(thousands, belowTwentyMap, tensMap));
			sb.append(" thousand ");
		}
		
		if(rest != 0) {
			sb.append(threeDigitToString(rest, belowTwentyMap, tensMap));
		}
		
		if(sb.charAt(sb.length()-1) == ' ')
			sb.deleteCharAt(sb.length()-1);
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Test.header("EnglishInt");
		
		Test.equals(intToEnglish(1), "one");
		Test.equals(intToEnglish(2), "two");
		Test.equals(intToEnglish(3), "three");
		Test.equals(intToEnglish(4), "four");
		Test.equals(intToEnglish(5), "five");
		Test.equals(intToEnglish(6), "six");
		Test.equals(intToEnglish(7), "seven");
		Test.equals(intToEnglish(8), "eight");
		Test.equals(intToEnglish(9), "nine");
		Test.equals(intToEnglish(0), "zero");
		Test.equals(intToEnglish(-8), "negative eight");
		Test.equals(intToEnglish(Integer.MAX_VALUE), "two billion one hundred fourty seven million four hundred eighty three thousand six hundred fourty seven");
		Test.equals(intToEnglish(Integer.MIN_VALUE+1), "negative two billion one hundred fourty seven million four hundred eighty three thousand six hundred fourty seven");
		Test.equals(intToEnglish(Integer.MIN_VALUE), "negative two billion one hundred fourty seven million four hundred eighty three thousand six hundred fourty eight");
		Test.equals(intToEnglish(1234), "one thousand two hundred thirty four");
		Test.equals(intToEnglish(100000), "one hundred thousand");
		Test.equals(intToEnglish(301000000), "three hundred one million");
		Test.equals(intToEnglish(21), "twenty one");
		Test.equals(intToEnglish(421), "four hundred twenty one");
		Test.equals(intToEnglish(19), "nineteen");
		Test.equals(intToEnglish(1020351401), "one billion twenty million three hundred fifty one thousand four hundred one");

		Test.results();
	}
}
