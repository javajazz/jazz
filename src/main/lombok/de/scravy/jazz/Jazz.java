package de.scravy.jazz;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.SwingUtilities;

import lombok.experimental.UtilityClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Jazz main class, used to create Jazz windows and adjust the global Jazz
 * configuration.
 *
 * @author Julian Fleischer
 * @since 1.0.0
 */
@UtilityClass
public class Jazz {

  private static final Logger LOGGER = LoggerFactory.getLogger(Jazz.class);

  /**
   * Jazz does have a global random generator which is seeded with a specific
   * seed. It is intended that a program always yields the same results.
   *
   * If you want to randomize the initial seed value, use {@link #seed()} in the
   * beginning of your program.
   *
   * All classes dealing with random values in Jazz should use one of the
   * methods generating random values provided by this class (i.e. use this
   * global random generator).
   */
  static private final Random random = new Random(4711337);

  /**
   * Always use OpenGL (this is a hint, i.e. if open gl is not available Java
   * will automatically fall back to something else).
   */
  static {
    System.setProperty("sun.java2d.opengl", "true");
    LOGGER.debug("System Property sun.java2d.opengl set to {}",
        System.getProperty("sun.java2d.opengl"));
  }

  /**
   * Sets the global random seed value.
   *
   * Jazz does have its own global random generator which is initially seeded
   * using a specific seed. Use this function to alter the seed.
   *
   * This method is thread-safe.
   *
   * @param seed
   *          The new seed value.
   *
   * @since 1.0.0
   */
  public static void seed(final long seed) {
    synchronized (random) {
      random.setSeed(seed);
      LOGGER.info("Random Seed set to {}", seed);
    }
  }

  /**
   * Sets the global random seed value randomly (i.e. using
   * {@link System#nanoTime()}).
   *
   * Jazz does have its own global random generator which is initially seeded
   * using a specific seed. Use this function to randomize the initial setup.
   *
   * This method is thread-safe.
   *
   * @since 1.0.0
   * @return The seed set so you have a chance to reproduce.
   */
  public static long seed() {
    final long time = System.nanoTime();
    seed(time);
    return time;
  }

  /**
   * Generates a random integer value using the global random generator.
   *
   * This method is thread-safe.
   *
   * @since 1.0.0
   * @return A random integer value (any integer value is possible).
   */
  public static int randomInt() {
    synchronized (random) {
      return random.nextInt();
    }
  }

  /**
   * Generates a random integer value between 0 (inclusive) and the specified
   * maximum value (exclusive). <code>randomInt(3)</code> will generate one of
   * zero, one, or two.
   *
   * This method will return zero if you pass it a value less than one.
   *
   * This method is thread-safe.
   *
   * @since 1.0.0
   * @param upto
   *          The maximum number (exclusive).
   * @return An integer value between 0 (inclusive) and <code>upto</code>
   *         (exclusive) or 0 if <code>upto</code> was less than 1.
   */
  public static int randomInt(final int upto) {
    if (upto >= 1) {
      synchronized (random) {
        return random.nextInt(upto);
      }
    } else {
      return 0;
    }
  }

  /**
   * Generates a random integer value between <code>from</code> (inclusive) and
   * <code>to</code> (exclusive).
   *
   * This method is thread-safe.
   *
   * @since 1.0.0
   * @param from
   *          The lower bound (inclusive)
   * @param upto
   *          The upper bound (exclusive)
   * @return A random integer value between <code>from</code> (inclusive) and
   *         <code>to</code> (exclusive).
   */
  public static int randomInt(final int from, final int upto) {
    return randomInt(upto - from) + from;
  }

  /**
   * Shuffles a list (in-place).
   *
   * The list is shuffled using the global random generator offered by Jazz.
   *
   * @since 1.0.0
   * @param list
   *          The list to shuffle. Note that the list needs to be modifiable
   *          since it is shuffled in-place.
   * @throws IllegalArgumentException
   *           If the given <code>list</code> was null.
   */
  public static void shuffle(final List<?> list) {
    if (list == null) {
      throw new IllegalArgumentException("list");
    }
    synchronized (list) {
      Collections.shuffle(list, random);
    }
  }

  /**
   * Shuffles an array (in-place).
   *
   * The array is shuffled using the global random generator offered by Jazz.
   *
   * @since 1.0.0
   * @param array
   *          The array to shuffle.
   */
  public static void shuffle(final Object[] array) {
    synchronized (array) {
      // asList uses the backing array as store,
      // therefore changes to the list are reflected by the array.
      Collections.shuffle(Arrays.asList(array), random);
    }
  }

