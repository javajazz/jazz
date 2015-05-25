package de.scravy.jazz;

import jazz.Event;
import jazz.Picture;

public interface Model {

  void update(final double time, final double delta);

  void on(final Event e);

  Picture getPicture();
}
