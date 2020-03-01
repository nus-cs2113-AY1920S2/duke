package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.commands.ExitCommand;
import duke.exception.CorruptedFileException;
import duke.format.DateTimeFormat;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.UI;
import org.fusesource.jansi.AnsiConsole;

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


/**
 * <h2>LumiChat</h2>
 * This Duke program is a Chat Box application named <b>LumiChat</b> that manages a list of user-created tasks.
 * <p></p>
 * User interacts with the application via the command line, and performs various actions like adding, deleting and
 * viewing tasks.
 *
 * @author iceclementi
 * @version 5.6.1.4
 * @since 2020, January
 */
public class Duke {

    private Storage storage;
    private UI ui;

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        AnsiConsole.systemInstall(); // External library for text formatting; install for use

        chatBot.runChat();

        AnsiConsole.systemUninstall(); // Uninstall external library
    }

    /**
     * Prints welcome message, initialises task list and runs program until termination.
     * <p></p>
     * <b>Note:</b> Program exits if task list initialisation fails.
     */
    private void runChat() {
        this.ui = new UI();
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

    /**
     * Initialises the program by loading up the task list saved in the storage file.
     * <p></p>
     * If file is corrupted, user will be prompted whether to allow file to be overwritten by a new <b>empty</b> file.
     *
     * @return <code>TRUE</code> if task list is successfully initialised, or <code>FALSE</code> otherwise
     * @throws IOException If there is a error accessing or creating the task list file
     */
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

    /**
     *  Reads user input from the command line until an <b><i>exit</i></b> command is given.
     *  Each user input is converted into a command by the <b>Parser</b> and executed. The <b>UI.java</b> then displays
     *  any feedback message or necessary information to the user.
     * @see Parser
     * @see UI
     */
    private void readInputUntilExit() {
        do {
            String input = ui.getInput();
            try {
                Command command = new Parser().parseInput(input);
                CommandResult result = command.execute();
                ui.showResult(result);
            } catch (Parser.InputLengthExceededException e) {
                System.out.println(INPUT_LENGTH_EXCEEDED_MESSAGE);
            } catch (Parser.EmptyInputException e) {
                System.out.println(EMPTY_INPUT_MESSAGE);
            } catch (Parser.InvalidCommandException e) {
                System.out.println(INVALID_ACTION_MESSAGE);
            }
        } while (!ExitCommand.isExit());
    }
}
