package jazz;

import java.util.Objects;

class Extent {

	final int ax;
	final int ay;
	final int bx;
	final int by;

	Extent(Point a, Point b) {
		this(
				(int) Math.round(a.x),
				(int) Math.round(a.y),
				(int) Math.round(b.x),
				(int) Math.round(b.y));
	}

	Extent(int ax, int ay, int bx, int by) {
		this.ax = ax < bx ? ax : bx;
		this.ay = ay < by ? ay : by;
		this.bx = ax >= bx ? ax : bx;
		this.by = ay >= by ? ay : by;
	}

	public Point getUpperLeftCorner() {
		return new Point(ax, ay);
	}

	public Point getLowerLeftCorner() {
		return new Point(ax, by);
	}

	public Point getUpperRightCorner() {
		return new Point(bx, ay);
	}

	public Point getLowerRightCorner() {
		return new Point(bx, by);
	}

	public int getLeftX() {
		return ax;
	}

	public int getRightX() {
		return bx;
	}

	public int getUpperY() {
		return ay;
	}

	public int getLowerY() {
		return by;
	}

	@Override
	public String toString() {
		return String.format("[(%d,%d),(%d,%d)]", ax, ay, bx, by);
	}
	
	@Override
	public boolean equals(Object e) {
		if (e instanceof Extent) {
			return ((Extent) e).ax == ax && ((Extent) e).ay == ay
					&& ((Extent) e).bx == bx && ((Extent) e).by == by;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(ax, ay, bx, by);
	}
}
