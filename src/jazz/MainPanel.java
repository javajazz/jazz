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
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
class MainPanel extends JPanel {

	final long maxFrameRate = 1000000000L / 33;
	final Model model;
	final Random rand = new Random();
	final ReentrantLock lock = new ReentrantLock();
	final Thread timer;
	final WindowImpl window;

	boolean doShowDebugOutput = false;
	long currentFrameRate = maxFrameRate;
	double startTime;
	double currentTime;
	double pausedTime;

	volatile boolean isPainting = false;
	volatile long startUpdate;
	volatile int mouseX = 0;
	volatile int mouseY = 0;
	volatile int offsetX = 0;
	volatile int offsetY = 0;

	MainPanel(final MainWindow mainWindow, final Model theModel,
			final WindowImpl window, final int a, final int b) {
		this.model = theModel;
		this.window = window;
		this.startTime = System.nanoTime() / 1000000000.0;

		setPreferredSize(new Dimension(a, b));
		setSize(a, b);

		this.timer = new Thread(new Runnable() {
			public void run() {

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
						Thread.sleep(currentFrameRate / 1000000);
					} catch (InterruptedException e) {
						break;
					}
				}
			}
		});
		this.timer.start();

		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {

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
		currentTime = System.nanoTime() / 1000000000.0 - startTime;
		model.update(currentTime);
	}

	void pause() {
		lock.lock();
		pausedTime = System.nanoTime();
	}

	void resume() {
		startTime += (System.nanoTime() - pausedTime) / 1000000000.0;
		lock.unlock();
	}

	void stop() {
		timer.interrupt();
	}

	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		super.paintComponent(g);

		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		g2d.setColor(Color.BLACK);
		g2d.translate(getWidth() / 2 + offsetX, getHeight() / 2 + offsetY);
		g2d.scale(1, -1);
		model.getPicture().draw(g2d);

		if (doShowDebugOutput) {
			g2d.setColor(Color.BLACK);
			g2d.drawString(
					Long.toString((long) (currentFrameRate / 1000000.0)), 10,
					20);
		}

		long delta = System.nanoTime() - startUpdate;
		currentFrameRate = Math.max(maxFrameRate, delta);

		isPainting = false;
	}
}
