import java.util.Random;

public class Board {
	
	// Grid info
	private Snake[][] grid;
	private int width;
	private int height;
	
	// Check if the player scored
	private boolean isScore;
	
	// Random num generator
	private static Random gen = new Random();
	
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
					if (grid[x][y].IsHead() || grid[x][y].IsPoint()) {
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
		grid[x][y] = new Snake(this, false, false);
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
		 
		 // See if the player scored
		 } else if (grid[s.getX()][s.getY()] != null && grid[s.getX()][s.getY()].IsPoint()) {
			 isScore = true;
			 
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
					
				} else if (grid[x][y].IsPoint()) {
					
					System.out.print(" * ");
				
				} else {
					
					System.out.print(" o ");
				}
			}
			
			// Right border print
			System.out.println("|");
		}
	}
	
	public boolean IsScore() {
		return isScore;
	}
	
	/** 
	 * Spawns a new point
	 */
	public void spawnSnakePoint() {
		
		// Initialize default coords
		int x = 0;
		int y = 0;
		
		// Loop until proper random coords found
		boolean done = false;
		while (!done) {
			
			// Generate new coords
			x = gen.nextInt(this.getWidth());
			y = gen.nextInt(this.getHeight());
			
			// See if we can place the point there
			if (grid[x][y] != null) {
				continue;
				
			} else {
				done = true;
			}
		}
		
		// Spawn in the new point
		grid[x][y] = new Snake(this,false,true);
		this.isScore = false;
	}
	
	// Extends the life of snake pieces
	public void extendLife(int value) {
		
		// Iterate over board and add life to non-head non-point pieces
		for (int x = 0; x < this.getWidth(); x++) {
			for (int y = 0; y < this.getHeight(); y++) {
			
				if (grid[x][y] != null && !grid[x][y].IsHead() && !grid[x][y].IsPoint()) {
					grid[x][y].addLife(value);
				}
			}
		}
	}
}