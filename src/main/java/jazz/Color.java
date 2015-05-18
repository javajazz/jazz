package jazz;

public interface Color {

  final Color BLACK = new RgbColor(0, 0, 0);
  final Color WHITE = new RgbColor(255, 255, 255);

  final Color RED = new RgbColor(255, 0, 0);
  final Color GREEN = new RgbColor(0, 255, 0);
  final Color BLUE = new RgbColor(0, 0, 255);

  final Color CYAN = new RgbColor(0, 255, 255);
  final Color MAGENTA = new RgbColor(255, 0, 255);
  final Color YELLOW = new RgbColor(255, 255, 0);

  final Color TEAL = new RgbColor(0, 127, 127);
  final Color PURPLE = new RgbColor(127, 0, 127);
  final Color OLIVE = new RgbColor(127, 127, 0);

  final Color ORANGE = new RgbColor(255, 127, 0);
  final Color ROSE = new RgbColor(255, 0, 127);

  final Color _1 = new RgbColor(0, 255, 127);
  final Color _2 = new RgbColor(127, 255, 0);

  final Color AZURE = new RgbColor(0, 127, 255);
  final Color VIOLET = new RgbColor(127, 0, 255);

  final Color MAROON = new RgbColor(127, 0, 0);
  final Color DARK_GREEN = new RgbColor(0, 127, 0);
  final Color NAVY = new RgbColor(0, 0, 127);

  final Color FOREST = new RgbColor(34, 139, 34);
  final Color BROWN = new RgbColor(150, 75, 0);

  java.awt.Color getAWTColor();
}
