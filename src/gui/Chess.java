package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Chess implements ActionListener {

    final game.Process Process = new game.Process();

    // JTextfields
    final JTextField jtfRows;
    final JTextField jtfColumns;
    final JTextField jtfQueens;
    final JTextField jtfBishops;
    // JLabels
    JLabel jlabMessage;
    JLabel jlabWhiteSpace;
    final JLabel jlabRows;
    final JLabel jlabColumns;
    final JLabel jlabQueens;
    final JLabel jlabBishops;
    final JLabel jlabLimitRows;
    final JLabel jlabLimitColumns;
    final JLabel jlabLimitQueens;
    final JLabel jlabLimitBishops;
    // JTextArea
    public final JTextArea jtaResult;
    // JButton
    public final JButton jbtnSubmit;

    // Defining JFrame and elements
    private Chess() {

        // Create a new JFrame container.
        JFrame jfrm = new JFrame("Chess Solver");
        jfrm.setBounds(100, 100, 640, 786); // Windows/Linux:
        //jfrm.setBounds(100, 100, 640, 778); // MacOS

        // Specify FlowLayout for the layout manager.
        jfrm.setLayout(new FlowLayout());

        // Give the frame an initial size.
        jfrm.setResizable(false);

        // Terminate the program when the user closes the application.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ////////////////////////////////////////////////GUI ELEMENTS////////////////////////////////////////////////////
        jlabMessage = new JLabel("Welcome to this chess solver made by Edian Annink!");
        jlabMessage.setPreferredSize(new Dimension(640, 20));
        jlabMessage.setBorder(new EmptyBorder(0,20,0,0));
        jlabMessage.setFont(jlabMessage.getFont().deriveFont(Font.BOLD, 14f));
        jfrm.add(jlabMessage);

        jlabWhiteSpace = new JLabel("");
        jlabWhiteSpace.setPreferredSize(new Dimension(640, 10));
        jfrm.add(jlabWhiteSpace);

        jlabMessage = new JLabel("This chess game computes the amount of Queens and Bishops that are needed to cover M x N");
        jlabMessage.setPreferredSize(new Dimension(640, 20));
        jlabMessage.setBorder(new EmptyBorder(0,20,0,0));
        jfrm.add(jlabMessage);

        jlabMessage = new JLabel("squares on a chessboard. The program automatically adds bishops when no solutions are found.");
        jlabMessage.setPreferredSize(new Dimension(640, 20));
        jlabMessage.setBorder(new EmptyBorder(0,20,0,0));
        jfrm.add(jlabMessage);

        jlabWhiteSpace = new JLabel("");
        jlabWhiteSpace.setPreferredSize(new Dimension(640, 10));
        jfrm.add(jlabWhiteSpace);

        int TextFieldWidth = 100;
        int LabelWidth = 180;
        int LabelLimitWidth = 340;
        int RowHeight = 25;

        // Create the labels.
        jlabRows = new JLabel("Number of Rows:");
        jlabRows.setPreferredSize(new Dimension(LabelWidth, RowHeight));
        jfrm.add(jlabRows);

        jtfRows = new JTextField(5);
        jtfRows.setPreferredSize(new Dimension(TextFieldWidth, RowHeight));
        jfrm.add(jtfRows);

        jlabLimitRows = new JLabel("");
        jlabLimitRows.setPreferredSize(new Dimension(LabelLimitWidth, RowHeight));
        jlabLimitRows.setBorder(new EmptyBorder(0, 20, 0, 0));
        jfrm.add(jlabLimitRows);

        jlabColumns = new JLabel("Number of Columns:");
        jlabColumns.setPreferredSize(new Dimension(LabelWidth, RowHeight));
        jfrm.add(jlabColumns);

        jtfColumns = new JTextField(5);
        jtfColumns.setPreferredSize(new Dimension(TextFieldWidth, RowHeight));
        jfrm.add(jtfColumns);

        jlabLimitColumns = new JLabel("");
        jlabLimitColumns.setPreferredSize(new Dimension(LabelLimitWidth, RowHeight));
        jlabLimitColumns.setBorder(new EmptyBorder(0, 20, 0, 0));
        jfrm.add(jlabLimitColumns);

        jlabQueens = new JLabel("Number of Queens:");
        jlabQueens.setPreferredSize(new Dimension(LabelWidth, RowHeight));
        jfrm.add(jlabQueens);

        jtfQueens = new JTextField(5);
        jtfQueens.setPreferredSize(new Dimension(TextFieldWidth, RowHeight));
        jfrm.add(jtfQueens);

        jlabLimitQueens = new JLabel("");
        jlabLimitQueens.setPreferredSize(new Dimension(LabelLimitWidth, RowHeight));
        jlabLimitQueens.setBorder(new EmptyBorder(0, 20, 0, 0));
        jfrm.add(jlabLimitQueens);

        jlabBishops = new JLabel("Number of Bishops:");
        jlabBishops.setPreferredSize(new Dimension(LabelWidth, RowHeight));
        jfrm.add(jlabBishops);

        jtfBishops = new JTextField(5);
        jtfBishops.setPreferredSize(new Dimension(TextFieldWidth, RowHeight));
        jtfBishops.setText("0");
        jfrm.add(jtfBishops);

        jlabLimitBishops = new JLabel("");
        jlabLimitBishops.setPreferredSize(new Dimension(LabelLimitWidth, RowHeight));
        jlabLimitBishops.setBorder(new EmptyBorder(0, 20, 0, 0));
        jfrm.add(jlabLimitBishops);

        jlabWhiteSpace = new JLabel("");
        jlabWhiteSpace.setPreferredSize(new Dimension(500, 10));
        jfrm.add(jlabWhiteSpace);

        // Create the result textarea.
        jtaResult = new JTextArea();
        jtaResult.setEditable(false);
        JScrollPane scroll = new JScrollPane(jtaResult);
        scroll.setPreferredSize(new Dimension(620, 460));
        scroll.setBorder(BorderFactory.createCompoundBorder(jtaResult.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        jfrm.add(scroll);

        jbtnSubmit = new JButton("Run");
        jbtnSubmit.setPreferredSize(new Dimension(626, 40));
        // Add action listener for the Run button.
            jbtnSubmit.addActionListener(this);
        jbtnSubmit.setFont(jlabMessage.getFont().deriveFont(Font.BOLD, 14f));
        jfrm.getRootPane().setDefaultButton(jbtnSubmit); //Default frame button
        jfrm.add(jbtnSubmit);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Display the frame.
        jfrm.setVisible(true);
    }

    // Execute event when button is pressed
    public void actionPerformed(ActionEvent ae) {
        int WrongFields = 0;
        int totalpieces = 0;

        // Clearing previous warnings
        jlabLimitRows.setText("");
        jlabLimitColumns.setText("");
        jlabLimitQueens.setText("");
        jlabLimitBishops.setText("");

        // First, confirm that numbers are filled in with the right limits
        if(isNumeric(jtfRows.getText(),1)) {
            jlabLimitRows.setText("Please enter a number between 1-100");
            jlabLimitRows.setForeground(Color.red);
        }
        else WrongFields += 1;
        if(isNumeric(jtfColumns.getText(),1)) {
            jlabLimitColumns.setText("Please enter a number between 1-100");
            jlabLimitColumns.setForeground(Color.red);
        }
        else WrongFields += 1;
        if(isNumeric(jtfQueens.getText(),0)) {
            jlabLimitQueens.setText("Please enter a number between 0-100");
            jlabLimitQueens.setForeground(Color.red);
        }
        else WrongFields += 1;
        if(isNumeric(jtfBishops.getText(),0)) {
            jlabLimitBishops.setText("Please enter a number between 0-100");
            jlabLimitBishops.setForeground(Color.red);
        }
        else WrongFields += 1;

        // Checking if all fields are entered correctly
        if(WrongFields < 4) {
            return;
        }

        if((Integer.parseInt(jtfBishops.getText()) + Integer.parseInt(jtfQueens.getText())) > (Integer.parseInt(jtfRows.getText())*Integer.parseInt(jtfColumns.getText()))) {
            jlabLimitQueens.setText("Nr. of pieces exceed " + (Integer.parseInt(jtfRows.getText())*Integer.parseInt(jtfColumns.getText())) + " squares");
            jlabLimitQueens.setForeground(Color.red);
            jlabLimitBishops.setText("Nr. of pieces exceed " + (Integer.parseInt(jtfRows.getText())*Integer.parseInt(jtfColumns.getText())) + " squares");
            jlabLimitBishops.setForeground(Color.red);
            return;
        }

        // Clearing previous results
        jtaResult.setText("");

        // Create a worker to prevent the GUI from freezing during execution
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                int AmountOfSolutions;
                int FilledInBishops;
                int FilledInQueens;
                int AddedBishops = 0;
                boolean NoSolutions = false;
                long starttime = System.currentTimeMillis();
                String notification = "";

                // Disabling JButton to prevent exceptions
                jbtnSubmit.setEnabled(false);
                jbtnSubmit.setText("Executing...");

                // Disabling JTextFields to prevent user input
                jtfRows.setEditable(false);
                jtfColumns.setEditable(false);
                jtfQueens.setEditable(false);
                jtfBishops.setEditable(false);

                // Fetching input parameters
                FilledInBishops = Integer.parseInt(jtfBishops.getText());
                FilledInQueens = Integer.parseInt(jtfQueens.getText());

                // Executing recursive algorithm with filled in parameters
                AmountOfSolutions = Process.ExecuteAlgorithm(Integer.parseInt(jtfColumns.getText()), Integer.parseInt(jtfRows.getText()), Integer.parseInt(jtfBishops.getText()), Integer.parseInt(jtfQueens.getText()));

                // Looping until a solution is found with a minimal amount of bishops
                while (AmountOfSolutions == 0) {
                    notification += "No solutions with " + FilledInQueens + " queen(s) and " + (FilledInBishops + AddedBishops) + " bishop(s).";
                    jtfBishops.setText(String.valueOf(Integer.parseInt(jtfBishops.getText()) + 1));
                    AddedBishops++; //Incrementing bishop to execute algorithm again with one extra bishop
                    notification += "\nTrying with " + (FilledInBishops + AddedBishops) + " bishop(s).\n\n";
                    jtaResult.setText(notification); //Updating user
                    AmountOfSolutions = Process.ExecuteAlgorithm(Integer.parseInt(jtfColumns.getText()), Integer.parseInt(jtfRows.getText()), Integer.parseInt(jtfBishops.getText()), Integer.parseInt(jtfQueens.getText()));
                    NoSolutions = true;
                }

                long endtime = System.currentTimeMillis();

                // Formatting currentTimeMillis() to execution duration in (HH:MM:SS:ss) format
                String format = String.format("%%0%dd", 2);
                long elapsedTime = (endtime - starttime) / 1000;
                long milliseconds = ((endtime - starttime) % 1000);
                String seconds = String.format(format, elapsedTime % 60);
                String minutes = String.format(format, (elapsedTime % 3600) / 60);
                String hours = String.format(format, elapsedTime / 3600);
                String elapsedtimestring = hours + ":" + minutes + ":" + seconds + ":" + milliseconds;

                // Letting the user know that no solutions were found with filled-in parameters
                if (NoSolutions)
                    jtaResult.setText("No solutions were found with " + FilledInQueens + " queen(s) and " + FilledInBishops + " bishop(s).\n" + "A minimum amount of " + AddedBishops + " bishop(s) had to be added to get " + AmountOfSolutions + " different solutions." + "\nExecution time (HH:MM:SS:ss): " + elapsedtimestring + "\n\nPossible solutions:\n\n" + Process.Text + "1 = Queen piece" + "\n2 = Bishop piece" + "\n\n4 = Covered square(s) by Queen(s)" + "\n8 = Covered square(s) by Bishop(s)" + "\n6 = Covered square(s) by 2 pieces or more");
                else
                    jtaResult.setText("There are " + AmountOfSolutions + " different solutions with " + FilledInQueens + " queen(s) and " + FilledInBishops + " bishop(s)." + "\nExecution time (HH:MM:SS:ss): " + elapsedtimestring + "\n\nPossible solutions:\n\n" + Process.Text + "1 = Queen piece" + "\n2 = Bishop piece" + "\n\n4 = Covered square(s) by Queen(s)" + "\n8 = Covered square(s) by Bishop(s)" + "\n6 = Covered square(s) by 2 pieces or more");

                // Resetting button text and re-enabling button for next execution
                jbtnSubmit.setText("Run");
                jbtnSubmit.setEnabled(true);

                // User can change parameters again for next execution
                jtfRows.setEditable(true);
                jtfColumns.setEditable(true);
                jtfQueens.setEditable(true);
                jtfBishops.setEditable(true);

                Process.runtwice = 0;
                Process.Text.setLength(0);

                return null;
            }
        };

        // Execute worker
        worker.execute();
    }

    // Method to check if numeric values are added and are between the limits 0-100
    private boolean isNumeric(String strNum, int bottomlimit) {
        int upper_limit = 100;
        try {
            int number = Integer.parseInt(strNum);
            return !(number >= bottomlimit && number <= upper_limit);

        } catch (NumberFormatException | NullPointerException nfe) {
            return true;
        }
    }

    // Main method
    public static void main(String[] args) {
        // Create the JFrame on the event dispatching thread.
        SwingUtilities.invokeLater(Chess::new);
    }
}