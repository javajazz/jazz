package de.scravy.jazz.examples.pong;

import de.scravy.jazz.Event;
import de.scravy.jazz.EventHandler;

public class PongEventHandler implements EventHandler<PongWorld> {

  double paddleSpeed = 200;

  @Override
  public void on(PongWorld m, Event e) {

    switch (e.getType()) {

    case KEY_DOWN:
      switch (e.getKey()) {
      case _Q:
        m.paddle1speed = +paddleSpeed;
        break;
      case _A:
        m.paddle1speed = -paddleSpeed;
        break;
      case UP:
        m.paddle2speed = +paddleSpeed;
        break;
      case DOWN:
        m.paddle2speed = -paddleSpeed;
        break;
      default:
      }
      break;

    case KEY_UP:
      switch (e.getKey()) {
      case _Q:
      case _A:
        m.paddle1speed = 0;
        break;
      case UP:
      case DOWN:
        m.paddle2speed = 0;
        break;
      case SPACE:
        if (m.speedX == 0) {
          m.start();
        }
        break;
      default:
      }
      break;

    default:
    }
  }

}
