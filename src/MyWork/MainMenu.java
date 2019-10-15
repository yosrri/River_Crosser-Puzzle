package MyWork;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu  {
Stage stage;
Scene scene;
StoryTwoGui storyTwoGui;
StoryOneGui gui;
ICrossingStrategy storyone = new StoryOne();
ICrossingStrategy storytwo = new StoryTwo();
  public MainMenu(Stage stage)
  {
	  this.stage = stage;
  }

	public void prepareScene() {
		Image backgroundimg = new Image("file:menubackground.jpg");
		ImageView background = new ImageView(backgroundimg);

		Image storyOneimg = new Image("file:storyone.png");
		ImageView storyOne = new ImageView(storyOneimg);

		Image storyTwoimg = new Image("file:storytwo.png");
		ImageView storyTwo = new ImageView(storyTwoimg);

		Image loadimg = new Image("file:load.png");
		ImageView load = new ImageView(loadimg);

		storyOne.setFitHeight(80);
		storyOne.setFitWidth(250);

		storyTwo.setFitHeight(80);
		storyTwo.setFitWidth(250);

		load.setFitHeight(80);
		load.setFitWidth(250);
     
		
		VBox vb = new VBox();

		vb.getChildren().addAll(storyOne, storyTwo, load);
		vb.setPadding(new Insets(200, 100, 500, 350));
		Group group = new Group();
		group.getChildren().addAll(background, vb);
    
		storyOne.setOnMouseClicked(e->{
			 
		  stage.setScene(gui.getScene()); 
		  Alert instructions = new Alert(AlertType.WARNING);
			instructions.setTitle("Game Instructions");
			instructions.setHeaderText(storyone.getInstructions()[0]+"\n"
			+storyone.getInstructions()[1]+"\n"
					+storyone.getInstructions()[2]);
			instructions.showAndWait();
		
		  ;});
		storyTwo.setOnMouseClicked(e->{
		 	
	        gui.prepareScene();
			stage.setScene(storyTwoGui.getScene());
			 Alert instructions = new Alert(AlertType.WARNING);
				instructions.setTitle("Game Instructions");
				instructions.setHeaderText(storytwo.getInstructions()[0]+"\n"
				+storytwo.getInstructions()[1]+"\n"
						+storytwo.getInstructions()[2]);
				instructions.showAndWait();
			
		
			;});
		load.setOnMouseClicked(e->{
		 	
			 
			;});
		
		 scene = new Scene(group, 1000, 764);
		 
	}
  public Scene getScene() {
	  return this.scene;
  }

public void setStoryTwoGui(StoryTwoGui storyTwoGui) {
	this.storyTwoGui = storyTwoGui;
}
public void setGui(StoryOneGui gui) {
	this.gui = gui;
}
  
}
