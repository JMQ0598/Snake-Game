import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;

public class GameManager {

	// How much the snake's tail will grow when score increments
	private final int incrementLength = 3;
	
	// The game score
	private int score;
	
	// Is the game over?
	private static boolean gameOver;
	
	// Main driver
	public static void main(String[] args) {
		
		// Scanner used to read in input. Will be outdated in future.
		Scanner input = new Scanner(System.in);
		
		// The game has just started, so it's not over
		gameOver = false;
		
		// Our board (HARDCODED; change in future)
		Board board = new Board(10, 10);
		
		// Our snake
		Snake snake = new Snake(board, true);
		board.snakeUpdate(snake);
		
		// Our next direction
		int dir;
		
		while (!gameOver) {
			
			board.printBoard();
			
			try {
				
				dir = input.nextInt();
				snake.move(dir);
				
				// Update the snake's location; also checks if game is over
				gameOver = board.snakeUpdate(snake);
				
				// REPLACE THIS WITH new snakeSpawn IN FUTURE
				board.grid[snake.getPrevX()][snake.getPrevY()] = null;
			
			// Handle improper input
			} catch (InputMismatchException ex) {
				
				// Invalid direction given, use previous
				System.err.println("WARN: Invalid dir");
				snake.move(snake.getPrevDir());
				
			// Handle strange error
			} catch (Exception ex) {
				
				// FATAL ERROR HAS OCCURRED
				System.err.println("FATAL ERROR");
				System.exit(1);
			}
			
		}
		
		System.out.println("GAME OVER");
	}
	
}