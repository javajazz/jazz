package de.scravy.jazz.examples.pong;

import de.scravy.jazz.UpdateHandler;

public class PongUpdateHandler implements UpdateHandler<PongWorld> {

  @Override
  public void update(PongWorld m, double time, double delta) {
    if (m.speedX == 0) {
      return;
    }

    double accel = 1;
    if (m.cooldown > 0) {
      m.cooldown -= delta;
      delta *= 1.5;
      accel = 1.5;
    }

    double paddle1 = m.paddle1 + m.paddle1speed * delta;
    double paddle2 = m.paddle2 + m.paddle2speed * delta;

    m.paddle1 = Math.min(250, Math.max(-250, paddle1));
    m.paddle2 = Math.min(250, Math.max(-250, paddle2));

    double ballX = m.ballX + m.speedX * delta;
    double ballY = m.ballY + m.speedY * (delta * accel);

    m.ballX = Math.min(385, Math.max(-385, ballX));
    m.ballY = Math.min(295, Math.max(-295, ballY));

    if (ballX >= 385) {
      double dist = Math.abs(ballY - m.paddle2);
      if (dist <= 55) {
        m.speedX *= -1.0;
        m.speedX -= 10;
        if (dist > 40) {
          m.cooldown = 5;
        }
      } else {
        m.player1 += 1;
        m.reset();
      }
    } else if (ballX <= -385) {
      double dist = Math.abs(ballY - m.paddle1);
      if (dist <= 55) {
        m.speedX *= -1.0;
        m.speedX += 10;
        if (dist > 40) {
          m.cooldown = 5;
        }
      } else {
        m.player2 += 1;
        m.reset();
      }
    } else if (Math.abs(m.ballY) >= 295) {
      m.speedY *= -1;
    }
  }

}
