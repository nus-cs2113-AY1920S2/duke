import commands.Command;
import commands.ExitCommand;
import data.Duke;
import data.exceptions.StorageOperationException;
import parser.Parser;
import storage.StorageFile;
import ui.TextUi;
import java.util.Scanner;


public class Main {

    private StorageFile storage = new StorageFile();
    Duke duke = new Duke();

    public Main() throws StorageOperationException {
    }
    //private TextUi ui;

    public static void main(String[] args) throws StorageOperationException {
        new Main().run();
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
            //parse input from String to command
            command = new Parser().parseCommand(userCommandText);;
            executeCommand(command);
        } while (!ExitCommand.isExit(command));
    }


    private void executeCommand(Command command) throws StorageOperationException {
        try {
            //Supplies the data the command will operate on.
            //if there is no file to load or the file is empty, setData will instantiate an empty task list
            command.setData(duke);
            // Execute according to the command itself
            command.execute();
            storage.save(duke);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}


