package main;

public class Board {
	private char board[][];
	private int pieces;
	
	public Board() {
		pieces = 3;
		
		char board[][] = {
				{'x', ' ', 'x', ' ', 'x'},
				{'1', ' ', 'x', ' ', 'x'},
				{'2', ' ', 'x', ' ', 'x'},
				{'3', ' ', 'x', ' ', 'x'},
				{'x', 'x', 'x', 'x', 'x'}
				
		};
		
		this.board = board;
	}
	public Board(int pieces) {
		this.pieces = pieces;
		char board[][] = new char[pieces +2][5];
		int placePiece = 0;
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				if(placePiece == 0) {
					if(col % 2 == 0) {
						board[row][col] = 'x';
					}
					else {
						board[row][col] = ' ';
					}
				}
				else if(placePiece > pieces) {
					board[row][col] = 'x';
				}
				else {
					if(col == 0) {
						board[row][col] = (char) (placePiece + 48);
					}
					else if(col % 2 == 0){
						board[row][col] = 'x';
					}
					else {
						board[row][col] = ' ';
					}
				}
			}
			placePiece++;
		}
		
		this.board = board;
		
	}
	
	public char[][] getBoard() {
		return board;
	}
	
	public void movePiece(int piece, char col, int row) {
		if(piece > pieces || pieces <= 0 || row < 0 || row > pieces - 1) {
			System.out.println("Error: Out of bounds");
		}
		int theRow = row + 1; // Should add the ability to have row 0 = the ground
		int theCol = 0;
		
		switch (col){
			case 'A':
				theCol = 0;
				break;
			case 'B':
				theCol = 2;
				break;
			case 'C':
				theCol = 4;
				break;
			case 'a':
				theCol = 0;
				break;
			case 'b':
				theCol = 2;
				break;
			case 'c':
				theCol = 4;
				break;
				
			default:
				System.out.println("Error: Out of bounds");
		}
		
		int originalCol = findPieceCol(piece);
		int originalRow = findPieceRow(piece);
		
		if(originalCol != -1 && originalRow != -1) {
			board[originalRow][originalCol] = 'x';
		}
		else {
			System.out.println("Error: Could not find original piece");
		}
		
	}
	
	/*
	 * Maybe I should make a piece class instead of running everything in the board ¯\_(ツ)_/¯
	 */
	
	private int findPieceCol(int piece) {
		if(piece > pieces || piece <= 0) {
			System.out.println("Error: Out of Bounds");
			return -1;
		}
		
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board.length; col++) {
				if((int) board[row][col] - 48 == piece) {
					return col;
				}
			}
		}
		
		System.out.println("Error: Could not find");
		return -1;
	}
	
	private int findPieceRow(int piece) {
		if(piece > pieces || piece <= 0) {
			System.out.println("Error: Out of Bounds");
			return -1;
		}
		
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board.length; col++) {
				if((int) board[row][col] - 48 == piece) {
					return row;
				}
			}
		}
		
		System.out.println("Error: Could not find");
		return -1;
	}
	
	public String toString() {
		String result = "";
		
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				result+= board[row][col];
			}
			result += "\n";
		}
		
		return result;
	}
}
