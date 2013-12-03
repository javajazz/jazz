package jazz;

import jazz.shapes.Pictures;

public abstract class World implements Model {

	protected Pictures pictures = new Pictures();
	
	@Override
	public void update(double time) {
		
	}

	@Override
	public void on(Event e) {
		
	}

	@Override
	public Picture getPicture() {
		return pictures;
	}
}
