package jazz;

public interface Event {

	static enum MouseButton {
		LEFT, MIDDLE, RIGHT, NONE
	}

	enum Type {
		MOUSE_DOWN, MOUSE_UP, CLICK, MOUSE_MOVE,
		KEY_DOWN, KEY_UP, KEY_TYPED,
		WINDOW_CLOSED,
		WINDOW_HIDDEN, WINDOW_SHOWN,
		WINDOW_ACTIVATED, WINDOW_DEACTIVATED
	}

	Type getType();

	int getX();

	int getY();

	MouseButton getButton();

	int getKeyCode();

	char getChar();

	boolean isShiftPressed();

	boolean isAltPressed();

	boolean isCtrlPressed();

	boolean isSuperPressed();

	Window getWindow();

	int getWindowY();

	int getWindowX();
}
