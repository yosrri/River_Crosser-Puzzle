package MyWork;
import javafx.application.Application;
import javafx.stage.Stage;

public class GuiMain extends Application {
   public static void main(String[] args)
   {
	   launch(args);
   }
	@Override
	public void start(Stage primaryStage) throws Exception {
		MainMenu mainmenu = new MainMenu(primaryStage);
		StoryTwoGui storyTwoGui = new StoryTwoGui(primaryStage);
		StoryOneGui gui = new StoryOneGui(primaryStage);
        primaryStage.setTitle("River Crossing");
        mainmenu.prepareScene();
        storyTwoGui.prepareScene();
        gui.prepareScene();
        storyTwoGui.setMainmenu(mainmenu);
        gui.setMainmenu(mainmenu);
        mainmenu.setGui(gui);
        mainmenu.setStoryTwoGui(storyTwoGui);
      
        primaryStage.setScene(mainmenu.getScene());
        primaryStage.show();
	}

}
