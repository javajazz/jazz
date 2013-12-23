package jazz;

public class RandomColor extends RgbColor {

  public RandomColor() {
    super(Jazz.randomInt(256), Jazz.randomInt(256), Jazz.randomInt(256));
  }

  public RandomColor(int r, int g, int b) {
    super(
        r >= 0 && r < 256 ? r : Jazz.randomInt(256),
        g >= 0 && g < 256 ? g : Jazz.randomInt(256),
        b >= 0 && b < 256 ? b : Jazz.randomInt(256));
  }

  public RandomColor(int minR, int maxR, int minG, int maxG, int minB, int maxB) {
    this(
        Jazz.randomInt(minR, maxR),
        Jazz.randomInt(minG, maxG),
        Jazz.randomInt(minB, maxB));
  }

  private RandomColor(int gray) {
    super(gray, gray, gray);
  }

  public RandomColor(int minGray, int maxGray) {
    this(Jazz.randomInt(minGray, maxGray));
  }
}
