package jazz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
class MainPanel extends JPanel {

    final Model model;
    final Random rand = new Random();
    final ReentrantLock lock = new ReentrantLock();
    final Thread timer;
    final DefaultWorld window;

    int maxFramesPerSecond = 100;
    long maxRefreshRate = 1000000000L / maxFramesPerSecond;
    boolean doShowDebugOutput = false;
    long currentRefreshRate = maxRefreshRate;
    double currentTime;
    double lastUpdate;
    double pausedTime;

    volatile boolean antialias = true;
    volatile boolean isPainting = false;
    volatile long startUpdate;
    volatile int mouseX = 0;
    volatile int mouseY = 0;
    volatile int offsetX = 0;
    volatile int offsetY = 0;
    volatile double acceleration = 1;
    volatile double scale = 1;

    private volatile boolean paused = false;

    MainPanel(final MainWindow mainWindow, final Model theModel,
            final DefaultWorld window, final int a, final int b) {
        this.model = theModel;
        this.window = window;

        setPreferredSize(new Dimension(a, b));
        setSize(a, b);

        setDoubleBuffered(true);

        this.timer = new Thread(new Runnable() {
            @Override
            public void run() {
                lastUpdate = System.nanoTime();
                for (; !Thread.interrupted();) {
                    try {
                        lock.lockInterruptibly();
                        lock.unlock();

                        if (!isPainting) {
                            isPainting = true;
                            startUpdate = System.nanoTime();
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        updateModel();
                                        repaint();
                                    } catch (final Exception exc) {
                                        exc.printStackTrace(System.err);
                                    }
                                }
                            });
                        } else {
                            //
                        }
                        Thread.sleep(currentRefreshRate / 1000000);
                    } catch (final InterruptedException e) {
                        break;
                    }
                }
            }
        });
        this.timer.start();

        addMouseWheelListener(new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(final MouseWheelEvent e) {
                model.on(new DefaultEvent(window, Event.Type.MOUSE_WHEEL, e));
            }
        });

        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(final MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                model.on(new DefaultEvent(window, Event.Type.MOUSE_MOVE, e));
            }

            @Override
            public void mouseMoved(final MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                model.on(new DefaultEvent(window, Event.Type.MOUSE_MOVE, e));
            }
        });

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(final MouseEvent e) {
                model.on(new DefaultEvent(window, Event.Type.CLICK, e));
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                model.on(new DefaultEvent(window, Event.Type.MOUSE_DOWN, e));
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
                model.on(new DefaultEvent(window, Event.Type.MOUSE_UP, e));
            }

            @Override
            public void mouseEntered(final MouseEvent e) {

            }

            @Override
            public void mouseExited(final MouseEvent e) {

            }
        });

        mainWindow.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(final KeyEvent e) {
                model.on(new DefaultEvent(window, Event.Type.KEY_TYPED, e,
                        mouseX,
                        mouseY));
            }

            @Override
            public void keyPressed(final KeyEvent e) {
                model.on(new DefaultEvent(window, Event.Type.KEY_DOWN, e,
                        mouseX,
                        mouseY));
            }

            @Override
            public void keyReleased(final KeyEvent e) {
                model.on(new DefaultEvent(window, Event.Type.KEY_UP, e, mouseX,
                        mouseY));
            }
        });
    }

    void updateModel() {
        final long currentUpdate = System.nanoTime();
        final double delta = (currentUpdate - lastUpdate) / 1000000000.0
                * acceleration;
        currentTime += delta;
        lastUpdate = currentUpdate;
        model.update(currentTime, delta);
    }

    void pause() {
        lock.lock();
        paused = true;
    }

    void resume() {
        lastUpdate = System.nanoTime();
        paused = false;
        lock.unlock();
    }

    boolean isPaused() {
        return paused;
    }

    void stop() {
        timer.interrupt();
    }

    @Override
    public void paintComponent(final Graphics g) {

        final Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antialias
                ? RenderingHints.VALUE_ANTIALIAS_ON
                : RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        super.paintComponent(g);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.BLACK);
        g2d.translate(getWidth() / 2 + offsetX, getHeight() / 2 - offsetY);
        g2d.scale(1 * scale, -1 * scale);
        model.getPicture().draw(g2d);

        if (doShowDebugOutput) {
            g2d.setTransform(new AffineTransform());
            g2d.setColor(Color.WHITE);
            g2d.drawString(
                    Long.toString(Math
                            .round(1000 / (currentRefreshRate / 1000000.0))),
                    10,
                    20);
        }

        final long delta = System.nanoTime() - startUpdate;
        currentRefreshRate = Math.max(maxRefreshRate, delta);

        isPainting = false;
    }
}
