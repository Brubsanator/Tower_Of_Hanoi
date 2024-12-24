package main;

public class Main {
	public static void main(String args[]) {
		Board board = new Board(3);
		
		System.out.println(board);
		
		board.movePiece(board.getPiece(board.BOTTOMROW, board.POLEONE), board.POLETHREE, board.BOTTOMROW);
		//board.movePiece(2, board.POLETHREE, board.BOTTOMROW-1);
		//board.movePiece(1, board.POLETHREE, board.BOTTOMROW-2);
		
		System.out.println("-----------");
		
		System.out.println(board);
		//System.out.println(board.solved());
		
		
		/*
		 * 
		 * Fix piece being able to move illegally
		 * 
		 */
		

	}
}
