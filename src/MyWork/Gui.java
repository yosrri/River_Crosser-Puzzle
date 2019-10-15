package MyWork;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
// javafx.scene.control.Alert;
import javafx.scene.control.Button;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
//import wtvr.Controller;
//import wtvr.ICrossingStrategy;
//import wtvr.StoryOne;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Stack;

public class Gui  {
    Controller controller = new Controller();
    ICrossingStrategy storyone = new StoryOne();
    ArrayList<ICrosser> boatRiders = new ArrayList<>();
    ICrosser farmerObj = new Farmer();
    ICrosser sheepObj = new Herbivorous();
    ICrosser plantObj = new Plant();
    Stack crossersUndo = new Stack();
    Stack crossersRedo = new Stack();
    
  Stage stage;
  Scene scene;
  MainMenu mainmenu;
    int flag=0;
    int farmerOnBoat=0;
    int plantOnBoat=0;
    int sheepOnBoat=0;
    int counter=0;
    boolean boatOneLeft= false;
    boolean sheepOnLeft=false;
    boolean fromRightToLeft = true;
    
     public Gui(Stage stage) {
    	 
    	 this.stage = stage;
     }
  
    public void prepareScene() {

        controller.newGame(storyone);
        Image rightImg = new Image("right.png");
        ImageView rightView = new ImageView(rightImg);
        Image leftImg = new Image("left.png");
        ImageView leftView = new ImageView(leftImg);

        Button right = new Button();
        Button left = new Button();
        Button undo = new Button("Undo");
        Button redo = new Button("Redo");
        HBox hb = new HBox(left, right,undo,redo);

        rightView.setFitWidth(30);
        rightView.setFitHeight(30);
        leftView.setFitWidth(30);
        leftView.setFitHeight(30);
        right.setGraphic(rightView);
        left.setGraphic(leftView);

        Group gp = new Group();

        Image boatImg = new Image("boat.png", 200, 200, false, false);
        ImageView boat = new ImageView(boatImg);
        Image worldImg = new Image("World.png", 0, 0, false, false);
        ImageView background = new ImageView(worldImg);
        Image farmerImg = new Image("farmer.jpg.png", 0, 0, false, false);
        ImageView farmer = new ImageView(farmerImg);
        Image plantImg = new Image("plant.png", 0, 0, false, false);
        ImageView plant = new ImageView(plantImg);
        Image sheepImg = new Image("sheep.png", 0, 0, false, false);
        ImageView sheep = new ImageView(sheepImg);

        plant.setFitWidth(60);
        sheep.setFitWidth(50);

        farmer.setX(800);
        farmer.setY(500);
        plant.setX(910);
        plant.setY(500);
        sheep.setX(960);
        sheep.setY(500);
        boat.setX(600);
        boat.setY(500);

        Pane pane = new Pane();
        pane.getChildren().addAll(background, boat, farmer,plant,sheep);

        left.setOnAction(e -> {
            if(controller.canMove(boatRiders,fromRightToLeft)) {
                controller.doMove(boatRiders,fromRightToLeft);
                fromRightToLeft = false;
                TranslateTransition obj = new TranslateTransition();

                obj.setDuration(Duration.seconds(1));
                if (boat.getX() == 600) {
                    obj.setToX(-300);
                    obj.setNode(boat);
                    if (farmerOnBoat == 1) {
                        TranslateTransition objFarmer = new TranslateTransition();
                        objFarmer.setDuration(Duration.seconds(1));
                        objFarmer.setByX(-300);
                        objFarmer.setNode(farmer);
                        objFarmer.play();
                    }
                    if (plantOnBoat == 1) {
                        TranslateTransition objPlant = new TranslateTransition();
                        objPlant.setDuration(Duration.seconds(1));
                        objPlant.setByX(-300);
                        objPlant.setNode(plant);
                        objPlant.play();
                    }
                    if (sheepOnBoat == 1) {
                        TranslateTransition objSheep = new TranslateTransition();
                        objSheep.setDuration(Duration.seconds(1));
                        objSheep.setByX(-300);
                        objSheep.setNode(sheep);
                        objSheep.play();
                        //boatOneLeft=true;
                    }
                    obj.play();
                    flag = 1;
                }
                left.setDisable(true);
                right.setDisable(false);

            };
        });
        right.setOnAction(e -> {
            if(controller.canMove(boatRiders,fromRightToLeft)) {
                controller.doMove(boatRiders, fromRightToLeft);
                fromRightToLeft = true;//
                TranslateTransition obj = new TranslateTransition();
                obj.setDuration(Duration.seconds(1));
                if (flag == 1) {
                    if (farmerOnBoat == 1) {
                        TranslateTransition objFarmer = new TranslateTransition();
                        objFarmer.setDuration(Duration.seconds(1));
                        objFarmer.setByX(300);
                        objFarmer.setNode(farmer);
                        objFarmer.play();
                    }
                    if (plantOnBoat == 1) {
                        TranslateTransition objPlant = new TranslateTransition();
                        objPlant.setDuration(Duration.seconds(1));
                        objPlant.setByX(300);
                        objPlant.setNode(plant);
                        objPlant.play();
                    }
                    if (sheepOnBoat == 1) {
                        TranslateTransition objSheep = new TranslateTransition();
                        objSheep.setDuration(Duration.seconds(1));
                        objSheep.setByX(300);
                        objSheep.setNode(sheep);
                        objSheep.play();
                        //boatOneLeft=false;
                    }
                    obj.setByX(300);
                    obj.setNode(boat);
                    obj.play();
                    flag = 0;
                }
                left.setDisable(false);
                right.setDisable(true);
            } ;
        });

        undo.setOnAction(e->{
        	if(controller.canUndo()) {
        		controller.undo();
        		if(fromRightToLeft==true)	{
        			fromRightToLeft = false;
        			right.setDisable(false);
                    left.setDisable(true);
                    TranslateTransition obj = new TranslateTransition();

                    obj.setDuration(Duration.seconds(1));
                    if (boat.getX() == 600) {
                        obj.setToX(-300);
                        obj.setNode(boat);
                      if(flag==0) {
                        if (farmerOnBoat == 1) {
                            TranslateTransition objFarmer = new TranslateTransition();
                            objFarmer.setDuration(Duration.seconds(1));
                            objFarmer.setByX(-300);
                            objFarmer.setNode(farmer);
                            objFarmer.play();
                        }
                        if (plantOnBoat == 1) {
                            TranslateTransition objPlant = new TranslateTransition();
                            objPlant.setDuration(Duration.seconds(1));
                            objPlant.setByX(-300);
                            objPlant.setNode(plant);
                            objPlant.play();
                        }
                        if (sheepOnBoat == 1) {
                            TranslateTransition objSheep = new TranslateTransition();
                            objSheep.setDuration(Duration.seconds(1));
                            objSheep.setByX(-300);
                            objSheep.setNode(sheep);
                            objSheep.play();
                            //boatOneLeft=true;
                        }
                        obj.play();
                        flag = 1;
                        }
                    }
        		
        		}
               else {
            	   fromRightToLeft = true;
            	   left.setDisable(false);
                   right.setDisable(true);
                   TranslateTransition obj = new TranslateTransition();
                   obj.setDuration(Duration.seconds(1));
                   if (flag == 1) {
                       if (farmerOnBoat == 1) {
                           TranslateTransition objFarmer = new TranslateTransition();
                           objFarmer.setDuration(Duration.seconds(1));
                           objFarmer.setByX(300);
                           objFarmer.setNode(farmer);
                           objFarmer.play();
                       }
                       if (plantOnBoat == 1) {
                           TranslateTransition objPlant = new TranslateTransition();
                           objPlant.setDuration(Duration.seconds(1));
                           objPlant.setByX(300);
                           objPlant.setNode(plant);
                           objPlant.play();
                       }
                       if (sheepOnBoat == 1) {
                           TranslateTransition objSheep = new TranslateTransition();
                           objSheep.setDuration(Duration.seconds(1));
                           objSheep.setByX(300);
                           objSheep.setNode(sheep);
                           objSheep.play();
                           //boatOneLeft=false;
                       }
                       obj.setByX(300);
                       obj.setNode(boat);
                       obj.play();
                       flag = 0;
                   }
               }
        		}

        });
        
        redo.setOnAction(e->{
        		if(controller.canRedo()) {
        			controller.redo();
        			
        			if(fromRightToLeft==true)	{
            			fromRightToLeft = false;
            			right.setDisable(false);
                        left.setDisable(true);
                        TranslateTransition obj = new TranslateTransition();

                        obj.setDuration(Duration.seconds(1));
                        if (boat.getX() == 600) {
                            obj.setToX(-300);
                            obj.setNode(boat);
                          if(flag==0) {
                            if (farmerOnBoat == 1) {
                                TranslateTransition objFarmer = new TranslateTransition();
                                objFarmer.setDuration(Duration.seconds(1));
                                objFarmer.setByX(-300);
                                objFarmer.setNode(farmer);
                                objFarmer.play();
                            }
                            if (plantOnBoat == 1) {
                                TranslateTransition objPlant = new TranslateTransition();
                                objPlant.setDuration(Duration.seconds(1));
                                objPlant.setByX(-300);
                                objPlant.setNode(plant);
                                objPlant.play();
                            }
                            if (sheepOnBoat == 1) {
                                TranslateTransition objSheep = new TranslateTransition();
                                objSheep.setDuration(Duration.seconds(1));
                                objSheep.setByX(-300);
                                objSheep.setNode(sheep);
                                objSheep.play();
                                //boatOneLeft=true;
                            }
                            obj.play();
                            flag = 1;
                            }
                        }
            		
            		}
                   else {
                	   fromRightToLeft = true;
                	   left.setDisable(false);
                       right.setDisable(true);
                       TranslateTransition obj = new TranslateTransition();
                       obj.setDuration(Duration.seconds(1));
                       if (flag == 1) {
                           if (farmerOnBoat == 1) {
                               TranslateTransition objFarmer = new TranslateTransition();
                               objFarmer.setDuration(Duration.seconds(1));
                               objFarmer.setByX(300);
                               objFarmer.setNode(farmer);
                               objFarmer.play();
                           }
                           if (plantOnBoat == 1) {
                               TranslateTransition objPlant = new TranslateTransition();
                               objPlant.setDuration(Duration.seconds(1));
                               objPlant.setByX(300);
                               objPlant.setNode(plant);
                               objPlant.play();
                           }
                           if (sheepOnBoat == 1) {
                               TranslateTransition objSheep = new TranslateTransition();
                               objSheep.setDuration(Duration.seconds(1));
                               objSheep.setByX(300);
                               objSheep.setNode(sheep);
                               objSheep.play();
                               //boatOneLeft=false;
                           }
                           obj.setByX(300);
                           obj.setNode(boat);
                           obj.play();
                           flag = 0;
                       }
                   }
            		}
        });
        gp.getChildren().addAll(pane, hb);
         scene = new Scene(gp, 1080, 720);


        farmer.setOnMouseClicked(e -> {
    		TranslateTransition obj = new TranslateTransition();
    		obj.setDuration(Duration.seconds(1));
    			if(farmerOnBoat==0&&flag==0)		//BOAT ON RIGHT AND FARMER ON RIGHT
    			{   boatRiders.add(farmerObj);
//    				crossersUndo.push(farmerObj);///////
//    			boatRiders.remove(boatRiders.get)
    				obj.setToX(-240);
    				obj.setToY(-20);
    				obj.setNode(farmer);
    				obj.play();	
    				farmerOnBoat=1;
    				counter++;
    			}
    			else if(farmerOnBoat==1&&flag==0)	//BOAT ON RIGHT AND FARMER ON BOAT 
    			{   boatRiders.remove(farmerObj);
    				
    					obj.setToX(5);
    					obj.setToY(0);
    					obj.setNode(farmer);
    					obj.play();	
    					farmerOnBoat=0;
    					counter--;
    			}
    			else if(farmerOnBoat==0&&flag==1)	//BOAT ON LEFT AND FARMER ON LEFT
    			{   boatRiders.add(farmerObj);
    				obj.setToX(-530);
    				obj.setToY(-20);
    				obj.setNode(farmer);
    				obj.play();	
    				farmerOnBoat=1;
    			}
    			else if(farmerOnBoat==1&&flag==1)	//BOAT ON LEFT AND FARMER ON BOAT 
    			{       boatRiders.remove(farmerObj);
    					obj.setToX(-700);
    					obj.setToY(0);
    					obj.setNode(farmer);
    					obj.play();	
    					farmerOnBoat=0;
    					counter--;
    			}
    	});
        plant.setOnMouseClicked(e -> {
    		TranslateTransition obj = new TranslateTransition();
    		obj.setDuration(Duration.seconds(1));
    		
    			if(plantOnBoat==0&&flag==0)		//BOAT ON RIGHT AND FARMER ON RIGHT
    			{   boatRiders.add(plantObj);
    				obj.setToX(-210);
    				obj.setToY(-20);
    				obj.setNode(plant);
    				obj.play();	
    				plantOnBoat=1;
    				counter++;
    			}
    			else if(plantOnBoat==1&&flag==0)	//BOAT ON RIGHT AND FARMER ON BOAT 
    			{ boatRiders.remove(plantObj);
    					obj.setToX(5);
    					obj.setToY(0);
    					obj.setNode(plant);
    					obj.play();	
    					plantOnBoat=0;
    					counter--;
    			}
    			else if(plantOnBoat==0&&flag==1)	//BOAT ON LEFT AND FARMER ON LEFT
    			{ boatRiders.add(plantObj);
    				obj.setToX(-500);
    				obj.setToY(-20);
    				obj.setNode(plant);
    				obj.play();	
    				plantOnBoat=1;
    				counter++;
    			}
    			else if(plantOnBoat==1&&flag==1)	//BOAT ON LEFT AND FARMER ON BOAT 
    			{    boatRiders.remove(plantObj);
    					obj.setToX(-700);
    					obj.setToY(0);
    					obj.setNode(plant);
    					obj.play();	
    					plantOnBoat=0;
    					counter--;
    			}
    		
    			
    	});
    	sheep.setOnMouseClicked(e -> {
    		TranslateTransition obj = new TranslateTransition();
    		obj.setDuration(Duration.seconds(1));
    		
    			if(sheepOnBoat==0&&flag==0)		//BOAT ON RIGHT AND FARMER ON RIGHT
    			{    boatRiders.add(sheepObj);
    				obj.setToX(-240);
    				obj.setToY(-15);
    				obj.setNode(sheep);
    				obj.play();	
    				sheepOnBoat=1;
    				counter++;
    			}
    			else if(sheepOnBoat==1&&flag==0)	//BOAT ON RIGHT AND FARMER ON BOAT 
    			{ boatRiders.remove(sheepObj);
    					obj.setToX(5);
    					obj.setToY(0);
    					obj.setNode(sheep);
    					obj.play();	
    					sheepOnBoat=0;
    					counter--;
    			}
    			else if(sheepOnBoat==0&&flag==1)	//BOAT ON LEFT AND FARMER ON LEFT
    			{    boatRiders.add(sheepObj);
    				obj.setToX(-500);
    				obj.setToY(-20);
    				obj.setNode(sheep);
    				obj.play();	
    				sheepOnBoat=1;
    				counter--;
    			}
    			else if(sheepOnBoat==1&&flag==1)	//BOAT ON LEFT AND FARMER ON BOAT 
    			{        boatRiders.remove(sheepObj);
    					obj.setToX(-700);
    					obj.setToY(0);
    					obj.setNode(sheep);
    					obj.play();	
    					sheepOnBoat=0;
    					counter--;
    			}
    		
    			
    	});
        
       
    }

	public Scene getScene() {
		return scene;
	}

	public void setMainmenu(MainMenu mainmenu) {
		this.mainmenu = mainmenu;
	}


  
}

