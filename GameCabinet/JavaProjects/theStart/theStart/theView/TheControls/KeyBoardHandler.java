package theStart.theView.TheControls;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import theStart.theSpace.FlatLandWindow;
import theStart.theView.TheStartCamera;



public class KeyBoardHandler {
	private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
	private static final String MOVE_RIGHT = "move right";
	private static final String MOVE_RIGHT_RELEASE = "move right release";
	private static final String MOVE_LEFT = "move left";
	private static final String MOVE_LEFT_RELEASE = "move left release";
	private static final String MOVE_UP = "move up";
	private static final String MOVE_UP_RELEASE = "move up release";
	private static final String MOVE_DOWN = "move down";
	private static final String MOVE_DOWN_RELEASE = "move down release";
	private static final String DISPLAY = "display";
	private static final String HIDE = "hide";

	private static final String FIRE = "move fire";
	private TheStartCamera camera;
	

	public KeyBoardHandler(TheStartCamera camera) {
		this.camera = camera;
		

	}

	public KeyBoardHandler buildKeyBindings(FlatLandWindow flatLandWindow, TheStartCamera camera2) {
		flatLandWindow.getPanel().getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), MOVE_RIGHT);
		flatLandWindow.getPanel().getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), MOVE_RIGHT_RELEASE);
		flatLandWindow.getPanel().getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), MOVE_LEFT);
		flatLandWindow.getPanel().getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), MOVE_LEFT_RELEASE);
		flatLandWindow.getPanel().getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
		flatLandWindow.getPanel().getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), MOVE_UP_RELEASE);
		flatLandWindow.getPanel().getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_DOWN);
		flatLandWindow.getPanel().getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), MOVE_DOWN_RELEASE);
		flatLandWindow.getPanel().getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), DISPLAY);
		flatLandWindow.getPanel().getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0, true), HIDE);

		flatLandWindow.getPanel().getInputMap(IFW).put(KeyStroke.getKeyStroke("control CONTROL"), FIRE);

		flatLandWindow.getPanel().getActionMap().put(MOVE_RIGHT, new MoveAction(1,0));
		flatLandWindow.getPanel().getActionMap().put(MOVE_RIGHT_RELEASE, new StopAction());
		flatLandWindow.getPanel().getActionMap().put(MOVE_LEFT, new MoveAction(-1,0));
		flatLandWindow.getPanel().getActionMap().put(MOVE_LEFT_RELEASE, new StopAction());
		flatLandWindow.getPanel().getActionMap().put(MOVE_UP, new MoveAction(0,-1));
		flatLandWindow.getPanel().getActionMap().put(MOVE_UP_RELEASE, new FallAction());
		flatLandWindow.getPanel().getActionMap().put(MOVE_DOWN, new MoveAction(0,1));
		flatLandWindow.getPanel().getActionMap().put(MOVE_DOWN_RELEASE, new FallAction());
		flatLandWindow.getPanel().getActionMap().put(DISPLAY, new DisplayAction(camera2));
		flatLandWindow.getPanel().getActionMap().put(HIDE, new HideAction(camera2));

		flatLandWindow.getPanel().getActionMap().put(FIRE, new AttackAction());

		return null;
	}

	private class HideAction extends AbstractAction {

		

		private TheStartCamera camera2;

		HideAction(TheStartCamera camera2) {
			this.camera2 = camera2;

		}

		public void actionPerformed(ActionEvent e) {
			camera.display =false;
			
		}
	}
	private class DisplayAction extends AbstractAction {
		
		
		
		private TheStartCamera camera2;

		DisplayAction(TheStartCamera camera2) {
			this.camera2 = camera2;
			
		}
		
		public void actionPerformed(ActionEvent e) {
			camera2.display = true;
			
		}
	}
	private class StopAction extends AbstractAction {
		
		
		
		StopAction() {
			
		}
		
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("Stop Action");
		}
	}

	private class MoveAction extends AbstractAction {

		

		private int xDirection;
		private int yDirection;

		MoveAction(int xDirection,int yDirection) {
			this.xDirection = xDirection;
			this.yDirection = yDirection;

			
		}

		public void actionPerformed(ActionEvent e) {
			
			System.out.println("Move Action");
			camera.setPosxinflatland(camera.getPosxinflatland()+xDirection*10);
			camera.setPosyinflatland(camera.getPosyinflatland()+yDirection*10);
		}
	}

	private class AttackAction extends AbstractAction {

		

		AttackAction() {

		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("Attck Action");
		}
	}

	private class JumpAction extends AbstractAction {

		

		public void actionPerformed(ActionEvent e) {
			System.out.println("Jump Action");
				
			
		}

	}

	private class FallAction extends AbstractAction {

		

		FallAction() {

		}

		public void actionPerformed(ActionEvent e) {
			
		}

	}
}