  /**
   * Displays a static picture in a single window.
   *
   * You can open multiple windows using this method.
   *
   * This method used an {@link Animation} and allows for zooming and panning.
   *
   * @since 1.0.0
   * @param title
   *          The title of the displayed window.
   * @param width
   *          The width of the displayed window.
   * @param height
   *          The height of the displayed window.
   * @param picture
   *          The picture to be drawn in the displayed window.
   * @return A reference to the newly created window.
   */
  public static Window display(final String title, final int width,
      final int height,
      final Picture picture) {

    return play(title, width, height, new Animation() {

      @Override
      public Picture getPicture() {
        return picture;
      }

      @Override
      public void update(final double time, final double delta) {
        // do nothing, the picture is static.
      }
    });
  }

  /**
   * Displays a {@link Picture} in a fullscreen window.
   *
   * @since 1.0.0
   * @param title
   *          The title of the fullscreen window.
   * @param picture
   *          The picture do display in the fullscreen window.
   * @return The fullscreen window.
   */
  public static Window displayFullscreen(
      final String title, final Picture picture) {
    return display(title, 0, 0, picture);
  }

  /**
   * Displays an {@link Animation} in a single window.
   *
   * You can open multiple windows using this method.
   *
   * @since 1.0.0
   * @param title
   *          The title of the displayed window.
   * @param width
   *          The width of the displayed window.
   * @param height
   *          The height of the displayed window.
   * @param animation
   *          The animation to be shown in the displayed window.
   * @return A reference to the newly created window.
   */
  public static Window animate(final String title, final int width,
      final int height,
      final Animation animation) {
    return play(title, width, height, animation);
  }

  /**
   * Displays an animation in a single window.
   *
   * You can open multiple windows using this method.
   *
   * @see Renderer
   * @since 1.0.0
   * @param title
   *          The title of the displayed window.
   * @param width
   *          The width of the displayed window.
   * @param height
   *          The height of the displayed window.
   * @param model
   *          The model (a data object that describes your world).
   * @param renderer
   *          The renderer that derives a picture from the model.
   * @param updateHandler
   *          The update handler that updates the model.
   * @return A reference to the newly created window.
   */
  public static <M> Window animate(final String title, final int width,
      final int height,
      final M model, final Renderer<M> renderer,
      final UpdateHandler<M> updateHandler) {
    return animate(title, width, height, new Animation() {

      @Override
      public void update(final double time, final double delta) {
        updateHandler.update(model, time, delta);
      }

      @Override
      public Picture getPicture() {
        return renderer.render(model);
      }
    });
  }

  /**
   *
   * @since 1.0.0
   * @param title
   * @param animation
   * @return The fullscreen window.
   */
  public static Window animateFullscreen(
      final String title,
      final Animation animation) {
    return play(title, 0, 0, animation);
  }

  public static Window play(final String title, final int a, final int b,
      final World world) {
    return play(title, a, b, (Model) world);
  }

  public static <M> Window play(final String title, final int a, final int b,
      final M world, final Renderer<M> r, final UpdateHandler<M> u,
      final EventHandler<M> e) {
    return play(title, a, b, new DelegatingWorld<M>(world, r, u, e));
  }

  public static <M> Window playFullscreen(final String title, final int a,
      final int b,
      final M world, final Renderer<M> r, final UpdateHandler<M> u,
      final EventHandler<M> e) {
    return play(title, 0, 0, new DelegatingWorld<M>(world, r, u, e));
  }

  public static Window playFullscreen(final String title, final World world) {
    return play(title, 0, 0, (Model) world);
  }

  /**
   * INTERNAL:
   *
   * @param title
   *          The title of the window to be created.
   * @param width
   *          The width of the canvas (not the window).
   * @param height
   *          The height of the canvas (not the window).
   * @param model
   *          The model which is used for rendering (includes a renderer, update
   *          handler, and event handler).
   * @return A reference to the newly created window.
   */
  static DefaultWorld play(
      final String title, final int width, final int height, final Model model) {

    final DefaultWorld window = new DefaultWorld();

    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        @Override
        public void run() {
          LOGGER.info("Initializing window on Thread `{}'",
              Thread.currentThread().getName());
          final MainWindow mainWindow = new MainWindow(
              title, model, window, width, height);
          final GraphicsDevice device = GraphicsEnvironment
              .getLocalGraphicsEnvironment()
              .getDefaultScreenDevice();
          if (width <= 0 || height <= 0) {
            if (device.isFullScreenSupported()) {
              device.setFullScreenWindow(mainWindow);
            } else {
              LOGGER.info(
                  "Fullscreen is not supported."
                      + " Showing ordinary window instead.");
              mainWindow.setVisible(true);
            }
          } else {
            mainWindow.setVisible(true);
          }
        }
      });
    } catch (InvocationTargetException | InterruptedException exc) {
      LOGGER.warn("Unexpected exception", exc);
    }
    return window;
  };
}
