package main;

public class Piece {
	private int value;
	public int x,y;  // Make these public for ease of use??
	private Piece topPiece;
	private Piece bottomPiece;
	
	public Piece(int value, int x, int y) {
		this.value = value;
		this.x = x;
		this.y = y;
		topPiece = null;
		bottomPiece = null;
	}
	
	public void setop(Piece top) {
		topPiece = top;
	}
	
	public void setottom(Piece bottom) {
		bottomPiece = bottom;
	}
	
	public void removeTop() {
		topPiece = null;
	}
	
	public void removeBottom() {
		bottomPiece = null;
	}
	
	
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
