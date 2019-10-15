package MyWork;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
// javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.io.File;
import javafx.scene.layout.StackPane;
import javafx.scene.media.*;

public class StoryOneGui {
	Controller controller = new Controller();
	ICrossingStrategy storyone = new StoryOne();
	ArrayList<ICrosser> boatRiders = new ArrayList<>();
	ICrosser farmerObj;
	ICrosser wolfObj;
	ICrosser sheepObj;
	ICrosser plantObj;
	List<ICrosser> leftCrossers = new ArrayList<>();
	List<ICrosser> rightCrossers = new ArrayList<ICrosser>();
	Stage stage;
	Scene scene;
	MainMenu mainmenu;
	int flag = 0;
	int farmerOnBoat = 0;
	int plantOnBoat = 0;
	int sheepOnBoat = 0;
	int wolfOnBoat = 0;
	int counter = 0;
	boolean boatOneLeft = false;
	boolean sheepOnLeft = false;
	boolean plantOnLeft = false;
	boolean farmerOnLeft = false;
	boolean wolfOnLeft = false;
	boolean fromRightToLeft = true;

	VBox farmer;
	VBox plant;
	VBox sheep;
	VBox wolf;
	ImageView boat;
	Button right;
	Button left;
	public StoryOneGui(Stage stage) {

		this.stage = stage;
	}

