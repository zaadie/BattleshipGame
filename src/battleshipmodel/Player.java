package battleshipmodel;

public class Player {
	
	// These are the lengths of all of the ships.
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    private static final int NUM_OF_SHIPS = 5;
    
    public Ship[] ships;
    public Coordinate playerCoordinate;
    public Coordinate artificialCoordinate;
   
    	
    
    public Player()
    {
        if (NUM_OF_SHIPS != 5) // Num of ships must be 5
        {
            throw new IllegalArgumentException("ERROR! Num of ships must be 5");
        }
        
        ships = new Ship[NUM_OF_SHIPS];
        for (int i = 0; i < NUM_OF_SHIPS; i++)
        {
            Ship tempShip = new Ship(SHIP_LENGTHS[i]);
            ships[i] = tempShip;
        }
        
        playerCoordinate = new Coordinate();
        artificialCoordinate = new Coordinate();
    }
    
    public Player(Coordinate c)
    {
        if (NUM_OF_SHIPS != 5) // Num of ships must be 5
        {
            throw new IllegalArgumentException("ERROR! Num of ships must be 5");
        }
        
        ships = new Ship[NUM_OF_SHIPS];
        for (int i = 0; i < NUM_OF_SHIPS; i++)
        {
            Ship tempShip = new Ship(SHIP_LENGTHS[i]);
            ships[i] = tempShip;
        }
        
    }
    
    public void addShips()
    {
        for (Ship s: ships)
        {
            playerCoordinate.addShip(s);
            artificialCoordinate.addShip(s);
        }
    }
    
    
    public int numOfShipsLeft()
    {
        int counter = 5;
        for (Ship s: ships)
        {
            if (s.isLocationSet() && s.isDirectionSet())
                counter--;
        }
        
        return counter;
        
    }
    
    public void chooseShipLocation(Ship s, int row, int col, int direction)
    {
        s.setLocation(row, col);
        s.setDirection(direction);
        playerCoordinate.addShip(s);
    }

}
