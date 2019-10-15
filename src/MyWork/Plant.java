package MyWork;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

public class Plant extends Crosser{
	public void initializeInstance() {
		instance=new Plant();
	}
	public   BufferedImage[] getImage()   {
		BufferedImage[] arr = new BufferedImage[5];

		BufferedImage img = null;

		File file =  new File("plant.png");
	try {
	
			img = ImageIO.read(file);
		} catch (IIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e) {
			
		}

		arr[0] = img;

		return arr;
	
	}

}
