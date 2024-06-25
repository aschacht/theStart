package theStart.thePeople;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.bytedeco.ffmpeg.avcodec.AVCodec.Send_frame_AVCodecContext_AVFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.IplImage;

import com.github.sarxos.webcam.Webcam;

import FlatLand.ViewableFlatLand;
import theStart.thePeople.FlatLander.XYPair;
import theStart.theSpace.FlatLand;
import theStart.theStuff.BranchType;
import theStart.theStuff.FlatLanderRandom;
import theStart.theStuff.FlatLanderType;
import theStart.theStuff.Synapse;
import theStart.theStuff.SynapseType;
import theStart.theView.ImageRepository;

public class FlatLander {

	private int xposinflatland;
	private int yposinflatland;
	ViewableFlatLand flatLand;
	FlatLanderRandom generator;
	private ArrayList<XYPair> dendrites;
	public XYPair axon;
	private long seed;

	private FlatLanderType nuronType;
	private BufferedImage image;
	private Integer current;
	private Integer previousTime;
	private int backFireCapacity;
	private int backFireThreshold = 3;
	public boolean fire;
	private int xinImage;
	private int yinImage;

	public class XYPair {
		private int x;
		private int y;
		private double direction;
		private XYPair parent;
		public ArrayList<XYPair> theBranches;
		private long identifier;
		private boolean grow = true;
		private Color baseColor;
		private Color fireColor;
		private Color backFireColor;
		private Color fireAndBackFireColor;
		private boolean fire = false;
		private int fireCount;
		private boolean backFire;
		private int backFireCount;
		private int length;
		private BranchType type;
		private Synapse dendriteSynapse;
		private Color dendriteColor;
		private Synapse axonSynapse;
		private Color axonColor;
		private Color dendriteSynapseColor;
		private Color axonSynapseColor;

		public boolean getGrow() {
			return grow;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public double getDirection() {
			return direction;
		}

		public void setDirection(double direction) {
			this.direction = direction;
		}

		public ArrayList<XYPair> getBranches() {
			return theBranches;
		}

		public void setBranches(ArrayList<XYPair> theBranches) {
			this.theBranches = theBranches;
		}

		public void setParent(XYPair pair) {
			parent = pair;
		}

		public XYPair getParent() {

			return parent;
		}

		public long getIdentifier() {
			return identifier;
		}

		public void setIdentifier(long identifier) {
			this.identifier = identifier;
		}

		public void setGrow(boolean b) {
			this.grow = false;
		}

		public Color getBaseColor() {
			return baseColor;
		}

		public void setBaseColor(Color baseColor) {
			this.baseColor = baseColor;
		}

		public Color getFireColor() {
			return fireColor;
		}

		public void setFireColor(Color activationColor) {
			this.fireColor = activationColor;
		}

		public boolean isFire() {

			return this.fire;
		}

		public void setFire(boolean fire) {
			this.fire = fire;
		}

		public int getFireCount() {
			return fireCount;
		}

		public void setFireCount(int fireCount) {
			this.fireCount = fireCount;
		}

		public void setBackFire(boolean backFire) {
			this.backFire = backFire;
		}

		public boolean getBackFire() {
			return backFire;
		}

		public int getBackFireCount() {

			return this.backFireCount;
		}

		public void setBackFireCount(int count) {
			this.backFireCount = count;
		}

		public Color getBackFireColor() {
			return backFireColor;
		}

		public void setBackFireColor(Color backFireColor) {
			this.backFireColor = backFireColor;
		}

		public Color getFireAndBackFireColor() {
			return fireAndBackFireColor;
		}

		public void setFireAndBackFireColor(Color fireAndBackFireColor) {
			this.fireAndBackFireColor = fireAndBackFireColor;
		}

		public void setLength(int length) {
			this.length = length;
		}

		public int getLength() {
			return this.length;
		}

		public void setType(BranchType branchType) {
			this.type = branchType;
		}

		public BranchType getType() {
			return this.type;
		}

		public void formDenriteSynapse() {
			this.setDendriteSynapse(new Synapse(SynapseType.Dendrite));
		}

		public void formAxionSynapse() {
			this.setAxonSynapse(new Synapse(SynapseType.Axon));
		}

		public Synapse getDendriteSynapse() {
			return dendriteSynapse;
		}

		public void setDendriteSynapse(Synapse dendriteSynapse) {
			this.dendriteSynapse = dendriteSynapse;
		}

		public Synapse getAxonSynapse() {
			return axonSynapse;
		}

		public void setAxonSynapse(Synapse axonSynapse) {
			this.axonSynapse = axonSynapse;
		}

		public Color getDendriteColor() {
			return dendriteColor;
		}

		public void setDendriteColor(Color dendriteColor) {
			this.dendriteColor = dendriteColor;
		}

		public Color getAxonColor() {
			return axonColor;
		}

		public void setAxonColor(Color axonColor) {
			this.axonColor = axonColor;
		}

		public void setDendriteSynapseColor(Color dendriteColor2) {
			this.dendriteSynapseColor = dendriteColor2;
		}

		public Color getDendriteSynapseColor() {
			return this.dendriteSynapseColor;
		}

		public void setAxonSynapseColor(Color axonSynapseColor) {
			this.axonSynapseColor = axonSynapseColor;
		}

		public Color getAxonSynapseColor() {
			return this.axonSynapseColor;
		}
	}

