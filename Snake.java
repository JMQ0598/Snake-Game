public class Snake {

	// The length of the snake
	private static int tailLength = 5;
	
	// How much longer snake part has to live; head is "immortal"
	public int life;
	
	// Location and prev location of snake head
	public int x;
	public int y;
	public int prevX;
	public int prevY;
	
	// Game manager has to set this at the start
	private boolean isHead;
	
	// Is this snake a point (not part of the snake)
	private boolean isPoint;
	
	// CHANGE TO ENUM IN FUTURE
	private int prevDir;
	
	public Snake (Board board, boolean isHead, boolean isPoint) {
		
		// Reset prevDir
		prevDir = 0;
		
		// Determines if snake is head
		this.isHead = isHead;
		
		// Determines if snake is a point
		this.isPoint = isPoint;
		
		// Set life of snake part
		if (this.isHead || this.isPoint) {
			life = -1;
		} else {
			life = tailLength;
		}
		
		// Obtaining snake's location
		x = (board.getWidth()/2) + (board.getWidth() % 2);
		y = (board.getHeight()/2) + (board.getHeight() % 2);
		prevX = x;
		prevY = y;
	}
	
	// CHANGE DIRS TO ENUM IN FUTURE
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
	
	public boolean IsPoint() {
		return isPoint;
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
	
	/**
	 * Extend life of snake part
	 */
	 public void addLife(int value) {
		 life += value;
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