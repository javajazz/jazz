package jazz;

import javax.swing.SwingUtilities;

public class Jazz {
	
	public static Window display(final String title, final Picture picture) {
		return play(title, new Model() {

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

	public static Window display(final String title, final Animation animation) {
		return play(title, animation);
	}

	public static Window play(final String title, final World world) {
		return play(title, (Model) world);
	}
	
	static WindowImpl play(final String title, final Model model) {
		final WindowImpl window = new WindowImpl();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow mainWindow = new MainWindow(title, model, window);
				mainWindow.setVisible(true);
			}
		});

		return window;
	};
}