	public FlatLander(int x, int y, FlatLanderType nuronType, FlatLanderRandom generator2, long seed,
			ViewableFlatLand flatland) {
		this.setNuronType(nuronType);
		this.seed = seed;
		this.flatLand = flatland;
		previousTime = flatland.getTime();
		this.generator = generator2;
		xposinflatland = x;
		yposinflatland = y;
	}

	public FlatLander(int x, int y, FlatLanderType nuronType, FlatLanderRandom generator2, long seed, ViewableFlatLand flatland,
			int axon, int axonDirection, int axonLength, int dendrites, int dendriteDirection, int dendriteLength,
			Color color, Color fireColor, Color backFireColor, Color fireAndBackFireColor, Color dendriteSynapseColor,
			Color axonSynapeColor, Color dendriteColor, Color axonColor) {
		this.setNuronType(nuronType);
		this.seed = seed;
		this.flatLand = flatland;
		previousTime = flatland.getTime();
		this.generator = generator2;
		xposinflatland = x;
		yposinflatland = y;
		if (nuronType == FlatLanderType.BipolarNuron) {
			buildAxon(1, axonDirection, axonLength, color, fireColor, backFireColor, fireAndBackFireColor,
					dendriteSynapseColor, axonSynapeColor, dendriteColor, axonColor);
			buildDendrites(1, axonDirection + 180, dendriteLength, 0, color, fireColor, backFireColor,
					fireAndBackFireColor, dendriteSynapseColor, axonSynapeColor, dendriteColor, axonColor);
		} else if (nuronType == FlatLanderType.MultipolarNuron) {
			buildAxon(1, axonDirection, axonLength, color, fireColor, backFireColor, fireAndBackFireColor,
					dendriteSynapseColor, axonSynapeColor, dendriteColor, axonColor);
			buildDendrites(6, axonDirection + 90, dendriteLength, 25, color, fireColor, backFireColor,
					fireAndBackFireColor, dendriteSynapseColor, axonSynapeColor, dendriteColor, axonColor);
		} else if (nuronType == FlatLanderType.PyramidalNuron) {
			buildAxon(1, axonDirection, axonLength, color, fireColor, backFireColor, fireAndBackFireColor,
					dendriteSynapseColor, axonSynapeColor, dendriteColor, axonColor);
			buildDendrites(2, axonDirection, dendriteLength,90, color, fireColor, backFireColor,
					fireAndBackFireColor, dendriteSynapseColor, axonSynapeColor, dendriteColor, axonColor);
		}else if (nuronType == FlatLanderType.PurkinjeNuron) {
			buildAxon(1, axonDirection, axonLength, color, fireColor, backFireColor, fireAndBackFireColor,
					dendriteSynapseColor, axonSynapeColor, dendriteColor, axonColor);
			buildDendrites(2, axonDirection, dendriteLength,90, color, fireColor, backFireColor,
					fireAndBackFireColor, dendriteSynapseColor, axonSynapeColor, dendriteColor, axonColor);
		} else {
			buildAxon(axon, axonDirection, axonLength, color, fireColor, backFireColor, fireAndBackFireColor,
					dendriteSynapseColor, axonSynapeColor, dendriteColor, axonColor);
			buildDendrites(dendrites, dendriteDirection, dendriteLength, 35, color, fireColor, backFireColor,
					fireAndBackFireColor, dendriteSynapseColor, axonSynapeColor, dendriteColor, axonColor);

		}
	}

