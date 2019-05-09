package battleshipmodel;

// Player class contains a default implementation for a player.
// This class could represent a simple human or artificial player.
public class Player {

	// Your implementation goes here.

	// Constants
	private int OCEAN_SIZE = 10;

	// Variables
	private int turn = 1;
	private int row = 0;
	private int column = 0;

	String[][] ocean = new String[OCEAN_SIZE][OCEAN_SIZE];

	// Constructor methods
	public Player() {
		// this.turn = turn;
		// this.row = row;
		// this.column = column;
	}

	public Player(int turn, int row, int column) {
		this.turn = turn;
		this.row = row;
		this.column = column;
	}

	// Mutators
	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	// Accessors
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public int showTurns() {
		return turn;
	}

	// Class methods
	public void printOcean() {

		for (int i = 1; i < (ocean.length + 1); i++) {
			System.out.print(" " + i + " ");
		}
		System.out.println();

		for (int i = 0; i < ocean.length; i++) {
			System.out.print((i + 1));
			for (int j = 0; j < ocean.length; j++) {
				ocean[i][j] = " ";
				System.out.print("[" + ocean[i][j] + "]");
			}
			System.out.println();
		}

	}
}
