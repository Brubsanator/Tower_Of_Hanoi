package main;

public class Board {
	private Piece board[][];
	private int pieces;
	public int BOTTOMROW;
	public int POLEONE = 0;
	public int POLETWO = 2;
	public int POLETHREE = 4;
	private int SOLUTIONPOLE = POLETHREE;
	
	public Board(int pieces) {
		this.pieces = pieces;
		Piece board[][] = new Piece[pieces +2][5];
		int placePiece = 0;
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[row].length; col++) {
				if(placePiece == 0) {
					if(col % 2 == 0) {
						board[row][col] = new Piece(0, col, row);
					}
					else {
						board[row][col] = new Piece(-1, col, row);
					}
				}
				else if(placePiece > pieces) {
					board[row][col] = new Piece(0, col, row);
				}
				else {
					if(col == 0) {
						board[row][col] = new Piece(placePiece, col, row);
					}
					else if(col % 2 == 0){
						board[row][col] = new Piece(0, col, row);
					}
					else {
						board[row][col] = new Piece(-1, col, row);
					}
				}
			}
			placePiece++;
		}
		
		this.board = board;
		BOTTOMROW = board.length - 2;
		
	}
	
	public Piece[][] getBoard() {
		return board;
	}
	
	public void movePiece(Piece piece, int col, int row) {
		if(piece.getValue() > pieces || piece.getValue() <= 0) {
			System.out.println("Error Moving Piece: Piece cannot be moved or doesn't exist");
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
		
		if(legalMove(piece, col, row)) {
			int originalRow = piece.y;
			int originalCol = piece.x;
			
			board[originalRow][originalCol] = board[row][col];
			board[row][col] = piece;
		}
		else {
			System.out.println("Illegal Move!");
		}
	}	
	/*
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
	*/
	
	private boolean legalMove(Piece piece, int col, int row) {
		if(piece.getValue() > pieces || piece.getValue() <= 0) {
			System.out.println("Error Moving Piece: Piece Cannot be moved or does not exist");
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
		if(board[col][row].getValue() == piece.getValue()) {
			return true;
		}
		
		// If piece above it
		if(board[col][row-1].getValue() != 0) {
			return false;
		}
		
		
		// Space is occupied
		if(board[col][row].getValue() != 0) {
			return false;
		}
		
		// If the piece below it is less than the piece we want to move, then it's an illegal move
		if(row != BOTTOMROW && board[col][row+1].getValue() < piece.getValue()) {
			return false;
		}
		
		return true;
	}
	
	public boolean solved() {
		int checkPiece = pieces;
		for(int i = BOTTOMROW; i > 0; i++) {
			if(board[BOTTOMROW][POLETHREE].getValue() != checkPiece) {
				return false;
			}
			pieces--;
		}
		
		return true;
	}
	
	public Piece getPiece(int y, int x) {
		return board[y][x];
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
