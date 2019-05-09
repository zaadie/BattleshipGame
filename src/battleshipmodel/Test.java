package battleshipmodel;

public class Test {
	
	// Method Grid Trial 1 
	int[][] myHidingPlaces = new int[][] { 
		{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
	int boardHeight = 1 + 2*myHidingPlaces.length;
	int boardWidth = 1 + 4*myHidingPlaces[0].length;
	char[][] board = new char[boardHeight][boardWidth];
	
	
	public void printOcean() {
		for(int i = 0; i < boardHeight; i++) {
		    for(int j = 0; j < boardWidth; j++) {
		        board[i][j] = ' ';
		    }
		}
		for(int i = 0; i < boardHeight; i += 2) {
		    for(int j = 1; j < boardWidth - 1; j++) {
		        board[i][j] = '_';
		    }
		}
		
		// Top row
		for(int i = 1; i < boardHeight; i++) {
		    for(int j = 0; j < boardWidth; j += 4) {
		        board[i][j] = '|';
		    }
		}
		
		for(int y = 0; y < myHidingPlaces.length; y++) {
		    for(int x = 0; x < myHidingPlaces[0].length; x++) {
		        if (myHidingPlaces[y][x] == 0) {
		            continue;
		        }
		        int i = myHidingPlaces.length - y - 1;
		        i = 1 + 2*i;
		        int j = 2 + 4*x;
		        // translate int to char;
		        char val = (char) (myHidingPlaces[y][x] + '0');
		        board[i][j] = val;
		    }
		}
		for(int i = 0; i < boardHeight; i++) {
			System.out.print(i);
		    System.out.println(String.valueOf(board[i]));
		 
		}

	}
}
