package MyWork;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
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
import MyWork.Controller;
import MyWork.ICrossingStrategy;
import MyWork.StoryTwo;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class StoryTwoGui {
	Controller controller = new Controller();
	ICrossingStrategy storytwo = new StoryTwo();
	ArrayList<ICrosser> boatRiders = new ArrayList<>();
	List<ICrosser> initialCrossers;
	ICrosser farmerObj;
	ICrosser farmer2Obj;
	ICrosser farmer3Obj;
	ICrosser farmer4Obj;
	ICrosser sheepObj;

	MainMenu mainmenu;
	Stage stage;
	Scene scene;

	public StoryTwoGui(Stage stage) {
		this.stage = stage;
	}

	int flag = 0;
	int farmerOnBoat = 0;
	int farmer2OnBoat = 0;
	int farmer3OnBoat = 0;
	int farmer4OnBoat = 0;
	int sheepOnBoat = 0;
	int counter = 0;
	boolean boatOneLeft = false;
	boolean sheepOnLeft = false;
	boolean fromRightToLeft = true;
	boolean farmerOneOnLeft = false;
	boolean farmerTwoOnLeft = false;
	boolean farmerThreeOnLeft = false;
	boolean farmerFourOnLeft = false;
	Button right;
	Button left;
	VBox farmer;
	VBox farmer2;
	VBox farmer3;
	VBox farmer4;
	VBox sheep;
	ImageView boat;
	ImageView background;

	public void prepareScene() {
		controller.newGame(storytwo);
		initialCrossers = controller.getCrossersOnLeftBank();

		ICrosser farmerObj = initialCrossers.get(0);
		ICrosser farmer2Obj = initialCrossers.get(1);
		ICrosser farmer3Obj = initialCrossers.get(2);
		ICrosser farmer4Obj = initialCrossers.get(3);
		ICrosser sheepObj = initialCrossers.get(4);
		Image rightImg = new Image("file:right.png");
		ImageView rightView = new ImageView(rightImg);
		Image leftImg = new Image("file:left.png");
		ImageView leftView = new ImageView(leftImg);

		right = new Button();
		left = new Button();
		Button solve = new Button("Solve");
		Button reset = new Button("Reset");
		Button save = new Button("Save");
		Button load = new Button("Load");
		HBox hb = new HBox(left, right, solve, reset, save, load);

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
		background = new ImageView(worldImg);
		Image farmerImg = SwingFXUtils.toFXImage(farmerObj.getImage()[0], null);

		ImageView farmeriv = new ImageView(farmerImg);
		Image farmer2Img = SwingFXUtils.toFXImage(farmerObj.getImage()[1], null);

		ImageView farmer2iv = new ImageView(farmer2Img);
		Image farmer3Img = SwingFXUtils.toFXImage(farmerObj.getImage()[2], null);
		ImageView farmer3iv = new ImageView(farmer3Img);
		Image farmer4Img = SwingFXUtils.toFXImage(farmerObj.getImage()[3], null);
		ImageView farmer4iv = new ImageView(farmer4Img);
		Image sheepImg = SwingFXUtils.toFXImage(sheepObj.getImage()[0], null);
		ImageView sheepiv = new ImageView(sheepImg);

		farmer2iv.setFitWidth(90);
		farmer2iv.setFitHeight(130);
		farmer3iv.setFitWidth(90);
		farmer3iv.setFitHeight(130);
		farmer4iv.setFitWidth(110);
		farmer4iv.setFitHeight(140);
		sheepiv.setFitWidth(50);

		farmer = new VBox();
		Label farmerinfo = new Label("\t" + farmerObj.getLabelTOBeShown());
		farmerinfo.setFont(new Font("Verdana", 20));
		farmer.getChildren().addAll(farmerinfo, farmeriv);

		farmer2 = new VBox();
		Label farmer2info = new Label(farmer2Obj.getLabelTOBeShown());
		farmer2info.setFont(new Font("Verdana", 20));
		farmer2.getChildren().addAll(farmer2info, farmer2iv);

		farmer3 = new VBox();
		Label farmer3info = new Label(farmer3Obj.getLabelTOBeShown());
		farmer3info.setFont(new Font("Verdana", 20));
		farmer3.getChildren().addAll(farmer3info, farmer3iv);

		farmer4 = new VBox();
		Label farmer4info = new Label("\t" + farmer4Obj.getLabelTOBeShown());
		farmer4info.setFont(new Font("Verdana", 20));
		farmer4.getChildren().addAll(farmer4info, farmer4iv);

		sheep = new VBox();
		Label sheepinfo = new Label(sheepObj.getLabelTOBeShown());
		sheepinfo.setFont(new Font("Verdana", 20));
		sheep.getChildren().addAll(sheepinfo, sheepiv);
		for (int i = 0; i < controller.getCrossersOnLeftBank().size(); i++) {
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown().equalsIgnoreCase("90")) {
				farmer.setLayoutX(800);
				farmer.setLayoutY(500);
			}
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown().equalsIgnoreCase("80")) {
				farmer2.setLayoutX(910);
				farmer2.setLayoutY(500);
			}
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown().equalsIgnoreCase("60")) {
				farmer3.setLayoutX(780);
				farmer3.setLayoutY(500);
			}
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown().equalsIgnoreCase("40")) {
				farmer4.setLayoutX(1000);
				farmer4.setLayoutY(500);

			}

			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown().equalsIgnoreCase("20")) {
				sheep.setLayoutX(980);
				sheep.setLayoutY(500);
			}

		}
		for (int j = 0; j < controller.getCrossersOnRightBank().size(); j++) {
			if (controller.getCrossersOnLeftBank().get(j).getLabelTOBeShown().equalsIgnoreCase("90")) {
				farmer.setLayoutX(300);
				farmer.setLayoutY(500);

			}
			if (controller.getCrossersOnLeftBank().get(j).getLabelTOBeShown().equalsIgnoreCase("80")) {
				farmer.setLayoutX(210);
				farmer.setLayoutY(500);

			}
			if (controller.getCrossersOnLeftBank().get(j).getLabelTOBeShown().equalsIgnoreCase("60")) {
				farmer.setLayoutX(170);
				farmer.setLayoutY(500);

			}
			if (controller.getCrossersOnLeftBank().get(j).getLabelTOBeShown().equalsIgnoreCase("40")) {
				farmer.setLayoutX(100);
				farmer.setLayoutY(500);

			}
			if (controller.getCrossersOnRightBank().get(j).getLabelTOBeShown().equalsIgnoreCase("20")) {
				sheep.setLayoutX(460);
				sheep.setLayoutY(500);
			}

		}

		boat.setX(600);
		boat.setY(530);

		Pane pane = new Pane();
		pane.getChildren().addAll(background, boat, farmer, farmer2, farmer3, farmer4, sheep);

		right.setDisable(true);

		left.setOnAction(e -> {
			if (controller.canMove(boatRiders, fromRightToLeft)) {
				controller.doMove(boatRiders, fromRightToLeft);
				score.setText("SCORE\n  " + controller.getNumberOfSails());
				fromRightToLeft = false;
				if (controller.gameover(boatRiders)) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Game Over!!");
					alert.setHeaderText("GOOD LUCK NEXT TIME");
					alert.showAndWait();
					stage.close();
					// stage.setScene(mainmenu.getScene());

				}
				if (controller.winner()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("winner!!");
					alert.setHeaderText("WINNER WINNER CHICKEN DINNER");
					alert.showAndWait();
					stage.close();
					// stage.setScene(mainmenu.getScene());

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
						farmerOneOnLeft = true;
					}
					if (farmer2OnBoat == 1) {
						TranslateTransition objFarmer2 = new TranslateTransition();
						objFarmer2.setDuration(Duration.seconds(1));
						objFarmer2.setByX(-300);
						objFarmer2.setNode(farmer2);
						objFarmer2.play();
						farmerTwoOnLeft = true;
					}
					if (farmer3OnBoat == 1) {
						TranslateTransition objFarmer3 = new TranslateTransition();
						objFarmer3.setDuration(Duration.seconds(1));
						objFarmer3.setByX(-300);
						objFarmer3.setNode(farmer3);
						objFarmer3.play();
						farmerThreeOnLeft = true;
					}
					if (farmer4OnBoat == 1) {
						TranslateTransition objFarmer4 = new TranslateTransition();
						objFarmer4.setDuration(Duration.seconds(1));
						objFarmer4.setByX(-300);
						objFarmer4.setNode(farmer4);
						objFarmer4.play();
						farmerFourOnLeft = true;
					}
					if (sheepOnBoat == 1) {
						TranslateTransition objSheep = new TranslateTransition();
						objSheep.setDuration(Duration.seconds(1));
						objSheep.setByX(-300);
						objSheep.setNode(sheep);
						objSheep.play();
						// boatOneLeft=true;
						sheepOnLeft = true;
					}
					obj.play();
					flag = 1;
				}
				left.setDisable(true);
				right.setDisable(false);

				;
			}
		});
		right.setOnAction(e -> {
			if (controller.canMove(boatRiders, fromRightToLeft)) {
				controller.doMove(boatRiders, fromRightToLeft);
				fromRightToLeft = true;
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
				score.setText("SCORE\n  " + controller.getNumberOfSails());

				TranslateTransition obj = new TranslateTransition();
				obj.setDuration(Duration.seconds(1));
				if (flag == 1) {
					if (farmerOnBoat == 1) {
						TranslateTransition objFarmer = new TranslateTransition();
						objFarmer.setDuration(Duration.seconds(1));
						objFarmer.setByX(300);
						objFarmer.setNode(farmer);
						objFarmer.play();
						farmerOneOnLeft = false;
					}
					if (farmer2OnBoat == 1) {
						TranslateTransition objFarmer2 = new TranslateTransition();
						objFarmer2.setDuration(Duration.seconds(1));
						objFarmer2.setByX(300);
						objFarmer2.setNode(farmer2);
						objFarmer2.play();
						farmerTwoOnLeft = false;
					}
					if (farmer3OnBoat == 1) {
						TranslateTransition objFarmer3 = new TranslateTransition();
						objFarmer3.setDuration(Duration.seconds(1));
						objFarmer3.setByX(300);
						objFarmer3.setNode(farmer3);
						objFarmer3.play();
						farmerThreeOnLeft = false;
					}
					if (farmer4OnBoat == 1) {
						TranslateTransition objFarmer4 = new TranslateTransition();
						objFarmer4.setDuration(Duration.seconds(1));
						objFarmer4.setByX(300);
						objFarmer4.setNode(farmer4);
						objFarmer4.play();
						farmerFourOnLeft = false;
					}
					if (sheepOnBoat == 1) {
						TranslateTransition objSheep = new TranslateTransition();
						objSheep.setDuration(Duration.seconds(1));
						objSheep.setByX(300);
						objSheep.setNode(sheep);
						objSheep.play();
						// boatOneLeft=false;
						sheepOnLeft = false;

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

		gp.getChildren().addAll(pane, hb, score);

		farmer.setOnMouseClicked(e -> {
			TranslateTransition obj = new TranslateTransition();
			obj.setDuration(Duration.seconds(1));
			if (!farmerOneOnLeft && farmerOnBoat == 0 && controller.isBoatOnTheLeftBank()) // BOAT ON RIGHT AND FARMER
																							// ON RIGHT
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
			} else if (farmerOneOnLeft && farmerOnBoat == 0 && !controller.isBoatOnTheLeftBank()) // BOAT ON LEFT AND
																									// FARMER ON LEFT
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
		farmer2.setOnMouseClicked(e -> {
			TranslateTransition obj = new TranslateTransition();
			obj.setDuration(Duration.seconds(1));
			if (!farmerTwoOnLeft && farmer2OnBoat == 0 && controller.isBoatOnTheLeftBank()) // BOAT ON RIGHT AND FARMER
																							// ON RIGHT
			{
				boatRiders.add(farmer2Obj);
				obj.setToX(-220);
				obj.setToY(-20);
				obj.setNode(farmer2);
				obj.play();
				farmer2OnBoat = 1;
				counter++;

			} else if (farmer2OnBoat == 1 && controller.isBoatOnTheLeftBank()) // BOAT ON RIGHT AND FARMER ON BOAT
			{
				boatRiders.remove(farmer2Obj);
				obj.setToX(5);
				obj.setToY(0);
				obj.setNode(farmer2);
				obj.play();
				farmer2OnBoat = 0;
				counter--;
			} else if (farmerTwoOnLeft && farmer2OnBoat == 0 && !controller.isBoatOnTheLeftBank()) // BOAT ON LEFT AND
																									// FARMER ON LEFT
			{
				boatRiders.add(farmer2Obj);
				obj.setToX(-530);
				obj.setToY(-20);
				obj.setNode(farmer2);
				obj.play();
				farmer2OnBoat = 1;
			} else if (farmer2OnBoat == 1 && !controller.isBoatOnTheLeftBank()) // BOAT ON LEFT AND FARMER ON BOAT
			{
				boatRiders.remove(farmer2Obj);
				obj.setToX(-700);
				obj.setToY(0);
				obj.setNode(farmer2);
				obj.play();
				farmer2OnBoat = 0;
				counter--;
			}
		});
		farmer3.setOnMouseClicked(e -> {
			TranslateTransition obj = new TranslateTransition();
			obj.setDuration(Duration.seconds(1));
			if (!farmerThreeOnLeft && farmer3OnBoat == 0 && controller.isBoatOnTheLeftBank()) // BOAT ON RIGHT AND
																								// FARMER ON RIGHT
			{
				boatRiders.add(farmer3Obj);
				obj.setToX(-180);
				obj.setToY(-20);
				obj.setNode(farmer3);
				obj.play();
				farmer3OnBoat = 1;
				counter++;

			} else if (farmer3OnBoat == 1 && controller.isBoatOnTheLeftBank()) // BOAT ON RIGHT AND FARMER ON BOAT
			{
				boatRiders.remove(farmer3Obj);
				obj.setToX(5);
				obj.setToY(0);
				obj.setNode(farmer3);
				obj.play();
				farmer3OnBoat = 0;
				counter--;
			} else if (farmerThreeOnLeft && farmer3OnBoat == 0 && !controller.isBoatOnTheLeftBank()) // BOAT ON LEFT AND
																										// FARMER ON
																										// LEFT
			{
				boatRiders.add(farmer3Obj);
				obj.setToX(-480);
				obj.setToY(-20);
				obj.setNode(farmer3);
				obj.play();
				farmer3OnBoat = 1;
			} else if (farmer3OnBoat == 1 && !controller.isBoatOnTheLeftBank()) // BOAT ON LEFT AND FARMER ON BOAT
			{
				boatRiders.remove(farmer3Obj);
				obj.setToX(-700);
				obj.setToY(0);
				obj.setNode(farmer3);
				obj.play();
				farmer3OnBoat = 0;
				counter--;
			}
		});
		farmer4.setOnMouseClicked(e -> {
			TranslateTransition obj = new TranslateTransition();
			obj.setDuration(Duration.seconds(1));
			if (!farmerFourOnLeft && farmer4OnBoat == 0 && controller.isBoatOnTheLeftBank()) // BOAT ON RIGHT AND FARMER
																								// ON RIGHT
			{
				boatRiders.add(farmer4Obj);
				obj.setToX(-300);
				obj.setToY(-20);
				obj.setNode(farmer4);
				obj.play();
				farmer4OnBoat = 1;
				counter++;

			} else if (farmer4OnBoat == 1 && controller.isBoatOnTheLeftBank()) // BOAT ON RIGHT AND FARMER ON BOAT
			{
				boatRiders.remove(farmer4Obj);
				obj.setToX(5);
				obj.setToY(0);
				obj.setNode(farmer4);
				obj.play();
				farmer4OnBoat = 0;
				counter--;
			} else if (farmerFourOnLeft && farmer4OnBoat == 0 && !controller.isBoatOnTheLeftBank()) // BOAT ON LEFT AND
																									// FARMER ON LEFT
			{
				boatRiders.add(farmer4Obj);
				obj.setToX(-600);
				obj.setToY(-20);
				obj.setNode(farmer4);
				obj.play();
				farmer4OnBoat = 1;
			} else if (farmer4OnBoat == 1 && !controller.isBoatOnTheLeftBank()) // BOAT ON LEFT AND FARMER ON BOAT
			{
				boatRiders.remove(farmer4Obj);
				obj.setToX(-890);
				obj.setToY(0);
				obj.setNode(farmer4);
				obj.play();
				farmer4OnBoat = 0;
				counter--;
			}
		});

		sheep.setOnMouseClicked(e -> {
			TranslateTransition obj = new TranslateTransition();
			obj.setDuration(Duration.seconds(1));

			if (!sheepOnLeft && sheepOnBoat == 0 && controller.isBoatOnTheLeftBank()) // BOAT ON RIGHT AND FARMER ON
																						// RIGHT
			{
				boatRiders.add(sheepObj);
				obj.setToX(-240);
				obj.setToY(-15);
				obj.setNode(sheep);
				obj.play();
				sheepOnBoat = 1;
				counter++;
			} else if (sheepOnBoat == 1 && controller.isBoatOnTheLeftBank()) // BOAT ON RIGHT AND FARMER ON BOAT
			{
				boatRiders.remove(sheepObj);
				obj.setToX(5);
				obj.setToY(0);
				obj.setNode(sheep);
				obj.play();
				sheepOnBoat = 0;
				counter--;
			} else if (sheepOnLeft && sheepOnBoat == 0 && !controller.isBoatOnTheLeftBank()) // BOAT ON LEFT AND FARMER
																								// ON LEFT
			{
				boatRiders.add(sheepObj);
				obj.setToX(-580);
				obj.setToY(-20);
				obj.setNode(sheep);
				obj.play();
				sheepOnBoat = 1;
				counter--;
			} else if (sheepOnBoat == 1 && !controller.isBoatOnTheLeftBank()) // BOAT ON LEFT AND FARMER ON BOAT
			{
				boatRiders.remove(sheepObj);
				obj.setToX(-800);
				obj.setToY(0);
				obj.setNode(sheep);
				obj.play();
				sheepOnBoat = 0;
				counter--;
			}

		});// String path = "C:\\Users\\OMAR\\Desktop\\solver.mp4";
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
		scene = new Scene(gp, 1080, 720);

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
		farmer2OnBoat = 0;
		farmer3OnBoat = 0;
		farmer4OnBoat = 0;
		sheepOnBoat = 0;

		counter = 0;
		boatOneLeft = false;
		sheepOnLeft = false;

		farmerOneOnLeft = false;
		farmerTwoOnLeft = false;
		farmerThreeOnLeft = false;
		farmerFourOnLeft = false;

		fromRightToLeft = true;
	}

	public void loadScene() {
		farmer.relocate(0, 0);
		farmer2.relocate(0, 0);
		farmer3.relocate(0, 0);
		farmer4.relocate(0, 0);
		sheep.relocate(0, 0);
    
      for (int i = 0; i < controller.getCrossersOnLeftBank().size(); i++) {
			
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown().equalsIgnoreCase("90")) {
				farmer.setLayoutX(800);
				farmer.setLayoutY(500);
				farmerOnBoat = 0;
				farmerOneOnLeft = false;
			}
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown().equalsIgnoreCase("80")) {
				farmer2.setLayoutX(910);
				farmer2.setLayoutY(500);
				farmer2OnBoat = 0;
				farmerTwoOnLeft = false;
			}
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown().equalsIgnoreCase("60")) {
				farmer3.setLayoutX(780);
				farmer3.setLayoutY(500);
				farmer3OnBoat = 0;
				farmerThreeOnLeft = false;
			}
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown().equalsIgnoreCase("40")) {
				farmer4.setLayoutX(1000);
				farmer4.setLayoutY(500);
				farmer4OnBoat = 0;
				farmerFourOnLeft = false;

			}
			if (controller.getCrossersOnLeftBank().get(i).getLabelTOBeShown().equalsIgnoreCase("20")) {
				sheep.setLayoutX(980);
				sheep.setLayoutY(500);
				sheepOnBoat = 0;
				sheepOnLeft = false;
			}
			right.setDisable(true);
			left.setDisable(false);
		}
		for (int j = 0; j < controller.getCrossersOnRightBank().size(); j++) {
			
			if (controller.getCrossersOnRightBank().get(j).getLabelTOBeShown()
					.equalsIgnoreCase("90")) {
				farmer.setLayoutX(300);
				farmer.setLayoutY(500);
				farmerOnBoat = 0;
				farmerOneOnLeft = true;
		
			}
			if (controller.getCrossersOnRightBank().get(j).getLabelTOBeShown()
					.equalsIgnoreCase("80")) {
				farmer.setLayoutX(210);
				farmer.setLayoutY(500);
				farmer2OnBoat = 0;
				farmerTwoOnLeft = true;
		
			}
			if (controller.getCrossersOnRightBank().get(j).getLabelTOBeShown()
					.equalsIgnoreCase("60")) {
	
				farmer3.setLayoutX(170);
				farmer3.setLayoutY(500);
				farmer3OnBoat = 0;
				farmerThreeOnLeft = true;
			}
			if (controller.getCrossersOnRightBank().get(j).getLabelTOBeShown()
					.equalsIgnoreCase("40")) {
			
				farmer4.setLayoutX(100);
				farmer4.setLayoutY(500);
				farmer4OnBoat = 0;
				farmerFourOnLeft = true;
			}
			if (controller.getCrossersOnRightBank().get(j).getLabelTOBeShown()
					.equalsIgnoreCase("20")) {
				sheep.setLayoutX(460);
				sheep.setLayoutY(500);
				sheepOnLeft = true;
				sheepOnBoat = 0;
			}
		
			if (!controller.isBoatOnTheLeftBank()) { // FALSE ------ RIGHT IN LOGIC --- LEFT IN GUI
				boat.setX(300);
				boat.setY(530);
			} else {
				boat.setX(600);
				boat.setY(530);
			}
			right.setDisable(false);
			left.setDisable(true);

		}
	}
}
