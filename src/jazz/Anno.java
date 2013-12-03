package jazz;

import jazz.shapes.Circle;
import jazz.shapes.Ellipse;
import jazz.shapes.Pictures;
import jazz.shapes.Rectangle;

public class Anno {

	static class MyModel implements Model {

		private final Pictures pictures = new Pictures();

		Pictures human = new Pictures();

		public MyModel() {
			human.color(Color.RED);
			
			pictures.add(new Ellipse(200, 100)
					.color(0, 0, 255));
			pictures.add(new Circle(50)
					.filled(true)
					.translate(150, 0)
					.rotate(0));
			pictures.add(new Rectangle(100, 100)
					.color(0, 255, 0)
					.filled(true)
					.rotate(45)
					.translate(0, 0));
			pictures.add(new Rectangle(200, 300)
					.color(255, 0, 0)
					.rotate(20));
			pictures.add(new Rectangle(200, 300)
					.rotate(20)
					.scale(-1, 1));

			human.add(new Circle(25)
					.filled(true)
					.translate(0, 100));
			human.add(new Rectangle(50, 160)
					.filled(true)
					.translate(0, -40));
			human.add(new Rectangle(5, 110)
					.filled(true)
					.rotate(35)
					.translate(50, 30));
			human.add(new Rectangle(5, 110)
					.filled(true)
					.rotate(-35)
					.translate(-50, 30));
			human.add(new Rectangle(5, 100)
					.filled(true)
					.rotate(30)
					.translate(50, -150));
			human.add(new Rectangle(5, 100)
					.filled(true)
					.rotate(-30)
					.translate(-50, -150));

			pictures.add(human);
		}

		@Override
		public void update(double time) {
			
			int c = (int) Math.floor(Math.abs(Math.sin(time) * 255));
			pictures.get(0)
					.reset()
					.rotate(time * -100)
					.translate(200, 100)
					.scale(1.5, 1.5);
			pictures.get(1)
					.remove()
					.color(c, c, c)
					.rotate(time * 50);
			pictures.get(2)
					.remove()
					.translate(Math.sin(time) * 200, 0);
		}

		@Override
		public void on(Event e) {
			switch (e.getType()) {
			case MOUSE_MOVE:
				human.reset().translate(e.getX(), e.getY());
			default:
				break;
			}
		}

		@Override
		public Picture getPicture() {
			return pictures;
		}

	}

	public static void main(String... args) {
		Jazz.play("Hello World", new MyModel()).onClose(new Runnable() {
			public void run() {
				System.exit(0);
			}
		});
	}
}
