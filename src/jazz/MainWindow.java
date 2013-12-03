package jazz;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
class MainWindow extends JFrame {

	final MainPanel mainPanel;

	MainWindow(final String title, final Model model, final WindowImpl window,
			final int a, final int b) {
		super(title);

		mainPanel = new MainPanel(this, model, window, a, b);

		setContentPane(mainPanel);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);

		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
				// do nothing
			}

			@Override
			public void windowClosing(WindowEvent e) {
				mainPanel.stop();
				MainWindow.this.dispose();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				model.on(new EventImpl(window, Event.Type.WINDOW_CLOSED, e));
				window.onClose();
			}

			@Override
			public void windowIconified(WindowEvent e) {
				mainPanel.pause();
				window.onHide();
				model.on(new EventImpl(window, Event.Type.WINDOW_HIDDEN, e));
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				mainPanel.resume();
				window.onShow();
				model.on(new EventImpl(window, Event.Type.WINDOW_SHOWN, e));
			}

			@Override
			public void windowActivated(WindowEvent e) {
				window.onFocus();
				model.on(new EventImpl(window, Event.Type.WINDOW_ACTIVATED, e));
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				window.onBlur();
				model.on(new EventImpl(window, Event.Type.WINDOW_DEACTIVATED, e));
			}
		});

		window.setMainWindow(this);
	}
}
