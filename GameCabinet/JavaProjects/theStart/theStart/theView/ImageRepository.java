package theStart.theView;

import java.awt.image.BufferedImage;

public class ImageRepository {
	
	
	
	private static ImageRepository instance;
	private static BufferedImage previousImage;
	private static BufferedImage image;
	private static boolean imageToken;

	public static ImageRepository getInstance() {
		if (instance == null) {
			instance = new ImageRepository();
		}
		return instance;
	}

	public void setImage(BufferedImage image2) {
		previousImage = image;
		image = image2;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void holdToken() {
		this.imageToken =true;
	}
	public void releaseToken() {
		this.imageToken =false;
	}

	public static boolean isImageToken() {
		return imageToken;
	}

	public static BufferedImage getPreviousImage() {
		return previousImage;
	}

}
