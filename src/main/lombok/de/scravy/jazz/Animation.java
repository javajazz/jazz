package de.scravy.jazz;


/**
 *
 * @since 1.0.0
 * @author Julian Fleischer
 */
public abstract class Animation implements Model {

  private int startDragX = 0;
  private int startDragY = 0;
  private int initialOffsetX = 0;
  private int initialOffsetY = 0;
  private boolean mouseDown = false;

  @Override
  final public void on(final Event e) {
    switch (e.getType()) {

    case MOUSE_DOWN: {
      this.initialOffsetX = e.getWindow().originX();
      this.initialOffsetY = e.getWindow().originY();
      this.startDragX = e.getWindowX();
      this.startDragY = e.getWindowY();
      this.mouseDown = true;
      break;
    }

    case MOUSE_UP: {
      this.mouseDown = false;
      final int deltaX = e.getWindowX() - this.startDragX;
      final int deltaY = e.getWindowY() - this.startDragY;
      e.getWindow().originX(this.initialOffsetX + deltaX);
      e.getWindow().originY(this.initialOffsetY + deltaY);
      break;
    }

    case MOUSE_MOVE: {
      if (this.mouseDown) {
        final int deltaX = e.getWindowX() - this.startDragX;
        final int deltaY = e.getWindowY() - this.startDragY;
        e.getWindow().originX(this.initialOffsetX + deltaX);
        e.getWindow().originY(this.initialOffsetY + deltaY);
      }
      break;
    }

    case MOUSE_WHEEL: {
      if (e.isCtrlPressed()) {
        e.getWindow().scale(
            Math.max(0.1,
                e.getWindow().scale() + e.getWheelRotation()
                    / 10.0));
      } else {
        e.getWindow().speed(
            Math.max(0.1,
                e.getWindow().speed() + e.getWheelRotation()
                    / 10.0));
      }
      break;
    }

    case KEY_TYPED: {
      switch (e.getChar()) {
      case '0':
        e.getWindow().originX(0).originY(0).scale(1).speed(1);
        break;
      }
      break;
    }

    default:
      break;
    }
  }
}
