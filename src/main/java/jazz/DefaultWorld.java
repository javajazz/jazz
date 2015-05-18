package jazz;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

class DefaultWorld implements Window {

  private MainWindow mainWindow = null;

  private final List<Runnable> onClose = new ArrayList<>();
  private final List<Runnable> onHide = new ArrayList<>();
  private final List<Runnable> onShow = new ArrayList<>();
  private final List<Runnable> onFocus = new ArrayList<>();
  private final List<Runnable> onBlur = new ArrayList<>();

  DefaultWorld() {

  }

  void setMainWindow(final MainWindow mainWindow) {
    this.mainWindow = mainWindow;
  }

  MainWindow getMainWindow() {
    return mainWindow;
  }

  @Override
  public DefaultWorld close() {
    if (mainWindow != null) {
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          mainWindow.dispose();
        }
      });
    }
    return this;
  }

  @Override
  public DefaultWorld onClose(final Runnable runnable) {
    onClose.add(runnable);
    return this;
  }

  @Override
  public DefaultWorld onShow(final Runnable runnable) {
    onShow.add(runnable);
    return this;
  }

  @Override
  public DefaultWorld onHide(final Runnable runnable) {
    onHide.add(runnable);
    return this;
  }

  @Override
  public DefaultWorld onActivate(final Runnable runnable) {
    onFocus.add(runnable);
    return this;
  }

  @Override
  public DefaultWorld onDeactivate(final Runnable runnable) {
    onBlur.add(runnable);
    return this;
  }

  @Override
  public DefaultWorld title(final String title) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        mainWindow.setTitle(title);
      }
    });
    return this;
  }

  void onClose() {
    for (final Runnable runnable : onClose) {
      runnable.run();
    }
  }

  public void onBlur() {
    for (final Runnable runnable : onBlur) {
      runnable.run();
    }
  }

  public void onFocus() {
    for (final Runnable runnable : onFocus) {
      runnable.run();
    }
  }

  public void onShow() {
    for (final Runnable runnable : onShow) {
      runnable.run();
    }
  }

  public void onHide() {
    for (final Runnable runnable : onHide) {
      runnable.run();
    }
  }

  @Override
  public int height() {
    if (mainWindow != null) {
      return mainWindow.getMainPanel().getHeight();
    }
    return 0;
  }

  @Override
  public int width() {
    if (mainWindow != null) {
      return mainWindow.getMainPanel().getWidth();
    }
    return 0;
  }

  @Override
  public int originX() {
    if (mainWindow != null) {
      return mainWindow.getMainPanel().getOffsetX();
    }
    return 0;
  }

  @Override
  public int originY() {
    if (mainWindow != null) {
      return mainWindow.getMainPanel().getOffsetY();
    }
    return 0;
  }

  @Override
  public DefaultWorld originX(final int originX) {
    if (mainWindow != null) {
      mainWindow.getMainPanel().setOffsetX(originX);
    }
    return this;
  }

  @Override
  public DefaultWorld originY(final int originY) {
    if (mainWindow != null) {
      mainWindow.getMainPanel().setOffsetY(originY);
    }
    return this;
  }

  @Override
  public DefaultWorld antiAlias(final boolean antialias) {
    if (mainWindow != null) {
      mainWindow.getMainPanel().setAntialias(antialias);
    }
    return this;
  }

  @Override
  public DefaultWorld maxFps(final int maxFps) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        if (mainWindow != null) {
          mainWindow.getMainPanel().setMaxFramesPerSecond(maxFps);
        }
      }
    });
    return this;
  }

  @Override
  public DefaultWorld speed(final double acceleration) {
    if (mainWindow != null) {
      mainWindow.getMainPanel().setAcceleration(acceleration);
    }
    return this;
  }

  @Override
  public double speed() {
    if (mainWindow != null) {
      return mainWindow.getMainPanel().getAcceleration();
    }
    return 0;
  }

  @Override
  public DefaultWorld scale(final double scale) {
    if (mainWindow != null) {
      mainWindow.getMainPanel().setScale(scale);
    }
    return this;
  }

  @Override
  public double scale() {
    if (mainWindow != null) {
      return mainWindow.getMainPanel().getScale();
    }
    return 1.0;
  }

  @Override
  public DefaultWorld pauseOrUnpause() {
    if (mainWindow != null) {
      if (mainWindow.getMainPanel().isPaused()) {
        mainWindow.getMainPanel().resume();
      } else {
        mainWindow.getMainPanel().pause();
      }
    }
    return this;
  }

  @Override
  public DefaultWorld pause() {
    if (mainWindow != null) {
      mainWindow.getMainPanel().pause();
    }
    return this;
  }

  @Override
  public DefaultWorld resume() {
    if (mainWindow != null) {
      mainWindow.getMainPanel().resume();
    }
    return this;
  }

  @Override
  public Window debugOutput(final boolean doShowDebugOutput) {
    if (mainWindow != null) {
      mainWindow.getMainPanel().setDoShowDebugOutput(doShowDebugOutput);
    }
    return this;
  }

}
