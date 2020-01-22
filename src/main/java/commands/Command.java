package commands;

public abstract class Command {

    public String COMMAND_WORD;
    //constructor
    public Command() {
    }

    public abstract void execute () ;

}
