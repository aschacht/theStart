package theStart.theView.TheControls;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import FlatLandStructure.ViewableFlatLand;
import theStart.thePeople.FlatLander;
import theStart.thePeople.FlatLander.XYPair;
import theStart.thePeople.FlatLanderFaceBook;
import theStart.theSpace.FlatLandWindow;
import theStart.theStuff.BranchType;
import theStart.theStuff.FlatLanderRandom;
import theStart.theStuff.FlatLanderType;
import theStart.theStuff.SynapseFaceBook;
import theStart.theStuff.SynapsePair;
import theStart.theView.TheControls.KeyBoardHandler;

public class TheStartCamera implements CameraContract {

	private int width;
	private int height;
	private int posxinflatland = 0;
	private int posyinflatland = 0;
	private KeyBoardHandler cameraKeybordHandler;
	private ViewableFlatLand flatland;
	private BufferStrategy bufferStrategy;
	private Integer currentTime;
	private Integer previousTime;
	boolean display = true;
	boolean displayContacts = true;
	boolean displayBody = true;
	boolean displayBranches = true;
	private int seed;
	private ArrayList<FlatLander> flatlanderFaceBook;
	private ArrayList<FlatLander> flatlanderFaceBookPool;
	private int nuroncount = 0;
	private Canvas canvas;
	private boolean runonce=false;

	public TheStartCamera(int width, int height, int posxinflatland, int posyinflatland, ViewableFlatLand flatLand2,
			int seed, int nroncount, Canvas canvas) {

		this.flatland = flatLand2;
		this.seed = seed;
		this.nuroncount = nroncount;
		this.canvas = canvas;
		this.setWidth(width);
		this.setHeight(height);
		this.setPosxinflatland(posxinflatland);
		this.setPosyinflatland(posyinflatland);
		this.cameraKeybordHandler = new KeyBoardHandler(this);
		previousTime = 0;
		generateFlatlanders();
		if(canvas!=null) {
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		}
	}

	public KeyBoardHandler setKeyBindingsForPlayer(FlatLandWindow flatLandWindow) {
		return cameraKeybordHandler.buildKeyBindings(flatLandWindow, this);
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
		
		Graphics graphics = bufferStrategy.getDrawGraphics();
		currentTime = flatland.getTime();
		if (this.display) {
			graphics.setColor(Color.green);
			graphics.drawString("BLAH", 20, 20);
			graphics.setColor(Color.black);
			graphics.fillRect(0, 0, width, height);
			runonce = false;
		}else if(!this.display && !runonce) {
			graphics.setColor(Color.green);
			graphics.drawString("BLAH", 20, 20);
			graphics.setColor(Color.black);
			graphics.fillRect(0, 0, width, height);
			runonce = true;
		}
		renderAxonsDendrites(graphics, flatlanderFaceBookPool);

		renderSynapse(graphics, flatlanderFaceBookPool);
		renderBody(graphics, flatlanderFaceBook, flatlanderFaceBookPool);

		graphics.dispose();
		bufferStrategy.show();
	}

