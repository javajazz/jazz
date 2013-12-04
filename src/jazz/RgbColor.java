package jazz;

public class RgbColor implements Color {

	final private int r;
	final private int g;
	final private int b;
	final private int a;

	public RgbColor(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 255;
	}

	public RgbColor(int r, int g, int b, int a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public int getRed() {
		return r;
	}

	public int getGreen() {
		return g;
	}

	public int getBlue() {
		return b;
	}
	
	public int getAlpha() {
		return a;
	}
}
