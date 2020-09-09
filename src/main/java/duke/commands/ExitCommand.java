package duke.commands;

import duke.storage.Storage;
import duke.ui.UI;

import java.io.IOException;

import static duke.ui.Messages.GOODBYE_MESSAGE;
import static duke.ui.Messages.SUCCESSFUL_SAVE_MESSAGE;
import static duke.exception.ExceptionMessages.FILE_SAVE_ERROR_MESSAGE;
import static duke.ui.Messages.PROMPT_VALID_EXIT_CONFIRMATION_MESSAGE;
import static duke.ui.Messages.UNSUCCESSFUL_SAVE_MESSAGE;
import static duke.ui.Messages.ABORT_EXIT_MESSAGE;

/**
 * <h3>Exit Command</h3>
 * A <b>Command</b> to exit the <b>LumiChat</b> program.
 * @see Command
 * @see duke.data.TaskList
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String FORMAT = "bye";
    private static boolean isExit; // To check if user requests to exit the program

    public ExitCommand() {
        isExit = false; // Set to false until user requests to exit the program (after confirmation if necessary)
    }

    /**
     * Executes the <b>Exit Command</b> to exit the <b>LumiChat</b> program.
     * <br> The <b>Task List</b> is saved into a <i>task list file</i> upon exiting.
     * <p></p>
     * <b>Note</b>: Should there be an error in saving the <b>Task List</b>, a confirmation request will be prompted
     * to the user whether to continue to exit <b>without</b> saving. If the user rejects, the <b>LumiChat</b> program
     * will continue to run without exiting.
     *
     * @return The <b>Command Result</b> of the execution
     * @see duke.data.TaskList
     * @see Storage
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        try {
            new Storage().saveTaskList();
            isExit = true;
            return new CommandResult(SUCCESSFUL_SAVE_MESSAGE + GOODBYE_MESSAGE);
        } catch (IOException e) {
            boolean canExit =
                    new UI().getConfirmation(FILE_SAVE_ERROR_MESSAGE, PROMPT_VALID_EXIT_CONFIRMATION_MESSAGE);
            if (canExit) {
                isExit = true;
                return new CommandResult(UNSUCCESSFUL_SAVE_MESSAGE + GOODBYE_MESSAGE);
            } else {
                return new CommandResult(ABORT_EXIT_MESSAGE);
            }
        }
    }

    /**
     * Checks if the user has requested to exit the program (after confirmation if necessary).
     *
     * @return <code>TRUE</code> if an exit is requested, and <code>FALSE</code> otherwise
     */
    public static boolean isExit () {
        return isExit; // Returns true if command is exit command
    }
}
