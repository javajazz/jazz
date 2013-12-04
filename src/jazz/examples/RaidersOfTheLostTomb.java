package jazz.examples;

import jazz.Jazz;

public class RaidersOfTheLostTomb {

	public static void main(String... args) {
		Jazz.play("Raiders of the Lost Tomb", 0, 0, new TombWorld())
				.onClose(new Runnable() {
					public void run() {
						System.exit(0);
					}
				}).maxFps(80);
		;
	}
}
