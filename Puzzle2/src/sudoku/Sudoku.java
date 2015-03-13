package sudoku;

public class Sudoku {
	private int [][] SudokuGrid;
	
	public Sudoku (int [][] SudokuGrid) {
		this.SudokuGrid = SudokuGrid;
	}
	
	/**
     * Getter method.
     */
	public int [][] getSudokuGrid () {
		return SudokuGrid;
	}
	
	/**
     * Setter method.
     */
	public void setSudokuGrid (int [][] SudokuGrid) {
		this.SudokuGrid = SudokuGrid;
	}
	
	/**
     * This method prints out a one dimensional array.
     * @param-i the one dimensional int array to be printed.
     */
	private void printArray(int [] i) {
		for (int row = 0; row < i.length ; row++ ) { 
			System.out.print(i[row]);    
		}    
	}	
	
	/**
     * This method prints out a two dimensional array.
     * @param-i the two dimensional int array to be printed.
     */
	private void printArray(int [][] i) {
		for (int row = 0; row < i.length ; row++ ) { 
			for (int col = 0; col < i[row].length; col++ ) { 
				System.out.print(i[row][col]);    
			}    
			System.out.println();
		}	
	}

	
	/**
     * This method solves the Sudoku puzzle from the 'SudokuGrid' array and prints 
     * out the result(Slightly modified from run() method in 'easy' program).
     */
	public void Run() {
		int [][] a = getSudokuGrid();
		int [][] b;
		int row=0, col=0;
		b = returnXGrid(a);
		while (row < 4) {
			while (col < 9) {
				if (b[row][col] == 0) {
					a[row][col] = returnSudokuValue(a,col,row, a[row][col]);
					 if (a[row][col] !=0) {
						 col++;
					 }
					 else if (a[row][col]==0)  {
						 col--;
						 if (col==-1) { col++; row--; }
						 if (row==-1) { row++; }
						 while (b[row][col]==1) {
							col--;
						 if (col==-1) { col++; row--; }
						 if (row==-1) { row++; }
						 }
			         }  
				}
				else if (b[row][col] == 1) {
						col++;					
			    }
			}
			row++;
			col = col%9;
		}
	}
	
	 /**
     * This method returns a one dimensional int array (size=9) of the 3x3 square 
     * surrounding a location in a 9x9 grid.
     * @param-i the two dimensional int array of the grid.
     * @param-posX the X position in the grid.
     * @param-posY the Y position in the grid.
     * @return-a one dimensional int array.
     */
	private int[] getSquare1DArray(int [][] i, int posX, int posY) {
		int [] a = new int [9];
		int b = 0;
		int x=posX - (posX%3);
		int y=posY - (posY%3);
		for (int row = 0; row < 3; row++ ) {
			for (int col = 0; col < 3; col++){
				a[b] = i[y][x];
				b++; x++;
			}
			x=x-3; y++;
		}
		return a;
	}
	
	/**
     * This method returns a one dimensional int array of the horizontal values 
     * at a row in location posY within a 9x9 grid.
     * @param-i the two dimensional int array of the grid.
     * @param-posY the Y position in the grid.
     * @return-a one dimensional int array.
     */
	private int[] getHorizontal1DArray(int [][] i, int posY) {
		int [] a = new int [9];
		for (int b = 0; b < 9 ; b++ ) {
			a[b] = i[posY][b];
		}
		return a;
	}
	
	/**
     * This method returns a one dimensional int array of the vertical values 
     * at a column in location posX within a 9x9 grid.
     * @param-i the two dimensional int array of the grid.
     * @param-posX the X position in the grid.
     * @return-a one dimensional int array.
     */
	private int[] getVertical1DArray(int [][] i, int posX) {
		int [] a = new int [9];
		for (int b = 0; b < 9 ; b++ ) {
			a[b] = i[b][posX];
		}
		return a;
	}

	/**
     * This method returns true if a value exists within a one dimensional array,
     * false otherwise.
     * @param-i a one dimensional int array.
     * @param-number the number to be checked in the array.
     * @return-x boolean value to identify if the value exists in the array.
     */
	private boolean numberExistsinArray (int [] i, int number) {
		boolean x = false;
		for (int j = 0; j < i.length; j++)
		{
			if (i[j] == number) {
				x = true;
			}
		}
		return x;	
	}
	
	/**
     * This method returns a two dimensional int array of values filled with 
     * '0's and '1's. If a value in the input 9x9 array at a defined location contains 
     * a '0', '0' is assigned. '1' is assigned if the value is greater than zero.
     * @param-i the input two dimensional int array.
     * @return-c the output two dimensional int array filled with '0's and '1's.
     */
	private int [][] returnXGrid(int [][] i ) {
		int [][] c = new int [9][9];
		for (int row = 0; row < i.length ; row++ ) { 
			for (int col = 0; col < i[row].length; col++ ) { 
				if(i[row][col]==0) { c[row][col] = 0; } 
				else c[row][col] = 1;
			}    
		}	
		return c;
	}
	
	/**
     * This method returns true if a specified value at (x,y) location exists within
     * a two dimensional int array according to the rules of sudoku.
     * @param-i a two dimensional int array.
     * @param-posX x position in the array.
     * @param-posY y position in the array.
     * @param-value the value to be checked at position (x,y).
     * @return-check the boolean value to identify if the value exists.
     */
	private boolean numberExistsinLocation(int [][] i,int posX, int posY, int value){
		int [] x = getHorizontal1DArray(i, posY);
		int [] y = getVertical1DArray(i, posX);
		int [] z = getSquare1DArray(i, posX, posY);
		boolean check;
		check = numberExistsinArray(x, value) || numberExistsinArray(y, value) || numberExistsinArray(z, value);
		return check;
	}
	
	/**
     * This method returns an int value that can be put in location (x,y) in the grid
     * according to the rules of sudoku. Value returns '0' if there is no possible value
     * or if the value iterates to greater than '9'.
     * @param-i a two dimensional int array.
     * @param-posX x position in the array.
     * @param-posY y position in the array.
     * @param-startingValue (x,y) value in i. 
     * @return-b int value.
     */
	private int returnSudokuValue (int [] [] i, int posX, int posY, int startingValue) {
		boolean a = true;
		int b = startingValue;
		while (a == true && b<11)  {
			a = numberExistsinLocation(i, posX, posY, b);
			b++;
		}
		b--;
		if (b == 10) { b = 0; }
		return b;
	}
	
}
