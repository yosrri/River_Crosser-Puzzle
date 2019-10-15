package MyWork;

import java.util.ArrayList;
import java.util.List;

public class StoryTwo implements ICrossingStrategy {
	
	Crosser farmerOne = new Farmer();
	Crosser farmerTwo = new Farmer();
	Crosser farmerThree = new Farmer();
		Crosser farmerFour = new Farmer();
		Crosser animal = new Herbivorous();
	public boolean isValid(List<ICrosser> rightBankCrossers,
			List<ICrosser> leftBankCrossers, List<ICrosser>
			boatRiders) {
		
		Boolean flag = false;
		    
		for(int i = 0 ; i < boatRiders.size(); i++)
		    {
		        if(boatRiders.get(i) instanceof Farmer)
		        	flag = true;
		    }
		  
		    	return flag;
		    
		    
		
	}
			
			public List<ICrosser> getInitialCrossers(){
				List<ICrosser> initialCrossers = new ArrayList<ICrosser>();		
  				farmerOne.setWeight(90.0);
  				farmerTwo.setWeight(80.0);
  				farmerThree.setWeight(60.0);
  				farmerFour.setWeight(40.0);
  				animal.setWeight(20.0);
  				farmerOne.setLabelToBeShown("90");
  				farmerTwo.setLabelToBeShown("80");
  				farmerThree.setLabelToBeShown("60");
  				farmerFour.setLabelToBeShown("40");
  				animal.setLabelToBeShown("20");
  				initialCrossers.add(farmerOne);
  				initialCrossers.add(farmerTwo);
  				initialCrossers.add(farmerThree);
  				initialCrossers.add(farmerFour);
  				initialCrossers.add(animal);
				return initialCrossers;
			}
		
			public String[] getInstructions() {
				String[] instructions = new String[3];
				instructions[0] = "1.The boat cannot bear a load heavier than 100 kg. ";
				instructions[1] = "2.All farmers can raft, while the animal cannot. ";
				instructions[2] = "How can they all get to the other side with their animal? ";
				return instructions;
			}
			public boolean winner(List<ICrosser> rightBankCrossers)
			{ 
				
				return rightBankCrossers.size()==5?true:false;	
			}
			public boolean gameOver(List<ICrosser> rightBankCrossers,
					List<ICrosser> leftBankCrossers,List<ICrosser> boatRiders)
			{
				
				Double boatRidersWeight = 0.0;
				
				for(int i = 0 ; i < boatRiders.size(); i++)
			    {
			    	boatRidersWeight = boatRidersWeight + boatRiders.get(i).getWeight();
			    }
			    if(boatRidersWeight > 100)
			    {
			    	return true;
			    }

			    else {
			    	return false;
			    }
			    

}
			}
