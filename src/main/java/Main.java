import static misc.Messages.MESSAGE_END_OF_INPUT_FILE;

import java.io.IOException;
import java.util.NoSuchElementException;

import command.CommandResult;
import command.ExitCommand;
import command.InvalidCommandException;
import command.Command;
import duke.Duke;
import duke.task.InvalidTaskArgumentException;
import parser.Parser;
import storage.InvalidStorageFilePathException;
import storage.Storage;
import storage.StorageReadWriteException;
import ui.Ui;

/**
 * Initializes the program and takes command from the user.
 * 
 * @author JosephLimWeiJie
 */
public class Main {

    private Ui ui;
    private Duke duke;
    private Storage storage;   
    
    /** Driver code for the program. */
    public static void main(String... args) {
        new Main().run(args);
    }
    
    /** Runs the program until the user terminates it. */
    public void run(String[] args) {
        start(args);
        runCommandUntilExit();
        exit();
    }
    
    /** 
     * Initializes the required classes, loads any data from storage text file and 
     * prints a welcome message. 
     * 
     * @param args arguments to be provided by the user at program launch.
     * @throws throws a RuntimeException when if the storage text file is corrupted.
     */
    public void start(String[] args) {
        try {
            this.ui = new Ui();
            this.storage = new Storage();           
            this.duke = new Duke(storage.load());  
            ui.displayWelcomeMessage();
        } catch (InvalidStorageFilePathException | StorageReadWriteException e) {
            ui.displayErrorMessage(e.getMessage());
            throw new RuntimeException(e);
        } 
    }
    
    /** Processes user command and execute until an exit command is given. */
    private void runCommandUntilExit() {                     
        while (true) {
            Command command;                          
            try {        
                String commandText = ui.getCommand();                 
                ui.displayBorder();
                command = new Parser().parseCommand(commandText);
                
                if (ExitCommand.isExit(command)) {
                    break;
                }
                
                CommandResult output = executeCommand(command, this.duke);                 
                ui.displayOutputMessage(output);             
            } catch (InvalidTaskArgumentException e) {                
                ui.displayErrorMessage(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.displayErrorMessage(e.getMessage());
            } catch (InvalidCommandException e) {
                ui.displayErrorMessage(e.getMessage());
            } catch (IllegalArgumentException e) {
                ui.displayErrorMessage(e.getMessage());
            } catch (NoSuchElementException nsee) {
                // Used only for testing runtest.bat reads from an input file.
                ui.displayErrorMessage(MESSAGE_END_OF_INPUT_FILE);
                exit();
            } finally {
                ui.displayBorder();
            }
        }
    }
    
    /** Executes a command and save the result onto storage text file. */
    private CommandResult executeCommand(Command command, Duke duke) {
        try {
            CommandResult output = command.execute(this.duke);
            storage.save(storage.getFilePath(), duke.getTaskList());
            return output;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        } 
    }
    
    /** Exit the program with a goodbye message. */
    private void exit() {
        ui.displayExitMessage();
        System.exit(0);
    }    
}

