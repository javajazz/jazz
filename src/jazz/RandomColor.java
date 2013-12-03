package jazz;

public class RandomColor extends RgbColor {

	public RandomColor() {
		super(
				(int) Math.floor(Math.random() * 255),
				(int) Math.floor(Math.random() * 255),
				(int) Math.floor(Math.random() * 255));
	}

	public RandomColor(int r, int g, int b) {
		super(
				r >= 0 && r < 256 ? r : (int) Math.floor(Math.random() * 255),
				g >= 0 && g < 256 ? g : (int) Math.floor(Math.random() * 255),
				b >= 0 && b < 256 ? b : (int) Math.floor(Math.random() * 255));
	}
	
	public RandomColor(int minR, int maxR, int minG, int maxG, int minB, int maxB) {
		this(
				minR + (int) Math.floor(Math.random() * (maxR - minR)),
				minG + (int) Math.floor(Math.random() * (maxG - minG)),
				minB + (int) Math.floor(Math.random() * (maxB - minB)));
	}

	private RandomColor(int gray) {
		super(gray, gray, gray);
	}

	public RandomColor(int minGray, int maxGray) {
		this(minGray + (int) Math.floor(Math.random() * (maxGray - minGray)));
	}
}
