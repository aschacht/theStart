package theStart.theView.TheControls;

import java.awt.BufferCapabilities;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import javax.swing.JPanel;


import TheGame.Board;
import View.FlatLandWindow;
import flatLand.trainingGround.FlatLandSelector;
import flatLand.trainingGround.GAMSTATUS;
import flatLand.trainingGround.GameStatus;
import flatLand.trainingGround.theStudio.Camera;
import src.Simulation;





public class GameScreen extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image buffer;
	private int width;
	private int height;
	private GameStatus statusInstance;
	private Simulation simulation;
	private FlatLandWindow flatLandWindow;
	private TheStartCamera theStartCamera;
	private Camera theEyeInTheSky;
	private Graphics graphics;
	private BufferStrategy bs;
	private  Board board;
	private FlatLandSelector flatLandSelector;

	public GameScreen(int width, int height, GameStatus statusInstance) {
		super();
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width,height));
		this.setSize(new Dimension(width,height));
		this.setMinimumSize(new Dimension(width,height));
		this.statusInstance = statusInstance;
		

		
		bs = new BufferStrategy() {

			@Override
			public void show() {
				
				paintComponent(graphics);

			}

			@Override
			public Graphics getDrawGraphics() {

				return graphics;
			}

			@Override
			public BufferCapabilities getCapabilities() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean contentsRestored() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean contentsLost() {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}

	@Override
	public void paintComponent(Graphics g) {

		
		g.clearRect(0, 0, width, height);
		
			if (statusInstance.isStatus(GAMSTATUS.MONOPOLY)&& board!=null) {
				board.draw(g);
			}
			if ((statusInstance.isStatus(GAMSTATUS.ANT)||statusInstance.isStatus(GAMSTATUS.ANTSIM))&& simulation!=null) {

				simulation.runSimulation(true, getFlatLandWindow(), g);
			}
			if (statusInstance.isStatus(GAMSTATUS.BRAIN)&& theStartCamera!=null) {

				theStartCamera.takePictureOfFlatLand(g);
			}
			if (!statusInstance.isStatus(GAMSTATUS.ANTSIM)) {

				theEyeInTheSky.takePictureOfFlatland(g);
			}

			

			
	}

	@Override
	public  Graphics getGraphics() {
		
		graphics = super.getGraphics();
		return graphics;
	}

	public FlatLandWindow getFlatLandWindow() {
		return flatLandWindow;
	}

	public void setFlatLandWindow(FlatLandWindow flatLandWindow) {
		this.flatLandWindow = flatLandWindow;
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

	public BufferStrategy getBs() {
		return bs;
	}

	public Camera getTheEyeInTheSky() {
		return theEyeInTheSky;
	}

	public void setTheEyeInTheSky(Camera theEyeInTheSky) {
		this.theEyeInTheSky = theEyeInTheSky;

	}

	public TheStartCamera getTheStartCamera() {
		return theStartCamera;
	}

	public void setTheStartCamera(TheStartCamera theStartCamera) {
		this.theStartCamera = theStartCamera;
	}

	public void setBoard(Board board) {
		this.board = board;

	}


}