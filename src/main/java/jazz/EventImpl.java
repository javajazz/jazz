package jazz;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import jazz.Key.Location;

class EventImpl implements Event {

  final private static Map<Integer, Key> keys = new HashMap<Integer, Key>();

  static {
    keys.put(KeyEvent.VK_0, Key._0);
    keys.put(KeyEvent.VK_1, Key._1);
    keys.put(KeyEvent.VK_2, Key._2);
    keys.put(KeyEvent.VK_3, Key._3);
    keys.put(KeyEvent.VK_4, Key._4);
    keys.put(KeyEvent.VK_5, Key._5);
    keys.put(KeyEvent.VK_6, Key._6);
    keys.put(KeyEvent.VK_7, Key._7);
    keys.put(KeyEvent.VK_8, Key._8);
    keys.put(KeyEvent.VK_9, Key._9);

    keys.put(KeyEvent.VK_A, Key._A);
    keys.put(KeyEvent.VK_B, Key._B);
    keys.put(KeyEvent.VK_C, Key._C);
    keys.put(KeyEvent.VK_D, Key._D);
    keys.put(KeyEvent.VK_E, Key._E);
    keys.put(KeyEvent.VK_F, Key._F);
    keys.put(KeyEvent.VK_G, Key._G);
    keys.put(KeyEvent.VK_H, Key._H);
    keys.put(KeyEvent.VK_I, Key._I);
    keys.put(KeyEvent.VK_J, Key._J);
    keys.put(KeyEvent.VK_K, Key._K);
    keys.put(KeyEvent.VK_L, Key._L);
    keys.put(KeyEvent.VK_M, Key._M);
    keys.put(KeyEvent.VK_N, Key._N);
    keys.put(KeyEvent.VK_O, Key._O);
    keys.put(KeyEvent.VK_P, Key._P);
    keys.put(KeyEvent.VK_Q, Key._Q);
    keys.put(KeyEvent.VK_R, Key._R);
    keys.put(KeyEvent.VK_S, Key._S);
    keys.put(KeyEvent.VK_T, Key._T);
    keys.put(KeyEvent.VK_U, Key._U);
    keys.put(KeyEvent.VK_V, Key._V);
    keys.put(KeyEvent.VK_W, Key._W);
    keys.put(KeyEvent.VK_X, Key._X);
    keys.put(KeyEvent.VK_Y, Key._Y);
    keys.put(KeyEvent.VK_Z, Key._Z);

    keys.put(KeyEvent.VK_BACK_SPACE, Key.BACKSPACE);
    keys.put(KeyEvent.VK_ESCAPE, Key.ESCAPE);
    keys.put(KeyEvent.VK_SPACE, Key.SPACE);
    keys.put(KeyEvent.VK_ENTER, Key.ENTER);
    keys.put(KeyEvent.VK_DELETE, Key.DELETE);
    keys.put(KeyEvent.VK_TAB, Key.TAB);

    keys.put(KeyEvent.VK_UP, Key.UP);
    keys.put(KeyEvent.VK_DOWN, Key.DOWN);
    keys.put(KeyEvent.VK_LEFT, Key.LEFT);
    keys.put(KeyEvent.VK_RIGHT, Key.RIGHT);

    keys.put(KeyEvent.VK_PAGE_DOWN, Key.PAGE_DOWN);
    keys.put(KeyEvent.VK_PAGE_UP, Key.PAGE_UP);
    keys.put(KeyEvent.VK_HOME, Key.HOME);
    keys.put(KeyEvent.VK_END, Key.END);

    keys.put(KeyEvent.VK_F1, Key.F1);
    keys.put(KeyEvent.VK_F2, Key.F2);
    keys.put(KeyEvent.VK_F3, Key.F3);
    keys.put(KeyEvent.VK_F4, Key.F4);
    keys.put(KeyEvent.VK_F5, Key.F5);
    keys.put(KeyEvent.VK_F6, Key.F6);
    keys.put(KeyEvent.VK_F7, Key.F7);
    keys.put(KeyEvent.VK_F8, Key.F8);
    keys.put(KeyEvent.VK_F9, Key.F9);
    keys.put(KeyEvent.VK_F10, Key.F10);
    keys.put(KeyEvent.VK_F11, Key.F11);
    keys.put(KeyEvent.VK_F12, Key.F12);
    keys.put(KeyEvent.VK_F13, Key.F13);
    keys.put(KeyEvent.VK_F14, Key.F14);
    keys.put(KeyEvent.VK_F15, Key.F15);
    keys.put(KeyEvent.VK_F16, Key.F16);
    keys.put(KeyEvent.VK_F17, Key.F17);
    keys.put(KeyEvent.VK_F18, Key.F18);
    keys.put(KeyEvent.VK_F19, Key.F19);
    keys.put(KeyEvent.VK_F20, Key.F20);
    keys.put(KeyEvent.VK_F21, Key.F21);
    keys.put(KeyEvent.VK_F22, Key.F22);
    keys.put(KeyEvent.VK_F23, Key.F23);
    keys.put(KeyEvent.VK_F24, Key.F24);
  }

