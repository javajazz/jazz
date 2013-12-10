package jazz.examples.pacman;

import jazz.UpdateHandler;

public class PacManUpdater implements UpdateHandler<PacManWorld> {

	@Override
	public void update(PacManWorld w, double time, double delta) {
		
		w.mouthOpening = Math.abs(30 * Math.sin(time * 5));

		w.posPacManX += w.speedX * delta * 50;
		w.posPacManY += w.speedY * delta * 50;
	}

}
