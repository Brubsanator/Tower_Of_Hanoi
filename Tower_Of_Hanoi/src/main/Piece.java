package main;

public class Piece {
	private int value;
	public int x,y;  // Make these public for ease of use??
	//private Piece parentPiece;
	//private Piece childPiece;
	
	public Piece(int value, int x, int y) {
		this.value = value;
		this.x = x;
		this.y = y;
		
		//parentPiece = null;
		//childPiece = null;
	}
	
	/*
	 public void addChild(Piece piece) {
		childPiece = piece;
	}
	
	public void addParent(Piece piece) {
		parentPiece = piece;
	}
	
	public void removeChild() {
		childPiece = null;
	}
	
	public void removeParent() {
		parentPiece = null;
	}
	 */
	
	public int getValue() {
		return value;
	}
	
	
	public String toString() {
		if(value == -1) {
			return " ";
		}
		else if(value == 0) {
			return "x";
		}
		else {
			return String.valueOf(value);
		}
	}
}
