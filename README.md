# Sudoku Solver

This project is a Sudoku Solver written in Java, designed for efficient board validation, error handling, and solving Sudoku puzzles using recursive backtracking. It includes several classes that interact to load, validate, and solve Sudoku boards.

Developed by Yusuf Mekias for CS143 Online, this project also includes a test engine for validating different Sudoku board states.

## Features

- **File-Based Board Loading**: Reads Sudoku board configurations from `.sdk` files.
- **Validation**: Checks if the board is complete, valid, or contains any rule violations.
- **Solving**: Implements recursive backtracking to solve incomplete boards.
- **CLI Interface**: Presents an ASCII art-based interface for interacting with the Sudoku solver.

## File Structure

The project consists of three main classes:

- **SudokuBoard.java**: Handles board representation, validation, and solving.
- **SudokuSolverEngine.java**: CLI interface for users to interact with the Sudoku solver.
- **SudokuCheckerEngineV2.java**: A testing engine that validates different board states and tests the functionality of the `SudokuBoard` class.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)** version 8 or higher.
- **IDE or Terminal**: An IDE like IntelliJ IDEA or running commands in a terminal will work.

### Setup and Running the Project

###Clone the repository:
   ```bash
   git clone https://github.com/YOUR_USERNAME/sudoku-solver.git
   cd sudoku-solver
```
##Add Sudoku Boards: Place .sdk files (Sudoku boards) in a boards folder at the root of the project. You can test with different boards such as:

valid-complete.sdk
valid-incomplete.sdk
grid-violation.sdk
row-violation.sdk
Run the Solver:

##Open SudokuSolverEngine.java and run the main method to start the CLI interface.
The CLI will present board options to solve or validate.
Example Output
```plaintext
Welcome to Sudoku solver (by Yusuf Mekias)
Here are the names of the boards you can select.
0 - Fast Solve
1 - Very Fast Solve
2 - Valid Complete
3 - Grid Violation
4 - Valid Incomplete
Sample Code Usage
To directly load and solve a Sudoku board from a file:
```
###Code Example
```java
// Import necessary classes
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        SudokuBoard board = new SudokuBoard("boards/valid-incomplete.sdk");
        
        if (board.isSolved()) {
            System.out.println("This board is already solved.");
        } else if (board.isValid()) {
            System.out.println("Initial board:");
            System.out.println(board);
            
            System.out.println("\nSolving board...");
            boolean solved = board.solve();
            
            if (solved) {
                System.out.println("Board solved:");
                System.out.println(board);
            } else {
                System.out.println("This board is not solvable.");
            }
        } else {
            System.out.println("The board is invalid.");
        }
    }
}
```
##Solving Algorithm: Recursive Backtracking
####The solve() method in the SudokuBoard class uses a recursive backtracking approach, which is efficient for solving constraint-based problems like Sudoku. Here’s a breakdown of how it works:

- **Find the Next Empty Cell: Starting from the top-left of the board, it recursively moves to the next cell.
- **Try Each Number: For an empty cell, it tries numbers 1 through 9.
- **Check Validity: For each number, it temporarily places the number in the cell and checks if it maintains a valid board state using the isValid() method.
- **Recursive Call: If the board remains valid with this number, it proceeds to the next cell. This recursive call continues until the board is either solved or no numbers fit a cell.
- **Backtrack: If placing a number doesn’t lead to a solution, it resets the cell to empty (0) and tries the next number. This process repeats, backtracking through cells as needed.
- **Return Solution Status: Once the entire board is filled in a valid configuration, the method returns true. If no solution exists, it returns false.

##Here’s the code for the solve() method using recursive backtracking in SudokuBoard.java:

```java
Copy code
public boolean solve() {
    return solveHelper(0, 0);
}

private boolean solveHelper(int row, int col) {
    if (row == 9) {
        return true;  // Reached the end of the board successfully
    }
    if (col == 9) {
        return solveHelper(row + 1, 0);  // Move to the next row
    }
    if (board[row][col] != 0) {
        return solveHelper(row, col + 1);  // Skip pre-filled cells
    }
    for (int num = 1; num <= 9; num++) {
        if (isValid(row, col, num)) {
            board[row][col] = num;
            if (solveHelper(row, col + 1)) {
                return true;  // Solution found
            }
            board[row][col] = 0;  // Reset cell and backtrack
        }
    }
    return false;  // No solution found for this configuration
}
```
##Explanation of solveHelper Steps
- **Base Case: If row == 9, the board is complete.
- **Move to Next Row: When col == 9, it proceeds to the next row.
- **Check Pre-filled Cells: If a cell isn’t empty, it skips it.
- **Recursive Call and Backtracking: For each empty cell, it tries numbers 1-9. If no valid number works, it resets the cell and backtracks.
