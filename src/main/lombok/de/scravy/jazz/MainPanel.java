package de.scravy.jazz;

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
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * INTERNAL
 *
 * @since 1.0.0
 */
class MainPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  private final Model model;
  private final ReentrantLock lock = new ReentrantLock();
  private final Thread timer;

  private int maxFramesPerSecond = 100;
  private long maxRefreshRate = 1000000000L / this.maxFramesPerSecond;
  private boolean doShowDebugOutput = false;
  private long currentRefreshRate = this.maxRefreshRate;
  private double currentTime;
  private double lastUpdate;

  private volatile boolean antialias = true;
  private volatile boolean isPainting = false;
  private volatile long startUpdate;
  private volatile int mouseX = 0;
  private volatile int mouseY = 0;
  private volatile int offsetX = 0;
  private volatile int offsetY = 0;
  private volatile double acceleration = 1;
  private volatile double scale = 1;

  private volatile boolean paused = false;

  MainPanel(final MainWindow mainWindow, final Model theModel,
      final DefaultWorld window, final int a, final int b) {

    this.model = theModel;

    setPreferredSize(new Dimension(a, b));
    setSize(a, b);

    setDoubleBuffered(true);

    this.timer = new Thread(new Runnable() {
      @Override
      public void run() {
        MainPanel.this.lastUpdate = System.nanoTime();
        for (; !Thread.interrupted();) {
          try {
            MainPanel.this.lock.lockInterruptibly();
            MainPanel.this.lock.unlock();

            if (!MainPanel.this.isPainting) {
              MainPanel.this.isPainting = true;
              MainPanel.this.startUpdate = System.nanoTime();
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
            Thread.sleep(MainPanel.this.currentRefreshRate / 1000000);
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
        MainPanel.this.model.on(new DefaultEvent(window,
            Event.Type.MOUSE_WHEEL, e));
      }
    });

    addMouseMotionListener(new MouseMotionListener() {

      @Override
      public void mouseDragged(final MouseEvent e) {
        MainPanel.this.mouseX = e.getX();
        MainPanel.this.mouseY = e.getY();
        MainPanel.this.model.on(new DefaultEvent(window, Event.Type.MOUSE_MOVE,
            e));
      }

      @Override
      public void mouseMoved(final MouseEvent e) {
        MainPanel.this.mouseX = e.getX();
        MainPanel.this.mouseY = e.getY();
        MainPanel.this.model.on(new DefaultEvent(window, Event.Type.MOUSE_MOVE,
            e));
      }
    });

    addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(final MouseEvent e) {
        MainPanel.this.model.on(new DefaultEvent(window, Event.Type.CLICK, e));
      }

      @Override
      public void mousePressed(final MouseEvent e) {
        MainPanel.this.model.on(new DefaultEvent(window, Event.Type.MOUSE_DOWN,
            e));
      }

      @Override
      public void mouseReleased(final MouseEvent e) {
        MainPanel.this.model
            .on(new DefaultEvent(window, Event.Type.MOUSE_UP, e));
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
        MainPanel.this.model.on(new DefaultEvent(
            window, Event.Type.KEY_TYPED, e, MainPanel.this.mouseX,
            MainPanel.this.mouseY));
      }

      @Override
      public void keyPressed(final KeyEvent e) {
        MainPanel.this.model.on(new DefaultEvent(
            window, Event.Type.KEY_DOWN, e, MainPanel.this.mouseX,
            MainPanel.this.mouseY));
      }

      @Override
      public void keyReleased(final KeyEvent e) {
        MainPanel.this.model.on(new DefaultEvent(
            window, Event.Type.KEY_UP, e, MainPanel.this.mouseX,
            MainPanel.this.mouseY));
      }
    });
  }

  void updateModel() {
    final long currentUpdate = System.nanoTime();
    final double delta = (currentUpdate - this.lastUpdate) / 1000000000.0
        * this.acceleration;
    this.currentTime += delta;
    this.lastUpdate = currentUpdate;
    this.model.update(this.currentTime, delta);
  }

  void pause() {
    this.lock.lock();
    this.paused = true;
  }

  void resume() {
    this.lastUpdate = System.nanoTime();
    this.paused = false;
    this.lock.unlock();
  }

  boolean isPaused() {
    return this.paused;
  }

  void stop() {
    this.timer.interrupt();
  }

  @Override
  public void paintComponent(final Graphics g) {

    final Graphics2D g2d = (Graphics2D) g;

    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, this.antialias
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
    g2d.translate(getWidth() / 2 + this.offsetX, getHeight() / 2 - this.offsetY);
    g2d.scale(1 * this.scale, -1 * this.scale);
    this.model.getPicture().draw(g2d);

    if (this.doShowDebugOutput) {
      g2d.setTransform(new AffineTransform());
      g2d.setColor(Color.WHITE);
      g2d.drawString(
          Long.toString(
              Math.round(1000 / (this.currentRefreshRate / 1000000.0))),
          10,
          20);
    }

    final long delta = System.nanoTime() - this.startUpdate;
    this.currentRefreshRate = Math.max(this.maxRefreshRate, delta);

    this.isPainting = false;
  }

  int getOffsetX() {
    return this.offsetX;
  }

  void setOffsetX(final int offsetX) {
    this.offsetX = offsetX;
  }

  int getOffsetY() {
    return this.offsetY;
  }

  void setOffsetY(final int offsetY) {
    this.offsetY = offsetY;
  }

  boolean isAntialias() {
    return this.antialias;
  }

  void setAntialias(final boolean antialias) {
    this.antialias = antialias;
  }

  int getMaxFramesPerSecond() {
    return this.maxFramesPerSecond;
  }

  void setMaxFramesPerSecond(final int maxFramesPerSecond) {
    this.maxFramesPerSecond = maxFramesPerSecond;
    this.maxRefreshRate = 1000000000L / maxFramesPerSecond;
  }

  double getAcceleration() {
    return this.acceleration;
  }

  void setAcceleration(final double acceleration) {
    this.acceleration = acceleration;
  }

  double getScale() {
    return this.scale;
  }

  void setScale(final double scale) {
    this.scale = scale;
  }

  boolean isDoShowDebugOutput() {
    return this.doShowDebugOutput;
  }

  void setDoShowDebugOutput(final boolean doShowDebugOutput) {
    this.doShowDebugOutput = doShowDebugOutput;
  }
}
