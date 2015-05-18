package jazz;

public class RandomColor extends RgbColor {

  public RandomColor() {
    super(Jazz.randomInt(256), Jazz.randomInt(256), Jazz.randomInt(256));
  }

  public RandomColor(final int r, final int g, final int b) {
    super(
        r >= 0 && r < 256 ? r : Jazz.randomInt(256),
        g >= 0 && g < 256 ? g : Jazz.randomInt(256),
        b >= 0 && b < 256 ? b : Jazz.randomInt(256));
  }

  public RandomColor(final int minR, final int maxR, final int minG,
      final int maxG, final int minB,
      final int maxB) {
    this(
        Jazz.randomInt(minR, maxR),
        Jazz.randomInt(minG, maxG),
        Jazz.randomInt(minB, maxB));
  }

  private RandomColor(final int gray) {
    super(gray, gray, gray);
  }

  public RandomColor(final int minGray, final int maxGray) {
    this(Jazz.randomInt(minGray, maxGray));
  }
}
