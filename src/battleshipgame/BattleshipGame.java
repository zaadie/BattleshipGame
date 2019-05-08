package battleshipgame;

import battleshipmodel.*;
import java.util.Scanner;
import java.util.Arrays;


// The BattleshipGame class contains the driver implementation
// for the Battleship game.
public class BattleshipGame {
    
    // Driver method.
    public static void main(String[] args) {
        
        System.out.println("Welcome to the Battleship game.");
        
        // Your implementation goes here.
        String[][] board = new String [10][10];
        for (int r = 0; r<board.length;r++){
        	   for (int c = 0; c <board.length;c++){
        	      board[r][c] = " ";  // Initialize the cell
        	      System.out.print("[" +board[r][c] + "]"); // Display the content of cell board
        	   }
        	   System.out.println();  // go to next line
        }
    }
}