package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.commands.ExitCommand;
import duke.exception.CorruptedFileException;
import duke.format.DateTimeFormat;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import static duke.ui.Messages.WELCOME_MESSAGE;
import static duke.ui.Messages.LOAD_MESSAGE;
import static duke.ui.Messages.READY_MESSAGE;
import static duke.ui.Messages.CREATE_NEW_FILE_MESSAGE;
import static duke.ui.Messages.CREATE_CONFIRMATION_MESSAGE;
import static duke.ui.Messages.PROMPT_VALID_CREATE_CONFIRMATION_MESSAGE;
import static duke.ui.Messages.ABORT_CREATE_NEW_FILE_MESSAGE;
import static duke.ui.Messages.EXIT_MESSAGE;
import static duke.exception.ExceptionMessages.EMPTY_INPUT_MESSAGE;
import static duke.exception.ExceptionMessages.INPUT_LENGTH_EXCEEDED_MESSAGE;
import static duke.exception.ExceptionMessages.INVALID_ACTION_MESSAGE;
import static duke.exception.ExceptionMessages.FILE_NOT_FOUND_MESSAGE;
import static duke.exception.ExceptionMessages.CORRUPTED_FILE_MESSAGE;
import static duke.exception.ExceptionMessages.IO_ERROR_MESSAGE;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private Ui ui;

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.runChat();
    }

    private void runChat() {
        this.ui = new Ui();
        this.storage = new Storage();

        ui.showSystemMessage(WELCOME_MESSAGE);

        // Initialise task list
        boolean canInitialise = false;
        try {
            canInitialise = initialiseChat();
        } catch (IOException e) {
            ui.showSystemMessage(IO_ERROR_MESSAGE);
        }

        if (canInitialise) {
            ui.showSystemMessage(READY_MESSAGE);
            readInputUntilExit();
        }

        ui.showSystemMessage(EXIT_MESSAGE);
    }

    private boolean initialiseChat() throws IOException {
        ui.showSystemMessage(LOAD_MESSAGE);
        try {
            storage.loadTaskList();
        } catch (FileNotFoundException e) {
            ui.showSystemMessage(FILE_NOT_FOUND_MESSAGE);
            storage.createTaskListFile(); // Create new task list file
        } catch (CorruptedFileException | IndexOutOfBoundsException |
                DateTimeFormat.InvalidTimeException | DateTimeFormat.InvalidDateException e) {
            ui.showSystemMessage(CORRUPTED_FILE_MESSAGE);

            boolean canCreateNewFile =
                    ui.getConfirmation(CREATE_CONFIRMATION_MESSAGE, PROMPT_VALID_CREATE_CONFIRMATION_MESSAGE);
            if (canCreateNewFile) {
                storage.createTaskListFile();
                ui.showSystemMessage(CREATE_NEW_FILE_MESSAGE);
            } else {
                ui.showSystemMessage(ABORT_CREATE_NEW_FILE_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private void readInputUntilExit() {
        Command command = null;
        do {
            String input = ui.getInput();
            try {
                command = new Parser().parseInput(input);
                CommandResult result = command.execute();
                ui.showResult(result);
            } catch (Parser.InputLengthExceededException e) {
                System.out.println(INPUT_LENGTH_EXCEEDED_MESSAGE);
            } catch (Parser.EmptyInputException e) {
                System.out.println(EMPTY_INPUT_MESSAGE);
            } catch (Parser.InvalidCommandException e) {
                System.out.println(INVALID_ACTION_MESSAGE);
            }
        } while (!ExitCommand.isExit(command));
    }
}
