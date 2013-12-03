package jazz.example;

import jazz.Picture;
import jazz.World;
import jazz.shapes.Pictures;
import jazz.shapes.Rectangle;

public class TombWorld extends World {

	Picture<?> pictures = new Pictures();
	
	@Override
	public Picture<?> getPicture() {
		
		Picture<?> r = new Rectangle(50, 50);

		pictures.add(r);
		
		return r;
	}

}
