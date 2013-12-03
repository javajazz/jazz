package jazz;

public class RandomColor extends RgbColor {

	public RandomColor() {
		super(
				(int) Math.floor(Math.random() * 255),
				(int) Math.floor(Math.random() * 255),
				(int) Math.floor(Math.random() * 255));
	}
}
