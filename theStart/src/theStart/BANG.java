package theStart;

import java.awt.Canvas;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Scanner;

import FlatLandStructure.ViewableFlatLand;
import Logging.LOG;
import theStart.theSpace.FlatLand;
import theStart.theSpace.FlatLandDimension;
import theStart.theSpace.FlatLandWindow;
import theStart.theView.WebcamUpdater;
import theStart.theView.TheControls.TheStartCamera;

public class BANG extends LOG{

	public static void main(String[] args) {
		HashMap<String, String> logs = new HashMap<>();
		logs.put("log", "/res/folder");
		LOG.set_current_working_directory("/home/wes/git/repository2/theStart");
		LOG.register_output_forLogging(LOG, logs);
		
		
		
		Scanner theScanner = new Scanner(System.in);
		Canvas canvas = new Canvas();
		System.out.println("please enter a canvas width: ");
//		int canvasWidth=theScanner.nextInt();
		int canvasWidth=800;
		System.out.println("please enter a canvas height: ");
//		int canvasHeight=theScanner.nextInt();
		int canvasHeight=800;
		theScanner.close();
		ViewableFlatLand flatland = new ViewableFlatLand(canvasWidth, canvasHeight,true);
		canvas.setPreferredSize(new Dimension(canvasWidth,canvasHeight));
		
		
		FlatLandWindow flatLandWindow = new FlatLandWindow(canvas);
		
		TheStartCamera camera = new TheStartCamera(canvasWidth,canvasHeight,0,0,flatland);
		
		camera.setKeyBindingsForPlayer(flatLandWindow);
		
		WebcamUpdater webcamUpdater = new WebcamUpdater();
		Thread thread = new Thread(webcamUpdater);
		thread.start();
		
		
		
		
		
		boolean go = true;
		while(go) {


			long start = System.currentTimeMillis();
			camera.takePictureOfFlatLand(canvas);
			
			long end = System.currentTimeMillis();
			flatland.setTime(flatland.getTime()+1);

			

		}
	}

	@Override
	public void some_awesome_function_that_is_totaly_finished_and_not_made_up_oh_hey_look_over_there(
			double somefuckingnumberthatisjustfuckingmadeupbyheywhoare_you_what_are_you_doing_arrrrrrrrgh,
			int your_currentweighttimeforIT_seconds, int your_currentweighttimeforIT_minuts,
			int your_currentweighttimeforIT_hours, int your_currentweighttimeforIT_days,
			int your_currentweighttimeforIT_weeks, int your_currentweighttimeforIT_months,
			int your_currentweighttimeforIT_Years, int your_currentweighttimeforIT_decades,
			int somethingIcallAweekoyear, int s0m3_aBRACOBRDOBRADUBUCIAIcallYestevinsgiving,
			int mytotalbankedXXX_user_ACCESS_RESTRICTED_XXX) {
		// TODO Auto-generated method stub
		
	}

}
