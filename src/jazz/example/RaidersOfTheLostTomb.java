package jazz.example;

import jazz.Jazz;
import jazz.shapes.Rectangle;

public class RaidersOfTheLostTomb {

	public static void main(String... args) {
		Jazz
				.display("Raider of the Lost Tomb", 1000, 600,
						new Rectangle(200, 200).filled(true).rotate(-45))
				.onClose(new Runnable() {
					public void run() {
						System.exit(0);
					}
				})
				.antialias(false);
		;

		/*
		 * new TombWorld())
		 */
	}
}
