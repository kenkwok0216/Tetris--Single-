package me.kenandwicky.tetris.GameLoop;

public class Speed {
	int[] levelspeed = {320, 290, 160, 220, 190, 160, 120, 90, 60, 50, 40, 40, 40, 30, 30, 30, 20, 20, 20, 10};
	/*
	 * Level  / 	   Speed		 // Level  / 	   Speed 
	 * 	 0				320				  10			 40
	 *   1				290				  11			 40
	 *   2				260				  12			 40
	 *   3				220				  13			 30   
	 *   4				190				  14			 30
	 *   5				160				  15			 30
	 *   6				120				  16			 20
	 *   7				 90				  17			 20
	 *   8				 60				  18			 20
	 *   9			     50				  19			 10
	 */

	public int ReturnSpeed(int playerlevel) {
		return levelspeed[playerlevel];
	}
	
	
	
}
