package duke.command;

import duke.TaskList;

/**
 *  A subtype of command that can be used to store any kind of wrong command given bt the user.
 */
public class WrongCommand extends Command {

    /**
     * A constructor creates a new wrong command with type "wrong".
     *
     * @param type The type of the wrong command, always be "wrong".
     */
    public WrongCommand(String type) {
        super(type);
    }

    @Override
    public void execute(TaskList tasks) {
    }
}
