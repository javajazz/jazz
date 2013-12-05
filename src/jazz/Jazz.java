package jazz;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

public class Jazz {

	static {
		System.setProperty("sun.java2d.opengl", "true");
	}

	public static Window display(final String title, int a, int b,
			final Picture picture) {
		return play(title, a, b, new Animation() {

			@Override
			public Picture getPicture() {
				return picture;
			}

			@Override
			public void update(double time) {

			}
		});
	}

	public static Window displayFullscreen(String title, Picture picture) {
		return display(title, 0, 0, picture);
	}

	public static Window animate(final String title, int a, int b,
			final Animation animation) {
		return play(title, a, b, animation);
	}

	public static Window animateFullscreen(String title, Animation animation) {
		return play(title, 0, 0, animation);
	}

	public static Window play(final String title, int a, int b,
			final World world) {
		return play(title, a, b, (Model) world);
	}

	public static Window playFullscreen(String title, World world) {
		return play(title, 0, 0, (Model) world);
	}

	static Window play(final String title, final int a, final int b,
			final Model model) {
		final WindowImpl window = new WindowImpl();

		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					final MainWindow mainWindow = new MainWindow(
							title, model, window, a, b);
					final GraphicsDevice device = GraphicsEnvironment
							.getLocalGraphicsEnvironment()
							.getDefaultScreenDevice();
					if ((a <= 0 || b <= 0) && device.isFullScreenSupported()) {
						device.setFullScreenWindow(mainWindow);
						mainWindow.setVisible(false);
					}
					mainWindow.setVisible(true);
				}
			});
		} catch (InvocationTargetException | InterruptedException exc) {

		}
		return window;
	};
}
