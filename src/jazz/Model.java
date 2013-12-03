package jazz;


public interface Model {

	void update(double time);

	void on(Event e);

	Picture getPicture();
}
