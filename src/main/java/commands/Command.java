package commands;

import data.Duke;

public abstract class Command {
    protected Duke duke;
    public String COMMAND_WORD;
    //constructor
    public Command() {
    }

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(Duke duke) {
        this.duke = duke;
    }

    public abstract CommandResult execute () ;

}
