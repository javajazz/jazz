package jazz;

public interface Window {

	Window close();

	Window title(String title);
	
	Window onClose(Runnable runnable);

	Window onShow(Runnable runnable);

	Window onHide(Runnable runnable);

	Window onActivate(Runnable runnable);

	Window onDeactivate(Runnable runnable);

	int width();

	int height();
	
	int originX();
	
	int originY();
	
	Window originX(int originX);
	
	Window originY(int originY);
	
	Window antiAlias(boolean antialias);
	
	Window maxFps(int maxFps);
	
	Window speed(double acceleration);

	double speed();
	
	Window pause();
	
	Window resume();

	Window pauseOrUnpause();

	double scale();

	WindowImpl scale(double scale);
}
