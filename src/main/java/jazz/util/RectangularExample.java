package jazz.util;

import jazz.Color;
import jazz.Event;
import jazz.Jazz;
import jazz.Picture;
import jazz.World;
import jazz.pictures.mutable.Circle;

public class RectangularExample {

	public static class Thing {
		
		boolean activated = false;
	}
	
	public static void main(String... args) {
		Jazz.play("PLAY", 800, 600, new World() {

			RectangularGrid<Thing> grid = new RectangularGrid<>(
					8, 6, 50, 50,
					new TileFactory<Thing>() {

						@Override
						public Thing createTile(int x, int y) {
							return new Thing();
						}

					}, new TileEventHandler<Thing>() {
						@Override
						public void on(Event ev, Thing tile) {
							if (ev.getType() == Event.Type.CLICK) {
								tile.activated = true;
							}
						}

					}, new TileRenderer<Thing>() {
						@Override
						public Picture render(
								Thing tile,
								double x, double y,
								double width, double height) {
							return new Circle(Math.min(width, height) / 2)
									.translate(x, y).filled(tile.activated);
						}
					});

			@Override
			public void on(Event e) {
				grid.on(e);
			}

			@Override
			public Picture getPicture() {
				return grid.getPicture();
			}
		});
	}
}
