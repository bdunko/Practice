package uncategorized;

import testing.Test;

//Determine if a board of tictactoe has a winner
public class TicTacToeWin {

	//returns the character of the winner, or ' ' if there is no winner
	//if board is improper, returns ' '
	public static char getWinner(char[][] board) {
		//verify board is proper
		int N = board.length;
		for(int i = 0; i < N; i++) {
			if(board[i].length != N)
				return ' ';
		}
		
		
		//check rows
		for(int r = 0; r < board.length; r++) {
			boolean hasWinner = true;
			
			//for each row, check if all values in column match
			char winnerChar = board[r][0];
			if(winnerChar == ' ') //blank can't be a winning character
				continue;
			
			for(int c = 1; c < board[r].length; c++) {
				if(board[r][c] != winnerChar) {
					hasWinner = false;
					break;
				}

			}
			
			if(hasWinner) 
				return winnerChar;
		}
		
		//check columns
		for(int c = 0; c < board[0].length; c++) {
			boolean hasWinner = true;
			
			char winnerChar = board[0][c];
			if(winnerChar == ' ') 
				continue;
			
			for(int r = 1; r < board.length; r++) {
				if(board[r][c] != winnerChar) {
					hasWinner = false;
					break;
				}
			}
			
			if(hasWinner)
				return winnerChar;
		}
		
		//check diagonals
		char winnerChar = board[0][0];
		if(winnerChar != ' ') {
			boolean hasWinner = true;
			for(int d = 1; d < board.length; d++) {
				if(board[d][d] != winnerChar) {
					hasWinner = false;
					break;
				}
			}
			
			if(hasWinner) 
				return winnerChar;
		}
		
		winnerChar = board[board.length-1][0];
		if(winnerChar != ' ') {
			boolean hasWinner = true;
			for(int d = 1; d < board.length; d++) {
				if(board[board.length-1-d][d] != winnerChar) {
					hasWinner = false;
					break;
				}
			}
			
			if(hasWinner)
				return winnerChar;
		}
		
		return ' ';
	}
	
	public static void main(String[] args) {
		Test.header("TicTacToeWin");
		
		char[][][] tests = {
				{
					{' ', ' ', ' '},
					{' ', ' ', ' '},
					{' ', ' ', ' '},
				},
				{
					{'X', 'O', 'O'},
					{'X', ' ', 'O'},
					{'O', 'X', 'X'},
				}, 
				{
					{' ', ' ', ' '},
					{'X', 'X', 'X'},
					{' ', ' ', ' '},
				},
				{
					{'X', ' ', ' '},
					{'X', ' ', ' '},
					{'X', ' ', ' '},
				}, 
				{
					{' ', ' ', 'O'},
					{' ', ' ', 'O'},
					{' ', ' ', 'O'},
				}, 
				{
					{'O', ' ', 'X'},
					{' ', 'O', 'X'},
					{' ', ' ', 'O'},
				}, 
				{
					{'O', ' ', 'X'},
					{' ', 'X', 'X'},
					{'X', ' ', 'O'},
				}, 
				{
					{'O', 'X', 'X'},
					{'X', 'X', 'O'},
					{'O', 'O', 'O'},
				}, 
				{
					{'X', 'X', 'X', 'X', 'O'},
					{'X', 'X', 'X', 'X', 'O'},
					{'X', 'O', 'X', 'X', 'O'},
					{'X', 'O', 'X', 'O', 'O'},
					{'O', 'X', 'O', 'O', 'X'},
				}, 
				{
					{'X', 'X', 'X', 'X', 'O'},
					{'X', 'X', 'X', 'X', 'O'},
					{'X', 'O', 'X', 'X', 'O'},
					{'X', 'O', 'X', 'O', 'O'},
					{'O', 'X', 'O', 'O', 'O'},
				}, 
				{
					{'X', 'X', 'X', 'X', 'O'},
					{'X', 'X', 'X', 'X', 'O'},
					{'X', 'O', 'X', 'X', 'O'},
					{'X', 'O', 'X', 'X', 'O'},
					{'O', 'X', 'O', 'O', 'X'},
				}, 
				{
					{'O', 'X', 'X', 'X', 'O'},
					{'O', 'X', 'X', 'X', 'O'},
					{'O', 'O', 'X', 'X', 'O'},
					{'O', 'O', 'X', 'O', 'O'},
					{'O', 'X', 'O', 'O', 'X'},
				}, 
				{
					{' ', ' ', ' '},
					{' ', ' ', ' '},
					{' ', ' '},
				},
				{
					{' ', ' '},
					{' ', ' ', ' '},
					{' ', ' ', ' '},
				},
				{
					{' ', ' ', ' '},
					{' ', ' ', ' ', ' '},
					{' ', ' ', ' '},
				},
				{
					{' ', ' ', ' ', ' '},
					{' ', ' ', ' ', ' '},
					{' ', ' ', ' '},
				},
		};
		
		char[] answers = {' ', ' ', 'X', 'X', 'O', 'O', 'X', 'O', ' ', 'O', 'X', 'O', ' ', ' ', ' ', ' '};
		
		for(int i = 0; i < tests.length; i++) {
			char[][] test = tests[i];
			char answer = answers[i];
			Test.equals(getWinner(test), answer);
		}
		
		Test.results();
	}
}
