package tests;
import java.io.*;

public class Input {
    private final BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
    private boolean bishop;

    public int InputHandler(String property)
        throws IOException {
        String chess;
        do {
            System.out.print("Please specify the amount of " + property + ": ");
            chess = br.readLine();
        } while (!isNumeric(chess));
        //System.out.println(chess_property); // Printing the value output for debugging
        return Integer.parseInt(chess);
    }

    public boolean BishopHandler()
            throws IOException {
        String chess;
        do {
            System.out.print("Do you want to specify the number of Bishops? ");
            chess = br.readLine();
        } while (!isBishop(chess));
        return bishop;
    }

    private boolean isBishop (String str) {
        try {
            if (!(str.contains("yes") || str.contains("no") || str.contains("y") || str.contains("n")) || str.length() > 3) {
                System.out.println("Please enter 'yes' or 'no'");
            }
            else {
                if (str.contains("yes") || str.contains("y")) {
                    bishop = true;
                    return true;
                }
                else if ((str.contains("no") || str.contains("n")) && str.length() < 3) {
                    bishop = false;
                    return true;
                }
                else return false;
            }
            return false;

        } catch (NumberFormatException | NullPointerException nfe) {
            System.out.println("Please enter 'y/yes' or 'n/no'");
            return false;
        }
    }

    private boolean isNumeric(String strNum) {
        int bottom_limit = 0;
        int upper_limit = 32767;
        try {
            int number = Integer.parseInt(strNum);
            if (!(number >= bottom_limit && number <= upper_limit)) {
                System.out.println("Please enter a number between " + bottom_limit + " and " + upper_limit);
                return false;
            }
            else return true;

        } catch (NumberFormatException | NullPointerException nfe) {
            System.out.println("Please enter a number!");
            return false;
        }
    }
}