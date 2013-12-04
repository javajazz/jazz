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
	final WindowImpl window;

	int maxFramesPerSecond = 100;
	long maxRefreshRate = 1000000000L / maxFramesPerSecond;
	boolean doShowDebugOutput = true;
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
	
	private volatile boolean paused = false;

	MainPanel(final MainWindow mainWindow, final Model theModel,
			final WindowImpl window, final int a, final int b) {
		this.model = theModel;
		this.window = window;

		setPreferredSize(new Dimension(a, b));
		setSize(a, b);

		setDoubleBuffered(true);
		
		this.timer = new Thread(new Runnable() {
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
									} catch (Exception exc) {
										exc.printStackTrace(System.err);
									}
								}
							});
						} else {
							//
						}
						Thread.sleep(currentRefreshRate / 1000000);
					} catch (InterruptedException e) {
						break;
					}
				}
			}
		});
		this.timer.start();

		addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				model.on(new EventImpl(window, Event.Type.MOUSE_WHEEL, e));
			}
		});
		
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				model.on(new EventImpl(window, Event.Type.MOUSE_MOVE, e));
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				model.on(new EventImpl(window, Event.Type.MOUSE_MOVE, e));
			}
		});

		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				model.on(new EventImpl(window, Event.Type.CLICK, e));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				model.on(new EventImpl(window, Event.Type.MOUSE_DOWN, e));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				model.on(new EventImpl(window, Event.Type.MOUSE_UP, e));
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		mainWindow.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				model.on(new EventImpl(window, Event.Type.KEY_TYPED, e, mouseX,
						mouseY));
			}

			@Override
			public void keyPressed(KeyEvent e) {
				model.on(new EventImpl(window, Event.Type.KEY_DOWN, e, mouseX,
						mouseY));
			}

			@Override
			public void keyReleased(KeyEvent e) {
				model.on(new EventImpl(window, Event.Type.KEY_UP, e, mouseX,
						mouseY));
			}
		});
	}

	void updateModel() {
		long currentUpdate = System.nanoTime();
		double delta = (currentUpdate - lastUpdate) / 1000000000.0 * acceleration;
		currentTime += delta;
		lastUpdate = currentUpdate;
		model.update(currentTime);
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
	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antialias
				? RenderingHints.VALUE_ANTIALIAS_ON
				: RenderingHints.VALUE_ANTIALIAS_OFF);

		super.paintComponent(g);

		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		g2d.setColor(Color.BLACK);
		g2d.translate(getWidth() / 2 + offsetX, getHeight() / 2 - offsetY);
		g2d.scale(1, -1);
		model.getPicture().draw(g2d);

		if (doShowDebugOutput) {
			g2d.setTransform(new AffineTransform());
			g2d.setColor(Color.WHITE);
			g2d.drawString(
					Long.toString(Math.round (1000 / (currentRefreshRate / 1000000.0))), 10,
					20);
		}

		long delta = System.nanoTime() - startUpdate;
		currentRefreshRate = Math.max(maxRefreshRate, delta);

		isPainting = false;
	}
}
