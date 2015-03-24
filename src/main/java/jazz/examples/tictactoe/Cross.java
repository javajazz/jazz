package jazz.examples.tictactoe;

import jazz.Vector;
import jazz.pictures.DelegatingPicture;
import jazz.pictures.mutable.Polygon;

public class Cross extends DelegatingPicture<Cross> {

    public Cross(double size) {
        super(new Polygon(
                new Vector(-0.5 * size, -0.25 * size),
                new Vector(-0.25 * size, -0.5 * size),
                new Vector(0, -0.25 * size),
                new Vector(0.25 * size, -0.5 * size),
                new Vector(0.5 * size, -0.25 * size),

                new Vector(0.25 * size, 0),

                new Vector(0.5 * size, 0.25 * size),
                new Vector(0.25 * size, 0.5 * size),
                new Vector(0, 0.25 * size),
                new Vector(-0.25 * size, 0.5 * size),
                new Vector(-0.5 * size, 0.25 * size),

                new Vector(-0.25 * size, 0)));
    }
}