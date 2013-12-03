package jazz;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

public class Jazz {

	public static Window display(final String title, int a, int b,
			final Picture picture) {
		return play(title, a, b, new World() {

			int startDragX = 0;
			int startDragY = 0;
			int initialOffsetX = 0;
			int initialOffsetY = 0;
			boolean mouseDown = false;

			@Override
			public void on(Event e) {
				switch (e.getType()) {

				case MOUSE_DOWN: {
					initialOffsetX = e.getWindow().originX();
					initialOffsetY = e.getWindow().originY();
					startDragX = e.getWindowX();
					startDragY = e.getWindowY();
					System.out.printf("%d %d\n", startDragX, startDragY);
					mouseDown = true;
					break;
				}

				case MOUSE_UP: {
					mouseDown = false;
					int deltaX = e.getWindowX() - startDragX;
					int deltaY = e.getWindowY() - startDragY;
					e.getWindow().originX(initialOffsetX + deltaX);
					e.getWindow().originY(initialOffsetY + deltaY);
					break;
				}

				case MOUSE_MOVE: {
					if (mouseDown) {
						int deltaX = e.getWindowX() - startDragX;
						int deltaY = e.getWindowY() - startDragY;
						e.getWindow().originX(initialOffsetX + deltaX);
						e.getWindow().originY(initialOffsetY + deltaY);
					}
					break;
				}

				default:
					break;
				}
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
