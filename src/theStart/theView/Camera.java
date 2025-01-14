package theStart.theView;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

import com.github.sarxos.webcam.Webcam;

import theStart.thePeople.FlatLander;
import theStart.thePeople.FlatLander.XYPair;
import theStart.thePeople.FlatLanderFaceBook;
import theStart.theSpace.FlatLand;
import theStart.theSpace.FlatLandWindow;
import theStart.theStuff.BranchType;
import theStart.theStuff.FlatLanderObjectLog;
import theStart.theStuff.FlatLanderRandom;
import theStart.theStuff.FlatLanderType;
import theStart.theStuff.SynapseFaceBook;
import theStart.theStuff.SynapsePair;
import theStart.theView.TheControls.KeyBoardHandler;

public class Camera {

	private int width;
	private int height;
	private int posxinflatland;
	private int posyinflatland;
	private int posxoncanvas;
	private int posyoncanvas;

	private Canvas canvas;
	private KeyBoardHandler cameraKeybordHandler;
	private FlatLand flatland;
	private BufferStrategy bufferStrategy;
	private Integer currentTime;
	private Integer previousTime;
	public boolean display=false;

	public Camera(int width, int height, int posxinflatland, int posyinflatland, Canvas canvas2, FlatLand flatland) {

		this.canvas = canvas2;
		this.flatland = flatland;
		this.setWidth(width);
		this.setHeight(height);
		this.setPosxinflatland(posxinflatland);
		this.setPosyinflatland(posyinflatland);
		this.posxoncanvas = 0;
		this.posyoncanvas = 0;
		this.cameraKeybordHandler = new KeyBoardHandler(this);
		this.canvas.createBufferStrategy(2);
		bufferStrategy = this.canvas.getBufferStrategy();
		previousTime = 0;

	}

