package MyWork;

import java.awt.image.BufferedImage;

import MyWork.ICrosser;

public abstract class Crosser implements ICrosser {
private String label;
private Double weight;
private int EatingRank;
Crosser instance;
public void initializeInstance() {
	
}
public double getWeight() {
	return weight;
}
public int getEatingRank() {
	return EatingRank;
}
public ICrosser makeCopy() {
	initializeInstance();
	instance.setEatingRank(EatingRank);
	instance.setWeight(weight);
	instance.setLabelToBeShown(label);
	return instance;
		}
public boolean canSail() {
//	Boat.getInstance().setWeight(Boat.getInstance().getWeight()+weight);
	if(Boat.getInstance().getWeight()+weight<Boat.getInstance().getMaxWeight()&&Boat.getInstance().getCapacity()+1<Boat.getInstance().getMaxCapacity())
		return true;
	else {
		return false;
	} 
}
public void setLabelToBeShown(String label) {
	this.label=label;
};
public String getLabelTOBeShown() {
	return label;
};
public void setWeight(Double weight) {
	this.weight = weight;
}
public void setEatingRank(int rank) {
	this.EatingRank = rank;
}
public BufferedImage[] getImage() {
	return null;
}
}
