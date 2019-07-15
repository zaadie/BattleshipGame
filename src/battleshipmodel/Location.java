package battleshipmodel;

public class Location {

	// Global Vars
	public static final int UNGUESSED = 0;
	public static final int HIT = 1;
	public static final int MISSED = 2;

	// Instance Variables
	private boolean hasShip;
	private int status;
	private int lengthOfShip;
	private int directionOfShip;

	// Location constructor.
	public Location() {
		// Set initial values
		status = 0;
		hasShip = false;
		lengthOfShip = -1;
		directionOfShip = -1;
	}

	/*
	 * ACCESSORS
	 */

	public int getLengthOfShip() {
		return lengthOfShip;
	}

	public int getDirectionOfShip() {
		return directionOfShip;
	}

	// Set the status of this Location.
	public void setStatus(int status) {
		this.status = status;
	}

	/*
	 * MUTATORS
	 */

	// Set the value of whether this location has a ship.
	public void setShip(boolean val) {
		this.hasShip = val;
	}

	public void setLengthOfShip(int val) {
		lengthOfShip = val;
	}

	public void setDirectionOfShip(int val) {
		directionOfShip = val;
	}

	/*
	 * METHODS
	 */

	// Was this Location a hit?
	public boolean checkHit() {
		if (status == HIT)
			return true;
		else
			return false;
	}

	// Was this location a miss?
	public boolean checkMiss() {
		if (status == MISSED)
			return true;
		else
			return false;
	}

	// Was this location unguessed?
	public boolean isUnguessed() {
		if (status == UNGUESSED)
			return true;
		else
			return false;
	}

	// Return whether or not this location has a ship.
	public boolean hasShip() {
		return hasShip;
	}

	// Mark this location a hit.
	public void markHit() {
		setStatus(HIT);
	}

	// Mark this location a miss.
	public void markMiss() {
		setStatus(MISSED);
	}

}
