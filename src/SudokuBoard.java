/*
 * Class: CS143 Online
 * Assignment: Long Homework #1 : Sudoku Game
 * Name: Yusuf Mekias
 * ASCII ART GENERATED BY https://patorjk.com/ 
 * 
 * TOPICS: sets, maps, efficiency, and boolean zen
 * 
 * DESCRIPTION OF PROGRAM:
 
    So far this program:
        1. Accepts a configuration of a sudo board from a file
        2. Handles file not found exception if there is no file
        3. Converts the file into a 2D array that is the manipulatable data structure for the program
        4. Can calculate if we are done with the game (the board is complete)
        5. Can evaluate if the input (from file or user) is incorrect
        6. Displays a pretty cool CLI style game 

@@@@@@@@@@@@@@@@@@@@@@@
    SUDOKU SOLVER EDITIONS:
    1. Overloaded the isValid method (kept the overloaded version private) in order to make checking if an option would produce a valid state easier
    2. Used a helper method solveHelper()
    3. Implemented a solve() method to allow the user to solve sudoku boards
@@@@@@@@@@@@@@@@@@@@@@@


 */
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class SudokuBoard {

    //######   MEMBER VARIABLES ---->

    // 2D array for representing the sudoku board
    private int[][] board;
    //3d array for describing the x y values for the bounds of the mini boxes in the board.
    //this will help my solution for is valid to be O(N) time
    //each 2 array pair represents the start an end of x range and start and end of y range
    private int[][][] bounds = {{{0,2},{0,2}},{{3,5},{0,2}},{{6,8},{0,2}},
                                {{0,2},{3,5}},{{3,5},{3,5}},{{6,8},{3,5}},
                                {{0,2},{6,8}},{{3,5},{6,8}},{{6,8},{6,8}}};

    
    //######    CONSTRUCTORS ---->

    // pre: path is a valid file path containing a Sudoku puzzle
    // post: initializes the board with values read from the file
    SudokuBoard(String path) throws FileNotFoundException {
        this.board = new int[9][9];
        // opening connection with file
        File f = new File(path);
        Scanner scanner = new Scanner(f);

        // pass each line of the file into my intify function then load it into the respective row in my board 2D array
        for (int i = 0; i < this.board.length; i++) {
            this.board[i] = intifyLine(scanner.nextLine());
        }
        // close scanner connection
        scanner.close();
    }


    //######     PRIVATE HELPER FUNCTIONS ------>

    //helper method to check a mini box for validity given the x/y bounds in the form of a 2d array
    //pre: bound should be a valid range for the sudoku board
    //post: returns true if all the elements are either 0 or unique for that mini square (therefor 
    //)
    private boolean checkMini(int[][] bound){
        HashSet<Integer> numbers = new HashSet<>();
        for(int i = bound[0][0]; i<=bound[0][1]; i++){
            for(int j = bound[1][0]; j<= bound[1][1]; j++){
                if(this.board[i][j] > 0 && !numbers.contains(this.board[i][j])){
                    numbers.add(this.board[i][j]);
                } else if(numbers.contains(this.board[i][j])){
                    return false;
                }
            }
        }
        return true;
    }
    // pre: line is a string representation of a Sudoku row
    // post: returns an array of integers representing the Sudoku row
    private int[] intifyLine(String line) {
        // local variable to store the length of the this.board row
        int[] output = new int[9];
        // creating variable to store current char outside of for loop
        char current;

        for (int i = 0; i < line.length(); i++) {
            current = line.charAt(i);

            // if current character is between '1' and '9' it is valid.  
            // we get the int value of the number by subtracting '0' from current
            if (current >= '1' && current <= '9') {
                output[i] = current - '0';
            } else if(current == '.'){
                output[i] = 0;
            }else{
                //Adding this to make sure isValid() fails if there is invalid input
                output[0] = 10;
            }
        }
        return output;
    }

    //###### PUBLIC FUNCTIONS FOR USER INTERACTION ------>

    // pre: none
    // post: returns true if the board is valid according to Sudoku rules; false otherwise
    public boolean isValid(){
        //check rows
        HashSet<Integer> numbers = new HashSet<>();
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board[0].length; j++){
                if(this.board[i][j] > 0 && !numbers.contains(this.board[i][j])){
                    numbers.add(this.board[i][j]);
                } else if(numbers.contains(this.board[i][j])){
                    return false;
                }
            }
            //empty the HashSet in order to check the next row
            numbers = new HashSet<>();
        }
        //check columns
        for(int i = 0; i < this.board[0].length; i++){
            for(int j = 0; j < this.board.length; j++){
                if(this.board[j][i] > 0 && !numbers.contains(this.board[j][i])){
                    numbers.add(this.board[j][i]);
                } else if(numbers.contains(this.board[j][i])){
                    return false;
                }
            }
            //empty the HashSet in order to check the next row
            numbers = new HashSet<>();
        }
        boolean output = true;
        //check mini boxes
        for(int i = 0; i< this.bounds.length; i++){
            output = checkMini(bounds[i]);
        }
        return output;
    }

    //Adding a private helper method for my solve() method
    //pre: assumes valid row and col and valid input k being an int 1-9
    //post: returns true if that number would produce a valid state in the board
    private boolean isValid(int row, int col, int k){
        int oldValue = this.board[row][col];
        this.board[row][col] = k;
        boolean output = isValid();
        this.board[row][col] = oldValue;
        return output;
    }

    //My logic here is that if the board is full (has no more zeros) AND the board 
    //is valid, the the board must be solved
    // pre: none
    // post: returns true if the Sudoku board is completely solved; false otherwise
    public boolean isSolved(){
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j< this.board[0].length; j++){
                if(this.board[i][j] == 0){
                    return false;
                }
            }
        }
        //only return true if the board is valid and there are no more zeros in the board
        return true && isValid();
    }


