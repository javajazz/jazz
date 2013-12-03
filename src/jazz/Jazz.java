package jazz;

import javax.swing.SwingUtilities;

public class Jazz {

	public static Window display(final String title, int a, int b,
			final Picture picture) {
		return play(title, a, b, new Model() {

			@Override
			public void update(double time) {
				// do nothing
			}

			@Override
			public void on(Event e) {
				// do nothing
			}

			@Override
			public Picture getPicture() {
				return picture;
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

	static WindowImpl play(final String title, final int a, final int b,
			final Model model) {
		final WindowImpl window = new WindowImpl();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow mainWindow = new MainWindow(
						title, model, window, a, b);
				mainWindow.setVisible(true);
			}
		});

		return window;
	};
}
