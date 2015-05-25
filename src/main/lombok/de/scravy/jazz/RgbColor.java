package de.scravy.jazz;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 *
 * @since 1.0.0
 * @author Julian Fleischer
 */
@Value
@AllArgsConstructor
class RgbColor implements Color {

  final private int r;
  final private int g;
  final private int b;
  final private int a;

  public RgbColor(final int r, final int g, final int b) {
    this(r, g, b, 255);
  }

  @Override
  public java.awt.Color getAWTColor() {
    return new java.awt.Color(this.r, this.g, this.b);
  }
}
