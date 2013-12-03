package jazz;

public class RgbColor implements Color {

	final private int r;
	final private int g;
	final private int b;
	
	public RgbColor(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public int getR() {
		return r;
	}

	public int getG() {
		return g;
	}

	public int getB() {
		return b;
	}
}