	private void generateFlatlanders() {
		FlatLanderRandom generator = new FlatLanderRandom(seed);
		Color color = Color.blue;
		Color fireColor = Color.MAGENTA;
		Color backFireColor = Color.YELLOW;
		Color fireAndBackFireColor = Color.PINK;
		Color dendriteSynapseColor = Color.CYAN;
		Color axonSynapseColor = Color.MAGENTA;
		Color dendriteColor = Color.blue;
		Color axonColor = Color.gray;
		int maxPerColumn = 2;
		int maxPerRow = 5;
		int initialColumn = posyinflatland - (height / 2);
		int initialrow = posxinflatland - (width / 2);

		for (int i = posxinflatland - (width / 2); i < posxinflatland + (width / 2); i += (int) (Math.random() * 20)
				+ 1) {
			int rowcont = 0;
			int columncount = 0;

			for (int j = posyinflatland - (height / 2); j < posyinflatland
					+ (height / 2); j += (int) (Math.random() * 20) + 1) {
				int j2 = (int) (Math.random() * 15);
				if (j2 == 1 && columncount < maxPerColumn && rowcont < maxPerRow
						&& (initialrow + 20 < i || initialColumn + 20 < j)) {
					if (nuroncount > 0) {
						initialrow = i;
						initialColumn = j;
						int dendritesStatic = (int) (6);
						int direction1 = (int) (Math.random() * 360);
						int length1 = (int) (Math.random() * 200) + 200;
						int axonStatic = (int) (1);
						int direction2 = (int) (Math.random() * 360);
						int length2 = (int) (Math.random() * 300) + 200;

						int ofType = (int) (1 + Math.random() * FlatLanderType.values().length);
						if (ofType >= 5)
							ofType = 4;
						if (!FlatLanderFaceBook.getInstance().check(i, j)) {
							FlatLanderFaceBook.getInstance()
									.add(new FlatLander(i, j, FlatLanderType.ofType(ofType), new FlatLanderRandom(seed),
											seed, flatland, 1, direction2, length2, dendritesStatic, direction1,
											length1, color, fireColor, backFireColor, fireAndBackFireColor,
											dendriteSynapseColor, axonSynapseColor, dendriteColor, axonColor));

							nuroncount--;
							columncount++;
							rowcont++;
						}
					}
				}
			}
		}

		long seed2 = (long) (Math.random() * 98594835);
		if (FlatLanderFaceBook.getInstance().getInputNurons().size() <= 0) {
			int xinImage = 0;
			int yinImage = 0;
			int dendritesStatic = (int) (0);
			int direction1 = (int) (90);
			int length1 = (int) (1000);
			int axonStatic = (int) (1);
			int direction2 = (int) (345);
			int length2 = (int) (3000);
			FlatLander InputNuron = new FlatLander(50, 50, FlatLanderType.InputNuron, xinImage, yinImage,
					new FlatLanderRandom(seed2), seed2, flatland, axonStatic, direction2, length2, dendritesStatic,
					direction1, length1, color, fireColor, backFireColor, fireAndBackFireColor, dendriteSynapseColor,
					axonSynapseColor, dendriteColor, axonColor);
			FlatLanderFaceBook.getInstance().addInputNuron(InputNuron);
		}
		flatlanderFaceBookPool = FlatLanderFaceBook.getInstance().getFlatlanderFaceBookPool();
		flatlanderFaceBook = FlatLanderFaceBook.getInstance().getFlatlanderFaceBook();
	}

