package MyWork;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;


public class Farmer extends Crosser {
	public void initializeInstance() {
		instance=new Farmer();
	}
	@Override
	public   BufferedImage[] getImage()   {
		BufferedImage[] arr = new BufferedImage[5];

		BufferedImage img = null;
for(int i=0;i<4;i++) {
		File file =  new File("farmer"+(i+1)+".png");
	try {
	
			img = ImageIO.read(file);
		} catch (IIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e) {
			
		}

		arr[i] = img;
}
		return arr;
	
	}
}
