package de.scravy.jazz;

import jazz.Jazz;
import lombok.experimental.UtilityClass;

/**
 * Static utility methods to deal with colors.
 *
 * @author Julian Fleischer
 * @since 1.0.0
 */
@UtilityClass
public class Colors {

  public Color fromRGB(final int r, final int g, final int b) {
    return new RgbColor(r, g, b);
  }

  public Color fromRGB(final int r, final int g, final int b, final int alpha) {
    return new RgbColor(r, g, b, alpha);
  }

  public Color fromHSV(final int h, final int s, final int v) {
    return new HsvColor(h, s, v);
  }

  public Color randomColor() {
    return new RgbColor(Jazz.randomInt(256), Jazz.randomInt(256),
        Jazz.randomInt(256));
  }

  public Color randomColor(
      final int r, final int g, final int b) {

    return new RgbColor(
        r >= 0 && r < 256 ? r : Jazz.randomInt(256),
        g >= 0 && g < 256 ? g : Jazz.randomInt(256),
        b >= 0 && b < 256 ? b : Jazz.randomInt(256));
  }

  public Color randomColor(
      final int minR, final int maxR, final int minG,
      final int maxG, final int minB, final int maxB) {

    return new RgbColor(
        Jazz.randomInt(minR, maxR),
        Jazz.randomInt(minG, maxG),
        Jazz.randomInt(minB, maxB));
  }

  public Color randomColor(final int gray) {
    return randomColor(gray, gray, gray);
  }

  public Color randomColor(final int minGray, final int maxGray) {
    return randomColor(Jazz.randomInt(minGray, maxGray));
  }
}