	public FlatLander(int x, int y, FlatLanderType nuronType, int xinImage2, int yinImage2, FlatLanderRandom generator2,
			long seed2, ViewableFlatLand flatland2, int axon, int direction, int length, int dendrites, int direction2,
			int length2, Color color, Color fireColor, Color backFireColor, Color fireAndBackFireColor,
			Color dendriteSynapseColor, Color axonSynapseColor, Color dendriteColor, Color axonColor) {
		this.xinImage = xinImage2;
		this.yinImage = yinImage2;
		this.setNuronType(nuronType);
		this.seed = seed2;
		this.flatLand = flatland2;
		previousTime = flatland2.getTime();
		this.generator = generator2;
		xposinflatland = x;
		yposinflatland = y;
		buildAxon(axon, direction, length, color, fireColor, backFireColor, fireAndBackFireColor, dendriteSynapseColor,
				axonSynapseColor, dendriteColor, axonColor);
		buildDendrites(dendrites, direction2, length2, 45, color, fireColor, backFireColor, fireAndBackFireColor,
				dendriteSynapseColor, axonSynapseColor, dendriteColor, axonColor);
	}



	private void buildDendrites(int dendrites2, int dendriteDirection, int dendriteLength, int dendriteAngle,
			Color color, Color fireColor, Color backFireColor, Color fireAndBackFireColor, Color dendriteSynapseColor,
			Color axonSynapseColor, Color dendriteColor, Color axonColor) {
		setDentrites(new ArrayList<XYPair>());
		int count = 0;
		while (dendrites2 > count) {
			XYPair xyPair = new XYPair();
			xyPair.setType(BranchType.Dendrite);
			xyPair.setBaseColor(color);
			xyPair.setFireColor(fireColor);
			xyPair.setBackFireColor(backFireColor);
			xyPair.setFireAndBackFireColor(fireAndBackFireColor);
			xyPair.setDendriteColor(dendriteColor);
			xyPair.setDendriteSynapseColor(dendriteSynapseColor);
			xyPair.setAxonColor(axonColor);
			xyPair.setAxonSynapseColor(axonSynapseColor);
			xyPair.setIdentifier(seed);
			xyPair.setLength(dendriteLength);

			int angle = count * dendriteAngle;
			dendriteDirection = dendriteDirection + dendriteAngle;
			double directionInRadians = Math.toRadians(dendriteDirection);
			xyPair.setDirection(directionInRadians);
			double round = 10 * Math.cos(directionInRadians);

			round = Math.round(round);

			xyPair.setX((int) (xposinflatland + round));
			double round2 = 10 * Math.sin(directionInRadians);
			round2 = Math.round(round2);
			xyPair.setY((int) (yposinflatland + round2));
			getDendrites().add(xyPair);
			count++;
		}

	}

	private void buildAxon(int axon2, int axonDirection, int axonLength, Color color, Color fireColor,
			Color backFireColor, Color fireAndBackFireColor, Color dendriteSynapseColor, Color axonSynapseColor,
			Color dendriteColor, Color axonColor) {

		while (axon2 > 0) {

			XYPair xyPair = new XYPair();
			xyPair.setType(BranchType.Axon);
			xyPair.setLength(axonLength);
			xyPair.setBaseColor(color);
			xyPair.setFireColor(fireColor);
			xyPair.setBackFireColor(backFireColor);
			xyPair.setFireAndBackFireColor(fireAndBackFireColor);
			xyPair.setDendriteColor(dendriteColor);
			xyPair.setDendriteSynapseColor(dendriteSynapseColor);
			xyPair.setAxonSynapseColor(axonSynapseColor);
			xyPair.setAxonColor(axonColor);
			xyPair.setIdentifier(seed);

			double directionInRadians = Math.toRadians(axonDirection);
			xyPair.setDirection(directionInRadians);
			double round = 10 * Math.cos(directionInRadians);
			if (round < 0) {
				round = Math.floor(round);
			} else if (round > 0) {
				round = Math.round(round);
			}
			xyPair.setX((int) (xposinflatland + round));
			double round2 = 10 * Math.sin(directionInRadians);
			if (round2 < 0) {
				round2 = Math.floor(round2);
			} else if (round2 > 0) {
				round2 = Math.round(round2);
			}
			xyPair.setY((int) (yposinflatland + round2));
			setAxon(xyPair);
			axon2--;
		}
	}

