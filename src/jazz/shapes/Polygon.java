package jazz.shapes;

import jazz.Point;

public class Polygon extends AbstractPicture<Polygon> {

	static int[] getXCoords(Point[] points) {
		int[] coords = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			coords[i] = (int) points[i].x;
		}
		return coords;
	}
	
	static int[] getYCoords(Point[] points) {
		int[] coords = new int[points.length];
		
		return coords;
	}
	
	public Polygon(Point... points) {
		super(new java.awt.Polygon(getXCoords(points), getYCoords(points), points.length));
	}

	@Override
	public Polygon clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