	public KeyBoardHandler setKeyBindingsForPlayer(FlatLandWindow flatLandWindow) {
		return cameraKeybordHandler.buildKeyBindings(flatLandWindow,this);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getPosxinflatland() {
		return posxinflatland;
	}

	public void setPosxinflatland(int posxinflatland) {
		this.posxinflatland = posxinflatland;
	}

	public int getPosyinflatland() {
		return posyinflatland;
	}

	public void setPosyinflatland(int posyinflatland) {
		this.posyinflatland = posyinflatland;
	}

	public void takePictureOfFlatLand() {
		currentTime = flatland.getTime();
		Graphics graphics = bufferStrategy.getDrawGraphics();
		graphics.setColor(Color.green);
		graphics.drawString("BLAH", 20, 20);
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, width, height);

		long seed = 10398127;
		FlatLanderRandom generator = new FlatLanderRandom(seed);
		Color color = Color.blue;
		Color fireColor = Color.MAGENTA;
		Color backFireColor = Color.YELLOW;
		Color fireAndBackFireColor = Color.PINK;
		Color dendriteSynapseColor = Color.CYAN;
		Color axonSynapseColor = Color.MAGENTA;
		Color dendriteColor = Color.blue;
		Color axonColor = Color.gray;
//		for (int i = posxinflatland - width / 2; i <= posxinflatland + width / 2; i++) {
//			for (int j = posyinflatland - height / 2; j <= posyinflatland + height / 2; j++) {
//				int j2 = (int) (generator.nextDouble(i, j, 0, 9000));
//				if (j2 == 1) {
//					long seed2 = (long) (Math.random() * 98594835);
//					int dendritesStatic = (int) (6);
//					int direction1 = (int) (Math.random() * 360);
//					int length1 = (int) (Math.random() * 200) + 200;
//					int axonStatic = (int) (1);
//					int direction2 = (int) (Math.random() * 360);
//					int length2 = (int) (Math.random() * 3000) + 200;
//					if (!FlatLanderFaceBook.getInstance().check(i, j)) {
////						if (FlatLanderFaceBook.getInstance().getFlatlanderFaceBook().size() < 3
////								&& i >= posxinflatland - width/8 && i <= posxinflatland + width/8 &&
////								j>=posyinflatland-height/8 && j<=posyinflatland+height/8) {
//
//						FlatLanderFaceBook.getInstance()
//								.add(new FlatLander(i, j, FlatLanderType.SupportNuron, new FlatLanderRandom(seed2),
//										seed2, flatland, 1, direction2, length2, dendritesStatic, direction1, length1,
//										color, fireColor, backFireColor, fireAndBackFireColor, dendriteSynapseColor,
//										axonSynapseColor, dendriteColor, axonColor));
//
//					}
//				}
////				}
//			}
//		}
		if (FlatLanderFaceBook.getInstance().getFlatlanderFaceBook().size()==0) {
		long seed22 = (long) (Math.random() * 98594835);
		int dendritesStatic1 = (int) (6);
		int direction11 = (int) (Math.random() * 360);
		int length11 = (int) (Math.random() * 200) + 200;
		int axonStatic2 = (int) (1);
		int direction22 = (int) (Math.random() * 360);
		int length22 = (int) (Math.random() * 3000) + 200;
		FlatLanderFaceBook.getInstance()
		.add(new FlatLander(50, 50, FlatLanderType.PyramidalNuron, new FlatLanderRandom(seed22),
				seed22, flatland, 1, direction22, length22, dendritesStatic1, direction11, length11,
				color, fireColor, backFireColor, fireAndBackFireColor, dendriteSynapseColor,
				axonSynapseColor, dendriteColor, axonColor));
		}
		
		
//		for (int k = 0; k < 10; k++) {
//			long seed2 = (long) (Math.random() * 98594835);
//			int dendritesStatic = (int) (6);
//			int direction1 = (int) (Math.random() * 360);
//			int length1 = (int) (Math.random() * 200) + 200;
//			int axonStatic = (int) (1);
//			int direction2 = (int) (Math.random() * 360);
//			int length2 = (int) (Math.random() * 3000) + 200;
//			if (k % 2 == 0) {
//				if (!FlatLanderFaceBook.getInstance().check(-50, -50 + (-50 * k))) {
//					FlatLanderFaceBook.getInstance().add(new FlatLander(-50, -50 + (-50 * k),
//							FlatLanderType.SupportNuron, new FlatLanderRandom(seed2), seed2, flatland, 1, direction2,
//							length2, dendritesStatic, direction1, length1, color, fireColor, backFireColor,
//							fireAndBackFireColor, dendriteSynapseColor, axonSynapseColor, dendriteColor, axonColor));
//				}
//			} else {
//				if (!FlatLanderFaceBook.getInstance().check(50 + (50 * k), 50)) {
//					FlatLanderFaceBook.getInstance().add(new FlatLander(50 + (50 * k), 50, FlatLanderType.SupportNuron,
//							new FlatLanderRandom(seed2), seed2, flatland, 1, direction2, length2,dendritesStatic,
//							270, length1, color, fireColor, backFireColor, fireAndBackFireColor,
//							dendriteSynapseColor, axonSynapseColor, dendriteColor, axonColor));
//				}
//			}
//		}

		ArrayList<FlatLander> flatlanderFaceBook = FlatLanderFaceBook.getInstance().getFlatlanderFaceBook();
		long seed2 = (long) (Math.random() * 98594835);
		if (FlatLanderFaceBook.getInstance().getInputNurons().size()<=0) {
			int xinImage =0;
			int yinImage =0;
			int dendritesStatic = (int) (0);
			int direction1 = (int) (90);
			int length1 = (int) (1000);
			int axonStatic = (int) (1);
			int direction2 = (int) (345);
			int length2 = (int) (3000);
			FlatLander InputNuron = new FlatLander(0, 0, FlatLanderType.InputNuron,xinImage,yinImage, new FlatLanderRandom(seed2), seed2,
					flatland, axonStatic,direction2, length2, dendritesStatic, direction1, length1, color, fireColor, backFireColor, fireAndBackFireColor,
					dendriteSynapseColor, axonSynapseColor, dendriteColor, axonColor);
			FlatLanderFaceBook.getInstance().addInputNuron(InputNuron);
		}

		
	
		
		
		
		
		ArrayList<FlatLander> flatlanderFaceBookPool = FlatLanderFaceBook.getInstance().getFlatlanderFaceBookPool();

		for (FlatLander flatLander : flatlanderFaceBookPool) {
			flatLander.updateBody();
		}

		for (FlatLander flatLander : flatlanderFaceBookPool) {
			if (flatLander.getXposinflatland() >= posxinflatland - width / 2
					&& flatLander.getXposinflatland() <= posxinflatland + width / 2) {
				if (flatLander.getYposinflatland() >= posyinflatland - height / 2
						&& flatLander.getYposinflatland() <= posyinflatland + height / 2) {

					flatLander.updateBranches();

				}
			}
		}

		for (FlatLander flatLander : flatlanderFaceBookPool) {
			if (flatLander.getXposinflatland() >= posxinflatland - width / 2
					&& flatLander.getXposinflatland() <= posxinflatland + width / 2) {
				if (flatLander.getYposinflatland() >= posyinflatland - height / 2
						&& flatLander.getYposinflatland() <= posyinflatland + height / 2) {

					flatLander.growBranches();

				}
			}
		}
		checkAndUpdateInputNuron(graphics);

		ArrayList<SynapsePair> synapseFaceBook = SynapseFaceBook.getInstance().getSynapseFaceBook();
		for (SynapsePair synapsePair : synapseFaceBook) {
			synapsePair.update();
		}

		for (FlatLander flatLander : flatlanderFaceBookPool) {

			ArrayList<XYPair> dendrites = flatLander.getDendrites();
			for (XYPair xyPair : dendrites) {

				XYPair terminal = getTerminalXYPair(xyPair);

				for (FlatLander flatLander2 : flatlanderFaceBookPool) {
					if (!flatLander2.equals(flatLander)) {
						XYPair axon = flatLander2.axon;
						if (axon != null) {
							if (checkXYAgainstTerminal(axon, terminal)) {
								terminal.setGrow(false);
								terminal.formDenriteSynapse();
								XYPair axonXYPair = getXYAgainstTerminal(axon, terminal);
								axonXYPair.formAxionSynapse();
								SynapseFaceBook.getInstance().add(new SynapsePair(axonXYPair, terminal));

							}
						}

					}
				}
			}

		}
		for (FlatLander flatLander : flatlanderFaceBookPool) {

			XYPair axon = flatLander.getAxon();
			if (axon != null) {
				XYPair terminal = getTerminalXYPair(axon);

				for (FlatLander flatLander2 : flatlanderFaceBookPool) {
					if (!flatLander2.equals(flatLander)) {
						ArrayList<XYPair> dendrites = flatLander2.getDendrites();

						for (XYPair dend : dendrites) {
							if (checkXYAgainstTerminal(dend, terminal)) {
								terminal.setGrow(false);
								terminal.formAxionSynapse();
								XYPair dendriteXYPair = getXYAgainstTerminal(dend, terminal);
								dendriteXYPair.formDenriteSynapse();
								SynapseFaceBook.getInstance().add(new SynapsePair(terminal, dendriteXYPair));

							}

						}
					}
				}
			}
		}

		if (this.display) {
			for (FlatLander flatLander : flatlanderFaceBookPool) {
				if (flatLander.getXposinflatland() >= posxinflatland - width / 2
						&& flatLander.getXposinflatland() <= posxinflatland + width / 2) {
					if (flatLander.getYposinflatland() >= posyinflatland - height / 2
							&& flatLander.getYposinflatland() <= posyinflatland + height / 2) {

						ArrayList<XYPair> branches = flatLander.getDendrites();
						for (XYPair xyPair : branches) {
							if (xyPair != null)
								drawBranches(graphics, xyPair);
						}

						XYPair axon = flatLander.getAxon();
						if (axon != null)
							drawBranches(graphics, axon);

					}
				}
			}
		
		for (FlatLander flatLander : flatlanderFaceBook) {
			if (flatLander.getXposinflatland() >= posxinflatland - width / 2
					&& flatLander.getXposinflatland() <= posxinflatland + width / 2) {
				if (flatLander.getYposinflatland() >= posyinflatland - height / 2
						&& flatLander.getYposinflatland() <= posyinflatland + height / 2) {

					if (flatLander.fire)
						graphics.setColor(Color.CYAN);
					else
						graphics.setColor(Color.red);

//					graphics.drawRect(mapFromFlatLandToScreenSpaceX(flatLander.getXposinflatland()),
//							mapFromFlatLandToScreenSpaceY(flatLander.getYposinflatland()), 1, 1);
					graphics.fillOval(mapFromFlatLandToScreenSpaceX(flatLander.getXposinflatland()-10),
							mapFromFlatLandToScreenSpaceY(flatLander.getYposinflatland()-10), 20, 20);
				}
			}
		}

		for (SynapsePair synapsePair : synapseFaceBook) {
			XYPair axon = synapsePair.getAxon();
			Color axonColor2 = axon.getAxonSynapseColor();
			XYPair dendrite = synapsePair.getDendrite();
			Color dendColor = dendrite.getDendriteSynapseColor();

			graphics.setColor(axonColor2);
			graphics.drawRect(mapFromFlatLandToScreenSpaceX(axon.getX()), mapFromFlatLandToScreenSpaceY(axon.getY()), 5,
					5);
			graphics.setColor(dendColor);
			graphics.drawRect(mapFromFlatLandToScreenSpaceX(dendrite.getX() - 5),
					mapFromFlatLandToScreenSpaceY(dendrite.getY() - 5), 5, 5);

		}
		}
		graphics.dispose();
		bufferStrategy.show();
	}





