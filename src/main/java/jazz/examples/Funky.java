package jazz.examples;

import jazz.Animation;
import jazz.Jazz;
import jazz.Picture;
import jazz.RandomColor;
import jazz.shapes.Pictures;
import jazz.shapes.Rectangle;

public class Funky extends Animation {

	public static void main(String... args) {
		Jazz.animate("Funky town", 1000, 600, new Funky());
	}
	
	private Pictures pictures = new Pictures(); 
	
	public Funky() {
		Rectangle r = new Rectangle(50, 50).filled(true);

		for (int i = -525; i <= 525; i += 50) {
			for (int j = -325; j <= 325; j += 50) {
				pictures.add(r.clone().color(new RandomColor()).translate(i, j));
			}
		}
	}

	@Override
	public void update(double time, double delta) {
		for (Picture p : pictures) {
			p.color(new RandomColor(-1, 0, 0));
		}
		pictures.reset()
				.scale(2 * Math.sin(time), 2 * Math.sin(time))
				.rotate(time * 100);
	}

	@Override
	public Picture getPicture() {
		return pictures;
	}

}
