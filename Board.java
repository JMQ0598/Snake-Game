public class Board {
	
	public Snake[][] grid;
	private int width;
	private int height;
	
	public Board (int width, int height) {
	
		this.width = width;
		this.height = height;
		grid = new Snake[width][height];
	}

	/**
	 * updateTiles
	 */
	public void updateTiles() {
		
		// Iterate over the board
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				
				// Null check
				if (grid[x][y] != null) {
					
					// Don't update the head
					if (grid[x][y].IsHead()) {
						continue;
					}
					
					// Decrement and delete dead tiles
					grid[x][y].decrement();
					if (grid[x][y].getLife() == 0) {
						grid[x][y] = null;
					}
				}
			}
		}
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Spawn a snake head in a given location.
	 */
	public void snakeSpawn(int x, int y) {
		grid[x][y] = new Snake(this, false);
	}
	
	/**
	 * Update the snake's location on the board; also check for game over
	 */
	 public boolean snakeUpdate(Snake s) {
		 
		 // Is the game over?
		 boolean gameOver = false;
		 
		 // See if snake crashed into wall
		 if (s.getX() >= this.getHeight() || s.getX() < 0 || s.getY() >= this.getWidth() || s.getY() < 0) {
			 
			 return true;
		 
		 // See if the snake crashed into tail
		} else if (grid[s.getX()][s.getY()] != null) {
			
			gameOver = true;
		 }
		 	 
		 // Move the snake in the grid
		 grid[s.getX()][s.getY()] = s;
		 
		 // Let the GM know if game is over
		 return gameOver;
	 }
	
	/**
	 * Prints out the board. Will be phased out in future in favor
	 * 	of GUI.
	 */
	public void printBoard() {
		
		for (int y = 0; y < this.getHeight(); y++) {
			
			// Left border print
			System.out.print("|");
			
			for (int x = 0; x < this.getWidth(); x++) {
				
				// Print placeholder for null
				if (grid[x][y] == null) {
					
					System.out.print(" - ");
					continue;
				}
				
				// Printing head vs. tail
				if (grid[x][y].IsHead()) {
					
					System.out.print(" 0 ");
					
				} else {
					
					System.out.print(" o ");
				}
			}
			
			// Right border print
			System.out.println("|");
		}
	}
}