	public boolean fire() {
//		if (getNuronType() != FlatLanderType.InputNuron) {
		double nextDouble = Math.random() * 2;
		if (nextDouble < 1) {
			this.fire = true;
			return this.fire;
		}
		this.fire = false;
		return this.fire;

//		} else {
//
//			BufferedImage image2 = getImage();
//			if (image2 != null) {
//				int rgb = image2.getRGB(xinImage, yinImage);
//				int red = (rgb & 0xff0000) >> 16;
//
//				if (red > 150) {
//					this.fire = true;
//					return this.fire;
//				} else {
//					this.fire = false;
//					return this.fire;
//				}
//			}
//			this.fire = false;
//			return this.fire;
//
//		}
	}

	private BufferedImage getImage() {
		if (ImageRepository.getInstance().isImageToken()) {
			return ImageRepository.getInstance().getPreviousImage();
		} else {
			return ImageRepository.getInstance().getImage();
		}
	}

	public static BufferedImage IplImageToBufferedImage(IplImage src) {
		OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
		Java2DFrameConverter paintConverter = new Java2DFrameConverter();
		Frame frame = grabberConverter.convert(src);
		return paintConverter.getBufferedImage(frame, 1);
	}

	public int getXposinflatland() {
		return xposinflatland;
	}

	public void setXposinflatland(int xposinflatland) {
		this.xposinflatland = xposinflatland;
	}

	public int getYposinflatland() {
		return yposinflatland;
	}

	public void setYposinflatland(int yposinflatland) {
		this.yposinflatland = yposinflatland;
	}

	public void growBranches() {
		if (getDendrites() == null) {
			setDentrites(new ArrayList<XYPair>());
			int tobranch = (int) (Math.random() * 8) + 1;

			int direction = (int) (generator.nextDouble(xposinflatland, yposinflatland, flatLand.getTime(), 0, 360));
			int length = (int) (generator.nextDouble(xposinflatland, yposinflatland, flatLand.getTime(), 1000, 3000));
			int count = 0;
			Color color = Color.black;
			Color fireColor = Color.ORANGE;
			Color backFireColor = Color.CYAN;
			Color fireAndBackFireColor = Color.BLUE;
			Color dendriteColor = Color.CYAN;
			Color axonColor = Color.MAGENTA;
			while (tobranch > count) {
				XYPair xyPair = new XYPair();
				xyPair.setType(BranchType.Dendrite);
				xyPair.setBaseColor(color);
				xyPair.setFireColor(fireColor);
				xyPair.setBackFireColor(backFireColor);
				xyPair.setFireAndBackFireColor(fireAndBackFireColor);
				xyPair.setDendriteColor(dendriteColor);
				xyPair.setAxonColor(axonColor);
				xyPair.setIdentifier(seed);
				xyPair.setLength(length);

				double directionInRadians = Math.toRadians(direction + (count * (360 / tobranch)));
				xyPair.setDirection(directionInRadians);
				double round = Math.cos(directionInRadians);
				if (round < 0) {
					round = Math.floor(round);
				} else if (round > 0) {
					round = Math.round(round);
				}
				xyPair.setX((int) (xposinflatland + round));
				double round2 = Math.sin(directionInRadians);
				if (round2 < 0) {
					round2 = Math.floor(round2);
				} else if (round2 > 0) {
					round2 = Math.round(round2);
				}
				xyPair.setY((int) (yposinflatland + round2));
				getDendrites().add(xyPair);
				count++;
			}

		} else {
		for (XYPair xyPair : getDendrites()) {
			if (xyPair != null)
				goToEnd(xyPair);

		}

		}

//		if (getAxon() == null) {
//			int direction = (int) (generator.nextDouble(xposinflatland, yposinflatland, flatLand.getTime(), 0, 360));
//			int length = (int) (generator.nextDouble(xposinflatland, yposinflatland, flatLand.getTime(), 5000, 10000));
//			Color color = Color.black;
//			Color fireColor = Color.MAGENTA;
//			Color backFireColor = Color.YELLOW;
//			Color fireAndBackFireColor = Color.PINK;
//			Color dendriteColor = Color.CYAN;
//			Color axonColor = Color.MAGENTA;
//			XYPair xyPair = new XYPair();
//			xyPair.setType(BranchType.Axon);
//			xyPair.setLength(length);
//			xyPair.setBaseColor(color);
//			xyPair.setFireColor(fireColor);
//			xyPair.setBackFireColor(backFireColor);
//			xyPair.setFireAndBackFireColor(fireAndBackFireColor);
//			xyPair.setDendriteColor(dendriteColor);
//			xyPair.setAxonColor(axonColor);
//			xyPair.setIdentifier(seed);
//
//			double directionInRadians = Math.toRadians(direction);
//			xyPair.setDirection(directionInRadians);
//			double round = Math.cos(directionInRadians);
//			if (round < 0) {
//				round = Math.floor(round);
//			} else if (round > 0) {
//				round = Math.round(round);
//			}
//			xyPair.setX((int) (xposinflatland + round));
//			double round2 = Math.sin(directionInRadians);
//			if (round2 < 0) {
//				round2 = Math.floor(round2);
//			} else if (round2 > 0) {
//				round2 = Math.round(round2);
//			}
//			xyPair.setY((int) (yposinflatland + round2));
//			setAxon(xyPair);
//		} else {
		XYPair axon2 = getAxon();
		if (axon2 != null)
			goToEnd(axon2);
//		}
	}

