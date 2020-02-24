package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

import static duke.ui.Messages.GOODBYE_MESSAGE;
import static duke.ui.Messages.SUCCESSFUL_SAVE_MESSAGE;
import static duke.exception.ExceptionMessages.FILE_SAVE_ERROR_MESSAGE;
import static duke.ui.Messages.PROMPT_VALID_EXIT_CONFIRMATION_MESSAGE;
import static duke.ui.Messages.UNSUCCESSFUL_SAVE_MESSAGE;
import static duke.ui.Messages.ABORT_EXIT_MESSAGE;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String FORMAT = "bye";
    private static boolean isExit;

    public ExitCommand() {
        isExit = false;
    }

    @Override
    public CommandResult execute() {
        try {
            new Storage().saveTaskList();
            isExit = true;
            return new CommandResult(SUCCESSFUL_SAVE_MESSAGE + GOODBYE_MESSAGE);
        } catch (IOException e) {
            boolean canExit =
                    new Ui().getConfirmation(FILE_SAVE_ERROR_MESSAGE, PROMPT_VALID_EXIT_CONFIRMATION_MESSAGE);
            if (canExit) {
                isExit = true;
                return new CommandResult(UNSUCCESSFUL_SAVE_MESSAGE + GOODBYE_MESSAGE);
            } else {
                return new CommandResult(ABORT_EXIT_MESSAGE);
            }
        }
    }

    public static boolean isExit (Command command) {
        return isExit; // Returns true if command is exit command
    }
}
