package recursiondynamic;

import testing.Test;

//Given an infinite number of coins (25, 10, 5, 1), how many ways are there to represent n cents?
public class Coins {

	public static int coins(int N) {
		int[] memo = new int[N+1];
		return coinsHelper(N, new int[] {25, 10, 5, 1}, 0, memo);
	}
	
	private static int coinsHelper(int amountRemaining, int[] coins, int index, int[] memo) {		
		if(index == coins.length - 1)
			return 1;
		
		int coin = coins[index];
		int combos = 0;
		int remaining = amountRemaining;
		while(remaining >= 0) {
			combos += coinsHelper(remaining, coins, index+1, memo);
			remaining -= coin;
		}
		
		memo[amountRemaining] = combos;
		
		return combos;
	}
	
	public static void main(String[] args) {
		Test.header("Coins");
		
		Test.equals(coins(1), 1);
		Test.equals(coins(5), 2);
		Test.equals(coins(10), 4); //10 penny, 2 nickle, 1 dime, 1 nickle + 5
		Test.equals(coins(11), 4); //11 penny, 2 nickle + 1, 1 dime + 1, 1 nickle + 6
		Test.equals(coins(12), 4);
		Test.equals(coins(13), 4);
		Test.equals(coins(14), 4);
		Test.equals(coins(15), 6); //15 penny, 3 nickle, 1 dime + 1 nickel, 2 nickel + 5 penny, 1 dime + 5 penny
		Test.equals(coins(25), 13); 
		Test.equals(coins(26), 13); 
		Test.equals(coins(100), 242); 
		
		Test.results();
	}
}
