package main;

public class Main {
	public static void main(String args[]) {
		int pieces = 3;
		Board board = new Board(pieces);
		
		System.out.println(board);
		
		board.solve(board.findPiece(pieces), board.POLETHREE);
		
		/*
		board.movePiece(board.getPiece(board.POLEONE, board.BOTTOMROW-2), board.POLETHREE);
		board.movePiece(board.getPiece(board.POLEONE, board.BOTTOMROW-1), board.POLETWO);
		board.movePiece(board.getPiece(board.POLETHREE, board.BOTTOMROW), board.POLETWO);
		
		board.movePiece(board.getPiece(board.POLEONE, board.BOTTOMROW), board.POLETHREE);
		board.movePiece(board.getPiece(board.POLETWO, board.BOTTOMROW-1), board.POLEONE);
		board.movePiece(board.getPiece(board.POLETWO, board.BOTTOMROW), board.POLETHREE);
		board.movePiece(board.getPiece(board.POLEONE, board.BOTTOMROW), board.POLETHREE);
		*/
		
		
		System.out.println("-----------");
		
		System.out.println(board);
		
		//System.out.println(board.solved(3, board.POLETHREE));
		
		

	}
}
