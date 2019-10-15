package MyWork;

public class CommandControl {

    Command slot;
    public void setCommand(Command command){

        slot=command;

    }
    public void  buttonWasPressed(){

        slot.excute();

    }




}
