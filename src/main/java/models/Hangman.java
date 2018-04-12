package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hangman {
  private String mysteryWord;
  private StringBuilder currentGuess;
  private List<Character> previousGuesses = new ArrayList<>();

  private int maxTries = 6;
  private int currentTry = 0;

  private List<String> dictionary = new ArrayList<>();
  private static FileReader fileReader;
  private static BufferedReader bufferedFileReader;

  // create constructor
  public Hangman() throws IOException {
    initializeStreams();
    this.mysteryWord = pickWord();
    this.currentGuess = initializeCurrentGuess();
  }

  // fill the dictionary with words from a text file
  private void initializeStreams() throws IOException {
    try {
      File inFile = new File("dictionary.txt");
      fileReader = new FileReader(inFile);
      bufferedFileReader = new BufferedReader(fileReader);
      String currentLine = bufferedFileReader.readLine();
      while (currentLine != null) {
        dictionary.add(currentLine);
        currentLine = bufferedFileReader.readLine();
      }
      bufferedFileReader.close();
      fileReader.close();
    } catch (IOException e) {
      System.out.println("Could not init streams");
    }
  }

  // pick a random word from dictionary list
  public String pickWord() {
    Random rand = new Random();
    int wordIndex = Math.abs(rand.nextInt()) % dictionary.size();
    return dictionary.get(wordIndex);
  }

  // initialise guess so it would look like this
  // _ A _ _ _ _ _ _

  public StringBuilder initializeCurrentGuess() {
    StringBuilder current = new StringBuilder();
    for (int i = 0; i < mysteryWord.length() * 2; i++) { //the length is * 2 cause we want spaces between characters
      if (i % 2 == 0) {         // if the index is even fill it with a _ where a letter should be
        current.append("_");
      } else {                  // else with a blank space
        current.append(" ");
      }
    }
    return current;
  }

  // show the current guess
  // _ _ A _ _ B
  public String getFormalCurrentGuess() {
    return "Current Guess: " + currentGuess.toString();
  }

  // output a particular message if won or lost and return a boolean to stop or continue game
  public boolean gameOver() {
    if (didWeWin()) {
      System.out.println("\nCongrats! We won!");
      return true;
    } else if (didWeLoose()) {
      System.out.println("\n" + fullPersonDraw());
      System.out.println("\nSorry, you lost. You spend all your 6 tries. " +
              "The word is " + mysteryWord + ".");
      return true;
    } else {
      return false;
    }
  }

  // return true of false depending if the current try exceeds or equals the max tries
  public boolean didWeLoose() {
    return currentTry >= maxTries;
  }

  // return true if the word is guessed (if it is the same as the mystery word)
  public boolean didWeWin() {
    String guess = getCondensedCurrentGuess();
    return guess.equals(mysteryWord);
  }

  // remove the white spaces from the current guess so it can be compared in the didWeWin() method
  public String getCondensedCurrentGuess() {
    String guess = currentGuess.toString();
    return guess.replace(" ", "");
  }

  // return true if the guess has been inputted before
  public boolean isGuessedAlready(char guess) {
    return previousGuesses.contains(guess);
  }

  // the method that checks the guesses
  public boolean playGuess(char guess) {
    boolean isItAGoodGuess = false;
    previousGuesses.add(guess);  // adds the guess to the previous guesses List
    for (int i = 0; i < mysteryWord.length(); i++) {  // checks every character in the mystery word
      if (mysteryWord.charAt(i) == guess) {
        currentGuess.setCharAt(i * 2, guess);  // if found it sets it in the current guess
        isItAGoodGuess = true;
      }
    }

    if (!isItAGoodGuess) {   // if the guess is not found in the mystery word take one from tries
      currentTry++;
    }
    return isItAGoodGuess;
  }

  // " - - - - -\n"+
  // "|        |\n"+
  // "|        O\n" +
  // "|      / | \\ \n"+
  // "|        |\n" +
  // "|       / \\ \n" +
  // "|\n" +
  // "|\n";

  public String drawPicture() {  // draws a particular picture depending of the tries
    switch (currentTry) {
      case 0:
        return noPersonDraw();
      case 1:
        return addHeadDraw();
      case 2:
        return addBodyDraw();
      case 3:
        return addOneArmDraw();
      case 4:
        return addSecondArmDraw();
      case 5:
        return addLeftLegDraw();
      default:
        return fullPersonDraw();
    }
  }

  private String noPersonDraw() {
    return " - - - - -\n" +
            "|        |\n" +
            "|        \n" +
            "|        \n" +
            "|        \n" +
            "|         \n" +
            "|\n" +
            "|\n";
  }

  private String addHeadDraw() {
    return " - - - - -\n" +
            "|        |\n" +
            "|        O\n" +
            "|       \n" +
            "|        \n" +
            "|        \n" +
            "|\n" +
            "|\n";
  }

  private String addBodyDraw() {
    return " - - - - -\n" +
            "|        |\n" +
            "|        O\n" +
            "|        |  \n" +
            "|          \n" +
            "|           \n" +
            "|\n" +
            "|\n";
  }

  private String addOneArmDraw() {
    return " - - - - -\n" +
            "|        |\n" +
            "|        O\n" +
            "|      / |  \n" +
            "|        \n" +
            "|        \n" +
            "|\n" +
            "|\n";
  }

  private String addSecondArmDraw() {
    return " - - - - -\n" +
            "|        |\n" +
            "|        O\n" +
            "|      / | \\ \n" +
            "|        \n" +
            "|        \n" +
            "|\n" +
            "|\n";
  }

  private String addLeftLegDraw() {
    return " - - - - -\n" +
            "|        |\n" +
            "|        O\n" +
            "|      / | \\ \n" +
            "|        |\n" +
            "|       /  \n" +
            "|\n" +
            "|\n";
  }

  private String fullPersonDraw() {
    return " - - - - -\n" +
            "|        |\n" +
            "|        O\n" +
            "|      / | \\ \n" +
            "|        |\n" +
            "|       / \\ \n" +
            "|\n" +
            "|\n";
  }
}
