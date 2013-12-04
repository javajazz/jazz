package jazz;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

public class Jazz {

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

	public static Window display(final String title, int a, int b,
			final Animation animation) {
		return play(title, a, b, animation);
	}

	public static Window play(final String title, int a, int b,
			final World world) {
		return play(title, a, b, (Model) world);
	}

	public static WindowImpl play(final String title, final int a, final int b,
			final Model model) {
		final WindowImpl window = new WindowImpl();

		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					MainWindow mainWindow = new MainWindow(
							title, model, window, a, b);
					mainWindow.setVisible(true);
				}
			});
		} catch (InvocationTargetException | InterruptedException exc) {

		}

		return window;
	};
}