/*
 * Solve Method with recursive backtracking -------->>>>>>>
 */
    //pre:must enter valid row and column
    //post: returns true if the board is solved and false if the board is not solvable
    private boolean solveHelper(int row, int col){
        //if the row is 9 then we have reached the end of the puzzle
        if (row == 9){
            return true;
        }
        //if columns is 9 then we are at the end of the row
        else if (col == 9){
            return solveHelper(row+1,0);
        }
        //if the current cell is not initially zero then dont edit it
        else if (this.board[row][col] != 0){
            return solveHelper(row, col +1);
        }
        else{
            //if the program gets this far it should have identified a
            //valid cell to edit
            // start looking through the options for a valid one
            for(int i = 1; i <= 9; i++){
                //if i is valid in the current board state, reursivly call 
                //solve helper on the next cell
                //if that call comes back true then the board is solved
                // **Because it will go all the way to the end before tripping 
                //this condition
                if(isValid(row,col,i)){
                    this.board[row][col] = i;
                    if (solveHelper(row,col+1)){
                        return true;
                    }
                    //if we have gotten this far then i is not correct
                    //reset cell to 0
                    this.board[row][col] = 0;
                }
            }
            //if we get to the bottom then board has no solution
            return false;
        }
    }

    //All of the actual solver code is in solveHelper() above

    //pre: none
    //post: returns false if the board is not solvable, returns true if the board is solvable.  edits the board attribute of the class directly
    public boolean solve(){
        return solveHelper(0,0);
    }



    // pre: none
    // post: returns a string representation of the Sudoku board
    @Override
    public String toString() {
        String output = " ░▒▓███████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\n" +
                        "░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\n" +
                        "░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\n" +
                        " ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░\n" +
                        "       ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\n" +
                        "       ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░\n" +
                        "░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░ \n" +
                        "\n\t\t\t\t\t\t\t\tBy Yusuf Mekias\n";
        output = output + "                  █████████████████████████████████████████\n";
        output = output + "                  █                                       █\n";
        for (int i = 0; i < this.board.length; i++) {
            output = output + "                  █   ";
            for (int j = 0; j < this.board[i].length; j++) {
                output = output + this.board[i][j] + "   ";
            }
            if (i == this.board.length - 1) {
                output = output + "█\n                  █                                       █\n" +
                                "                  █████████████████████████████████████████\n";
                break;
            }
            output = output + "█\n                  █                                       █\n";
        }

        return output;
    }
}