  final private WindowImpl window;
  final private Event.Type type;

  final private int x;
  final private int y;
  final private int wx;
  final private int wy;
  final private int keyCode;
  final private char keyChar;
  final private double wheelRotation;
  final private Location keyLocation;

  private boolean isShiftPressed = false;
  private boolean isAltPressed = false;
  private boolean isCtrlPressed = false;
  private boolean isMetaPressed = false;

  private EventImpl(WindowImpl window, Event.Type type, int x, int y,
      int keyCode, char keyChar, Key.Location keyLoc, double z) {
    this.window = window;
    this.type = type;

    this.x = x - window.width() / 2 + window.originX();
    this.y = -(y - window.height() / 2 + window.originY());
    this.wheelRotation = z;
    this.wx = x;
    this.wy = getWindow().height() - y;
    this.keyCode = keyCode;
    this.keyChar = keyChar;
    this.keyLocation = keyLoc;
  }

  private EventImpl(WindowImpl window, Event.Type type, InputEvent e,
      int x, int y, int keyCode, char keyChar, Key.Location keyLoc,
      double z) {
    this(window, type, x, y, keyCode, keyChar, keyLoc, z);

    isAltPressed = e.isAltDown();
    isShiftPressed = e.isShiftDown();
    isCtrlPressed = e.isControlDown();
    isMetaPressed = e.isMetaDown();
  }

  EventImpl(WindowImpl window, Event.Type type, KeyEvent e, int x, int y) {
    this(window, type, (InputEvent) e, x, y, e.getKeyCode(),
        e.getKeyChar(), getLocation(e), 0.0);
  }

  EventImpl(WindowImpl window, Event.Type type, MouseEvent e) {
    this(window, type, (InputEvent) e, e.getX(), e.getY(),
        e.getButton(), '\0', Key.Location.UNKNOWN, 0.0);
  }

  EventImpl(WindowImpl window, Event.Type type, WindowEvent e) {
    this(window, type, 0, 0, 0, '\0', Key.Location.UNKNOWN, 0.0);
  }

  EventImpl(WindowImpl window, Event.Type type, MouseWheelEvent e) {
    this(window, type, (InputEvent) e, e.getX(), e.getY(), 0, '\0',
        Key.Location.UNKNOWN, e.getPreciseWheelRotation());
  }

  private static Key.Location getLocation(KeyEvent e) {
    int loc = e.getKeyLocation();
    if ((loc & KeyEvent.KEY_LOCATION_LEFT) != 0) {
      return Key.Location.LEFT;
    } else if ((loc & KeyEvent.KEY_LOCATION_RIGHT) != 0) {
      return Key.Location.RIGHT;
    } else if ((loc & KeyEvent.KEY_LOCATION_NUMPAD) != 0) {
      return Key.Location.NUMPAD;
    } else if ((loc & KeyEvent.KEY_LOCATION_STANDARD) != 0) {
      return Key.Location.STANDARD;
    } else {
      return Key.Location.UNKNOWN;
    }
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
  
  public Vector getPosition() {
	  return new Vector(x, y);
  }

  @Override
  public int getWindowX() {
    return wx;
  }

  @Override
  public int getWindowY() {
    return wy;
  }

  @Override
  public int getKeyCode() {
    return keyCode;
  }

  public Key getKey() {
    Key key = keys.get(keyCode);
    if (key == null) {
      switch (keyCode) {
      case KeyEvent.VK_SHIFT:
        switch (keyLocation) {
        case RIGHT:
          return Key.RSHIFT;
        default:
          return Key.LSHIFT;
        }
      case KeyEvent.VK_ALT:
        switch (keyLocation) {
        case RIGHT:
          return Key.RALT;
        default:
          return Key.LALT;
        }
      case KeyEvent.VK_CONTROL:
        switch (keyLocation) {
        case RIGHT:
          return Key.RCTRL;
        default:
          return Key.LCTRL;
        }
      case KeyEvent.VK_META:
        switch (keyLocation) {
        case RIGHT:
          return Key.RSUPER;
        default:
          return Key.LSUPER;
        }
      }
      return Key.UNKNOWN;
    }
    return key;
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
    case 4:
      return MouseButton.FOURTH;
    case 5:
      return MouseButton.FIFTH;
    case 6:
      return MouseButton.SIXTH;
    }
    return MouseButton.NONE;
  }

  @Override
  public char getChar() {
    return keyChar;
  }

  @Override
  public double getWheelRotation() {
    return wheelRotation;
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

  @Override
  public Location getKeyLocation() {
    return keyLocation;
  }

  public String toString() {
    return String.format("%s (x=%d,y=%d,%s,#%d,'%s')",
        type, x, y, getButton(), keyCode, keyChar);
  }
}
