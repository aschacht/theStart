package theStart.theView;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

public class WebcamUpdater implements Runnable {

	private Webcam webcam;


	@Override
	public void run() {
		webcam = Webcam.getDefault();
			
		if(!webcam.isOpen())webcam.open();	
		while(true) {
			takeApicture();
			try {
				Thread.sleep(50);
			
			} catch (InterruptedException e) {			
				webcam.close();
				e.printStackTrace();
			}
		}
		
	}

	
	private void takeApicture() {
		
		ImageRepository.getInstance().holdToken();

		
		BufferedImage image = webcam.getImage();
		
		ImageRepository.getInstance().setImage(image);
		ImageRepository.getInstance().releaseToken();
		
	}
	
	
}
