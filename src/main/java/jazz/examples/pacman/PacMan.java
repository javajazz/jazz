package jazz.examples.pacman;

import java.io.IOException;

import jazz.DelegatingWorld;
import jazz.Jazz;

public class PacMan {

	public static void main(String... args) throws IOException {
		Jazz.play(
				"PacMan", 0, 0,
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
				}).maxFps(120).antialias(false);
	}

}