	public void prepareScene() {
		controller.newGame(storyone);
		
		farmer = new VBox();
		plant = new VBox();
		sheep = new VBox();
		wolf = new VBox();

		ICrosser farmerObj = controller.getCrossersOnLeftBank().get(0);
		ICrosser wolfObj = controller.getCrossersOnLeftBank().get(1);
		ICrosser sheepObj = controller.getCrossersOnLeftBank().get(2);
		ICrosser plantObj = controller.getCrossersOnLeftBank().get(3);

		Image rightImg = new Image("file:right.png");
		ImageView rightView = new ImageView(rightImg);
		Image leftImg = new Image("file:left.png");
		ImageView leftView = new ImageView(leftImg);
		Button undo = new Button("Undo");
		right = new Button();
		left = new Button();
		Button solve = new Button("Solve");
		Button reset = new Button("Reset");
		Button save = new Button("Save");
		Button load = new Button("Load");
		Button redo= new Button("Redo");
		HBox hb = new HBox(left, right, solve, reset, save, load,undo,redo);

		Label score = new Label();
		score.setLayoutX(0);
		score.setLayoutY(600);
		score.setFont(new Font("Arial", 40));
		score.setText("SCORE\n  " + controller.getNumberOfSails());

		rightView.setFitWidth(30);
		rightView.setFitHeight(30);
		leftView.setFitWidth(30);
		leftView.setFitHeight(30);
		right.setGraphic(rightView);
		left.setGraphic(leftView);

		Group gp = new Group();

		Image boatImg = new Image("file:boat.png", 200, 200, false, false);
		boat = new ImageView(boatImg);

		Image worldImg = new Image("file:World.png", 0, 0, false, false);
		ImageView background = new ImageView(worldImg);
		Image farmerImg = SwingFXUtils.toFXImage(farmerObj.getImage()[0], null);
		ImageView farmeriv = new ImageView(farmerImg);
		Image plantImg = SwingFXUtils.toFXImage(plantObj.getImage()[0], null);
		ImageView plantiv = new ImageView(plantImg);
		Image sheepImg = SwingFXUtils.toFXImage(sheepObj.getImage()[0], null);
		ImageView sheepiv = new ImageView(sheepImg);
		Image wolfImg = SwingFXUtils.toFXImage(wolfObj.getImage()[0], null);
		ImageView wolfiv = new ImageView(wolfImg);

		plantiv.setFitWidth(60);
		sheepiv.setFitWidth(50);
		wolfiv.setFitHeight(140);
		wolfiv.setFitWidth(120);
		farmeriv.setFitWidth(100);
		farmeriv.setFitHeight(130);

		Label farmerinfo = new Label(farmerObj.getLabelTOBeShown());
		farmerinfo.setFont(new Font("Verdana", 20));
		farmer.getChildren().addAll(farmerinfo, farmeriv);

		// VBox wolf = new VBox();
		Label wolfinfo = new Label("       " + wolfObj.getLabelTOBeShown());
		wolfinfo.setFont(new Font("Verdana", 20));
		wolf.getChildren().addAll(wolfinfo, wolfiv);

		// VBox sheep = new VBox();
		Label sheepinfo = new Label("  " + sheepObj.getLabelTOBeShown());
		sheepinfo.setFont(new Font("Verdana", 20));
		sheep.getChildren().addAll(sheepinfo, sheepiv);

		// VBox plant = new VBox();
		Label plantinfo = new Label(plantObj.getLabelTOBeShown());
		plantinfo.setFont(new Font("Verdana", 20));
		plant.getChildren().addAll(plantinfo, plantiv);

		for (int i = 0; i < controller.getCrossersOnLeftBank().size(); i++) {
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown()
					.equalsIgnoreCase("Farmer")) {
				farmer.setLayoutX(800);
				farmer.setLayoutY(500);
			}
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown()
					.equalsIgnoreCase("plant")) {
				plant.setLayoutX(910);
				plant.setLayoutY(500);
			}
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown()
					.equalsIgnoreCase("sheep")) {
				sheep.setLayoutX(960);
				sheep.setLayoutY(500);
			}
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown()
					.equalsIgnoreCase("wolf")) {
				wolf.setLayoutX(990);
				wolf.setLayoutY(500);
			}
		}
		for (int j = 0; j < controller.getCrossersOnRightBank().size(); j++) {
			if (controller.getCrossersOnLeftBank().get(j).getLabelTOBeShown()
					.equalsIgnoreCase("Farmer")) {
				farmer.setLayoutX(300);
				farmer.setLayoutY(500);
			}
			if (controller.getCrossersOnRightBank().get(j).getLabelTOBeShown()
					.equalsIgnoreCase("plant")) {
				plant.setLayoutX(210);
				plant.setLayoutY(500);
				plantOnLeft = true;
				plantOnBoat = 0;
			}
			if (controller.getCrossersOnRightBank().get(j).getLabelTOBeShown()
					.equalsIgnoreCase("sheep")) {
				sheep.setLayoutX(460);
				sheep.setLayoutY(500);
			}
			if (controller.getCrossersOnRightBank().get(j).getLabelTOBeShown()
					.equalsIgnoreCase("wolf")) {
				wolf.setLayoutX(490);
				wolf.setLayoutY(500);
			}
		}

		boat.setX(600);
		boat.setY(530);

		Pane pane = new Pane();
		pane.getChildren().addAll(background, boat, farmer, plant, sheep, wolf);

		right.setDisable(true);

		left.setOnAction(e -> {

			if (controller.canMove(boatRiders, fromRightToLeft)) {
				controller.doMove(boatRiders, fromRightToLeft);
				fromRightToLeft = false;
				score.setText("SCORE\n     " + controller.getNumberOfSails());
				if (controller.gameover(boatRiders)) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Game Over!!");
					alert.setHeaderText("GOOD LUCK NEXT TIME");
					alert.showAndWait();
					stage.close();
				}
				if (controller.winner()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("winner!!");
					alert.setHeaderText("WINNER WINNER CHICKEN DINNER");
					alert.showAndWait();
					stage.close();
				}
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
						farmerOnLeft = true;
					}
					if (plantOnBoat == 1) {
						TranslateTransition objPlant = new TranslateTransition();
						objPlant.setDuration(Duration.seconds(1));
						objPlant.setByX(-300);
						objPlant.setNode(plant);
						objPlant.play();
						plantOnLeft = true;
					}
					if (sheepOnBoat == 1) {
						TranslateTransition objSheep = new TranslateTransition();
						objSheep.setDuration(Duration.seconds(1));
						objSheep.setByX(-300);
						objSheep.setNode(sheep);
						objSheep.play();
						sheepOnLeft = true;
						// boatOneLeft=true;
					}
					if (wolfOnBoat == 1) {
						TranslateTransition objWolf = new TranslateTransition();
						objWolf.setDuration(Duration.seconds(1));
						objWolf.setByX(-300);
						objWolf.setNode(wolf);
						objWolf.play();
						wolfOnLeft = true;
					}
					obj.play();
					flag = 1;

				}
				left.setDisable(true);
				right.setDisable(false);

			}
			;
		});
		right.setOnAction(e -> {
			if (controller.canMove(boatRiders, fromRightToLeft)) {
				controller.doMove(boatRiders, fromRightToLeft);
				fromRightToLeft = true;
				score.setText("SCORE\n     " + controller.getNumberOfSails());
				if (controller.gameover(boatRiders)) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Game Over!!");
					alert.setHeaderText("GOOD LUCK NEXT TIME");
					alert.showAndWait();
					stage.setScene(mainmenu.getScene());
				}
				if (controller.winner()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("winner!!");
					alert.setHeaderText("WINNER WINNER CHICKEN DINNER");
					alert.showAndWait();
					stage.setScene(mainmenu.getScene());

				}
				TranslateTransition obj = new TranslateTransition();
				obj.setDuration(Duration.seconds(1));
				if (controller.isBoatOnTheLeftBank()) {
					if (farmerOnBoat == 1) {
						TranslateTransition objFarmer = new TranslateTransition();
						objFarmer.setDuration(Duration.seconds(1));
						objFarmer.setByX(300);
						objFarmer.setNode(farmer);
						objFarmer.play();
						farmerOnLeft = false;
					}
					if (plantOnBoat == 1) {
						TranslateTransition objPlant = new TranslateTransition();
						objPlant.setDuration(Duration.seconds(1));
						objPlant.setByX(300);
						objPlant.setNode(plant);
						objPlant.play();
						plantOnLeft = false;
					}
					if (sheepOnBoat == 1) {
						TranslateTransition objSheep = new TranslateTransition();
						objSheep.setDuration(Duration.seconds(1));
						objSheep.setByX(300);
						objSheep.setNode(sheep);
						objSheep.play();
						sheepOnLeft = false;
						// boatOneLeft=false;
					}
					if (wolfOnBoat == 1) {

						TranslateTransition objWolf = new TranslateTransition();
						objWolf.setDuration(Duration.seconds(1));
						objWolf.setByX(300);
						objWolf.setNode(wolf);
						objWolf.play();
						wolfOnLeft = false;
					}
					obj.setByX(300);
					obj.setNode(boat);
					obj.play();
					flag = 0;

				}
				left.setDisable(false);
				right.setDisable(true);

			}
			;
		});
		undo.setOnAction(e -> {
			if (controller.canUndo()) {
				controller.undo();
			if(fromRightToLeft==false) {
				fromRightToLeft=true;
				left.setDisable(false);
				right.setDisable(true);
				score.setText("SCORE\n     " + controller.getNumberOfSails());
				if (controller.gameover(boatRiders)) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Game Over!!");
					alert.setHeaderText("GOOD LUCK NEXT TIME");
					alert.showAndWait();
					stage.setScene(mainmenu.getScene());
				}
				if (controller.winner()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("winner!!");
					alert.setHeaderText("WINNER WINNER CHICKEN DINNER");
					alert.showAndWait();
					stage.setScene(mainmenu.getScene());

				}
				TranslateTransition obj = new TranslateTransition();
				obj.setDuration(Duration.seconds(1));
				if (controller.isBoatOnTheLeftBank()) {
					if (farmerOnBoat == 1) {
						TranslateTransition objFarmer = new TranslateTransition();
						objFarmer.setDuration(Duration.seconds(1));
						objFarmer.setByX(300);
						objFarmer.setNode(farmer);
						objFarmer.play();
						farmerOnLeft = false;
					}
					if (plantOnBoat == 1) {
						TranslateTransition objPlant = new TranslateTransition();
						objPlant.setDuration(Duration.seconds(1));
						objPlant.setByX(300);
						objPlant.setNode(plant);
						objPlant.play();
						plantOnLeft = false;
					}
					if (sheepOnBoat == 1) {
						TranslateTransition objSheep = new TranslateTransition();
						objSheep.setDuration(Duration.seconds(1));
						objSheep.setByX(300);
						objSheep.setNode(sheep);
						objSheep.play();
						sheepOnLeft = false;
						// boatOneLeft=false;
					}
					if (wolfOnBoat == 1) {

						TranslateTransition objWolf = new TranslateTransition();
						objWolf.setDuration(Duration.seconds(1));
						objWolf.setByX(300);
						objWolf.setNode(wolf);
						objWolf.play();
						wolfOnLeft = false;
					}
					obj.setByX(300);
					obj.setNode(boat);
					obj.play();
					flag = 0;

				}

			}
			else {
				fromRightToLeft=false;
				left.setDisable(true);
				right.setDisable(false);
				score.setText("SCORE\n     " + controller.getNumberOfSails());
				if (controller.gameover(boatRiders)) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Game Over!!");
					alert.setHeaderText("GOOD LUCK NEXT TIME");
					alert.showAndWait();
					stage.close();
				}
				if (controller.winner()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("winner!!");
					alert.setHeaderText("WINNER WINNER CHICKEN DINNER");
					alert.showAndWait();
					stage.close();
				}
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
						farmerOnLeft = true;
					}
					if (plantOnBoat == 1) {
						TranslateTransition objPlant = new TranslateTransition();
						objPlant.setDuration(Duration.seconds(1));
						objPlant.setByX(-300);
						objPlant.setNode(plant);
						objPlant.play();
						plantOnLeft = true;
					}
					if (sheepOnBoat == 1) {
						TranslateTransition objSheep = new TranslateTransition();
						objSheep.setDuration(Duration.seconds(1));
						objSheep.setByX(-300);
						objSheep.setNode(sheep);
						objSheep.play();
						sheepOnLeft = true;
						// boatOneLeft=true;
					}
					if (wolfOnBoat == 1) {
						TranslateTransition objWolf = new TranslateTransition();
						objWolf.setDuration(Duration.seconds(1));
						objWolf.setByX(-300);
						objWolf.setNode(wolf);
						objWolf.play();
						wolfOnLeft = true;
					}
					obj.play();
					flag = 1;

				}
			}
			
			}
		});
