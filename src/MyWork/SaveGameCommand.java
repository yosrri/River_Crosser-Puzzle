package MyWork;

public class SaveGameCommand implements Command {

    SaveGame Save;
public SaveGameCommand(SaveGame Save){

    this.Save=Save;
}

    @Override
    public void excute() {
        Save.saveProgress();

    }
}
