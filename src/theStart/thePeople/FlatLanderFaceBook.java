package theStart.thePeople;

import java.util.ArrayList;


public class FlatLanderFaceBook {
	
	
	private static FlatLanderFaceBook instance;
	private Integer count =0;
	
	private static ArrayList<FlatLander> flatlanderFaceBook = new ArrayList<FlatLander>();
	private static ArrayList<FlatLander> inputNurons = new ArrayList<FlatLander>();
	
	
	
	public static FlatLanderFaceBook getInstance() {
		if (instance == null) {
			instance = new FlatLanderFaceBook();
		}
		return instance;
	}


	public static ArrayList<FlatLander> getFlatlanderFaceBook() {
		return flatlanderFaceBook;
	}


	public void add(FlatLander flatlander) {
		flatlanderFaceBook.add(flatlander);
	}


	public void remove(int xlower, int ylower, int xupper, int yupper) {
		ArrayList<FlatLander> flatLandersToRemove= new ArrayList<>();
		for (FlatLander flatLander : flatlanderFaceBook) {
			if(flatLander.getXposinflatland()<xlower || flatLander.getXposinflatland()>xupper  ) {
				flatLandersToRemove.add(flatLander);
			}
			if(flatLander.getYposinflatland()<ylower || flatLander.getYposinflatland()>yupper  ) {
				flatLandersToRemove.add(flatLander);
			}
		}
		
		for (FlatLander flatLander : flatLandersToRemove) {
			flatlanderFaceBook.remove(flatLander);
		}
		
	}


	public Integer getCount() {
		Integer countToReturn = count;
		count++;
		return countToReturn;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	public boolean check(int i, int j) {
		for (FlatLander flatLander : flatlanderFaceBook) {
			if(flatLander.getXposinflatland()==i && flatLander.getYposinflatland()==j)
				return true;
		}
		return false;
	}


	public void addInputNuron(FlatLander inputNuron) {
		inputNurons.add(inputNuron);
	}


	public  ArrayList<FlatLander> getInputNurons() {
		return inputNurons;
	}


	


	public ArrayList<FlatLander> getFlatlanderFaceBookPool() {
		ArrayList<FlatLander> flatlanderFaceBook2=new ArrayList<FlatLander>(flatlanderFaceBook);
		flatlanderFaceBook2.addAll(inputNurons);
		return flatlanderFaceBook2;
	}
	
	
	

}
