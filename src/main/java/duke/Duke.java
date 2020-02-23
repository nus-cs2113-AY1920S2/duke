package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.commands.ExitCommand;
import duke.data.TaskList;
import duke.exception.CorruptedFileException;
import duke.exception.EmptyInputException;
import duke.exception.InputLengthExceededException;
import duke.exception.InvalidActionException;
import duke.parser.Parser;
import duke.task.Task;

import static duke.exception.ExceptionMessage.CORRUPTED_FILE_MESSAGE;
import static duke.exception.ExceptionMessage.EMPTY_INPUT_MESSAGE;
import static duke.exception.ExceptionMessage.FILE_NOT_FOUND_MESSAGE;
import static duke.exception.ExceptionMessage.INPUT_LENGTH_EXCEEDED_MESSAGE;
import static duke.exception.ExceptionMessage.INVALID_ACTION_MESSAGE;
import static duke.exception.ExceptionMessage.IO_ERROR_MESSAGE;
import static duke.format.Printer.PROMPT_VALID_CREATE_CONFIRMATION_MESSAGE;
import static duke.format.Printer.printAbortCreateNewFileMessage;
import static duke.format.Printer.printCreateNewFileMessage;
import static duke.format.Printer.printExitMessage;
import static duke.format.Printer.printLoadMessage;
import static duke.format.Printer.printReadyMessage;
import static duke.format.Printer.printWelcomeMessage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();
    private static TaskList taskList;
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.runChat();
    }

    private void runChat() {
        printWelcomeMessage();

        boolean canInitialise = false;
        try {
            canInitialise = initialiseChat();
        } catch (IOException e) {
            System.out.println(IO_ERROR_MESSAGE);
        }

        if (canInitialise) {
            printReadyMessage();
            readInputUntilExit();
        }

        scanner.close();
        printExitMessage();
    }

    private boolean initialiseChat() throws IOException {
        printLoadMessage();
        try {
            FileManager.loadTaskList(list);
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND_MESSAGE);

            // Create new task list file
            FileManager.createTaskListFile();
        } catch (CorruptedFileException | IndexOutOfBoundsException e) {
            System.out.println(CORRUPTED_FILE_MESSAGE);

            boolean canCreateNewFile = getConfirmation(PROMPT_VALID_CREATE_CONFIRMATION_MESSAGE);
            if (canCreateNewFile) {
                FileManager.createTaskListFile();
                printCreateNewFileMessage();
            } else {
                printAbortCreateNewFileMessage();
                printExitMessage();
                return false;
            }
        }
        return true;
    }

    private boolean getConfirmation(String validConfirmationMessage) {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.println(validConfirmationMessage);
            }
        }
    }

    private void readInputUntilExit() {
        Command command = null;
        do {
            String input = scanner.nextLine().trim();
            try {
                command = new Parser().parseInput(input);
                CommandResult result = command.execute();
                System.out.println(result.getMessage());
            } catch (InputLengthExceededException e) {
                System.out.println(INPUT_LENGTH_EXCEEDED_MESSAGE);
            } catch (EmptyInputException e) {
                System.out.println(EMPTY_INPUT_MESSAGE);
            } catch (InvalidActionException e) {
                System.out.println(INVALID_ACTION_MESSAGE);
            }
        } while (!ExitCommand.isExit(command));
    }
}