redo.setOnAction(e->{
	if (controller.canRedo()) {
		controller.redo();
	if(fromRightToLeft==false) {
		fromRightToLeft=true;
		left.setDisable(false);
		right.setDisable(true);
		score.setText("SCORE\n     " + controller.getNumberOfSails());
		if (controller.gameover(boatRiders)) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Game Over!!");
			alert.setHeaderText("GOOD LUCK NEXT TIME");
			alert.showAndWait();
			stage.setScene(mainmenu.getScene());
		}
		if (controller.winner()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("winner!!");
			alert.setHeaderText("WINNER WINNER CHICKEN DINNER");
			alert.showAndWait();
			stage.setScene(mainmenu.getScene());

		}
		TranslateTransition obj = new TranslateTransition();
		obj.setDuration(Duration.seconds(1));
		if (controller.isBoatOnTheLeftBank()) {
			if (farmerOnBoat == 1) {
				TranslateTransition objFarmer = new TranslateTransition();
				objFarmer.setDuration(Duration.seconds(1));
				objFarmer.setByX(300);
				objFarmer.setNode(farmer);
				objFarmer.play();
				farmerOnLeft = false;
			}
			if (plantOnBoat == 1) {
				TranslateTransition objPlant = new TranslateTransition();
				objPlant.setDuration(Duration.seconds(1));
				objPlant.setByX(300);
				objPlant.setNode(plant);
				objPlant.play();
				plantOnLeft = false;
			}
			if (sheepOnBoat == 1) {
				TranslateTransition objSheep = new TranslateTransition();
				objSheep.setDuration(Duration.seconds(1));
				objSheep.setByX(300);
				objSheep.setNode(sheep);
				objSheep.play();
				sheepOnLeft = false;
				// boatOneLeft=false;
			}
			if (wolfOnBoat == 1) {

				TranslateTransition objWolf = new TranslateTransition();
				objWolf.setDuration(Duration.seconds(1));
				objWolf.setByX(300);
				objWolf.setNode(wolf);
				objWolf.play();
				wolfOnLeft = false;
			}
			obj.setByX(300);
			obj.setNode(boat);
			obj.play();
			flag = 0;

		}

	}
	else {
		fromRightToLeft=false;
		left.setDisable(true);
		right.setDisable(false);
		score.setText("SCORE\n     " + controller.getNumberOfSails());
		if (controller.gameover(boatRiders)) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Game Over!!");
			alert.setHeaderText("GOOD LUCK NEXT TIME");
			alert.showAndWait();
			stage.close();
		}
		if (controller.winner()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("winner!!");
			alert.setHeaderText("WINNER WINNER CHICKEN DINNER");
			alert.showAndWait();
			stage.close();
		}
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
				farmerOnLeft = true;
			}
			if (plantOnBoat == 1) {
				TranslateTransition objPlant = new TranslateTransition();
				objPlant.setDuration(Duration.seconds(1));
				objPlant.setByX(-300);
				objPlant.setNode(plant);
				objPlant.play();
				plantOnLeft = true;
			}
			if (sheepOnBoat == 1) {
				TranslateTransition objSheep = new TranslateTransition();
				objSheep.setDuration(Duration.seconds(1));
				objSheep.setByX(-300);
				objSheep.setNode(sheep);
				objSheep.play();
				sheepOnLeft = true;
				// boatOneLeft=true;
			}
			if (wolfOnBoat == 1) {
				TranslateTransition objWolf = new TranslateTransition();
				objWolf.setDuration(Duration.seconds(1));
				objWolf.setByX(-300);
				objWolf.setNode(wolf);
				objWolf.play();
				wolfOnLeft = true;
			}
			obj.play();
			flag = 1;

		}
	}
	
	}
});
		gp.getChildren().addAll(pane, hb, score);
		scene = new Scene(gp, 1080, 720);

		farmer.setOnMouseClicked(e -> {
			TranslateTransition obj = new TranslateTransition();
			obj.setDuration(Duration.seconds(1));
			if (!farmerOnLeft && farmerOnBoat == 0 && controller.isBoatOnTheLeftBank()) // BOAT ON RIGHT AND FARMER ON
																						// RIGHT
			{
				boatRiders.add(farmerObj);
				obj.setToX(-220);
				obj.setToY(-20);
				obj.setNode(farmer);
				obj.play();
				farmerOnBoat = 1;
				counter++;

			} else if (farmerOnBoat == 1 && controller.isBoatOnTheLeftBank()) // BOAT ON RIGHT AND FARMER ON BOAT
			{
				boatRiders.remove(farmerObj);
				obj.setToX(5);
				obj.setToY(0);
				obj.setNode(farmer);
				obj.play();
				farmerOnBoat = 0;
				counter--;
			}
			else if (farmerOnLeft && farmerOnBoat == 0 && !controller.isBoatOnTheLeftBank()) // BOAT ON LEFT AND																// FARMER ON LEFT
			{
				boatRiders.add(farmerObj);
				obj.setToX(-530);
				obj.setToY(-20);
				obj.setNode(farmer);
				obj.play();
				farmerOnBoat = 1;
			} else if (farmerOnBoat == 1 && !controller.isBoatOnTheLeftBank()) // BOAT ON LEFT AND FARMER ON BOAT
			{
				boatRiders.remove(farmerObj);
				obj.setToX(-700);
				obj.setToY(0);
				obj.setNode(farmer);
				obj.play();
				farmerOnBoat = 0;
				counter--;
			}
		});
		plant.setOnMouseClicked(e -> {
			TranslateTransition obj = new TranslateTransition();
			obj.setDuration(Duration.seconds(1));
			if (!plantOnLeft && plantOnBoat == 0
					&& controller.isBoatOnTheLeftBank()) {
				boatRiders.add(plantObj);
				obj.setToX(-210);
				obj.setToY(-20);
				obj.setNode(plant);
				obj.play();
				plantOnBoat = 1;
				counter++;
			} else if (plantOnBoat == 1 && controller.isBoatOnTheLeftBank()) {
				boatRiders.remove(plantObj);
				obj.setToX(5);
				obj.setToY(0);
				obj.setNode(plant);
				obj.play();
				plantOnBoat = 0;
				counter--;
			} else if (plantOnLeft && plantOnBoat == 0
					&& !controller.isBoatOnTheLeftBank())
			{
				boatRiders.add(plantObj);
				obj.setToX(-500);
				obj.setToY(-20);
				obj.setNode(plant);
				obj.play();
				plantOnBoat = 1;
				counter++;
			} else if (plantOnBoat == 1 && !controller.isBoatOnTheLeftBank()) {
				boatRiders.remove(plantObj);
				obj.setToX(-700);
				obj.setToY(0);
				obj.setNode(plant);
				obj.play();
				plantOnBoat = 0;
				counter--;
			}
		});
		sheep.setOnMouseClicked(e -> {
			TranslateTransition obj = new TranslateTransition();
			obj.setDuration(Duration.seconds(1));

			if (!sheepOnLeft && sheepOnBoat == 0
					&& controller.isBoatOnTheLeftBank()) {
				boatRiders.add(sheepObj);
				obj.setToX(-240);
				obj.setToY(-15);
				obj.setNode(sheep);
				obj.play();
				sheepOnBoat = 1;
				counter++;
			} else if (sheepOnBoat == 1 && controller.isBoatOnTheLeftBank()) {
				boatRiders.remove(sheepObj);
				obj.setToX(5);
				obj.setToY(0);
				obj.setNode(sheep);
				obj.play();
				sheepOnBoat = 0;
				counter--;
			} else if (sheepOnLeft && sheepOnBoat == 0
					&& !controller.isBoatOnTheLeftBank()) {
				boatRiders.add(sheepObj);
				obj.setToX(-500);
				obj.setToY(-20);
				obj.setNode(sheep);
				obj.play();
				sheepOnBoat = 1;
				counter--;
			} else if (sheepOnBoat == 1 && !controller.isBoatOnTheLeftBank()) {
				boatRiders.remove(sheepObj);
				obj.setToX(-700);
				obj.setToY(0);
				obj.setNode(sheep);
				obj.play();
				sheepOnBoat = 0;
				counter--;
			}
		});
		wolf.setOnMouseClicked(e -> {
			TranslateTransition obj = new TranslateTransition();
			obj.setDuration(Duration.seconds(1));
			if (!wolfOnLeft && wolfOnBoat == 0
					&& controller.isBoatOnTheLeftBank()) {
				boatRiders.add(wolfObj);
				obj.setToX(-320);
				obj.setToY(-30);
				obj.setNode(wolf);
				obj.play();
				wolfOnBoat = 1;

			} else if (wolfOnBoat == 1 && controller.isBoatOnTheLeftBank()) // ON
																			// BOAT
			{
				boatRiders.remove(wolfObj);
				obj.setToX(5);
				obj.setToY(0);
				obj.setNode(wolf);
				obj.play();
				wolfOnBoat = 0;
			} else if (wolfOnLeft && wolfOnBoat == 0
					&& !controller.isBoatOnTheLeftBank()) {
				boatRiders.add(wolfObj);
				obj.setToX(-620);
				obj.setToY(-30);
				obj.setNode(wolf);
				obj.play();
				wolfOnBoat = 1;
			} else if (wolfOnBoat == 1 && !controller.isBoatOnTheLeftBank()) {
				boatRiders.remove(wolfObj);
				obj.setToX(-950);
				obj.setToY(-10);
				obj.setNode(wolf);
				obj.play();
				wolfOnBoat = 0;
			}
		});
