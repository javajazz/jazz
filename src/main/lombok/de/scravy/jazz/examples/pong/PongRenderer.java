package de.scravy.jazz.examples.pong;

import de.scravy.jazz.Color;
import de.scravy.jazz.Picture;
import de.scravy.jazz.Renderer;
import de.scravy.jazz.pictures.mutable.Pictures;
import de.scravy.jazz.pictures.mutable.Rectangle;
import de.scravy.jazz.pictures.mutable.Square;
import de.scravy.jazz.pictures.mutable.Text;

public class PongRenderer implements Renderer<PongWorld> {

  @Override
  public Picture render(PongWorld model) {

    return new Pictures(
        new Rectangle(20, 100)
            .color(Color.BLACK)
            .filled(true)
            .translate(-390, model.paddle1),
        new Rectangle(20, 100)
            .color(Color.BLACK)
            .filled(true)
            .translate(+390, model.paddle2),
        new Square(10)
            .color(model.cooldown > 0 ? Color.RED : Color.BLACK)
            .filled(true)
            .translate(model.ballX, model.ballY),
        new Text(String.format("%d : %d", model.player1, model.player2))
            .scale(2, 2)
            .translate(0, 280));
  }

}
