import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class HiLo extends JFrame {

  private final JTextField txtGuess;
  private final JLabel lblOutput;
  private final JLabel triesOutput;
  private int theNumber;
  private int theScore;
  private int tries = 0;

  //GUI Controls
  public HiLo() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Austin's HiLo Guessing Game");
    getContentPane().setLayout(null);

    JLabel lblTitle = new JLabel("Austin's HiLo Guessing Game!");
    lblTitle.setFont(new Font("Impact", Font.PLAIN, 20));
    lblTitle.setBounds(100, 11, 234, 26);
    getContentPane().add(lblTitle);

    JLabel lblInstructions = new JLabel("Guess a number between 1 and 100:");
    lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblInstructions.setBounds(56, 81, 254, 17);
    getContentPane().add(lblInstructions);

    txtGuess = new JTextField();
    txtGuess.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        tries++;
        checkGuess();
      }
    });
    txtGuess.setBounds(320, 81, 44, 20);
    getContentPane().add(txtGuess);
    txtGuess.setColumns(10);

    JButton guessButton = new JButton("Guess!");
    guessButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        tries++;
        checkGuess();
      }
    });
    guessButton.setBounds(180, 122, 74, 35);
    getContentPane().add(guessButton);

    lblOutput = new JLabel("Enter a number above and click Guess!");
    lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
    lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 12));
    lblOutput.setBounds(10, 185, 414, 15);
    getContentPane().add(lblOutput);

    JLabel lblTriesText = new JLabel("# Tries");
    lblTriesText.setHorizontalAlignment(SwingConstants.CENTER);
    lblTriesText.setBounds(378, 191, 46, 14);
    getContentPane().add(lblTriesText);

    triesOutput = new JLabel("0");
    triesOutput.setHorizontalAlignment(SwingConstants.CENTER);
    triesOutput.setFont(new Font("Impact", Font.PLAIN, 23));
    triesOutput.setBounds(393, 208, 16, 42);
    getContentPane().add(triesOutput);
  }


  //Check if guess is correct
  public void checkGuess() {

    String guessText = txtGuess.getText();
    String message1 = "";

    try {
      int theGuess = Integer.parseInt(guessText);
      if (theGuess < theNumber) {
        message1 = theGuess + " is too low. Try again.";
      } else if (theGuess > theNumber) {
        message1 = theGuess + " is too high. Try again.";
      } else {
        message1 = theGuess + " is correct! You win!";
        newGame(this.theScore);
      }
    } catch (Exception e) {
      message1 = "ERROR: Enter a whole number between 1 and 100";
    } finally {
      lblOutput.setText(message1);
      triesOutput.setText(Integer.toString(tries));

      //txtGuess.setText("");
      txtGuess.grabFocus();
      txtGuess.selectAll();
    }
  }


  //Start new gameplay session and generate random number
  public void newGame(int theScore) {
    this.theScore = theScore;
    Random rand = new Random();
    theNumber = rand.nextInt(101);
  }


  //set initial score as zero and start GUI session
  public static void main(String[] args) {
    int score = 0;

    HiLo theGame = new HiLo();
    theGame.newGame(score);
    theGame.setSize(new Dimension(450, 300));
    theGame.setVisible(true);
  }
}