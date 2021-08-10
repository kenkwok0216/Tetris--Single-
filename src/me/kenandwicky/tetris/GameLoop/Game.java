package me.kenandwicky.tetris.GameLoop;

import me.kenandwicky.tetris.Board.Board;
import me.kenandwicky.tetris.Tetromino.TetrominoType;

public class Game {

	public static Board board;
	private static int[] xy = new int[2];
	
	public Game(Board board) {
		Game.board = board;
		xy[0] = 3;
		xy[1] = 17;
		GameStart();
	}
	
	private void GameStart() {
		board.NextPiece();   //all the things is start
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
		
	}

	public static void moveDown() {
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, TetrominoType.Empty);
		xy[1] -= 1;
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
	}

	public static void moveLeft() {
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, TetrominoType.Empty);
		xy[0] -= 1;
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
		
	}

	public static void moveRight() {
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, TetrominoType.Empty);
		xy[0] += 1;
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
		
	}

	public static void rotateLeft() {
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, TetrominoType.Empty);
		Board.currentpiece.rotateLeft();
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
		
	}

	public static void rotateRight() {
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, TetrominoType.Empty);
		Board.currentpiece.rotateRight();
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
	}

	public static void holdPiece() {
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, TetrominoType.Empty);
		board.HoldBox();
		xy[0] = 3;
		xy[1] = 17;
		board.TetrisBoard(xy[0], xy[1], Board.currentpiece, Board.currentpiece.type);
	}
	
	
}
