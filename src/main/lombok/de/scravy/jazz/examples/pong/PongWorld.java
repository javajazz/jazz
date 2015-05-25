package de.scravy.jazz.examples.pong;

public class PongWorld {

  int player1 = 0;
  int player2 = 0;

  double paddle1;
  double paddle2;

  double ballX;
  double ballY;

  double speedX;
  double speedY;

  double paddle1speed;
  double paddle2speed;

  double cooldown = 0;

  void reset() {
    ballX = 0;
    ballY = 0;

    speedX = 0;
    speedY = 0;

    paddle1 = 0;
    paddle2 = 0;
  }

  void start() {
    speedX = 150;
    speedY = 100;
  }
}
