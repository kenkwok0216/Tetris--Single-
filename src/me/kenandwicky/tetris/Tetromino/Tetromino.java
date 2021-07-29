package me.kenandwicky.tetris.Tetromino;

public class Tetromino {
    private static final int[][][] coordsTable = new int[][][]{
    	/* I */     { {  0, 1 }, {  -1,  1 }, {  -2, 1 }, {  -3, 1 } },
    	/* O */     { {  -1,  1 }, {  -2,  1 }, {  -1, 2 }, {  -2, 2 } },
    	/* T */     { { -1,  1 }, {  0,  2 }, {  -1, 2 }, {  -2, 2 } },
    	/* J */     { { -1,  1 }, { -2, 1 }, {  -2, 2 }, {  -2, 3 } },
    	/* L */     { { -1, 1 }, {  -2, 1 }, {  -1, 2 }, {  -1, 3 } },
    	/* Z */     { {  -2, 1 }, {  -3,  1 }, { -1, 2 }, { -2, 2 } },
        /* S */     { {  -1, 1 }, {  -2,  1 }, {  -2, 2 }, {  -3, 2 } }       
    };

	public int[][] coords;
	public TetrominoType type;

	public void rotateLeft() {
		if (type == TetrominoType.O) return;
    	for (int i = 0; i < 4; i++) {
    		int tmp = coords[i][0];
    		coords[i][0] = -coords[i][1];
    		coords[i][1] = tmp;
    	}
	}

	public void rotateRight() {
		if (type == TetrominoType.O) return;
		for (int i = 0; i < 4; i++) {
			int tmp = coords[i][0];
			coords[i][0] = coords[i][1];
			coords[i][1] = -tmp;
		}
	}

	public Tetromino(TetrominoType type) {
		this.coords = new int[4][2];
		for (int i = 0; i < 4; i++) {
			this.coords[i] = coordsTable[type.ordinal()][i].clone();
		}
		this.type = type;
	}
}
