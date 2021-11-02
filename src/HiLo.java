import java.util.*;

public class HiLo {

    public static void main(String[] args){
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        boolean playing;

        do{
            int tries = 0;
            int theGuess = 0;
            String playAgain;
            int theNumber = rand.nextInt(0,101);

            while(theGuess!=theNumber){
                System.out.println("Guess a number between 1 and 100:");
                theGuess = scan.nextInt();
                scan.nextLine();

                if(theGuess<theNumber) System.out.println(theGuess+" is too low. Try again");
                else if(theGuess>theNumber) System.out.println(theGuess+" is too high. Try again");
                else System.out.println(theGuess+" is correct! You win!");
                tries++;
            }
            System.out.println("It only took you "+tries+" tries! Good work!\n");
            System.out.println("Do you want to play again? (y/n)");

            playAgain = scan.nextLine();
            playing = (playAgain.contains("y"));

        }while(playing);
    }
}