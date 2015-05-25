package de.scravy.jazz.grids;

import lombok.Value;
import de.scravy.pair.ComparableSerializablePair;
import de.scravy.pair.Pairs;

@Value
public class TilePos implements ComparableSerializablePair<Integer, Integer> {

  private static final long serialVersionUID = 1L;

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

  @Override
  public int compareTo(final ComparableSerializablePair<Integer, Integer> o) {
    return Pairs.compare(this, o);
  }
}
