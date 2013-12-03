package jazz;

public interface Color {

	final Color BLACK = new RgbColor(0, 0, 0);
	final Color WHITE = new RgbColor(255, 255, 255);

	final Color RED = new RgbColor(255, 0, 0);
	final Color GREEN = new RgbColor(0, 255, 0);
	final Color BLUE = new RgbColor(0, 0, 255);

	final Color CYAN = new RgbColor(0, 255, 255);
	final Color MAGENTA = new RgbColor(255, 0, 255);
	final Color YELLOW = new RgbColor(255, 255, 0);

	final Color ORANGE = new RgbColor(255, 0xA5, 0);
}
