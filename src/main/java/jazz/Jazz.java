package jazz;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

public final class Jazz {

	static {
		System.setProperty("sun.java2d.opengl", "true");
	}

	private Jazz() {

	}

	public static Window display(final String title, int a, int b,
			final Picture picture) {
		return play(title, a, b, new Animation() {

			@Override
			public Picture getPicture() {
				return picture;
			}

			@Override
			public void update(double time, double delta) {

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

	public static <M> Window animate(final String title, int a, int b,
			final M w, final Renderer<M> r, final UpdateHandler<M> u) {
		return animate(title, a, b, new Animation() {
			M world = w;

			@Override
			public void update(double time, double delta) {
				u.update(world, time, delta);
			}

			@Override
			public Picture getPicture() {
				return r.render(world);
			}
		});
	}

	public static Window animateFullscreen(String title, Animation animation) {
		return play(title, 0, 0, animation);
	}

	public static Window play(final String title, int a, int b,
			final World world) {
		return play(title, a, b, (Model) world);
	}

	public static <M> Window play(final String title, int a, int b,
			final M world, final Renderer<M> r, final UpdateHandler<M> u,
			final EventHandler<M> e) {
		return play(title, a, b, new DelegatingWorld<M>(world, r, u, e));
	}

	public static <M> Window playFullscreen(final String title, int a, int b,
			final M world, final Renderer<M> r, final UpdateHandler<M> u,
			final EventHandler<M> e) {
		return play(title, 0, 0, new DelegatingWorld<M>(world, r, u, e));
	}
	public static Window playFullscreen(String title, World world) {
		return play(title, 0, 0, (Model) world);
	}

	static WindowImpl play(final String title, final int a, final int b,
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
