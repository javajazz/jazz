package jazz.util;

import jazz.Event;

public interface TileEventHandler<T> {

	void on(Event ev, T tile);
	
}