	private void goToEnd(XYPair pair) {

		if (pair.getGrow()) {
			if (pair.getBranches() != null) {
				for (XYPair xyPair : pair.getBranches()) {
					goToEnd(xyPair);
				}
			} else {
				if (pair.getLength() >= 1) {
					ArrayList<XYPair> theBranches = new ArrayList<XYPair>();
					pair.setBranches(theBranches);
					int x = pair.getX();
					int y = pair.getY();
					XYPair xyPair = new XYPair();
					xyPair.setType(pair.getType());
					xyPair.setLength(pair.getLength() - 1);
					xyPair.setIdentifier(seed);
					xyPair.setBaseColor(pair.getBaseColor());
					xyPair.setFireColor(pair.getFireColor());
					xyPair.setBackFireColor(pair.getBackFireColor());
					xyPair.setFireAndBackFireColor(pair.getFireAndBackFireColor());
					xyPair.setDendriteColor(pair.getDendriteColor());
					xyPair.setAxonColor(pair.getAxonColor());
					xyPair.setDendriteSynapseColor(pair.getDendriteSynapseColor());
					xyPair.setAxonSynapseColor(pair.getAxonSynapseColor());

					do {
						int direction = (int) (Math.random() * 90);
						int plusMinus = (int) (Math.random() * 360);
						if (plusMinus > 180) {
							plusMinus = 1;
						} else {
							plusMinus = -1;
						}
						direction = (int) (averageOfParents(pair, Math.toDegrees(pair.getDirection()), 0)
								+ direction * plusMinus);
						xyPair.setParent(pair);
						double directionInRadians2 = Math.toRadians(direction);
//						double directionInRadians2 = Math.toRadians(Math.toDegrees(pair.direction));
						xyPair.setDirection(directionInRadians2);

						double round3 = Math.cos(directionInRadians2);
						if (round3 < 0) {
							round3 = Math.floor(round3);
						} else if (round3 > 0) {
							round3 = Math.round(round3);
						}
						double round4 = Math.sin(directionInRadians2);
						if (round4 < 0) {
							round4 = Math.floor(round4);
						} else if (round4 > 0) {
							round4 = Math.round(round4);
						}
						x = (int) (pair.getX() + round3);
						y = (int) (pair.getY() + round4);
						xyPair.setX(x);
						xyPair.setY(y);
					} while (checkToSeeIfAnyPreviousHadXY(pair, x, y));

					ArrayList<XYPair> branches2 = pair.getBranches();

					branches2.add(xyPair);

					pair.setBranches(branches2);
				}
			}
		}
	}

	private double averageOfParents(XYPair pair, double direction, int i) {
		if (pair == null) {
			return direction / i;
		} else {
			direction = direction + Math.toDegrees(pair.getDirection());
			i = i + 1;
			return averageOfParents(pair.getParent(), direction, i);
		}

	}

	private boolean checkToSeeIfAnyPreviousHadXY(XYPair pair, int x, int y) {

		if (pair.getParent() != null && pair.getX() != x && pair.getY() != y) {
			return checkToSeeIfAnyPreviousHadXY(pair.getParent(), x, y);
		} else if (pair.getParent() != null && pair.getX() == x && pair.getY() == y) {
			return true;
		} else if (pair.getParent() == null && pair.getX() != x && pair.getY() != y) {
			return false;
		} else if (pair.getParent() == null && pair.getX() == x && pair.getY() == y) {
			return true;
		}

		return false;

	}

