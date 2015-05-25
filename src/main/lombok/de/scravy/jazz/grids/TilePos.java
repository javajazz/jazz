package de.scravy.jazz.grids;

import lombok.Value;
import de.scravy.pair.Pair;

@Value
public class TilePos implements Pair<Integer, Integer> {

  public final int x;
  public final int y;

  @Override
  public String toString() {
    return String.format("(%d,%d)", this.x, this.y);
  }

  @Override
  public Integer getFirst() {
    return Integer.valueOf(this.x);
  }

  @Override
  public Integer getSecond() {
    return Integer.valueOf(this.y);
  }
}
