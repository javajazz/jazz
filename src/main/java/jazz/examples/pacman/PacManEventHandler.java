package jazz.examples.pacman;

import jazz.Direction;
import jazz.Event;
import jazz.EventHandler;

public class PacManEventHandler implements EventHandler<PacManWorld> {

	@Override
	public void on(PacManWorld w, Event e) {
		switch (e.getType()) {
		
		case KEY_DOWN:
			switch (e.getKey()) {
			case UP:
				w.setDirection(Direction.UP);
				break;
			case DOWN:
				w.setDirection(Direction.DOWN);
				break;
			case LEFT:
				w.setDirection(Direction.LEFT);
				break;
			case RIGHT:
				w.setDirection(Direction.RIGHT);
				break;
			default:
				break;
			}
			break;
			
		default:
			break;
		}

	}

}
