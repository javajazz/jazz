package jazz;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
class MainWindow extends JFrame {

    final MainPanel mainPanel;

    MainWindow(final String title, final Model model,
            final DefaultWorld window,
            final int a, final int b) {
        super(title);

        mainPanel = new MainPanel(this, model, window, a, b);

        setContentPane(mainPanel);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(final WindowEvent e) {
                model.on(new DefaultEvent(window, Event.Type.WINDOW_OPENED, e));
            }

            @Override
            public void windowClosing(final WindowEvent e) {
                mainPanel.stop();
                window.setMainWindow(null);
                MainWindow.this.dispose();
            }

            @Override
            public void windowClosed(final WindowEvent e) {
                model.on(new DefaultEvent(window, Event.Type.WINDOW_CLOSED, e));
                window.onClose();
            }

            @Override
            public void windowIconified(final WindowEvent e) {
                mainPanel.pause();
                window.onHide();
                model.on(new DefaultEvent(window, Event.Type.WINDOW_HIDDEN, e));
            }

            @Override
            public void windowDeiconified(final WindowEvent e) {
                mainPanel.resume();
                window.onShow();
                model.on(new DefaultEvent(window, Event.Type.WINDOW_SHOWN, e));
            }

            @Override
            public void windowActivated(final WindowEvent e) {
                window.onFocus();
                model.on(new DefaultEvent(window, Event.Type.WINDOW_ACTIVATED,
                        e));
            }

            @Override
            public void windowDeactivated(final WindowEvent e) {
                window.onBlur();
                model.on(new DefaultEvent(window,
                        Event.Type.WINDOW_DEACTIVATED, e));
            }
        });

        window.setMainWindow(this);
    }
}