	private void checkAndUpdateInputNuron(Graphics graphics) {
		ArrayList<FlatLander> InputNurons = FlatLanderFaceBook.getInstance().getInputNurons();
		
		
		for (FlatLander InputNuron : InputNurons) {
			
		
		InputNuron.updateBranches();
		InputNuron.growBranches();
		if (this.display) {
			if (InputNuron.fire())
				graphics.setColor(Color.CYAN);
			else
				graphics.setColor(Color.red);

			
			graphics.fillOval(mapFromFlatLandToScreenSpaceX(InputNuron.getXposinflatland()-10),
					mapFromFlatLandToScreenSpaceY(InputNuron.getYposinflatland()-10), 20, 20);

			drawBranches(graphics, InputNuron.getAxon());
			ArrayList<XYPair> branches = InputNuron.getDendrites();
			for (XYPair xyPair : branches) {

				drawBranches(graphics, xyPair);
			}
		}
		}
	}

	private void checkXYForBackFire(XYPair xyPair2, XYPair terminal) {
		if ((xyPair2.getX() == terminal.getX() + 1 || xyPair2.getX() == terminal.getX() - 1)
				&& (xyPair2.getY() == terminal.getY() + 1 || xyPair2.getY() == terminal.getY() - 1)
				&& terminal.isFire()) {
			xyPair2.setBackFire(true);
			xyPair2.setBackFireCount(1);
		} else if (xyPair2.getBranches() != null) {
			checkXYForBackFire(xyPair2.getBranches().get(0), terminal);
		}
	}