	public int mapFromFlatLandToDirectionX(int input) {
		int input_end = 360;
		int input_start = 0;
		int output_start = -1;
		int output_end = 1;
		double slope = 1.0 * (output_end - output_start) / (input_end - input_start);
		return output_start + (int) Math.round(slope * (input - input_start));
	}

	public int mapFromFlatLandToDirectionY(int input) {
		int input_end = 360;
		int input_start = 0;
		int output_start = -1;
		int output_end = 1;
		double slope = 1.0 * (output_end - output_start) / (input_end - input_start);
		return output_start + (int) Math.round(slope * (input - input_start));
	}

	public ArrayList<XYPair> getDendrites() {
		return dendrites;
	}

	public void setDentrites(ArrayList<XYPair> branches) {
		this.dendrites = branches;
	}

	public XYPair getAxon() {
		return axon;
	}

	public void setAxon(XYPair axon) {
		this.axon = axon;
	}

	public FlatLanderType getNuronType() {
		return nuronType;
	}

	public void setNuronType(FlatLanderType nuronType) {
		this.nuronType = nuronType;
	}

	public void updateBranches() {
		if (getDendrites() != null) {
			for (XYPair xyPair : getDendrites()) {

				updateToEnd(xyPair);

			}
		}

		if (getAxon() != null) {
			XYPair axon2 = getAxon();
			if (nuronType == FlatLanderType.InputNuron) {
				fire();
				if (this.fire) {
					axon2.setFire(true);
					axon2.setFireCount(1);
				}
			} else {
				if (this.fire) {
					axon2.setFire(true);
					axon2.setFireCount(1);
				}
			}
			updateToEnd(axon2);
		}

	}

	private void updateToEnd(XYPair pair) {
		if (pair.getBranches() != null) {
			for (XYPair xyPair : pair.getBranches()) {
				if (pair.isFire() && pair.getFireCount() == 0) {
					xyPair.parent.setFire(false);
					xyPair.parent.setFireCount(0);
					xyPair.setFire(true);
					xyPair.setFireCount(1);
				} else if (pair.isFire() && pair.getFireCount() > 0) {
					pair.setFireCount(pair.getFireCount() - 1);
				}


				updateToEnd(xyPair);
			}
		} else {
			if (pair.isFire() && pair.getFireCount() == 0) {
				pair.setFire(false);
				pair.setFireCount(0);
			} else if (pair.isFire() && pair.getFireCount() > 0) {
				pair.setFireCount(pair.getFireCount() - 1);
			}

			if (pair.getBackFire() && pair.getBackFireCount() == 0) {
				pair.setBackFire(false);
				pair.setBackFireCount(0);
				pair.parent.setBackFire(true);
				pair.parent.setBackFireCount(1);
			} else if (pair.getBackFire() && pair.getBackFireCount() > 0) {
				pair.setBackFireCount(pair.getBackFireCount() - 1);
			}
			if (pair.getParent() != null) {
				if (!pair.getGrow()) {
					pair.getParent().setGrow(false);
				}
				updateFromEnd(pair.parent);
			}

		}

	}

	private void updateFromEnd(XYPair pair) {
		if (!pair.getGrow()) {
			if (pair.getParent() != null) {
				pair.getParent().setGrow(false);
			}
		}
		if (pair.getParent() != null) {
			if (pair.getBackFire() && pair.getBackFireCount() == 0) {
				pair.setBackFire(false);
				pair.setBackFireCount(0);
				pair.parent.setBackFire(true);
				pair.parent.setBackFireCount(1);
			} else if (pair.getBackFire() && pair.getBackFireCount() > 0) {
				pair.setBackFireCount(pair.getBackFireCount() - 1);
			}
			updateFromEnd(pair.parent);
		} else {
			if (pair.getBackFire() && pair.getBackFireCount() == 0) {
				pair.setBackFire(false);
				pair.setBackFireCount(0);
			} else if (pair.getBackFire() && pair.getBackFireCount() > 0) {
				pair.setBackFireCount(pair.getBackFireCount() - 1);
			}
		}
	}

	public void updateBody() {
		ArrayList<XYPair> dendrites2 = dendrites;
		for (XYPair xyPair : dendrites2) {
			if (xyPair.getBackFire()) {
				this.backFireCapacity += 1;
			}
		}

		if (this.backFireCapacity > this.backFireThreshold) {
			this.fire = true;
			this.backFireCapacity = 0;
		} else {
			this.fire = false;
		}
	}

}
