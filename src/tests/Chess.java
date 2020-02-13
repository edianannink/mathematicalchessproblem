package tests;

import java.io.IOException;

class  Chess {
    public static void main(String[] args)
            throws IOException {

        tests.Input Input = new tests.Input();
        game.Process Process = new game.Process();

        System.out.println("Welcome to this chess solver made by Edian Annink!");
        System.out.println("This chess game computes the amount of Queens and Bishops that are needed to cover all the squares on the chessboard\n");

        int board_m = Input.InputHandler("Chessboard rows");
        int board_n = Input.InputHandler("Chessboard columns");
        int board_queen = Input.InputHandler("Queen pieces");
        boolean bishop = Input.BishopHandler();
        int board_bishop = 0;
        if (bishop) board_bishop = Input.InputHandler("Bishop pieces");

        System.out.println("\nFilled in properties:\n");
        System.out.println("Chessboard rows: " + board_m);
        System.out.println("Chessboard columns: " + board_n);
        System.out.println("Number of Queen pieces: " + board_queen);
        if (bishop) System.out.println("Number of Bishop pieces: " + board_bishop);

        System.out.println("\nExecuting.. This may take a while to complete.\n");

        //int solution_quantity = Process.ExecuteAlgorithm(5,5,3,1);
        int solution_quantity = Process.ExecuteAlgorithm(board_n,board_m,board_bishop,board_queen);
        String solutions = Integer.toString(solution_quantity);
        System.out.println("There are " + solutions + " valid solutions.");
    }
}