import java.io.FileNotFoundException;
import java.util.Scanner;
/*
Before trying to solve the board, check if the board is in an invalid state. If it is, print a message to the screen that says that the board cannot be solved.
You can test with the any of the rules violating boards from Sudoku #2
Before trying to solve the board, check if the board is already solved. If it is, print a message to the screen that says that the board is already solved.
You can test with the valid-complete.sdk board from Sudoku #2
 */

public class SudokuSolverEngine {

   public static void main(String[] args) throws FileNotFoundException{
      Scanner sc = new Scanner(System.in);
      String[] paths = {"boards/fast-solve.sdk","boards/very-fast-solve.sdk","boards/valid-complete.sdk","boards/grid-violation.sdk","boards/valid-incomplete.sdk"};

      System.out.println("$$$$$$$$$ FOR THIS TO WORK ON YOUR MACHINE YOU MUST HAVE ALL THE .SDK FILES IN A boards FOLDER IN THE PROJECTS ROOT DIRECTORY.");
      System.out.println("$$$$$$$$$ THE GIVEN NAMES OF TEH FILES REMAIN UNCHANGED SO MOVING THE .SDK FILES INTO A boards FOLDER SHOULD WORK\n\n\n");
      System.out.println("Welcome to Sudoku solver (by Yusuf Mekias)");
      System.out.println("Here are the names of the boards you can select. \n Enter the number of the board you want to solve \n ");
      System.out.println("0 - Fast Solve\n1 - Very Fast Solve\n2 - Valid Complete\n3 - "+
                        "Grid Violation (dont choose this)\n4 - Valid Incomplete (solves surprisingly quickly)\n EXIT: press 9\n");
      int input = sc.nextInt();
      while(input != 9){
         if(input > 4 || input < 0){
            System.out.println("\n Invalid Input \nRestart Program :/");
            break;
         }
         SudokuBoard board = new SudokuBoard(paths[input]);
         if (board.isSolved()){
            System.out.println("This board is already solved.\n");
            System.out.println("\n\nWould you like to continue solving boards? (press 9 to exit)\n");
            input = sc.nextInt();
            continue;
         }
         if(board.isValid()){
            System.out.println("Initial board");
            System.out.println(board);
            System.out.println();
            System.out.print("Solving board...");
            long start = System.currentTimeMillis();
            board.solve();
            long stop = System.currentTimeMillis();
            System.out.printf("SOLVED in %.3f seconds.\n", ((stop-start)/1000.0));
            System.out.println();
            System.out.println(board);
         } else {
            System.out.println("Sorry but this in not a valid board. Please select another option.");
         }
         System.out.println("\n\nWould you like to continue solving boards? (press 9 to exit)\n");
         System.out.println("Here are the names of the boards you can select. \n Enter the number of the board you want to solve \n ");
         System.out.println("0 - Fast Solve\n1 - Very Fast Solve\n2 - Valid Complete\n3 - "+
                        "Grid Violation (dont choose this either)\n4 - Valid Incomplete (solves surprisingly quickly)\n EXIT: press 9\n");
         input = sc.nextInt();
      }

      System.out.println("Goodbye for now! :)");
      sc.close();
   }
}

