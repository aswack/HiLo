import java.util.*;

public class HiLo {

    public static void main(String[] args){
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        boolean playing = true;

        do{
            int tries = 1;
            int theNumber = rand.nextInt(0,101);
            System.out.println("Guess a number between 1 and 100:");
            int theGuess = scan.nextInt();

            while(theGuess!=theNumber){
                if(theGuess<theNumber) System.out.println(theGuess+" is too low. Try again");
                else if(theGuess>theNumber) System.out.println(theGuess+" is too high. Try again");

                System.out.println("Guess a number between 1 and 100:");
                theGuess = scan.nextInt();
                tries++;
            }
            System.out.println(theGuess+" is correct! You win!");
            System.out.println("It only took you "+tries+" tries! Good work!");
            playing = false;

        }while(playing == true);
    }
}