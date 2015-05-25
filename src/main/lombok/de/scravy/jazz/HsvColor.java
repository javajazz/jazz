package de.scravy.jazz;

import lombok.Value;

@Value
class HsvColor implements Color {

  final private float h;
  final private float s;
  final private float v;

  @Override
  public java.awt.Color getAWTColor() {
    return java.awt.Color.getHSBColor(this.h, this.s, this.v);
  }
}
