package theStart.theStuff;

import java.util.ArrayList;

import theStart.thePeople.FlatLander;
import theStart.thePeople.FlatLanderFaceBook;

public class FlatLanderObjectLog {

	
	private static FlatLanderObjectLog instance;
	
	
	private static ArrayList<FlatLander> flatlanderObjectLog = new ArrayList<FlatLander>();
	
	
	public static FlatLanderObjectLog getInstance() {
		if (instance == null) {
			instance = new FlatLanderObjectLog();
		}
		return instance;
	}


	public static ArrayList<FlatLander> getFlatLanderObjectLog() {
		return flatlanderObjectLog;
	}


	public void add(FlatLander flatlander) {
		flatlanderObjectLog.add(flatlander);
	}
	
	
	
}
