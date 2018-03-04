public class Snake {

	private int tailLength;
	public int life;
	public int x;
	public int y;
	public int prevX;
	public int prevY;
	
	// Game manager has to set this at the start
	public boolean isHead;
	
	// CHANGE TO ENUM IN FUTURE
	private int prevDir;
	
	public Snake (Board board, boolean isHead) {
		
		// Reset prevDir
		prevDir = -1;
		
		// Determines if snake is head
		this.isHead = isHead;
		
		// Set life of snake part
		if (this.isHead) {
			life = -1;
		} else {
			life = tailLength;
		}
		
		// Set tail length
		tailLength = 0;
		
		// Obtaining snake's location
		x = (board.getWidth()/2) + 1;
		y = (board.getHeight()/2) + 1;
		prevX = x;
		prevY = y;
	}
	
	// CHANGE DIRS TO ENUM IN FUTURE
	// ALSO, HAVE IT RETURN BOOLEAN; IF FALSE GM WILL USE PREVDIR
	public void move(int direction) {
		
		// Used to check if invalid direction (snake can't move backwards)
		if ((prevDir == 0 && direction == 1) || (prevDir == 1 && direction == 0) || (prevDir == 2 && direction == 3) || (prevDir == 3 && direction == 2)) {
			System.err.println("WARN: Invalid dir");
			move(prevDir);
			return;
		}
		
		// Update prevX and prevY
		prevX = x;
		prevY = y;
		
		// Used to check if move was successful - will be used to check crash
		boolean succ;
		
		if (direction == 0) {
			succ = moveUp();
		} else if (direction == 1) {
			succ = moveDown();
		} else if (direction == 2) {
			succ = moveLeft();
		} else if (direction == 3) {
			succ = moveRight();
		} else {
			
			// Used to check if an invalid int was given
			System.err.println("WARN: Invalid dir");
			move(prevDir);
			return;
		}
	}
	
	public boolean moveUp() {
		
		y--;
		prevDir = 0;
		return true;
	}
	
	public boolean moveDown() {
		
		y++;
		prevDir = 1;
		return true;
	}
	
	public boolean moveLeft() {
		
		x--;
		prevDir = 2;
		return true;
	}
	
	public boolean moveRight() {
		
		x++;
		prevDir = 3;
		return true;
	}
	
	public int getLength() {
		return tailLength;
	}
	
	public boolean IsHead() {
		return isHead;
	}
	
	/**
	 * Used to keep decrease how much longer a snake part has to live
	 * 	Head portion will ALWAYS be negative
	 */
	public void decrement() {
		life--;
	}
	
	/**
	 * Return the life of the snake part
	 */
	public int getLife() {
		return life;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getPrevX() {
		return prevX;
	}
	
	public int getPrevY() {
		return prevY;
	}
	
	public void elongate(int length) {
		tailLength += length;
	}
	
	public int getPrevDir() {
		return this.prevDir;
	}
}