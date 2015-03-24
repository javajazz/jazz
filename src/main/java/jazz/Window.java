package jazz;

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
 * (-) ------------+------------> x (+)
 *                 |
 *                 |
 *                 |
 *                (-)
 * </pre>
 *
 * @author Julian Fleischer
 */
public interface Window {

    /**
     * @return
     */
    Window close();

    /**
     * @param title
     * @return
     */
    Window title(final String title);

    /**
     * @param runnable
     * @return
     */
    Window onClose(final Runnable runnable);

    /**
     * @param runnable
     * @return
     */
    Window onShow(final Runnable runnable);

    /**
     * @param runnable
     * @return
     */
    Window onHide(final Runnable runnable);

    /**
     * @param runnable
     * @return
     */
    Window onActivate(final Runnable runnable);

    /**
     * @param runnable
     * @return
     */
    Window onDeactivate(final Runnable runnable);

    /**
     * @return
     */
    int width();

    /**
     * @return
     */
    int height();

    /**
     * @return
     */
    int originX();

    /**
     * @return
     */
    int originY();

    /**
     * @param originX
     * @return this.
     */
    Window originX(final int originX);

    /**
     * @param originY
     * @return this.
     */
    Window originY(final int originY);

    /**
     * @param antialias
     * @return this.
     */
    Window antiAlias(final boolean antialias);

    /**
     * @param maxFps
     * @return this.
     */
    Window maxFps(final int maxFps);

    /**
     * @param doShowDebugOutput
     * @return this.
     */
    Window debugOutput(final boolean doShowDebugOutput);

    /**
     * @param acceleration
     * @return this.
     */
    Window speed(final double acceleration);

    /**
     * @return
     */
    double speed();

    /**
     * @return this.
     */
    Window pause();

    /**
     * @return this.
     */
    Window resume();

    /**
     * @return this.
     */
    Window pauseOrUnpause();

    /**
     * @return
     */
    double scale();

    /**
     * @param scale
     * @return this.
     */
    Window scale(final double scale);
}
