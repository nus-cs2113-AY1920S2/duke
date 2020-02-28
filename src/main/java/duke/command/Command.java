package duke.command;

import duke.TaskList;
import duke.excpetions.DukeException;

public abstract class Command {

    protected String type;

    public Command(String type){
        this.type = type;
    }

    abstract public void execute(TaskList tasks) throws DukeException;

    public boolean isExit(){
        return type.equals("bye");
    }

    public String getType() {
        return type;
    }
}
