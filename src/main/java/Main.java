import commands.Command;
import commands.CommandResult;
import commands.ExitCommand;
import data.TaskManager;
import data.exceptions.StorageOperationException;
import javafx.application.Application;
import parser.Parser;
import storage.StorageFile;
import ui.TextUi;

import java.util.Scanner;

public class Main  {
    private CommandResult commandResult;
    public TaskManager taskManager;

    public Main() throws StorageOperationException {
        taskManager = new TaskManager();
    }

    public static void main(String[] args) throws StorageOperationException {
        TextUi.clearScreen();
        TextUi.displayLogo();
        chooseInterface(args);
    }

    private static void chooseInterface(String[] args) throws StorageOperationException {
        int userChoice;
        userChoice = TextUi.getUserChoice();
        TextUi.acknowledgementUserChoice(userChoice);
        switch (userChoice){
        case 1:
            Application.launch(Gui.class, args);
            break;
        case 2:
            new Main().run();
            break;
        default:
            TextUi.askForReInput();
            chooseInterface(args);
        }
    }

    private void run() throws StorageOperationException {
        TextUi.showWelcomeMessage();
        runCommandLoopUntilExitCommand();
    }

    private void runCommandLoopUntilExitCommand() throws StorageOperationException {
        Command command;
        Scanner scanner = new Scanner(System.in);
        do {
            //read in user input
            String userCommandText = scanner.nextLine();
            //construct a command from user input
            command = new Parser().parseCommand(taskManager, userCommandText);
            executeCommand(command);
        } while (!ExitCommand.isExit(command));
    }

    /**
     * initialize the taskManager system, execute command and save the list to the file
     * @param command the parsed Command object
     * @return commandResult that contains the execute output information
     */
    private CommandResult executeCommand(Command command) throws StorageOperationException {
        try {
            // supplies the data the command will operate on.
            // if there is no file to load or the file is empty, setData will initialize a new taskManager system
            command.setData(taskManager);
            // Execute according to the command itself
            commandResult = command.execute();
            // save the taskManager to a file
            taskManager.getStorager().save(taskManager);
            StorageFile.saveJson(taskManager);
        } catch (Exception ex) {
            // the out layer exception handler
            System.out.println(ex);
        }
        return commandResult;
    }

    /**
     * @param input the full length of user input
     * @return commandResult.feedbackToUser
     */
    public String getResponse(String input) throws StorageOperationException {
        //construct a command from user input
        Command command;
        command = new Parser().parseCommand(taskManager, input);
        commandResult = executeCommand(command);
        return commandResult.feedbackToUser;
    }
}


