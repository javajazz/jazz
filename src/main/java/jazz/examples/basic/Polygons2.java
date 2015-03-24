package jazz.examples.basic;

import jazz.Animation;
import jazz.Jazz;
import jazz.Picture;
import jazz.pictures.mutable.Pictures;
import jazz.pictures.mutable.Polygon;

public class Polygons2 {

    public static void main(final String... args) {
        Jazz.animate(
                "Woohoo", 800, 600, new Animation() {

                    Pictures pictures = new Pictures();
                    {
                        for (int i = 3; i < 15; i++) {
                            pictures.add(new Polygon(i, (i - 2) * 25));
                        }
                    }

                    @Override
                    public void update(final double time, final double delta) {
                        double i = 30;
                        for (final Picture p : pictures) {
                            p.reset().rotate(time * i);
                            i *= -1;
                        }
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
