import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    //region UI Variables
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    //endregion
    // region UI Components
    Random random = new Random();

    JPanel titlePanel = new JPanel();
    JPanel turnPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    JLabel turnText = new JLabel();
    JLabel titleText = new JLabel();

    JButton[] buttons = new JButton[9];

    Color buttonColor = UIManager.getColor("Button.background");
    //endregion
    //region Game Variables
    boolean running = false;
    boolean player1_turn;
    int availableSquares = 9;
    //endregion

    //region GamePanel Constructor
    // this is what holds the game itself.
    GamePanel() {
        //region Game Panel Settings
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        //endregion

        //region Title Text Settings
        titleText.setBackground(Color.black);
        titleText.setForeground(Color.red);
        titleText.setFont(new Font("Helvetica", Font.BOLD, 75));
        titleText.setHorizontalAlignment(JLabel.CENTER);
        titleText.setText("TIC-TAC-TOE");
        titleText.setOpaque(true);
        //endregion

        //region Turn Text Settings
        turnText.setBackground(Color.black);
        turnText.setForeground(Color.red);
        turnText.setFont(new Font("Helvetica", Font.BOLD, 75));
        turnText.setHorizontalAlignment(JLabel.CENTER);
        turnText.setOpaque(true);
        //endregion

        //region Title Panel
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 600, 100);
        titlePanel.add(titleText);
        this.add(titlePanel, BorderLayout.NORTH);
        //endregion

        //region Turn Panel
        turnPanel.setLayout(new BorderLayout());
        turnPanel.setBounds(0, 0, 600, 100);
        turnPanel.add(turnText);
        this.add(turnPanel, BorderLayout.SOUTH);
        //endregion

        //region Button Panel
        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(Color.darkGray);
        this.add(buttonPanel);
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Helvetica", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }
        //endregion
        startGame();
    }

    //endregion
    //region Game State Method
    public void startGame() {
        running = true;
        randomizeTurn();
    }

    public void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setBackground(buttonColor);
        }
        availableSquares = 9;
        startGame();
    }

    //endregion
    //region Event Listener Method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == buttons[i]) {
                    if (player1_turn) {
                        if (buttons[i].getText().length() == 0) {
                            buttons[i].setForeground(Color.red);
                            buttons[i].setText("X");
                            availableSquares--;
                            player1_turn = false;
                            turnText.setText("O's Turn");
                            check();
                            if (!running) {
                                int result = JOptionPane.showConfirmDialog(null,
                                        "Would you like to play again?", null, JOptionPane.YES_NO_OPTION);
                                if (result == JOptionPane.YES_OPTION) {
                                    resetGame();
                                } else {
                                    System.exit(0);
                                }
                            }
                        }
                    } else {
                        if (buttons[i].getText().length() == 0) {
                            buttons[i].setForeground(Color.blue);
                            buttons[i].setText("O");
                            availableSquares--;
                            player1_turn = true;
                            turnText.setText("X's Turn");
                            check();
                            if (!running) {
                                int result = JOptionPane.showConfirmDialog(null,
                                        "Would you like to play again?", null, JOptionPane.YES_NO_OPTION);
                                if (result == JOptionPane.YES_OPTION) {
                                    resetGame();
                                } else {
                                    System.exit(0);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //endregion
    //region Set Up Method
    public void randomizeTurn() {
        if (random.nextInt(2) == 0) {
            player1_turn = true;
            turnText.setText("X's Turn");
        } else {
            player1_turn = false;
            turnText.setText("O's Turn");
        }
    }

    //endregion
    //region Game State Check Method
    public void check() {
        // ROW 1
        if (buttons[0].getText().length() > 0
                && buttons[0].getText().equals(buttons[1].getText())
                && buttons[1].getText().equals(buttons[2].getText())) {
            turnText.setText(buttons[0].getText() + " WINS!");
            running = false;
            buttons[0].setBackground(Color.green);
            buttons[1].setBackground(Color.green);
            buttons[2].setBackground(Color.green);
        }
        // ROW 2
        else if (buttons[3].getText().length() > 0
                && buttons[3].getText().equals(buttons[4].getText())
                && buttons[4].getText().equals(buttons[5].getText())) {
            turnText.setText(buttons[3].getText() + " WINS!");
            running = false;
            buttons[3].setBackground(Color.green);
            buttons[4].setBackground(Color.green);
            buttons[5].setBackground(Color.green);
        }
        // ROW 3
        else if (buttons[6].getText().length() > 0
                && buttons[6].getText().equals(buttons[7].getText())
                && buttons[7].getText().equals(buttons[8].getText())) {
            turnText.setText(buttons[6].getText() + " WINS!");
            running = false;
            buttons[6].setBackground(Color.green);
            buttons[7].setBackground(Color.green);
            buttons[8].setBackground(Color.green);
        }
        // COLUMN 1
        else if (buttons[0].getText().length() > 0
                && buttons[0].getText().equals(buttons[3].getText())
                && buttons[3].getText().equals(buttons[6].getText())) {
            turnText.setText(buttons[0].getText() + " WINS!");
            running = false;
            buttons[0].setBackground(Color.green);
            buttons[3].setBackground(Color.green);
            buttons[6].setBackground(Color.green);
        }
        // COLUMN 2
        else if (buttons[1].getText().length() > 0
                && buttons[1].getText().equals(buttons[4].getText())
                && buttons[4].getText().equals(buttons[7].getText())) {
            turnText.setText(buttons[1].getText() + " WINS!");
            running = false;
            buttons[1].setBackground(Color.green);
            buttons[4].setBackground(Color.green);
            buttons[7].setBackground(Color.green);
        }
        // COLUMN 3
        else if (buttons[2].getText().length() > 0
                && buttons[2].getText().equals(buttons[5].getText())
                && buttons[5].getText().equals(buttons[8].getText())) {
            turnText.setText(buttons[2].getText() + " WINS!");
            running = false;
            buttons[2].setBackground(Color.green);
            buttons[5].setBackground(Color.green);
            buttons[8].setBackground(Color.green);
        }
        // DIAGONAL 1
        else if (buttons[0].getText().length() > 0
                && buttons[0].getText().equals(buttons[4].getText())
                && buttons[4].getText().equals(buttons[8].getText())) {
            turnText.setText(buttons[0].getText() + " WINS!");
            running = false;
            buttons[0].setBackground(Color.green);
            buttons[4].setBackground(Color.green);
            buttons[8].setBackground(Color.green);
        }
        // DIAGONAL 2
        else if (buttons[2].getText().length() > 0
                && buttons[2].getText().equals(buttons[4].getText())
                && buttons[4].getText().equals(buttons[6].getText())) {
            turnText.setText(buttons[2].getText() + " WINS!");
            running = false;
            buttons[2].setBackground(Color.green);
            buttons[4].setBackground(Color.green);
            buttons[6].setBackground(Color.green);
        } else if (availableSquares == 0) {
            for (int i = 0; i < 9; i++) {
                buttons[i].setBackground(Color.lightGray);
                running = false;
                turnText.setText("IT'S A DRAW!");
            }
        }
    }
    //endregion

}
