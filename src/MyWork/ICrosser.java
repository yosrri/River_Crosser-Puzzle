package MyWork;

import java.awt.image.BufferedImage;

public interface ICrosser {
public boolean canSail();
public double getWeight();
public int getEatingRank();
public BufferedImage[] getImage();
public ICrosser makeCopy();
public void setLabelToBeShown(String label);
public String getLabelTOBeShown();
}
