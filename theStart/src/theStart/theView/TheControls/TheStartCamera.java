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
	private int posxinflatland;
	private int posyinflatland;
	private int posxoncanvas;
	private int posyoncanvas;

	private KeyBoardHandler cameraKeybordHandler;
	private ViewableFlatLand flatland;
	private BufferStrategy bufferStrategy;
	private Integer currentTime;
	private Integer previousTime;
	public boolean display = true;
	private ArrayList<FlatLander> flatlanderFaceBook;

	public TheStartCamera(int width, int height, int posxinflatland, int posyinflatland, ViewableFlatLand flatLand2) {

		this.flatland = flatLand2;
		this.setWidth(width);
		this.setHeight(height);
		this.setPosxinflatland(posxinflatland);
		this.setPosyinflatland(posyinflatland);
		this.posxoncanvas = 0;
		this.posyoncanvas = 0;
		this.cameraKeybordHandler = new KeyBoardHandler(this);
		previousTime = 0;
		flatlanderFaceBook = addFlatlanders();

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

	public void takePictureOfFlatLand(Canvas canvas) {
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		Graphics graphics = bufferStrategy.getDrawGraphics();
		currentTime = flatland.getTime();

		ArrayList<FlatLander> flatlanderFaceBookPool = FlatLanderFaceBook.getInstance().getFlatlanderFaceBookPool();

		SynapseFaceBook.getInstance();
		ArrayList<SynapsePair> synapseFaceBook = SynapseFaceBook.getSynapseFaceBook();
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, width, height);
		draw(graphics, flatlanderFaceBook, flatlanderFaceBookPool, synapseFaceBook);
		graphics.dispose();
		bufferStrategy.show();
	}

	public void update(ArrayList<FlatLander> flatlanderFaceBookPool) {

		updateBranches(flatlanderFaceBookPool);

		// checkAndUpdateInputNuron(graphics);

		updateSynapse();

		formAxon(flatlanderFaceBookPool);
		formDendrite(flatlanderFaceBookPool);
	}

	private ArrayList<FlatLander> addFlatlanders() {
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
		for (int i = posxinflatland - width / 2; i <= posxinflatland + width / 2; i++) {
			for (int j = posyinflatland - height / 2; j <= posyinflatland + height / 2; j++) {
				int j2 = (int) (generator.nextDouble(i, j, 0, 9000));
				if (j2 == 1) {
					long seed2 = (long) (Math.random() * 98594835);
					int dendritesStatic = (int) (6);
					int direction1 = (int) (Math.random() * 360);
					int length1 = (int) (Math.random() * 200) + 200;
					int axonStatic = (int) (1);
					int direction2 = (int) (Math.random() * 360);
					int length2 = (int) (Math.random() * 3000) + 200;
					int ofType = (int) (Math.random() * FlatLanderType.values().length + 1);
					if (!FlatLanderFaceBook.getInstance().check(i, j)) {
						FlatLanderFaceBook.getInstance()
								.add(new FlatLander(i, j, FlatLanderType.ofType(ofType), new FlatLanderRandom(seed2),
										seed2, flatland, 1, direction2, length2, dendritesStatic, direction1, length1,
										color, fireColor, backFireColor, fireAndBackFireColor, dendriteSynapseColor,
										axonSynapseColor, dendriteColor, axonColor));

					}
				}
			}
		}

		FlatLanderFaceBook.getInstance();
		ArrayList<FlatLander> flatlanderFaceBook = FlatLanderFaceBook.getFlatlanderFaceBook();
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
			FlatLander InputNuron = new FlatLander(0, 0, FlatLanderType.InputNuron, xinImage, yinImage,
					new FlatLanderRandom(seed2), seed2, flatland, axonStatic, direction2, length2, dendritesStatic,
					direction1, length1, color, fireColor, backFireColor, fireAndBackFireColor, dendriteSynapseColor,
					axonSynapseColor, dendriteColor, axonColor);
			FlatLanderFaceBook.getInstance().addInputNuron(InputNuron);
		}
		return flatlanderFaceBook;
	}

	private void updateBody(FlatLander fl) {

		fl.updateBody();

	}

	private void updateBranches(ArrayList<FlatLander> flatlanderFaceBookPool) {
		for (FlatLander flatLander : flatlanderFaceBookPool) {
			updateBody(flatLander);
			updateBranch(flatLander);
			growBranches(flatLander);
		}
	}

	private void updateBranch(FlatLander flatLander) {
		if (flatLander.getXposinflatland() >= posxinflatland - width / 2
				&& flatLander.getXposinflatland() <= posxinflatland + width / 2) {
			if (flatLander.getYposinflatland() >= posyinflatland - height / 2
					&& flatLander.getYposinflatland() <= posyinflatland + height / 2) {

				flatLander.updateBranches();

			}
		}
	}

	private void growBranches(FlatLander fl) {
		if (fl.getXposinflatland() >= posxinflatland - width / 2
				&& fl.getXposinflatland() <= posxinflatland + width / 2) {
			if (fl.getYposinflatland() >= posyinflatland - height / 2
					&& fl.getYposinflatland() <= posyinflatland + height / 2) {

				fl.growBranches();

			}
		}

	}

	private ArrayList<SynapsePair> updateSynapse() {
		SynapseFaceBook.getInstance();
		ArrayList<SynapsePair> synapseFaceBook = SynapseFaceBook.getSynapseFaceBook();
		for (SynapsePair synapsePair : synapseFaceBook) {
			synapsePair.update();
		}
		return synapseFaceBook;
	}

	private void draw(Graphics graphics, ArrayList<FlatLander> flatlanderFaceBook,
			ArrayList<FlatLander> flatlanderFaceBookPool, ArrayList<SynapsePair> synapseFaceBook) {
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

						graphics.fillOval(mapFromFlatLandToScreenSpaceX(flatLander.getXposinflatland() - 10),
								mapFromFlatLandToScreenSpaceY(flatLander.getYposinflatland() - 10), 20, 20);
					}
				}
			}

			for (SynapsePair synapsePair : synapseFaceBook) {
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

	private void formDendrite(ArrayList<FlatLander> flatlanderFaceBookPool) {
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
	}

	private void formAxon(ArrayList<FlatLander> flatlanderFaceBookPool) {
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
	}

	public Graphics takePictureOfFlatLand(Graphics graphics) {
		currentTime = flatland.getTime();

		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, width, height);

		ArrayList<FlatLander> flatlanderFaceBookPool = FlatLanderFaceBook.getInstance().getFlatlanderFaceBookPool();

		SynapseFaceBook.getInstance();
		ArrayList<SynapsePair> synapseFaceBook = SynapseFaceBook.getSynapseFaceBook();
		draw(graphics, flatlanderFaceBook, flatlanderFaceBookPool, synapseFaceBook);

		return graphics;
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