//		String path = "C:\\Users\\OMAR\\Desktop\\solver.mp4";
//
//		Media media = new Media(new File(path).toURI().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(media);
//		MediaView mediaView = new MediaView(mediaPlayer);
//		StackPane root = new StackPane();
//		root.getChildren().add(mediaView);
//		solve.setOnAction(e -> {
//			mediaPlayer.play();
//			mediaPlayer.setOnEndOfMedia(new Runnable() {
//				@Override
//				public void run() {
//					stage.setScene(scene);
//				}
//			});
//			stage.setScene(new Scene(root, 1080, 720));
//		});
		reset.setOnAction(e -> {
			controller.resetGame();
			controller.getCrossersOnLeftBank().clear();
			controller.getCrossersOnRightBank().clear();
			prepareScene();
			resetbool();
			stage.setScene(scene);
		});
		save.setOnAction(e -> {
			controller.saveGame();
		});
		load.setOnAction(e -> {
			resetbool();
			controller.loadGame();
			loadScene();
		});
	}
	public Scene getScene() {
		return scene;
	}

	public void setMainmenu(MainMenu mainmenu) {
		this.mainmenu = mainmenu;
	}

	public void resetbool() {
		flag = 0;
		farmerOnBoat = 0;
		plantOnBoat = 0;
		sheepOnBoat = 0;
		wolfOnBoat = 0;
		counter = 0;
		boatOneLeft = false;
		sheepOnLeft = false;
		plantOnLeft = false;
		farmerOnLeft = false;
		wolfOnLeft = false;
		fromRightToLeft = true;
	}
	public void loadScene() {
		farmer.relocate(0, 0);
		sheep.relocate(0, 0);
		plant.relocate(0, 0);
		wolf.relocate(0, 0);
		for (int i = 0; i < controller.getCrossersOnLeftBank().size(); i++) {
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown()
					.equalsIgnoreCase("Farmer")) {
				farmer.setLayoutX(800);
				farmer.setLayoutY(500);
				farmerOnLeft = false;
				farmerOnBoat = 0;
			}
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown()
					.equalsIgnoreCase("plant")) {
				plant.setLayoutX(910);
				plant.setLayoutY(500);
				plantOnLeft = false;
				plantOnBoat = 0;
			}
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown()
					.equalsIgnoreCase("sheep")) {
				sheep.setLayoutX(960);
				sheep.setLayoutY(500);
				sheepOnLeft = false;
				sheepOnBoat = 0;
			}
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown()
					.equalsIgnoreCase("wolf")) {
				wolf.setLayoutX(990);
				wolf.setLayoutY(500);
				wolfOnLeft = false;
				wolfOnBoat = 0;
			}
			right.setDisable(true);
			left.setDisable(false);
		}
		for (int j = 0; j < controller.getCrossersOnRightBank().size(); j++) {
			if (controller.getCrossersOnRightBank().get(j).getLabelTOBeShown()
					.equalsIgnoreCase("farmer")) {
				farmer.setLayoutX(100);
				farmer.setLayoutY(500);
				farmerOnLeft = true;
				farmerOnBoat = 0;
			}
			if (controller.getCrossersOnRightBank().get(j).getLabelTOBeShown()
					.equalsIgnoreCase("plant")) {
				plant.setLayoutX(170);
				plant.setLayoutY(500);
				plantOnLeft = true;
				plantOnBoat = 0;
			}
			if (controller.getCrossersOnRightBank().get(j).getLabelTOBeShown()
					.equalsIgnoreCase("sheep")) {
				sheep.setLayoutX(240);
				sheep.setLayoutY(500);
				sheepOnLeft = true;
				sheepOnBoat = 0;
			}
			if (controller.getCrossersOnRightBank().get(j).getLabelTOBeShown()
					.equalsIgnoreCase("wolf")) {
				wolf.setLayoutX(150);
				wolf.setLayoutY(500);
				wolfOnLeft = true;
				wolfOnBoat = 0;
			}
				if (!controller.isBoatOnTheLeftBank()) {		//FALSE ------ RIGHT IN LOGIC --- LEFT IN GUI
					boat.setX(300);
					boat.setY(530);
				}
				else
				{
					boat.setX(600);
					boat.setY(530);
				}
				right.setDisable(false);
				left.setDisable(true);
	
		}
	}
}