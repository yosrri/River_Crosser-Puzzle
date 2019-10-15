package MyWork;

public class LoadGameCommand implements Command{
	LoadGame Load;
public LoadGameCommand(LoadGame Load) {
this.Load=Load;
	
}
	@Override
	public void excute() {
		Load.loadProgress();
		
	}

}
