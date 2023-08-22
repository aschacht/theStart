package theStart;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JPanel;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.IplImage;

import theStart.thePeople.FlatLander;
import theStart.thePeople.FlatLanderFaceBook;
import theStart.theSpace.FlatLand;
import theStart.theSpace.FlatLandDimension;
import theStart.theSpace.FlatLandWindow;
import theStart.theStuff.FlatLanderObjectLog;
import theStart.theView.Camera;
import theStart.theView.WebcamUpdater;

public class BANG {

	public static void main(String[] args) {
		Scanner theScanner = new Scanner(System.in);
		FlatLand flatland = new FlatLand(FlatLandDimension.POSINF,FlatLandDimension.POSINF,FlatLandDimension.NEGINF,FlatLandDimension.NEGINF);
		Canvas canvas = new Canvas();
		System.out.println("please enter a canvas width: ");
		int canvasWidth=theScanner.nextInt();
		System.out.println("please enter a canvas height: ");
		int canvasHeight=theScanner.nextInt();
		canvas.setPreferredSize(new Dimension(canvasWidth,canvasHeight));
		
		
		FlatLandWindow flatLandWindow = new FlatLandWindow(canvas);
		
		Camera camera = new Camera(canvasWidth,canvasHeight,0,0,canvas,flatland);
		
		camera.setKeyBindingsForPlayer(flatLandWindow);
		
		WebcamUpdater webcamUpdater = new WebcamUpdater();
		Thread thread = new Thread(webcamUpdater);
		thread.start();
		
		
		
		
		
		boolean go = true;
		while(go) {

//			try {
			long start = System.currentTimeMillis();
			camera.takePictureOfFlatLand();
			
			long end = System.currentTimeMillis();
			flatland.setTime(flatland.getTime()+1);
			System.out.println("Elapsed Time: "+(end-start));
//				Thread.sleep(25);
//			} catch (InterruptedException e) {
				
//				e.printStackTrace();
//			}
			

		}
	}

}
