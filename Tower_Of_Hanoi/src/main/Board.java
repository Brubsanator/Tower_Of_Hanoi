package main;

public class Board {
	private char board[][];
	private int pieces;
	public int BOTTOMROW;
	public int POLEONE = 0;
	public int POLETWO = 2;
	public int POLETHREE = 4;
	private int SOLUTIONPOLE = POLETHREE;
	
	public Board() {
		pieces = 3;
		
		char board[][] = {
				{'x', ' ', 'x', ' ', 'x'},
				{'1', ' ', 'x', ' ', 'x'},
				{'2', ' ', 'x', ' ', 'x'},
				{'3', ' ', 'x', ' ', 'x'},
				{'x', 'x', 'x', 'x', 'x'}
				
		};
		
		BOTTOMROW = 3;
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
		BOTTOMROW = board.length - 2;
		
	}
	
	public char[][] getBoard() {
		return board;
	}
	
	public void movePiece(int piece, int col, int row) {
		if(piece > pieces || pieces <= 0) {
			System.out.println("Error Moving Piece: Piece Out of bounds");
			return;
		}
		if(row > BOTTOMROW || row < 1) {
			System.out.println("Error Moving Piece: Row Out of bounds");
			return;
		}
		if( col < POLEONE || col > POLETHREE) {
			System.out.println("Error Moving Piece: Col Out of bounds");
			return;
		}
		
		int originalCol = findPieceCol(piece);
		int originalRow = findPieceRow(piece);
		
		if(originalCol != -1 && originalRow != -1) {
			board[originalRow][originalCol] = 'x';
		}
		else {
			System.out.println("Error Moving Piece: Could not find original piece");
			return;
		}
		
		board[row][col] = (char) (piece + 48);
		
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
	
	public boolean solve(int piece, int col) {
		if(piece > pieces || pieces <= 0) {
			System.out.println("Error Solver: Piece Out of bounds");
			return false;
		}
		if(col < 1 || col > 3) {
			System.out.println("Error Solver: Col Out of bounds");
			return false;
		}
		
		if(solved()) {
			return true;
		}
		
		
		if(findPieceCol(piece) == POLETHREE) {
			solve(piece-1, col);
		}
		
		
			
		return false;
	}
	
	private boolean legalMove(int piece, int col, int row) {
		if(piece > pieces || pieces <= 0) {
			System.out.println("Error Moving Piece: Piece Out of bounds");
			return false;
		}
		if(row > BOTTOMROW || row < 1) {
			System.out.println("Error Moving Piece: Row Out of bounds");
			return false;
		}
		if( col < POLEONE || col > POLETHREE) {
			System.out.println("Error Moving Piece: Col Out of bounds");
			return false;
		}
		
		// Piece already there
		if(board[col][row] == (char) (piece + 48)) {
			return true;
		}
		
		// If piece above it
		if(board[col][row-1] != 'x') {
			return false;
		}
		
		
		// Space is occupied
		if(board[col][row] != 'x') {
			return false;
		}
		
		// If the piece below it is less than the piece we want to move, then it's an illegal move
		if(row != BOTTOMROW && (int) (board[col][row+1] - 48) < piece) {
			return false;
		}
		
		return true;
	}
	
	public boolean solved() {
		int checkPiece = pieces;
		for(int i = BOTTOMROW; i > 0; i++) {
			if(board[BOTTOMROW][POLETHREE] != (char) (checkPiece + 48)) {
				return false;
			}
			pieces--;
		}
		
		return true;
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
