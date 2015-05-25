package de.scravy.jazz;

/**
 *
 * @since 1.0.0
 * @author Julian Fleischer
 */
public interface Color {

  Color BLACK = new RgbColor(0, 0, 0);
  Color WHITE = new RgbColor(255, 255, 255);

  Color RED = new RgbColor(255, 0, 0);
  Color GREEN = new RgbColor(0, 255, 0);
  Color BLUE = new RgbColor(0, 0, 255);

  Color CYAN = new RgbColor(0, 255, 255);
  Color MAGENTA = new RgbColor(255, 0, 255);
  Color YELLOW = new RgbColor(255, 255, 0);

  Color TEAL = new RgbColor(0, 127, 127);
  Color PURPLE = new RgbColor(127, 0, 127);
  Color OLIVE = new RgbColor(127, 127, 0);

  Color ORANGE = new RgbColor(255, 127, 0);
  Color ROSE = new RgbColor(255, 0, 127);

  Color _1 = new RgbColor(0, 255, 127);
  Color _2 = new RgbColor(127, 255, 0);

  Color AZURE = new RgbColor(0, 127, 255);
  Color VIOLET = new RgbColor(127, 0, 255);

  Color MAROON = new RgbColor(127, 0, 0);
  Color DARK_GREEN = new RgbColor(0, 127, 0);
  Color NAVY = new RgbColor(0, 0, 127);

  Color FOREST = new RgbColor(34, 139, 34);
  Color BROWN = new RgbColor(150, 75, 0);

  java.awt.Color getAWTColor();
}
