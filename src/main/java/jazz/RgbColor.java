package jazz;

public class RgbColor implements Color {

  final private int r;
  final private int g;
  final private int b;
  final private int a;

  public RgbColor(final int r, final int g, final int b) {
    this.r = r;
    this.g = g;
    this.b = b;
    this.a = 255;
  }

  public RgbColor(final int r, final int g, final int b, final int a) {
    this.r = r;
    this.g = g;
    this.b = b;
    this.a = a;
  }

  public int getRed() {
    return r;
  }

  public int getGreen() {
    return g;
  }

  public int getBlue() {
    return b;
  }

  public int getAlpha() {
    return a;
  }

  @Override
  public java.awt.Color getAWTColor() {
    return new java.awt.Color(r, g, b);
  }
}
