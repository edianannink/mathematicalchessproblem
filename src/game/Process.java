package game;

public class Process {
    private static int M;
    private static int N;
    private int amount_of_solutions = 0;
    public boolean runonce = true;
    //public String Text = "";
    public final StringBuilder Text = new StringBuilder();

    private static int board_bishop_max_static;
    private static int board_queen_max_static;

    //Method that executed the recursive algorithm with the right parameters
    public int ExecuteAlgorithm(int columns, int rows, int board_bishop_max, int board_queen_max) {
        int[][] board = new int[rows][columns];
        M = rows;
        N = columns;

        board_bishop_max_static = board_bishop_max;
        board_queen_max_static = board_queen_max;

        amount_of_solutions = 0;
        return ProcessAlgorithm(board, 0, rows * columns, 0, 0); //Returning total amount of solutions
    }

    //Recursive algorithm that brute-forces all the possible combinations of Bishops, Queens and Empty on the board with the defined parameters
    private int ProcessAlgorithm(int[][] board, int current_square, int max_square, int placed_bishops, int placed_queens) {
        //Converting square to array coordinates
        int i = current_square % board.length;
        int j = current_square / board.length;
        int combinations = 0; // Number of solutions in solution set

        ///////////////DEBUGGING/////////////////
        if ((current_square >= max_square) || (placed_bishops >= board_bishop_max_static && placed_queens >= board_queen_max_static)) {
            if (CheckSolution(board)) {
                amount_of_solutions++;
                //System.out.println(amount_of_solutions); // Printing amount of solutions
            }
            return amount_of_solutions; // Break out of recursion after solution check
        }

        if (placed_queens < board_queen_max_static) { // Placing queens on the board
            board[i][j] = 1;
            combinations += ProcessAlgorithm(board, current_square + 1, max_square, placed_bishops, placed_queens + 1);
        }

        if (placed_bishops < board_bishop_max_static) { // Placing Bishops on the board
            board[i][j] = 2;
            combinations += ProcessAlgorithm(board, current_square + 1, max_square, placed_bishops + 1, placed_queens);
        }

        board[i][j] = 0; // Placing "Empty" on the board
        //noinspection UnusedAssignment
        combinations += ProcessAlgorithm(board, current_square + 1, max_square, placed_bishops, placed_queens);
        return amount_of_solutions;
    }

