package MyWork;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

public class Controller implements IRiverCrossingController {

    String[] instructions = new String[3];

    ICrossingStrategy strategy;
    private List<ICrosser> boatCrossers = new ArrayList<ICrosser>();
    private List<ICrosser> temp = new ArrayList<ICrosser>();
//    ICrosser temp;
    boolean boatOnLeft=true;

    private List<ICrosser>   intialCrossers=new ArrayList<ICrosser>();
    private List<ICrosser> RightCrossers=new ArrayList<ICrosser>();
    private List<ICrosser> leftCrossers=new ArrayList<ICrosser>();
    private CommandControl CommandTool=new CommandControl();

   // Stack<List<ICrosser>> zazzy=new Stack<>();
    private List<ICrosser> undoCrosser=new ArrayList<ICrosser>();

    Stack x = new Stack();
    Stack y = new Stack();
    
    SaveGame save;

    Boat boat=new Boat();


    public void newGame(ICrossingStrategy gameStrategy) {
        this.strategy=gameStrategy;

        leftCrossers.addAll(gameStrategy.getInitialCrossers());
        intialCrossers.addAll(gameStrategy.getInitialCrossers());
        instructions=gameStrategy.getInstructions();
    }

    @Override
    public void resetGame() {

        RightCrossers.removeAll(RightCrossers);
        leftCrossers.removeAll(leftCrossers);
        leftCrossers.addAll(intialCrossers);

    }

    @Override
    public String[] getInstructions() {
        return strategy.getInstructions();
    }

    @Override
    public List<ICrosser> getCrossersOnRightBank() {
        return RightCrossers;
    }

    @Override
    public List<ICrosser> getCrossersOnLeftBank() {
        return leftCrossers;
    }

    @Override
    public boolean isBoatOnTheLeftBank() {
    	if(boat.getNoSails()%2==0)
        return true;
    	else
    	return false;
    }

    @Override
    public int getNumberOfSails() {

        return boat.getNoSails();
    }

    @Override
    public boolean canMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
        if(fromLeftToRightBank)
            return strategy.isValid(RightCrossers,leftCrossers,crossers);
        else
            return strategy.isValid(leftCrossers,RightCrossers,crossers);
    }

    @Override
    public void doMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
//    	x.push(doMove(crossers, fromLeftToRightBank));
       
    	if(fromLeftToRightBank){
           // System.out.println("right to left");
    		RightCrossers.addAll(crossers);
            leftCrossers.removeAll(crossers);
            boat.setNoSails(boat.getNoSails()+1);
           
           // boatOnLeft =false;
        }
        else{
        	//System.out.println("left to right");
            leftCrossers.addAll(crossers);
            RightCrossers.removeAll(crossers);
            boat.setNoSails(boat.getNoSails()+1);
            }
    		undoCrosser.addAll(crossers);
    		x.push(undoCrosser);

    		//System.out.println(boat.getNoSails()+"  from do move");
       // boatOnLeft =true;
       
     
    boatCrossers.addAll(crossers);
    }
//    public void reverseMove(List<ICrosser> crossers, boolean fromLeftToRightBank) {
//            }

    @Override
    public boolean canUndo() {
    	//x=StoryOneGui.x;
        if(x.isEmpty())return false;
    	else return true;
        
    }

    @Override
    public boolean canRedo() {
if(y.isEmpty())	return false;
else return true;
    }

    @Override
    public void undo() {
    	if(canUndo()) {
    		undoCrosser=(List<ICrosser>) x.pop();
    		y.push(undoCrosser);
    		if(leftCrossers.contains(undoCrosser)){
    	           // System.out.println("right to left");
    	    		RightCrossers.addAll(undoCrosser);
    	            leftCrossers.removeAll(undoCrosser);
    	            boat.setNoSails(boat.getNoSails()+1);
    	           
    	           // boatOnLeft =false;
    	        }
    	        else{
    	        	//System.out.println("left to right");
    	            leftCrossers.addAll(undoCrosser);
    	            RightCrossers.removeAll(undoCrosser);
    	            boat.setNoSails(boat.getNoSails()+1);
    	            }
    	    	
    		
    		
//    		Coordinates temp = new Coordinates();
//    		temp=x.pop();
//    		y.push(temp);
//    		if(!x.isEmpty())
//    		{
//    			Coordinates co = new Coordinates();
//        		co.setX(x.peek().getX());
//        		co.setY(x.peek().getY());
//        		Robot robot = null;
//        		try {
//    				robot = new Robot();
//    			} catch (AWTException e) {
//    				e.printStackTrace();
//    			}
//        		robot.mouseMove(co.getX(), co.getY());
//        		robot.mousePress(MouseEvent.BUTTON1_MASK);
//        		robot.mouseRelease(MouseEvent.BUTTON1_MASK);
//    		}
//    		
    	}
    	}

    @Override
    public void redo() {
if(canRedo()) {
	
	undoCrosser=(List<ICrosser>) y.pop();
	x.push(undoCrosser);
	if(leftCrossers.contains(undoCrosser)){
           // System.out.println("right to left");
    		RightCrossers.addAll(undoCrosser);
            leftCrossers.removeAll(undoCrosser);
            boat.setNoSails(boat.getNoSails()+1);
           
           // boatOnLeft =false;
        }
        else{
        	//System.out.println("left to right");
            leftCrossers.addAll(undoCrosser);
            RightCrossers.removeAll(undoCrosser);
            boat.setNoSails(boat.getNoSails()+1);
            }
    
}
    }

    @Override
    public void saveGame() {
    	if(strategy instanceof StoryOne)
        CommandTool.setCommand(new SaveGameCommand(new SaveGame(leftCrossers,RightCrossers,"storyOne",getNumberOfSails(),isBoatOnTheLeftBank())));
    	else
    		 CommandTool.setCommand(new SaveGameCommand(new SaveGame(leftCrossers,RightCrossers,"storyTwo",getNumberOfSails(),isBoatOnTheLeftBank())));
    	CommandTool.buttonWasPressed();

    }

    @Override
    public void loadGame() {
    	leftCrossers.clear();
    	RightCrossers.clear();
    	if(strategy instanceof StoryOne)
    	CommandTool.setCommand(new LoadGameCommand(new LoadGame(leftCrossers,RightCrossers,"storyOne",getNumberOfSails(),isBoatOnTheLeftBank())));
    	else
    		CommandTool.setCommand(new LoadGameCommand(new LoadGame(leftCrossers,RightCrossers,"storyTwo",getNumberOfSails(),isBoatOnTheLeftBank())));	
    	CommandTool.buttonWasPressed();

    }

    @Override
    public List<List<ICrosser>> solveGame() {
        return null;
    }
    public boolean gameover(List<ICrosser> boatRiders) {
    	boolean gameover =strategy.gameOver(RightCrossers, leftCrossers,boatRiders);
        return gameover;
    }
    public boolean winner() { 
    	
    	boolean winner=strategy.winner(getCrossersOnRightBank());
    
    	
    	return winner;
    }
}
