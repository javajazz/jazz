package jazz.examples.basic;

import de.scravy.jazz.Color;
import jazz.Animation;
import jazz.Jazz;
import jazz.Picture;
import jazz.pictures.mutable.Pictures;
import jazz.pictures.mutable.Text;

public class Texts {

  public static void main(String... args) {
    Jazz.animate("", 800, 600, new Animation() {

      Pictures p = new Pictures(new Text("Hello, World!")
          .color(Color.DARK_GREEN)
          .scale(8, 8)
          .rotate(0));

      @Override
      public void update(double time, double delta) {
        p.get(0).remove().rotate(20 * time);
      }

      @Override
      public Picture getPicture() {
        return p;
      }
    });
  }
}
