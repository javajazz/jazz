package jazz.examples.pacman;

import jazz.Picture;
import jazz.Renderer;
import jazz.shapes.Pictures;
import jazz.shapes.Pie;

public class PacManRenderer implements Renderer<PacManWorld> {

	@Override
	public Picture render(PacManWorld w) {
		return new Pictures(
				new Pie(200, 200,
						w.pacManRotate + w.mouthOpening,
						w.pacManRotate + 360 - w.mouthOpening)
						.translate(w.posPacManX, w.posPacManY)
						.filled(true)
		);
	}

}