    private boolean CheckSolution(int[][] board){ // Method that checks every "full" board for solutions
        int[][] covered_squares = new int[M][N];
        int c, d, e, f, g, h, k, l;
        int covered_squares_counter = 0;
        boolean is_solution = false;
        boolean topleft = true, bottomleft = true, topright = true, bottomright = true, horizontalright = true, horizontalleft = true, verticalup = true ,verticaldown = true;

        // This loop generates all the paths for every piece on the board. A counter is used to count the number of "unique" placements on the board. This is used to check for a solution.
        // "Unique" means the amount of squares that are covered by one piece and not by multiple pieces (to prevent false positives)
        for (int i = 0; i < M; i++) { // array[row][column]
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) { //Square is a queen
                    covered_squares_counter += 1;
                    covered_squares[i][j] = 1;
                    for (int a = j+1; a < N; a++) { // Queen horizontal right path
                        if ((board[i][a] != 1 && board[i][a] != 2) && horizontalright) {
                            if (covered_squares[i][a] != 4 && covered_squares[i][a] != 8 && covered_squares[i][a] != 6) {
                                covered_squares[i][a] = 4;
                                covered_squares_counter += 1;
                            }
                            else covered_squares[i][a] = 6;
                        }
                        else horizontalright = false;
                    }
                    for (int a = j-1; a >= 0; a--) { // Queen horizontal left path
                        if ((board[i][a] != 1 && board[i][a] != 2) && horizontalleft) {
                            if (covered_squares[i][a] != 4 && covered_squares[i][a] != 8 && covered_squares[i][a] != 6) {
                                covered_squares[i][a] = 4;
                                covered_squares_counter += 1;
                            }
                            else covered_squares[i][a] = 6;
                        }
                        else horizontalleft = false;
                    }
                    for (int b = i+1; b < M; b++) { // Queen vertical downwards path
                        if ((board[b][j] != 1 && board[b][j] != 2) && verticaldown) {
                            if (covered_squares[b][j] != 4 && covered_squares[b][j] != 8 && covered_squares[b][j] != 6) {
                                covered_squares[b][j] = 4;
                                covered_squares_counter += 1;
                            }
                            else covered_squares[b][j] = 6;
                        }
                        else verticaldown = false;
                    }
                    for (int b = i-1; b >= 0; b--) { // Queen vertical upwards path
                        if ((board[b][j] != 1 && board[b][j] != 2) && verticalup) {
                            if (covered_squares[b][j] != 4 && covered_squares[b][j] != 8 && covered_squares[b][j] != 6) {
                                covered_squares[b][j] = 4;
                                covered_squares_counter += 1;
                            }
                            else covered_squares[b][j] = 6;
                        }
                        else verticalup = false;
                    }

                    for (c = i-1, d = j-1; c >= 0 && d >= 0; c--, d--) { // Queen diagonal left and upwards path
                        if ((board[c][d] != 1 && board[c][d] != 2) && topleft) {
                            if (covered_squares[c][d] != 4 && covered_squares[c][d] != 8 && covered_squares[c][d] != 6) {
                                covered_squares[c][d] = 4;
                                covered_squares_counter += 1;
                            }
                            else covered_squares[c][d] = 6;
                        }
                        else topleft = false;
                    }

                    for (e = i+1, f = j+1; e < M && f < N; e++, f++) { // Queen diagonal right and downwards path
                        if ((board[e][f] != 1 && board[e][f] != 2) && bottomright) {
                            if (covered_squares[e][f] != 4 && covered_squares[e][f] != 8 && covered_squares[e][f] != 6) {
                                covered_squares[e][f] = 4;
                                covered_squares_counter += 1;
                            }
                            else covered_squares[e][f] = 6;
                        }
                        else bottomright = false;
                    }

                    for (g = i+1, h = j-1; g < M && h >= 0; g++, h--) { // Queen diagonal left and downwards path
                        if ((board[g][h] != 1 && board[g][h] != 2) && bottomleft) {
                            if (covered_squares[g][h] != 4 && covered_squares[g][h] != 8 && covered_squares[g][h] != 6) {
                                covered_squares[g][h] = 4;
                                covered_squares_counter += 1;
                            }
                            else covered_squares[g][h] = 6;
                        }
                        else bottomleft = false;
                    }

                    for (k = i-1, l = j+1; k >= 0 && l < N; k--, l++) { // Queen diagonal right and upwards path
                        if ((board[k][l] != 1 && board[k][l] != 2) && topright) {
                            if (covered_squares[k][l] != 4 && covered_squares[k][l] != 8 && covered_squares[k][l] != 6) {
                                covered_squares[k][l] = 4;
                                covered_squares_counter += 1;
                            }
                            else covered_squares[k][l] = 6;
                        }
                        else topright = false;
                    }
                }

                if (board[i][j] == 2) { //Square is a bishop
                    covered_squares_counter += 1;
                    covered_squares[i][j] = 2;
                    for (c = i-1, d = j-1; c >= 0 && d >= 0; c--, d--) { // Bishop diagonal left and upwards path
                        if ((board[c][d] != 1 && board[c][d] != 2) && topleft) {
                            if (covered_squares[c][d] != 4 && covered_squares[c][d] != 8 && covered_squares[c][d] != 6) {
                                covered_squares[c][d] = 8;
                                covered_squares_counter += 1;
                            }
                            else covered_squares[c][d] = 6;
                        }
                        else topleft = false;
                    }

                    for (e = i+1, f = j+1; e < M && f < N; e++, f++) { // Bishop diagonal right and downwards path
                        if ((board[e][f] != 1 && board[e][f] != 2) && bottomright) {
                            if (covered_squares[e][f] != 4 && covered_squares[e][f] != 8 && covered_squares[e][f] != 6) {
                                covered_squares[e][f] = 8;
                                covered_squares_counter += 1;
                            }
                            else covered_squares[e][f] = 6;
                        }
                        else bottomright = false;
                    }

                    for (g = i+1, h = j-1; g < M && h >= 0; g++, h--) { // Bishop diagonal left and downwards path
                        if ((board[g][h] != 1 && board[g][h] != 2) && bottomleft) {
                            if (covered_squares[g][h] != 4 && covered_squares[g][h] != 8 && covered_squares[g][h] != 6) {
                                covered_squares[g][h] = 8;
                                covered_squares_counter += 1;
                            }
                            else covered_squares[g][h] = 6;
                        }
                        else bottomleft = false;
                    }

                    for (k = i-1, l = j+1; k >= 0 && l < N; k--, l++) { // Bishop diagonal right and upwards path
                        if ((board[k][l] != 1 && board[k][l] != 2) && topright) {
                            if (covered_squares[k][l] != 4 && covered_squares[k][l] != 8 && covered_squares[k][l] != 6) {
                                covered_squares[k][l] = 8;
                                covered_squares_counter += 1;
                            }
                            else covered_squares[k][l] = 6;
                        }
                        else topright = false;
                    }
                }
            }
        }

        if (covered_squares_counter == M*N) { // Checking if "unique" covered squares are equal to board dimension
            is_solution = true;
        }

        if(runonce && is_solution) {
            // Generating String to print the first solution in the GUI
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    Text.append(" ").append(covered_squares[i][j]).append(" ");
                }
                Text.append("    ");
                for (int j = 0; j < N; j++) {
                    Text.append(" ").append(board[i][j]).append(" ");
                }
                Text.append("\n");
            }
            Text.append("\n");
            runonce = false;
        }

        return is_solution;
    }
}