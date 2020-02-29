package duke.command;

import duke.TaskList;
import duke.excpetions.DukeException;

/**
 * Command is an extract of users' inputs with all useful information.
 * Command has a field "type" to mark the category of the command.
 */
public abstract class Command {

    protected String type;

    public Command(String type){
        this.type = type;
    }

    /**
     * An abstract method to execute a command.
     *
     * @param tasks The task list where the execution will be done.
     * @throws DukeException If there is something wrong when executing the command.
     */
    abstract public void execute(TaskList tasks) throws DukeException;

    /**
     * Returns a boolean whether the command is an exit command.
     *
     * @return A boolean tells whether the command is an exit command or not.
     */
    public boolean isExit(){
        return type.equals("bye");
    }

    public String getType() {
        return type;
    }
}
