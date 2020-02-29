package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 *  A subtype of command which is used to exit the software.
 */
public class ExitCommand extends Command{

    /**
     *  A constructor to create a new exit command with type "bye".
     *
     * @param type The type of the exit command, always be "bye".
     */
    public ExitCommand(String type) {
        super(type);
    }

    /**
     * Executes an exit command which updates the file saved in the disk with current task list
     * and then exit the software.
     *
     * @param tasks The task list where the execution will be done.
     */
    @Override
    public void execute(TaskList tasks){
        try {
            Storage.writeFile(tasks);
            Ui.exitMessage();
        }catch (IOException E){
            System.out.println("Something goes wrong.");
        }
    }
}
