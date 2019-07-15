package battleshipgame;

import java.util.Random;
import java.util.Scanner;
import battleshipmodel.*;

public class BattleshipGame {

	/*
	 * Helper methods
	 */
	public static Scanner sc = new Scanner(System.in);
	public static Random rand = new Random();

	public static void main(String[] args) {
		System.out.println("Welcome to the Battleship game.");
		System.out.println("Ocean grid (you):");

		Player userPlayer = new Player();
		setup(userPlayer);
		// userPlayer.playerCoordinate.printShips();

		Player artificialPlayer = new Player();
		setup(artificialPlayer);
		artificialPlayer.playerCoordinate.printShips();

		String result = "";
		while (true) {
			System.out.println(result);
			result = askForGuess(userPlayer, artificialPlayer);

			if (userPlayer.playerCoordinate.hasLost()) {
				System.out.println("COMP HIT!...USER LOSES");
				break;
			} else if (artificialPlayer.playerCoordinate.hasLost()) {
				System.out.println("HIT!...COMPUTER LOSES");
				break;
			}

			System.out.println("\nCOMPUTER IS MAKING GUESS...");

			compMakeGuess(artificialPlayer, userPlayer);

		}

	}

	private static void setup(Player p) {
		int counter = 1;
		int normCounter = 0;

		Random rand = new Random();

		while (p.numOfShipsLeft() > 0) {
			for (Ship s : p.ships) {

				int row = rand.nextInt(9) + 1;
				int col = rand.nextInt(9) + 1;
				int dir = (int) Math.round(Math.random());

				// System.out.println("DEBUG: row-" + row + "; col-" + col + "; dir-" + dir);

				while (hasErrors(row, col, dir, p, normCounter)) // while the random nums make error, start again
				{
					row = rand.nextInt(9) + 1;
					col = rand.nextInt(9) + 1;
					dir = (int) Math.round(Math.random());
					// System.out.println("AGAIN-DEBUG: row-" + row + "; col-" + col + "; dir-" +
					// dir);
				}

				// System.out.println("FURTHER DEBUG: row = " + row + "; col = " + col);
				p.ships[normCounter].setLocation(row, col);
				p.ships[normCounter].setDirection(dir);
				p.playerCoordinate.addShip(p.ships[normCounter]);

				normCounter++;
				counter++;
			}
		}
	}

	private static String askForGuess(Player p, Player opp) {
		System.out.println("Target grid(opponent) \n");

		p.artificialCoordinate.printStatus();

		int t = 0;

		int row = -1;
		int col = -1;

		int oldRow = -1;
		int oldCol = -1;

		while (true) {
			p.playerCoordinate.setTurn();
			t = p.playerCoordinate.getTurn();

			System.out.println("\nNobody has won yet. Keep playing...");
			System.out.println("Turn..." + t);

			System.out.print("Guess a row (or 'x' for exit):");
			row = sc.nextInt();
			oldRow = row;
			row = convertUserColToProCol(row);

			System.out.print("Guess a column (or 'x' for exit):");
			col = sc.nextInt();
			oldCol = col;
			col = convertUserColToProCol(col);

			// System.out.println("DEBUG: " + row + col);

			if (col >= 0 && col <= 9 && row != -1)
				break;

			System.out.println("Invalid location!");
		}

		if (opp.playerCoordinate.hasShip(row, col)) {
			p.artificialCoordinate.markHit(row, col);
			opp.playerCoordinate.markHit(row, col);
			p.playerCoordinate.addTallyHit();
			return "You hit! Progress: " + p.playerCoordinate.getHit() + " hits and " + p.playerCoordinate.getMiss()
					+ " misses. \n";
		} else {
			p.artificialCoordinate.markMiss(row, col);
			opp.playerCoordinate.markMiss(row, col);
			p.playerCoordinate.addTallyMiss();
			return "You missed! Progress: " + p.playerCoordinate.getHit() + " hits and " + p.playerCoordinate.getMiss()
					+ " misses. \n";
		}
	}

	private static void compMakeGuess(Player comp, Player user) {
		Random rand = new Random();
		int row = rand.nextInt(9) + 1;
		int col = rand.nextInt(9) + 1;

		// While computer already guessed this posiiton, make a new random guess
		while (comp.artificialCoordinate.alreadyGuessed(row, col)) {
			row = rand.nextInt(9) + 1;
			col = rand.nextInt(9) + 1;
		}

		if (user.playerCoordinate.hasShip(row, col)) {
			comp.artificialCoordinate.markHit(row, col);
			user.playerCoordinate.markHit(row, col);
			comp.artificialCoordinate.addTallyHit();
			System.out.println("Player 2 missed! Progress: " + comp.artificialCoordinate.getHit() + " hits and "
					+ comp.artificialCoordinate.getMiss() + " misses. \n");
		} else {
			comp.artificialCoordinate.markMiss(row, col);
			user.playerCoordinate.markMiss(row, col);
			comp.artificialCoordinate.addTallyMiss();
			System.out.println("Player 2 missed! Progress: " + comp.artificialCoordinate.getHit() + " hits and "
					+ comp.artificialCoordinate.getMiss() + " misses. \n");
		}

		System.out.println("\nYOUR BOARD...PRESS ENTER TO CONTINUE...");
		sc.nextLine();
		user.playerCoordinate.printCombined();
		System.out.println("PRESS ENTER TO CONTINUE...");
		sc.nextLine();
	}

	private static int convertUserColToProCol(int val) {
		int toReturn = -1;
		switch (val) {
		case 1:
			toReturn = 0;
			break;
		case 2:
			toReturn = 1;
			break;
		case 3:
			toReturn = 2;
			break;
		case 4:
			toReturn = 3;
			break;
		case 5:
			toReturn = 4;
			break;
		case 6:
			toReturn = 5;
			break;
		case 7:
			toReturn = 6;
			break;
		case 8:
			toReturn = 7;
			break;
		case 9:
			toReturn = 8;
			break;
		case 10:
			toReturn = 9;
			break;
		default:
			toReturn = -1;
			break;
		}

		return toReturn;
	}

	private static boolean hasErrors(int row, int col, int dir, Player p, int count) {
		// System.out.println("DEBUG: count arg is " + count);

		int length = p.ships[count].getLength();

		// Check if off grid - Horizontal
		if (dir == 0) {
			int checker = length + col;
			// System.out.println("DEBUG: checker is " + checker);
			if (checker > 10) {
				// System.out.println("SHIP DOES NOT FIT");
				return true;
			}
		}

		// Check if off grid - Vertical
		if (dir == 1) // VERTICAL
		{
			int checker = length + row;
			// System.out.println("DEBUG: checker is " + checker);
			if (checker > 10) {
				// System.out.println("SHIP DOES NOT FIT");
				return true;
			}
		}

		// Check if overlapping with another ship
		if (dir == 0) // Hortizontal
		{
			// For each location a ship occupies, check if ship is already there
			for (int i = col; i < col + length; i++) {
				// System.out.println("DEBUG: row = " + row + "; col = " + i);
				if (p.playerCoordinate.hasShip(row, i)) {
					// System.out.println("THERE IS ALREADY A SHIP AT THAT LOCATION");
					return true;
				}
			}
		} else if (dir == 1) // Vertical
		{
			// For each location a ship occupies, check if ship is already there
			for (int i = row; i < row + length; i++) {
				// System.out.println("DEBUG: row = " + row + "; col = " + i);
				if (p.playerCoordinate.hasShip(i, col)) {
					// System.out.println("THERE IS ALREADY A SHIP AT THAT LOCATION");
					return true;
				}
			}
		}

		return false;
	}
}
