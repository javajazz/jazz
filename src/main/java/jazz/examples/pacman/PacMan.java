package jazz.examples.pacman;

import jazz.DelegatingWorld;
import jazz.Jazz;

public class PacMan {

	public static void main(String... args) {
		Jazz.play(
				"PacMan", 800, 600,
				new DelegatingWorld<PacManWorld>(
						new PacManWorld(),
						new PacManRenderer(),
						new PacManUpdater(),
						new PacManEventHandler()))
				.onClose(new Runnable() {
					@Override
					public void run() {
						System.exit(0);
					}
				}).maxFps(120);
	}

}