	private void renderSynapse(Graphics graphics, ArrayList<FlatLander> flatlanderFaceBookPool) {
		ArrayList<SynapsePair> synapseFaceBook = SynapseFaceBook.getInstance().getSynapseFaceBook();

		SynapseFaceBook.getInstance().removeAll();

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
						if (axon != null) {
							XYPair terminal1 = getTerminalXYPair(axon);

							for (XYPair dend : dendrites) {
								if (checkXYAgainstTerminal(dend, terminal1)) {
									terminal1.setGrow(false);
									terminal1.formAxionSynapse();
									XYPair dendriteXYPair = getXYAgainstTerminal(dend, terminal1);
									dendriteXYPair.formDenriteSynapse();
									SynapseFaceBook.getInstance().add(new SynapsePair(terminal1, dendriteXYPair));

								}

							}

						}

					}
				}

			}
		}
		for (SynapsePair synapsePair : synapseFaceBook) {
			synapsePair.update();
			if (this.display) {
				if (this.displayContacts) {
					XYPair axon = synapsePair.getAxon();
					Color axonColor2 = axon.getAxonSynapseColor();
					XYPair dendrite = synapsePair.getDendrite();
					Color dendColor = dendrite.getDendriteSynapseColor();

					graphics.setColor(axonColor2);
					graphics.drawRect(mapFromFlatLandToScreenSpaceX(axon.getX()),
							mapFromFlatLandToScreenSpaceY(axon.getY()), 5, 5);
					graphics.setColor(dendColor);
					graphics.drawRect(mapFromFlatLandToScreenSpaceX(dendrite.getX() - 5),
							mapFromFlatLandToScreenSpaceY(dendrite.getY() - 5), 5, 5);
				}
			}
		}
	}

	private void renderAxonsDendrites(Graphics graphics, ArrayList<FlatLander> flatlanderFaceBookPool) {
		for (FlatLander flatLander : flatlanderFaceBookPool) {
			if (flatLander.getXposinflatland() >= posxinflatland - width / 2
					&& flatLander.getXposinflatland() <= posxinflatland + width / 2) {
				if (flatLander.getYposinflatland() >= posyinflatland - height / 2
						&& flatLander.getYposinflatland() <= posyinflatland + height / 2) {
					flatLander.updateBranches();
					flatLander.growBranches();
					if (this.display) {
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
		}

		checkAndUpdateInputNuron(graphics);
	}

	private void renderBody(Graphics graphics, ArrayList<FlatLander> flatlanderFaceBook,
			ArrayList<FlatLander> flatlanderFaceBookPool) {
		
			
				for (FlatLander flatLander : flatlanderFaceBookPool) {
					flatLander.updateBody();
				}
if (this.display) {
	if (this.displayBody) {
				for (FlatLander flatLander : flatlanderFaceBook) {
					if (flatLander.getXposinflatland() >= posxinflatland - width / 2
							&& flatLander.getXposinflatland() <= posxinflatland + width / 2) {
						if (flatLander.getYposinflatland() >= posyinflatland - height / 2
								&& flatLander.getYposinflatland() <= posyinflatland + height / 2) {

							if (flatLander.fire)
								graphics.setColor(Color.CYAN);
							else
								graphics.setColor(Color.red);

							graphics.fillOval(mapFromFlatLandToScreenSpaceX(flatLander.getXposinflatland() - 10),
									mapFromFlatLandToScreenSpaceY(flatLander.getYposinflatland() - 10), 20, 20);
						}
					}
				}
			}
		}
	}

	public Graphics takePictureOfFlatLand(Graphics graphics) {
		currentTime = flatland.getTime();
		if (this.display) {
			graphics.setColor(Color.green);
			graphics.drawString("BLAH", 20, 20);
			graphics.setColor(Color.black);
			graphics.fillRect(0, 0, width, height);
		}


		ArrayList<FlatLander> flatlanderFaceBookPool = FlatLanderFaceBook.getInstance().getFlatlanderFaceBookPool();

		renderAxonsDendrites(graphics, flatlanderFaceBookPool);

		renderSynapse(graphics, flatlanderFaceBookPool);
		renderBody(graphics, flatlanderFaceBook, flatlanderFaceBookPool);

		return graphics;
	}

	private void checkAndUpdateInputNuron(Graphics graphics) {
		ArrayList<FlatLander> InputNurons = FlatLanderFaceBook.getInstance().getInputNurons();

		for (FlatLander InputNuron : InputNurons) {

			InputNuron.updateBranches();
			InputNuron.growBranches();
			if (this.display) {

				if (this.displayBody) {
					if (InputNuron.fire())
						graphics.setColor(Color.CYAN);
					else
						graphics.setColor(Color.red);

					graphics.fillOval(mapFromFlatLandToScreenSpaceX(InputNuron.getXposinflatland() - 10),
							mapFromFlatLandToScreenSpaceY(InputNuron.getYposinflatland() - 10), 20, 20);
				}
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
			if (xyPair2.getBranches().size() > 0)
				return checkXYAgainstTerminal(xyPair2.getBranches().get(0), terminal);
			else
				return false;
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
			if (xyPair.theBranches.size() > 0)
				return getTerminalXYPair(xyPair.theBranches.get(0));
			return xyPair;
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

			if (!this.displayBranches) {
				if (xyPair.isFire() || xyPair.getBackFire()) {
					graphics.drawRect(mapFromFlatLandToScreenSpaceX(xyPair.getX()),
							mapFromFlatLandToScreenSpaceY(xyPair.getY()), 1, 1);
				}
			} else {
				graphics.drawRect(mapFromFlatLandToScreenSpaceX(xyPair.getX()),
						mapFromFlatLandToScreenSpaceY(xyPair.getY()), 1, 1);
			}
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
		int i = output_start + (int) Math.round(slope * (input - input_start));

		return i;
	}

	public int mapFromFlatLandToScreenSpaceY(int input) {
		int input_end = posyinflatland + height / 2;
		int input_start = posyinflatland - height / 2;
		int output_start = 0;
		int output_end = height;
		double slope = 1.0 * (output_end - output_start) / (input_end - input_start);
		int i = output_start + (int) Math.round(slope * (input - input_start));

		return i;
	}

}
