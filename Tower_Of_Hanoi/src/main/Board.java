package main;

public class Board {
	private Piece board[][];
	private int pieces;
	public int BOTTOMROW;
	public int POLEONE = 0;
	public int POLETWO = 2;
	public int POLETHREE = 4;
	private int SOLUTIONPOLE = POLETHREE;
	private int OPPOSITEPOLE = POLETWO;
	
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
	
	public void movePiece(Piece piece, int col) {
		if(piece.getValue() > pieces || piece.getValue() <= 0) {
			System.out.println("Error Moving Piece: Piece cannot be moved or doesn't exist");
			return;
		}
		if(col < POLEONE || col > POLETHREE) {
			System.out.println("Error Moving Piece: Col Out of bounds");
			return;
		}
		
		for(int row = BOTTOMROW; row > 0; row--) {
			if(legalMove(piece, col, row)) {
				int originalRow = piece.y;
				int originalCol = piece.x;
				
				board[row][col] = piece;
				board[row][col].x = col;
				board[row][col].y = row;
				board[originalRow][originalCol] = new Piece(0, originalCol, originalRow);
				return;
			}
		}
		
		System.out.println("Error Move Piece: Cannot move piece");
	}
	
	public boolean solve(Piece piece, int col) {
		if(piece.getValue() > pieces || pieces <= 0) {
			System.out.println("Error Solver: Piece Out doesn't exist or unable to move");
			return false;
		}
		if(col < POLEONE || col > POLETHREE) {
			System.out.println("Error Solver: Col Out of bounds");
			return false;
		}
		
		if(solved(piece.getValue(), SOLUTIONPOLE)) {
			return true;
		}
		
		if(piece.getValue() == 1) {
			movePiece(piece, col);
			return true;
		}
		
		// Has piece on top
		if(board[piece.y-1][piece.x].getValue() != 0) {
			swapPoles(piece.x);
			solve(board[piece.y-1][piece.x], SOLUTIONPOLE);
			swapPoles(piece.x);
		}
		
		
		//System.out.println("Passes Through");
		movePiece(piece, col);
		//System.out.println(piece.getValue());
		solve(findPiece(piece.getValue()-1), SOLUTIONPOLE);
		
		
		return true;
	}

	private boolean swapPoles(int originPole) {
		switch (originPole) {
		case 0: // POLE ONE
			if(SOLUTIONPOLE == POLETHREE) {
				SOLUTIONPOLE = POLETWO;
				OPPOSITEPOLE = POLETHREE;
			}
			else {
				SOLUTIONPOLE = POLETHREE;
				OPPOSITEPOLE = POLETWO;
			}
			break;
		case 2: // POLE TWO
			if(SOLUTIONPOLE == POLETHREE) {
				SOLUTIONPOLE = POLEONE;
				OPPOSITEPOLE = POLETHREE;
			}
			else {
				SOLUTIONPOLE = POLETHREE;
				OPPOSITEPOLE = POLEONE;
			}
			break;
		case 4: // POLE THREE
			if(SOLUTIONPOLE == POLEONE) {
				SOLUTIONPOLE = POLETWO;
				OPPOSITEPOLE = POLEONE;
			}
			else {
				SOLUTIONPOLE = POLEONE;
				OPPOSITEPOLE = POLETWO;
			}
			break;
		default:
			System.out.println("Error Swapper: Could not find origin pole");
			return false;
		}
		
		return true;
	}
	
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
		if(board[piece.y-1][piece.x].getValue() != 0) {
			//System.out.println("Above it");
			return false;
		}
		
		
		// Space is occupied
		if(board[row][col].getValue() != 0) {
			//System.out.println("Occupied");
			return false;
		}
		
		// If the piece below it is less than the piece we want to move, then it's an illegal move
		if(row != BOTTOMROW && board[row+1][col].getValue() < piece.getValue()) {
			//System.out.println("Too low");
			return false;
		}
		
		return true;
	}
	
	public boolean solved(int value, int col) {
		Piece piece = findPiece(value);
		if(piece.x != col) {
			return false;
		}
		if(value == 1) {
			return true;
		}
		return solved(value-1, col);
	}
	
	public Piece findPiece(int value) {
		for(int row = 1; row <= BOTTOMROW; row++) {
			for(int col = POLEONE; col <= POLETHREE; col+=2) {
				if(board[row][col].getValue() == value) {
					return board[row][col];
				}
			}
		}
		System.out.println("Error Find Piece: Could not find piece");
		return null;
	}
	
	public Piece getPiece(int x, int y) {
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
