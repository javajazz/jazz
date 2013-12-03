package jazz;

import jazz.shapes.Pictures;

public abstract class World implements Model {

	protected Pictures picture = new Pictures();
	
	@Override
	public void update(double time) {
		
	}

	@Override
	public void on(Event e) {
		
	}

	@Override
	public Picture getPicture() {
		return picture;
	}
}
