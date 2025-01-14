package theStart.theStuff;

import theStart.thePeople.FlatLander.XYPair;

public class SynapsePair {
	private  XYPair dendrite;
	private  XYPair axon;

	public SynapsePair(XYPair axon, XYPair dendrite) {
		this.setAxon(axon);
		this.setDendrite(dendrite);
	}

	public XYPair getDendrite() {
		return dendrite;
	}

	public void setDendrite(XYPair dendrite) {
		this.dendrite = dendrite;
	}

	public XYPair getAxon() {
		return axon;
	}

	public void setAxon(XYPair axon) {
		this.axon = axon;
	}

	public void update() {
		if (axon.isFire()) {
			dendrite.setBackFire(true);
			dendrite.setBackFireCount(1);
		} 

		

	}

}
