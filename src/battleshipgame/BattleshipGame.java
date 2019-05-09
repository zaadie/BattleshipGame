package battleshipgame;

import battleshipmodel.*;
import java.util.Scanner;
import java.util.Arrays;

// The BattleshipGame class contains the driver implementation
// for the Battleship game.
public class BattleshipGame {

	// Driver method.
	static int row;
	static int column;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// Create User and Computer Players
		Player p1 = new Player();
		Player p2 = new Player();

		System.out.println("Welcome to the Battleship game.");
		System.out.println("Target grid (opponent): ");

		p1.printOcean();
		System.out.println("Ocean grid (you): ");
		p2.printOcean();
		
		System.out.println("Turn "+ p1.showTurns() + "...");
		
		System.out.println("Guess a row (or 'x' for exit): ");
		row = sc.nextInt();
		p1.setRow(row);
		System.out.println("Row is: " + p1.getRow());
		
		
		System.out.print("Guess a column (or 'x' for exit): ");
		column = sc.nextInt();
		p1.setColumn(column);
		System.out.println("Column is: " + p1.getColumn());

		sc.close();
		
	}
}