package jazz.examples.basic;

import jazz.Animation;
import jazz.Jazz;
import jazz.Picture;
import jazz.Vector;
import jazz.pictures.mutable.Circle;
import jazz.pictures.mutable.Pictures;
import jazz.pictures.mutable.Polygon;

public class Polygons {

  public static void main(String... args) {
    Jazz.animate(
        "Woohoo", 640, 480, new Animation() {

          Pictures pictures = new Pictures(
              new Circle(25).filled(true),
              new Polygon(
                  new Vector(-50, 100),
                  new Vector(-100, -50),
                  new Vector(-200, 0)).filled(true)
                  .color(255,
                      0, 255, 0.35).translate(100, -25)
                  .rotate(0),
              new Polygon(
                  new Vector(200, 200),
                  new Vector(100, 100),
                  new Vector(200, 0),
                  new Vector(0, 0),
                  new Vector(0, 200)).filled(true)
                  .color(255, 0,
                      0, 0.5).translate(-100, -100)
                  .rotate(0));

          @Override
          public void update(double time, double delta) {
            pictures.get(1).remove().rotate(time * 45);
            pictures.get(2).remove().rotate(time * -90);
          }

          @Override
          public Picture getPicture() {
            return pictures;
          }
        }).onClose(new Runnable() {
      @Override
      public void run() {
        System.exit(0);
      }
    }).maxFps(120);
  }

}
