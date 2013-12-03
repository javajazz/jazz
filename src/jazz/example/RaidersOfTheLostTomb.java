package jazz.example;

import jazz.Jazz;

public class RaidersOfTheLostTomb {

	public static void main(String... args) {
		Jazz
				.play("Raider of the Lost Tomb", new TombWorld())
				.onClose(
						new Runnable() {
							public void run() {
								System.exit(0);
							}
						});
	}
}
