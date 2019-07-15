package battleshipmodel;

public class Coordinate {

	private Location[][] grid;
	private int points;
	private int miss;
	private int hit;
	
	private int turn = 0;

	// Constants for number of rows and columns.
	public static final int NUM_ROWS = 10;
	public static final int NUM_COLS = 10;

	/*
	 * Constructor
	 */
	public Coordinate() {
		if (NUM_ROWS > 26) {
			throw new IllegalArgumentException("ERROR! NUM_ROWS CANNOT BE > 26");
		}

		grid = new Location[NUM_ROWS][NUM_COLS];

		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				Location tempLoc = new Location();
				grid[row][col] = tempLoc;
			}
		}
	}
	/*
	 * MUTATOR
	 */
	
	public void setTurn(){
		this.turn = turn +1;
	}
	
	/*
	 * ACCESSOR
	 */
	
	public int getTurn(){
		return turn;
	}
	
	
	/*
	 * METHODS
	 */
	public void printStatus() {
		generalPrintMethod(0);
	}

	public void printShips() {
		generalPrintMethod(1);
	}

	public void printCombined() {
		generalPrintMethod(2);
	}

	public boolean hasLost() {
		if (points >= 17)
			return true;
		else
			return false;
	}

	// Return whether or not there is a ship here
	public boolean hasShip(int row, int col) {
		return grid[row][col].hasShip();
	}

	// Mark a hit in this location by calling the markHit method
	// on the Location object.
	public void markHit(int row, int col) {
		grid[row][col].markHit();
		points++;
	}

	// Mark a miss on this location.
	public void markMiss(int row, int col) {
		grid[row][col].markMiss();
	}
	
	public int getMiss() {
		return miss;
	}
	
	public void addTallyMiss() {
		miss++;
	}

	public int getHit() {
		return hit;
	}
	
	public void addTallyHit() {
		miss++;
	}
	
	// Return whether or not this Location has already been guessed.
	public boolean alreadyGuessed(int row, int col) {
		return !grid[row][col].isUnguessed();
	}

	public void addShip(Ship s) {
		int row = s.getRow();
		int col = s.getCol();
		int length = s.getLength();
		int dir = s.getDirection();

		if (!(s.isDirectionSet()) || !(s.isLocationSet()))
			throw new IllegalArgumentException("ERROR! Direction or Location is unset/default");

		// 0 - hor; 1 - ver
		if (dir == 0) // Hortizontal
		{
			for (int i = col; i < col + length; i++) {
				// System.out.println("DEBUG: row = " + row + "; col = " + i);
				grid[row][i].setShip(true);
				grid[row][i].setLengthOfShip(length);
				grid[row][i].setDirectionOfShip(dir);
			}
		} else if (dir == 1) // Vertical
		{
			for (int i = row; i < row + length; i++) {
				// System.out.println("DEBUG: row = " + row + "; col = " + i);
				grid[i][col].setShip(true);
				grid[i][col].setLengthOfShip(length);
				grid[i][col].setDirectionOfShip(dir);
			}
		}
	}

	// Type: 0 for status, 1 for ships, 2 for combined
	private void generalPrintMethod(int type) {	
		// Print columns (HEADER)
		System.out.print("  ");
		for (int i = 1; i <= NUM_COLS; i++) {
			System.out.print(i + " ");
		}
		System.out.println();

		// Print rows
		for (int i = 1; i <= NUM_ROWS; i++) {
			if (i < 10) {
				System.out.print(i + " ");
			} else {
				System.out.print(i);
			}

			for (int j = 0; j < NUM_COLS; j++) {
				if (type == 0) // type == 0; status
				{
					if (grid[i - 1][j].isUnguessed())
						System.out.print("- ");
					else if (grid[i - 1][j].checkMiss())
						System.out.print("O ");
					else if (grid[i - 1][j].checkHit())
						System.out.print("X ");
				} else if (type == 1) // type == 1; ships
				{
					if (grid[i - 1][j].hasShip()) {
						// System.out.print("X ");
						if (grid[i - 1][j].getLengthOfShip() == 2) {
							System.out.print("D ");
						} else if (grid[i - 1][j].getLengthOfShip() == 3) {
							System.out.print("C ");
						} else if (grid[i - 1][j].getLengthOfShip() == 4) {
							System.out.print("B ");
						} else if (grid[i - 1][j].getLengthOfShip() == 5) {
							System.out.print("A ");
						}
					}

					else if (!(grid[i - 1][j].hasShip())) {
						System.out.print("- ");
					}				

				} else // type == 2; combined
				{
					if (grid[i - 1][j].checkHit())
						System.out.print("X ");
					else if (grid[i - 1][j].hasShip()) {
						// System.out.print("X ");
						if (grid[i - 1][j].getLengthOfShip() == 2) {
							System.out.print("D ");
						} else if (grid[i - 1][j].getLengthOfShip() == 3) {
							System.out.print("C ");
						} else if (grid[i - 1][j].getLengthOfShip() == 4) {
							System.out.print("B ");
						} else if (grid[i - 1][j].getLengthOfShip() == 5) {
							System.out.print("A ");
						}
					} else if (!(grid[i - 1][j].hasShip())) {
						System.out.print("- ");
					}
				}
			}
			System.out.println();
		}
	}

}
