package jazz.examples.pong;

import jazz.Color;
import jazz.Picture;
import jazz.Renderer;
import jazz.pictures.Pictures;
import jazz.pictures.Rectangle;
import jazz.pictures.Square;
import jazz.pictures.Text;

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
