package de.scravy.jazz;

/**
 * A Jazz Window - a drawing canvas with a window border, or a fullscreen
 * canvas.
 *
 * The canvas in a Jazz window can be accessed via a coordinate system just like
 * in mathematics.
 *
 * <pre>
 *                (+)
 *               y ^
 *                 |
 *                 |
 *                 |
 * (-) ------------+------------&gt; x (+)
 *                 |
 *                 |
 *                 |
 *                (-)
 * </pre>
 *
 * @since 1.0.0
 * @author Julian Fleischer
 */
public interface Window {

  /**
   * @return This window.
   */
  Window close();

  /**
   * @param title
   * @return This window.
   */
  Window title(final String title);

  /**
   * @param runnable
   * @return This window.
   */
  Window onClose(final Runnable runnable);

  /**
   * @param runnable
   * @return This window.
   */
  Window onShow(final Runnable runnable);

  /**
   * @param runnable
   * @return This window.
   */
  Window onHide(final Runnable runnable);

  /**
   * @param runnable
   * @return This window.
   */
  Window onActivate(final Runnable runnable);

  /**
   * @param runnable
   * @return This window.
   */
  Window onDeactivate(final Runnable runnable);

  /**
   * @return The width of this window.
   */
  int width();

  /**
   * @return The height of this window.
   */
  int height();

  /**
   * @return The horizontal position on the screen.
   */
  int originX();

  /**
   * @return The vertical position on the screen.
   */
  int originY();

  /**
   * @param originX
   * @return This window.
   */
  Window originX(final int originX);

  /**
   * @param originY
   * @return This window.
   */
  Window originY(final int originY);

  /**
   * @param antialias
   * @return This window.
   */
  Window antiAlias(final boolean antialias);

  /**
   * @param maxFps
   * @return This window.
   */
  Window maxFps(final int maxFps);

  /**
   * @param doShowDebugOutput
   * @return This window.
   */
  Window debugOutput(final boolean doShowDebugOutput);

  /**
   * @param acceleration
   * @return This window.
   */
  Window speed(final double acceleration);

  /**
   * @return The speed currently set.
   */
  double speed();

  /**
   * @return This window.
   */
  Window pause();

  /**
   * @return This window.
   */
  Window resume();

  /**
   * @return This window.
   */
  Window pauseOrUnpause();

  /**
   * @return The scale currently set.
   */
  double scale();

  /**
   * @param scale
   * @return This window.
   */
  Window scale(final double scale);
}