//PROGRAM OUTPUT (ALL OPTIONS INCLUDED)
/*
 $$$$$$$$$ FOR THIS TO WORK ON YOUR MACHINE YOU MUST HAVE ALL THE .SDK FILES IN A boards FOLDER IN THE PROJECTS ROOT DIRECTORY.
$$$$$$$$$ THE GIVEN NAMES OF TEH FILES REMAIN UNCHANGED SO MOVING THE .SDK FILES INTO A boards FOLDER SHOULD WORK



Welcome to Sudoku solver (by Yusuf Mekias)
Here are the names of the boards you can select. 
 Enter the number of the board you want to solve 
 
0 - Fast Solve
1 - Very Fast Solve
2 - Valid Complete
3 - Grid Violation (dont choose this)
4 - Valid Incomplete (solves surprisingly quickly)
 EXIT: press 9

0
Initial board
 ░▒▓███████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
 ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░
       ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
       ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░ 

                                                                By Yusuf Mekias
                  █████████████████████████████████████████
                  █                                       █
                  █   8   2   7   1   5   4   3   9   6   █
                  █                                       █
                  █   9   6   5   0   2   7   1   4   8   █
                  █                                       █
                  █   3   4   1   6   0   9   7   5   2   █
                  █                                       █
                  █   0   0   0   0   0   0   0   0   0   █
                  █                                       █
                  █   0   0   0   0   0   0   0   0   0   █
                  █                                       █
                  █   6   1   8   9   7   0   4   3   5   █
                  █                                       █
                  █   7   8   6   2   3   5   0   1   4   █
                  █                                       █
                  █   1   5   4   7   9   6   8   0   3   █
                  █                                       █
                  █   2   3   9   8   4   0   0   0   0   █
                  █                                       █
                  █████████████████████████████████████████


Solving board...SOLVED in 0.004 seconds.

 ░▒▓███████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
 ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░
       ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
       ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░ 

                                                                By Yusuf Mekias
                  █████████████████████████████████████████
                  █                                       █
                  █   8   2   7   1   5   4   3   9   6   █
                  █                                       █
                  █   9   6   5   3   2   7   1   4   8   █
                  █                                       █
                  █   3   4   1   6   8   9   7   5   2   █
                  █                                       █
                  █   4   7   2   5   1   3   6   8   9   █
                  █                                       █
                  █   5   9   3   4   6   8   2   7   1   █
                  █                                       █
                  █   6   1   8   9   7   2   4   3   5   █
                  █                                       █
                  █   7   8   6   2   3   5   9   1   4   █
                  █                                       █
                  █   1   5   4   7   9   6   8   2   3   █
                  █                                       █
                  █   2   3   9   8   4   1   5   6   7   █
                  █                                       █
                  █████████████████████████████████████████



Would you like to continue solving boards? (press 9 to exit)

Here are the names of the boards you can select. 
 Enter the number of the board you want to solve 
 
0 - Fast Solve
1 - Very Fast Solve
2 - Valid Complete
3 - Grid Violation (dont choose this either)
4 - Valid Incomplete (solves surprisingly quickly)
 EXIT: press 9

1
Initial board
 ░▒▓███████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
 ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░
       ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
       ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░ 

                                                                By Yusuf Mekias
                  █████████████████████████████████████████
                  █                                       █
                  █   0   3   4   6   7   8   9   1   2   █
                  █                                       █
                  █   0   7   2   1   9   5   3   4   8   █
                  █                                       █
                  █   1   9   8   3   4   2   5   6   7   █
                  █                                       █
                  █   0   0   9   0   6   1   4   2   3   █
                  █                                       █
                  █   0   2   6   8   5   3   7   9   1   █
                  █                                       █
                  █   0   1   3   9   2   4   0   5   6   █
                  █                                       █
                  █   0   6   1   5   3   7   2   8   4   █
                  █                                       █
                  █   0   8   0   4   1   9   6   3   5   █
                  █                                       █
                  █   3   4   5   0   8   6   1   7   9   █
                  █                                       █
                  █████████████████████████████████████████


Solving board...SOLVED in 0.000 seconds.

 ░▒▓███████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
 ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░
       ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
       ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░ 

                                                                By Yusuf Mekias
                  █████████████████████████████████████████
                  █                                       █
                  █   5   3   4   6   7   8   9   1   2   █
                  █                                       █
                  █   6   7   2   1   9   5   3   4   8   █
                  █                                       █
                  █   1   9   8   3   4   2   5   6   7   █
                  █                                       █
                  █   8   5   9   7   6   1   4   2   3   █
                  █                                       █
                  █   4   2   6   8   5   3   7   9   1   █
                  █                                       █
                  █   7   1   3   9   2   4   8   5   6   █
                  █                                       █
                  █   9   6   1   5   3   7   2   8   4   █
                  █                                       █
                  █   2   8   7   4   1   9   6   3   5   █
                  █                                       █
                  █   3   4   5   2   8   6   1   7   9   █
                  █                                       █
                  █████████████████████████████████████████



Would you like to continue solving boards? (press 9 to exit)

Here are the names of the boards you can select. 
 Enter the number of the board you want to solve 
 
0 - Fast Solve
1 - Very Fast Solve
2 - Valid Complete
3 - Grid Violation (dont choose this either)
4 - Valid Incomplete (solves surprisingly quickly)
 EXIT: press 9

2
This board is already solved.



Would you like to continue solving boards? (press 9 to exit)

3
Sorry but this in not a valid board. Please select another option.


Would you like to continue solving boards? (press 9 to exit)

Here are the names of the boards you can select. 
 Enter the number of the board you want to solve 
 
0 - Fast Solve
1 - Very Fast Solve
2 - Valid Complete
3 - Grid Violation (dont choose this either)
4 - Valid Incomplete (solves surprisingly quickly)
 EXIT: press 9

4
Initial board
 ░▒▓███████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
 ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░
       ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
       ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░ 

                                                                By Yusuf Mekias
                  █████████████████████████████████████████
                  █                                       █
                  █   5   3   0   0   7   0   0   0   0   █
                  █                                       █
                  █   6   0   0   1   9   5   0   0   0   █
                  █                                       █
                  █   0   9   8   0   0   0   0   6   0   █
                  █                                       █
                  █   8   0   0   0   6   0   0   0   3   █
                  █                                       █
                  █   4   0   0   8   0   3   0   0   1   █
                  █                                       █
                  █   7   0   0   0   2   0   0   0   6   █
                  █                                       █
                  █   0   6   0   0   0   0   2   8   0   █
                  █                                       █
                  █   0   0   0   4   1   9   0   0   5   █
                  █                                       █
                  █   0   0   0   0   8   0   0   7   9   █
                  █                                       █
                  █████████████████████████████████████████


Solving board...SOLVED in 2.199 seconds.

 ░▒▓███████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
 ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░
       ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
       ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░
░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░ 

                                                                By Yusuf Mekias
                  █████████████████████████████████████████
                  █                                       █
                  █   5   3   1   2   7   6   4   9   8   █
                  █                                       █
                  █   6   2   3   1   9   5   8   4   7   █
                  █                                       █
                  █   1   9   8   3   4   7   5   6   2   █
                  █                                       █
                  █   8   1   2   9   6   4   7   5   3   █
                  █                                       █
                  █   4   7   6   8   5   3   9   2   1   █
                  █                                       █
                  █   7   4   9   5   2   8   3   1   6   █
                  █                                       █
                  █   9   6   5   7   3   1   2   8   4   █
                  █                                       █
                  █   2   8   7   4   1   9   6   3   5   █
                  █                                       █
                  █   3   5   4   6   8   2   1   7   9   █
                  █                                       █
                  █████████████████████████████████████████



Would you like to continue solving boards? (press 9 to exit)

Here are the names of the boards you can select. 
 Enter the number of the board you want to solve 
 
0 - Fast Solve
1 - Very Fast Solve
2 - Valid Complete
3 - Grid Violation (dont choose this either)
4 - Valid Incomplete (solves surprisingly quickly)
 EXIT: press 9

5

 Invalid Input 
Restart Program :/
Goodbye for now! :)
 */