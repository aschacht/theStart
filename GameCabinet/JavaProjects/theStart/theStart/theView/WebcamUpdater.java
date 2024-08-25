package theStart.theView;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import com.github.sarxos.webcam.Webcam;

public class WebcamUpdater implements Runnable {

	private Webcam webcam;


	@Override
	public void run() {
		webcam = Webcam.getDefault();
		webcam.setViewSize(new Dimension(176, 144));
		while(true) {
			takeApicture();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}

	
	private void takeApicture() {
		
		
		ImageRepository.getInstance().holdToken();
		webcam.open();
		
		BufferedImage image = webcam.getImage();
		webcam.close();
		ImageRepository.getInstance().setImage(image);
		ImageRepository.getInstance().releaseToken();
		
	}
	
	
}
