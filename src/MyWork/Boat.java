package MyWork;

public class Boat {
private int capacity,maxCapacity;
private double weight,maxWeight;
private int NoSails;
public int getCapacity() {
	return capacity;
}

	public int getNoSails() {
		return NoSails;
	}

	public void setNoSails(int noSails) {
		NoSails = noSails;
	}

	public int getMaxCapacity() {
	return maxCapacity;
}
public double getWeight() {
	return weight;
}
public double getMaxWeight() {
	return maxWeight;
}
public void setCapacity(int capacity) {
	this.capacity = capacity;
}
public void setMaxCapacity(int maxCapacity) {
	this.maxCapacity = maxCapacity;
}
public void setWeight(double weight) {
	this.weight = weight;
}
public void setMaxWeight(double maxWeight) {
	this.maxWeight = maxWeight;
}
private static Boat boat;
public static Boat getInstance() {
	if(boat==null)
		boat=new Boat();
	return boat;
};
}
