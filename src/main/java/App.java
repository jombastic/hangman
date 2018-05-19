import models.Hangman;

import java.io.IOException;
import java.util.Scanner;

public class App {
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to hangman! I will pick a word and you will try to guess it. " +
            "If you guess it wrong 6 times, that I win, If you can guess it before then, you win. \n");
    boolean playGame = true;
    while (playGame) {
      System.out.println("Let's play!");
      Hangman game = new Hangman();
      do {
        // Draw the things
        System.out.println("\n" + game.drawPicture());
        System.out.println("\n" + game.getFormalCurrentGuess());

        // Get the guess
        System.out.println("\nEnter a character that you think is in the word");
        char guess = (scanner.next().toLowerCase()).charAt(0);

        // Check if the character is guessed already
        while (game.isGuessedAlready(guess)) {
          System.out.println("\nTry again! You've already guessed the character.");
          guess = (scanner.next().toLowerCase()).charAt(0);
        }

        if (game.playGuess(guess)) {
          System.out.println("Great guess!");
        } else {
          System.out.println("Ups, wrong guess!");
        }
      } while (!game.gameOver());

      System.out.println("\nPlay again? Enter 'Y' if you do.");
      Character response = (scanner.next().toUpperCase()).charAt(0);
      playGame = (response == 'Y');
    }
  }
}
