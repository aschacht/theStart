package theStart.theSpace;

import theStart.thePeople.FlatLanderFaceBook;
import theStart.theStuff.FlatLanderObjectLog;

public class FlatLand {
	
	
	
	
	
	private FlatLandDimension xabovezero;
	private FlatLandDimension yabovezero;
	private FlatLandDimension xbelowzero;
	private FlatLandDimension ybelowzero;
	public FlatLanderFaceBook fb = FlatLanderFaceBook.getInstance(); 
	public FlatLanderObjectLog oL= FlatLanderObjectLog.getInstance();
	private Integer time =0;

	public FlatLand(FlatLandDimension xabovezero,FlatLandDimension yabovezero,FlatLandDimension xbelowzero,FlatLandDimension ybelowzero) {
		this.xabovezero = xabovezero;
		this.yabovezero = yabovezero;
		this.xbelowzero = xbelowzero;
		this.ybelowzero = ybelowzero;
		
		
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
	
	

}
