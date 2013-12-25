package jazz.util;

import jazz.Color;
import jazz.Event;
import jazz.Jazz;
import jazz.MouseButton;
import jazz.Picture;
import jazz.Vector;
import jazz.World;
import jazz.pictures.mutable.Rectangle;

public class RectangularArea extends AbstractGrid<RectangularArea> {

	public RectangularArea(double width, double height) {
		super(width, height);
	}

	public Picture getPicture() {
		Vector p = getCenter();
		return new Rectangle(width, height).translate(p.x, p.y);
	}

	public static void main(String... args) {

		Jazz.play("TITEL", 800, 600, new World() {

			RectangularArea a = new RectangularArea(200, 150);

			@Override
			public void update(double time, double delta) {

			}

			@Override
			public void on(Event e) {
				if (e.getType() == Event.Type.CLICK) {
					if (e.getButton() == MouseButton.RIGHT) {
						a.setCenter(e.getX(), e.getY());
					} else if (e.getX() > 0) {
						if (e.getY() > 0) {
							a.setUpperRightCorner(new Vector(e.getX(), e.getY()), !e.isCtrlPressed());
						} else {
							a.setLowerRightCorner(new Vector(e.getX(), e.getY()), !e.isCtrlPressed());
						}
					} else {
						if (e.getY() > 0) {
							a.setUpperLeftCorner(new Vector(e.getX(), e.getY()), !e.isCtrlPressed());
						} else {
							a.setLowerLeftCorner(new Vector(e.getX(), e.getY()), !e.isCtrlPressed());
						}
					}
				}
			}

			@Override
			public Picture getPicture() {
				return a.getPicture().color(Color.BLACK);
			}

		});

	}
}
