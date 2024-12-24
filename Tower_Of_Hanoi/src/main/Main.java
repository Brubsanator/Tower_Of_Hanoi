package main;

public class Main {
	public static void main(String args[]) {
		Board board = new Board(3);
		
		System.out.println(board);
		
		board.solve(board.findPiece(3), board.POLETHREE);
		/*
		board.sudoMovePiece(board.getPiece(board.POLEONE, board.BOTTOMROW-2), board.POLETHREE, board.BOTTOMROW);
		board.sudoMovePiece(board.getPiece(board.POLEONE, board.BOTTOMROW-1), board.POLETWO, board.BOTTOMROW);
		board.sudoMovePiece(board.getPiece(board.POLETHREE, board.BOTTOMROW), board.POLETWO, board.BOTTOMROW-1);
		board.sudoMovePiece(board.getPiece(board.POLEONE, board.BOTTOMROW), board.POLETHREE, board.BOTTOMROW);
		board.sudoMovePiece(board.getPiece(board.POLETWO, board.BOTTOMROW-1), board.POLEONE, board.BOTTOMROW);
		board.sudoMovePiece(board.getPiece(board.POLETWO, board.BOTTOMROW), board.POLETHREE, board.BOTTOMROW-1);
		board.sudoMovePiece(board.getPiece(board.POLEONE, board.BOTTOMROW), board.POLETHREE, board.BOTTOMROW-2);
		*/
		
		System.out.println("-----------");
		
		System.out.println(board);
		
		//System.out.println(board.solved(3, board.POLETHREE));
		
		

	}
}
