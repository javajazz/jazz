Jazz
====

Jazz is a tiny drawing library for Java 7+. It is based on Java 2D and tries to offer a
simplistic API. Have a look at the [Examples](/src/main/java/jazz/examples).

It is primarily aimed at beginner Java programmers.

Concepts
========

Jazz offers various levels of control:

- Pictures (static drawings)
- Animations
- Worlds (animations with event handling)

Pictures are drawings that do not change over time. Jazz automatically
aids in support for zooming and moving the canvas.

Animations are pictures which change over time. Jazz automatically
aids in support for increasing and decreasing the animation speed.

Worlds are suitable for implementing simple games. They are capable
of handling input events of any kind.

Here is how you fire up a simple Jazz window that displays a circle
with a radius of 20 pixel:

    public static void main(String... args) {
        Jazz.display("Hello Circle", 200, 200, new Circle(20.0));
    }

Jazz uses a coordinate system like you use in mathematics, i.e.
`(0,0)` is in the center of the window and the `x` and `y` axis
grow towards the top and to the left:

                   (+)
                  y ^
                    |
                    |
                    |
    (-) ------------+------------> x (+)
                    |
                    |
                    |
                   (-)

    
That is, the above code will display a circle with a diameter of
40 pixels in the center of the window. If you want to move an object,
you can apply a translation:

    new Circle(20).translate(100, 100);

This will display the same circle in the upper right of the window.

An Animation can be created by extending the `Animation` class:

    Jazz.animate("Moving circle", 600, 400, new Animation() {
        double posX = 0;

        public void update(double time, double delta) {
            posX = Math.sin(time) * 100;
        }

        public Picture getPicture() {
            return new Circle(20).translate(posX, 0);
        }
    });

This will display a circle oscillating between `(-100,0)` and `(100,0)`.


Javadoc
=======

To build the javadoc, download or clone the repository and run

    mvn javadoc

in the Jazz project directory. 


Roadmap
=======

+ More examples
+ Full Javadoc documentation
+ Upload to Maven Central


This document: 2014-01-13

