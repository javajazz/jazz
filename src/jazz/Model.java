package jazz;


public interface Model {

	void update(double time, double delta);

	void on(Event e);

	Picture getPicture();
}
