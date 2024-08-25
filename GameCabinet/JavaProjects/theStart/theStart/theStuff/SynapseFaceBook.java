package theStart.theStuff;

import java.util.ArrayList;

import theStart.thePeople.FlatLander.XYPair;

public class SynapseFaceBook {

	private static SynapseFaceBook instance;

	private static ArrayList<SynapsePair> synapseFaceBook = new ArrayList<SynapsePair>();

	public static SynapseFaceBook getInstance() {
		if (instance == null) {
			instance = new SynapseFaceBook();
		}
		return instance;
	}

	public static ArrayList<SynapsePair> getSynapseFaceBook() {
		return synapseFaceBook;
	}

	public void add(SynapsePair pair) {
		boolean isAreadyAdded = false;
		for (SynapsePair synapsePair : synapseFaceBook) {
			if (pair.getAxon().getX() == synapsePair.getAxon().getX()
					&& pair.getAxon().getY() == synapsePair.getAxon().getY()
					&& pair.getDendrite().getX() == synapsePair.getDendrite().getX()
					&& pair.getDendrite().getY() == synapsePair.getDendrite().getY()) {
				isAreadyAdded =true;
			}
		}
		if(!isAreadyAdded)
			synapseFaceBook.add(pair);
	}

}