	private boolean checkXYAgainstTerminal(XYPair xyPair2, XYPair terminal) {
		if ((xyPair2.getX() == terminal.getX() + 1 || xyPair2.getX() == terminal.getX() - 1)
				&& (xyPair2.getY() == terminal.getY() + 1 || xyPair2.getY() == terminal.getY() - 1)) {

			return true;
		} else if (xyPair2.getBranches() != null) {
			return checkXYAgainstTerminal(xyPair2.getBranches().get(0), terminal);
		} else {
			return false;
		}
	}

	private XYPair getXYAgainstTerminal(XYPair xyPair2, XYPair terminal) {
		if ((xyPair2.getX() == terminal.getX() + 1 || xyPair2.getX() == terminal.getX() - 1)
				&& (xyPair2.getY() == terminal.getY() + 1 || xyPair2.getY() == terminal.getY() - 1)) {

			return xyPair2;
		} else if (xyPair2.getBranches() != null) {
			return getXYAgainstTerminal(xyPair2.theBranches.get(0), terminal);
		} else {
			return null;
		}
	}

	private XYPair getTerminalXYPair(XYPair xyPair) {
		if (xyPair.getBranches() == null) {
			return xyPair;
		} else {
			return getTerminalXYPair(xyPair.theBranches.get(0));
		}
	}

	private void drawBranches(Graphics graphics, XYPair xyPair) {

		if (xyPair.isFire() && !xyPair.getBackFire()) {
			graphics.setColor(xyPair.getFireColor());

		} else if (!xyPair.isFire() && xyPair.getBackFire()) {
			graphics.setColor(xyPair.getBackFireColor());

		} else if (xyPair.isFire() && xyPair.getBackFire()) {
			graphics.setColor(xyPair.getFireAndBackFireColor());

		} else {
			if (xyPair.getType() == BranchType.Axon) {

				graphics.setColor(xyPair.getAxonColor());
			} else if (xyPair.getType() == BranchType.Dendrite) {

				graphics.setColor(xyPair.getDendriteColor());
			}
//			graphics.setColor(xyPair.getBaseColor());
		}
		if (xyPair.getBranches() != null) {

			graphics.drawRect(mapFromFlatLandToScreenSpaceX(xyPair.getX()),
					mapFromFlatLandToScreenSpaceY(xyPair.getY()), 1, 1);

			for (XYPair pair : xyPair.getBranches()) {
				drawBranches(graphics, pair);
			}
		} else {

			graphics.drawRect(mapFromFlatLandToScreenSpaceX(xyPair.getX()),
					mapFromFlatLandToScreenSpaceY(xyPair.getY()), 1, 1);
		}

	}

	public int mapFromFlatLandToScreenSpaceX(int input) {
		int input_end = posxinflatland + width / 2;
		int input_start = posxinflatland - width / 2;
		int output_start = 0;
		int output_end = width;
		double slope = 1.0 * (output_end - output_start) / (input_end - input_start);
		return output_start + (int) Math.round(slope * (input - input_start));
	}

	public int mapFromFlatLandToScreenSpaceY(int input) {
		int input_end = posyinflatland + height / 2;
		int input_start = posyinflatland - height / 2;
		int output_start = 0;
		int output_end = height;
		double slope = 1.0 * (output_end - output_start) / (input_end - input_start);
		return output_start + (int) Math.round(slope * (input - input_start));
	}

}
