package recursiondynamic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import testing.Test;

public class NQueens {

	private static class Board {
		boolean[][] board;
		int size;
		
		public Board(int size) {
			this.board = new boolean[size][size];
			this.size = size;
		}
		
		public Board clone() {
			Board cloned = new Board(this.size);
			cloned.board = this.board.clone();
			for(int i = 0; i < cloned.size; i++) {
				cloned.board[i] = this.board[i].clone();
			}
			return cloned;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			for(int x = 0; x < size; x++) {
				for(int y = 0; y < size; y++) {
					sb.append(board[x][y] ? "X" : "O");
				}
				sb.append("\n");
			}
			
			return sb.toString();
		}
	}
	
	public static List<Board> placeQueens(int boardSize) {
		List<Board> answers = new LinkedList<Board>();
		
		queensHelper(new Board(boardSize), new Board(boardSize), 0, answers);
		
		return answers;
	}
	
	public static void queensHelper(Board answer, Board possibilities, int rowIndex, List<Board> answers) {
		//if all queens have been placed
		if(rowIndex == answer.size) {
			answers.add(answer);
			return;
		}
		
		//place queen on all possible spots in this row, and recur
		for(int column = 0; column < answer.size; column++) {
			boolean canPlaceQueen = !possibilities.board[rowIndex][column];
			if(canPlaceQueen) {
				//place queen on answer
				Board newAnswer = answer.clone();
				newAnswer.board[rowIndex][column] = true; 
				
				//update possibilities
				Board newPossibilities = placeQueenOnPossibilitiesBoard(possibilities.clone(), rowIndex, column);
				
				//recur
				queensHelper(newAnswer, newPossibilities, rowIndex+1, answers);
			}
		}
	}
	
	private static Board placeQueenOnPossibilitiesBoard(Board possibilities, int row, int column) {
		//mark placement as impossible (as there is already a queen there)
		possibilities.board[row][column] = true;
		
		//row & column
		for(int i = 0; i < possibilities.size; i++) {
			possibilities.board[row][i] = true;
			possibilities.board[i][column] = true;
		}
		
		//diagonals
		markDiagonals(possibilities, row, column, 1, 1);
		markDiagonals(possibilities, row, column, 1, -1);
		markDiagonals(possibilities, row, column, -1, 1);
		markDiagonals(possibilities, row, column, -1, -1);
		
		return possibilities;
	}
	
	private static void markDiagonals(Board possibilities, int x, int y, int deltaX, int deltaY) {
		x += deltaX;
		y += deltaY;
		while(x >= 0 && x < possibilities.size && y >= 0 && y < possibilities.size) {
			possibilities.board[x][y] = true;
			x += deltaX;
			y += deltaY;
		}
	}
	
	private static boolean verifyDiagonal(boolean[][] board, int x, int y, int deltaX, int deltaY) {
		x += deltaX;
		y += deltaY;
		while(x >= 0 && x < board.length && y >= 0 && y < board[x].length) {
			if(board[x][y])
				return false;
			x += deltaX;
			y += deltaY;
		}
		return true;
	}
	
	private static boolean verify(List<Board> boards, int expected) {
		if(boards == null)
			return false;
		
		if(boards.size() != expected) {
			System.out.printf("Size mismatched: was %d expected %d\n", boards.size(), expected);
			return false;
		}
		
		for(Board b : boards) {
			for(int x = 0; x < b.size; x++) {
				for(int y = 0; y < b.size; y++) {
					//if a queen is at this position, check row, column, and diagonal
					if(b.board[x][y]) {
						
						//row/column
						for(int i = 0; i < b.size; i++) {
							if(b.board[x][i] && i != y) 
								return false;
							if(b.board[i][y] && i != x) 
								return false;
						}
						
						//check diagonals
						if(!verifyDiagonal(b.board, x, y, -1, -1) || 
								!verifyDiagonal(b.board, x, y, 1, -1) || 
								!verifyDiagonal(b.board, x, y, -1, 1) ||
								!verifyDiagonal(b.board, x, y, 1, 1))
							return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private static boolean verify(Board board) {
		List<Board> l = new ArrayList<Board>();
		l.add(board);
		return verify(l, 1);
	}
	
	public static void main(String[] args) {
		Test.header("NQueens");
		
		Test.header("verify");
		Board b = new Board(8);
		Test.assertion(verify(b));
		b.board[3][1] = true;
		b.board[3][5] = true;
		Test.assertion(!verify(b));
		b.board[3][1] = false;
		b.board[7][5] = true;
		Test.assertion(!verify(b));
		b.board[7][5] = false;
		b.board[5][7] = true; //add both
		Test.assertion(!verify(b));
		b.board[5][7] = false;
		b.board[0][2] = true; //sub both
		Test.assertion(!verify(b));
		b.board[0][2] = false;
		b.board[4][4] = true; //add x sub y
		Test.assertion(!verify(b));
		b.board[4][4] = false;
		b.board[1][7] = true; //sub x add y
		Test.assertion(!verify(b));
		b.board[1][7] = false;
		b.board[0][0] = true; //non-conflict
		Test.assertion(verify(b));
		
		Test.header("Place Queens");
		Test.assertion(verify(placeQueens(1), 1));
		Test.assertion(verify(placeQueens(2), 0));
		Test.assertion(verify(placeQueens(3), 0));
		Test.assertion(verify(placeQueens(4), 2));
		Test.assertion(verify(placeQueens(5), 10));
		Test.assertion(verify(placeQueens(6), 4));
		Test.assertion(verify(placeQueens(7), 40));
		Test.assertion(verify(placeQueens(8), 92));
		Test.assertion(verify(placeQueens(9), 352));
		Test.assertion(verify(placeQueens(10), 724));
		
		Test.results();
	}
}
