package MyWork;

import java.util.List;
import java.util.ArrayList;

public class StoryOne implements ICrossingStrategy {
	Crosser farmer =new Farmer();
	Crosser wolf = new Carnivorous();
	Crosser sheep = new Herbivorous();
	Crosser plant = new Plant();



	public boolean isValid(List<ICrosser> rightBankCrossers,
			List<ICrosser> leftBankCrossers, List<ICrosser> boatRiders) {
		for (int i = 0; i < boatRiders.size(); i++) {
			if ((boatRiders.get(i) instanceof Farmer) && (boatRiders.size() == 2)||((boatRiders.get(i) instanceof Farmer)&&(boatRiders.size() == 1)))
				return true;
		}
		return false;
	}

	public List<ICrosser> getInitialCrossers() {
		List<ICrosser> leftBankCrossers = new ArrayList<>();
		farmer.setLabelToBeShown("farmer");
		wolf.setLabelToBeShown("wolf");
		sheep.setLabelToBeShown("sheep");
		plant.setLabelToBeShown("plant");
		setAllEatingRanks();
		leftBankCrossers.add(farmer);
		leftBankCrossers.add(wolf);
		leftBankCrossers.add(sheep);
		leftBankCrossers.add(plant);
		


		return leftBankCrossers;
	}

	public void setAllEatingRanks() {
		farmer.setEatingRank(Integer.MAX_VALUE);
		plant.setEatingRank(0);
		sheep.setEatingRank(1);
		wolf.setEatingRank(2);
	}

	public String[] getInstructions() {
		String[] instructions = new String[3];
		instructions[0] = "1.The farmer is the only one who can sail the boat. He can only take one passenger, in addition to himself. ";
		instructions[1] = "2.You can not leave any two crossers on the same bank if they can harm(eat) each other ";
		instructions[2] = "How can the farmer get across the river with all the 2 animals and the plant without any losses? ";
		return instructions;
	}
	public boolean winner(List<ICrosser> rightBankCrossers)
	{
		return rightBankCrossers.size()==4?true:false;	
	}
	public boolean gameOver(List<ICrosser> rightBankCrossers,
			List<ICrosser> leftBankCrossers,List<ICrosser> boatRiders)
	{
				
				for (int i = 0; i < leftBankCrossers.size(); i++) {
					for (int j = i + 1; j < leftBankCrossers.size(); j++) {
				if ((leftBankCrossers.size()< 3)
						&& (Math.abs(leftBankCrossers.get(i).getEatingRank()
								- leftBankCrossers.get(j).getEatingRank()) == 1))
					{
					return true;
					}	
			}}
		for (int i = 0; i < rightBankCrossers.size(); i++) {
			for (int j = i + 1; j < rightBankCrossers.size(); j++) {
				if ((rightBankCrossers.size() < 3)
						&& (Math.abs(rightBankCrossers.get(i).getEatingRank()
								- rightBankCrossers.get(j).getEatingRank() )== 1))
					return true;
			}}
		return false;
	}


}