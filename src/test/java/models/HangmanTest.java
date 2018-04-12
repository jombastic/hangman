package models;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class HangmanTest {
  @Test
  public void newHangman_instanceOfHangman() throws IOException {
    Hangman testHangman = new Hangman();
    assertEquals(true, testHangman instanceof Hangman);
  }

  @Test
  public void pickWord_generateRandomWordFromLibrary_String() throws IOException {
    Hangman testHangman = new Hangman();
    assertEquals(true, testHangman.pickWord() instanceof String);
  }

  @Test
  public void initializeCurrentGuess_initializeAGuessWithSpacesAndUnderscores_StringBuilder() throws IOException {
    Hangman testHangman = new Hangman();
    assertEquals(true, testHangman.initializeCurrentGuess() instanceof StringBuilder);
  }

  @Test
  public void didWeLoose_inCaseWeLoose_boolean() throws IOException {
    Hangman testHangman = new Hangman();
    assertEquals(false, testHangman.didWeLoose());
  }

  @Test
  public void didWeWin_inCaseWeWin_boolean() throws IOException {
    Hangman testHangman = new Hangman();
    assertEquals(false, testHangman.didWeWin());
  }

  @Test
  public void gameOver_checkIfGameOver_boolean() throws IOException {
    Hangman testHangman = new Hangman();
    assertEquals(false, testHangman.gameOver());
  }

  @Test
  public void getCondensedCurrentGuess_trimWhitespaceFromTheGuess_String() throws IOException {
    Hangman testHangman = new Hangman();
    assertEquals(true, testHangman.getCondensedCurrentGuess() instanceof String);
  }

  @Test
  public void playGuess_checkIfGuessIsTrue_boolean() throws IOException {
    Hangman testHangman = new Hangman();
    assertEquals(true, testHangman.playGuess('a'));
  }

  @Test
  public void playGuess_checkIfGuessIsFalse_boolean() throws IOException {
    Hangman testHangman = new Hangman();
    assertEquals(false, testHangman.playGuess('a'));
  }
}