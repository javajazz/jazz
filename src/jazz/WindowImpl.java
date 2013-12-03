package jazz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

class WindowImpl implements Window {

	private MainWindow mainWindow = null;
	private List<Runnable> onClose = new ArrayList<>();
	private List<Runnable> onHide = new ArrayList<>();
	private List<Runnable> onShow = new ArrayList<>();
	private List<Runnable> onFocus = new ArrayList<>();
	private List<Runnable> onBlur = new ArrayList<>();

	void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	MainWindow getMainWindow() {
		return mainWindow;
	}

	@Override
	public Window close() {
		if (mainWindow != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					mainWindow.dispose();
				}
			});
		}
		return this;
	}

	@Override
	public Window onClose(Runnable runnable) {
		onClose.add(runnable);
		return this;
	}

	@Override
	public Window onShow(Runnable runnable) {
		onShow.add(runnable);
		return this;
	}

	@Override
	public Window onHide(Runnable runnable) {
		onHide.add(runnable);
		return this;
	}

	@Override
	public Window onActivate(Runnable runnable) {
		onFocus.add(runnable);
		return this;
	}

	@Override
	public Window onDeactivate(Runnable runnable) {
		onBlur.add(runnable);
		return this;
	}

	@Override
	public Window title(final String title) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mainWindow.setTitle(title);
			}
		});
		return this;
	}

	void onClose() {
		for (Runnable runnable : onClose) {
			runnable.run();
		}
	}

	public void onBlur() {
		for (Runnable runnable : onBlur) {
			runnable.run();
		}
	}

	public void onFocus() {
		for (Runnable runnable : onFocus) {
			runnable.run();
		}
	}

	public void onShow() {
		for (Runnable runnable : onShow) {
			runnable.run();
		}
	}

	public void onHide() {
		for (Runnable runnable : onHide) {
			runnable.run();
		}
	}

}
