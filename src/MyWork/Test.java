package MyWork;


public class Test {

	public static void main(String[] args) {
		
		Crosser farmer= new Carnivorous();
//		farmer.instance= new Farmer();
		farmer.setWeight(200.9);
		farmer.setEatingRank(2);
		farmer.setLabelToBeShown("carni");
		ICrosser x= farmer.makeCopy();
//		System.out.println(farmer.getEatingRank());
//		System.out.println(farmer.getLabelTOBeShown());
//
		System.out.println(x.getEatingRank());
		System.out.println(x.getLabelTOBeShown());
		
//Boat.getInstance().setMaxWeight(200);
//System.out.println(farmer.canSail());
		
		IRiverCrossingController controller = new Controller();
		ICrossingStrategy storyone = new StoryOne();
		controller.newGame(storyone);
		controller.saveGame();
		controller.loadGame();
		
		
		
		
		
		
	}

}
