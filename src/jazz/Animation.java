package jazz;

public abstract class Animation implements Model {

	private int startDragX = 0;
	private int startDragY = 0;
	private int initialOffsetX = 0;
	private int initialOffsetY = 0;
	private boolean mouseDown = false;

	@Override
	final public void on(Event e) {
		switch (e.getType()) {

		case MOUSE_DOWN: {
			initialOffsetX = e.getWindow().originX();
			initialOffsetY = e.getWindow().originY();
			startDragX = e.getWindowX();
			startDragY = e.getWindowY();
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
		
		case MOUSE_WHEEL: {
			
		}

		default:
			break;
		}
	}
}
