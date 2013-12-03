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

	WindowImpl() {
		
	}

	void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	MainWindow getMainWindow() {
		return mainWindow;
	}

	@Override
	public WindowImpl close() {
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
	public WindowImpl onClose(Runnable runnable) {
		onClose.add(runnable);
		return this;
	}

	@Override
	public WindowImpl onShow(Runnable runnable) {
		onShow.add(runnable);
		return this;
	}

	@Override
	public WindowImpl onHide(Runnable runnable) {
		onHide.add(runnable);
		return this;
	}

	@Override
	public WindowImpl onActivate(Runnable runnable) {
		onFocus.add(runnable);
		return this;
	}

	@Override
	public WindowImpl onDeactivate(Runnable runnable) {
		onBlur.add(runnable);
		return this;
	}

	@Override
	public WindowImpl title(final String title) {
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

	@Override
	public int height() {
		return mainWindow.mainPanel.getWidth();
	}

	@Override
	public int width() {
		return mainWindow.mainPanel.getHeight();
	}

	@Override
	public int originX() {
		return mainWindow.mainPanel.offsetX;
	}

	@Override
	public int originY() {
		return mainWindow.mainPanel.offsetX;
	}

	@Override
	public WindowImpl originX(int originX) {
		mainWindow.mainPanel.offsetX = originX;
		return this;
	}

	@Override
	public WindowImpl originY(int originY) {
		mainWindow.mainPanel.offsetY = originY;
		return this;
	}
}
