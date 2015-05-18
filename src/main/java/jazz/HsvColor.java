package jazz;

public class HsvColor implements Color {

  final private float h;
  final private float s;
  final private float v;

  public HsvColor(final float h, final float s, final float v) {
    this.h = h;
    this.s = s;
    this.v = v;
  }

  public float getHue() {
    return h;
  }

  public float getSaturation() {
    return s;
  }

  public float getValue() {
    return v;
  }

  @Override
  public java.awt.Color getAWTColor() {
    return java.awt.Color.getHSBColor(h, s, v);
  }
}
