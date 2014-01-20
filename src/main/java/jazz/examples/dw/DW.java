package jazz.examples.dw;

import jazz.Event;
import jazz.Jazz;
import jazz.Picture;
import jazz.World;
import jazz.util.SimpleHexagonGrid;

public class DW {

	public static void main(String... args) {
		Jazz.play("DW", 800, 600, new World() {

			SimpleHexagonGrid<Object> grid = new SimpleHexagonGrid<Object>(
					null, 10, 45, 35);

			@Override
			public void update(double time, double delta) {

			}

			@Override
			public void on(Event e) {

			}

			@Override
			public Picture getPicture() {
				return grid.getPicture();
			}
		});
	}
}
