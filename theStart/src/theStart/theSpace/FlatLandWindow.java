package theStart.theSpace;

import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import FlatLandStructure.ViewableFlatLand;
import theStart.theView.TheControls.GameScreen;

public class FlatLandWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4725289298438890095L;
	private boolean Close = false;
	private JPanel panel;

	public FlatLandWindow(Canvas canvas) {

		setupPanel(canvas);
		setupWindowListner();

		this.add(getPanel());
		getPanel().setFocusable(true);
		getPanel().requestFocusInWindow();
		this.pack();
		this.setVisible(true);
		this.repaint();
	}

	public FlatLandWindow(String string, ViewableFlatLand flatLand, GameScreen panel2, int canvasWidth,
			int canvasHeight) {
		setupPanel(panel2, canvasWidth, canvasHeight);

		setupWindowListner();

		this.add(getPanel());
		getPanel().setFocusable(true);
		getPanel().requestFocusInWindow();
		this.pack();
		this.setVisible(true);
		this.repaint();
	}

	public void refresh() {
		this.repaint();
	}

	private void setupPanel(Canvas canvas) {
		setPanel(new JPanel());

		getPanel().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		getPanel().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		getPanel().add(canvas);

	}

	private void setupPanel(JPanel panel, int width, int height) {
		setPanel(panel);

		getPanel().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		getPanel().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Canvas canvas = new Canvas();
		canvas.setSize(width, height);
		getPanel().add(canvas);

	}

	public boolean isClose() {
		return Close;
	}

	private void setupWindowListner() {
		WindowListener windowListener2 = new WindowListener() {

			public void windowOpened(WindowEvent e) {

			}

			public void windowClosing(WindowEvent e) {
				Close = true;

			}

			public void windowActivated(WindowEvent arg0) {
			}

			public void windowClosed(WindowEvent arg0) {
			}

			public void windowDeactivated(WindowEvent arg0) {
			}

			public void windowDeiconified(WindowEvent arg0) {
			}

			public void windowIconified(WindowEvent arg0) {
			}

		};

		this.addWindowListener(windowListener2);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}
