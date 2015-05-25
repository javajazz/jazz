package de.scravy.jazz;

/**
 *
 * @since 1.0.0
 * @author Julian Fleischer
 */
public interface Color {

  public static final Color BLACK = new RgbColor(0, 0, 0);
  public static final Color WHITE = new RgbColor(255, 255, 255);

  public static final Color RED = new RgbColor(255, 0, 0);
  public static final Color GREEN = new RgbColor(0, 255, 0);
  public static final Color BLUE = new RgbColor(0, 0, 255);

  public static final Color CYAN = new RgbColor(0, 255, 255);
  public static final Color MAGENTA = new RgbColor(255, 0, 255);
  public static final Color YELLOW = new RgbColor(255, 255, 0);

  public static final Color TEAL = new RgbColor(0, 127, 127);
  public static final Color PURPLE = new RgbColor(127, 0, 127);
  public static final Color OLIVE = new RgbColor(127, 127, 0);

  public static final Color ORANGE = new RgbColor(255, 127, 0);
  public static final Color ROSE = new RgbColor(255, 0, 127);

  public static final Color _1 = new RgbColor(0, 255, 127);
  public static final Color _2 = new RgbColor(127, 255, 0);

  public static final Color AZURE = new RgbColor(0, 127, 255);
  public static final Color VIOLET = new RgbColor(127, 0, 255);

  public static final Color MAROON = new RgbColor(127, 0, 0);
  public static final Color DARK_GREEN = new RgbColor(0, 127, 0);
  public static final Color NAVY = new RgbColor(0, 0, 127);

  public static final Color FOREST = new RgbColor(34, 139, 34);
  public static final Color BROWN = new RgbColor(150, 75, 0);

  java.awt.Color getAWTColor();
}
