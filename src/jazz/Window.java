package jazz;

public interface Window {

	Window close();

	Window title(String title);
	
	Window onClose(Runnable runnable);

	Window onShow(Runnable runnable);

	Window onHide(Runnable runnable);

	Window onActivate(Runnable runnable);

	Window onDeactivate(Runnable runnable);
}
