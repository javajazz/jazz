package jazz.examples.tictactoe;

import jazz.Jazz;

public class TicTacToe {

  public static void main(String... args) {
    Jazz.play("Tic Tac Toe", 600, 600, new GameBoard());
  }
}
