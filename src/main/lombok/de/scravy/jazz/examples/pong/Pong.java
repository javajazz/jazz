package de.scravy.jazz.examples.pong;

import de.scravy.jazz.Jazz;

public class Pong {

  private static final String title = "Pong";
  private static final int width = 800;
  private static final int height = 600;

  public static void main(String... args) {
    Jazz.play(title, width, height,
        new PongWorld(),
        new PongRenderer(),
        new PongUpdateHandler(),
        new PongEventHandler());
  }

}
