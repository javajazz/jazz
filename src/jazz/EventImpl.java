package jazz;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

class EventImpl implements Event {

	final private WindowImpl window;
	final private Event.Type type;
	
	final private int x;
	final private int y;
	final private int keyCode;
	final private char keyChar;
	
	private boolean isShiftPressed = false;
	private boolean isAltPressed = false;
	private boolean isCtrlPressed = false;
	private boolean isMetaPressed = false;

	private EventImpl(WindowImpl window, Event.Type type, int x, int y, int keyCode, char keyChar) {
		this.window = window;
		this.type = type;

		this.x = x - window.getMainWindow().mainPanel.getWidth() / 2;
		this.y = -(y - window.getMainWindow().mainPanel.getHeight() / 2);
		this.keyCode = keyCode;
		this.keyChar = keyChar;
	}
	
	private EventImpl(WindowImpl window, Event.Type type, InputEvent e, int x, int y, int keyCode,
			char keyChar) {
		this(window, type, x, y, keyCode, keyChar);
		
		isAltPressed = e.isAltDown();
		isShiftPressed = e.isShiftDown();
		isCtrlPressed = e.isControlDown();
		isMetaPressed = e.isMetaDown();
	}

	EventImpl(WindowImpl window, Event.Type type, KeyEvent e, int x, int y) {
		this(window, type, (InputEvent) e, x, y, e.getKeyCode(), e.getKeyChar());
	}

	EventImpl(WindowImpl window, Event.Type type, MouseEvent e) {
		this(window, type, (InputEvent) e, e.getX(), e.getY(), e.getButton(), '\0');
	}

	EventImpl(WindowImpl window, jazz.Event.Type type, WindowEvent e) {
		this(window, type, 0, 0, 0, '\0');
	}

	@Override
	public Event.Type getType() {
		return type;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getKeyCode() {
		return keyCode;
	}

	@Override
	public MouseButton getButton() {
		switch (keyCode) {
		case MouseEvent.BUTTON1:
			return MouseButton.LEFT;
		case MouseEvent.BUTTON2:
			return MouseButton.MIDDLE;
		case MouseEvent.BUTTON3:
			return MouseButton.RIGHT;
		}
		return MouseButton.NONE;
	}

	@Override
	public char getChar() {
		return keyChar;
	}

	@Override
	public boolean isShiftPressed() {
		return isShiftPressed;
	}

	@Override
	public boolean isAltPressed() {
		return isAltPressed;
	}

	@Override
	public boolean isCtrlPressed() {
		return isCtrlPressed;
	}

	@Override
	public boolean isSuperPressed() {
		return isMetaPressed;
	}

	@Override
	public WindowImpl getWindow() {
		return window;
	}
	
	public String toString() {
		return String.format("%s (x=%d,y=%d,%s,#%d,'%s')",
				type, x, y, getButton(), keyCode, keyChar);
	}
}
