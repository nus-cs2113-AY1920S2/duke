import java.io.IOException;

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
     * Initialize the required classes, load any data from storage.txt and 
     * prints a welcome message. 
     * 
     * @param args arguments to be provided by the user at program launch
     */
    public void start(String[] args) {
        try {
            this.ui = new Ui();
            this.storage = new Storage();           
            this.duke = new Duke(storage.load());  
            ui.displayWelcomeMessage();
        } catch (InvalidStorageFilePathException e) {
            ui.displayErrorMessage(e.getMessage());
        } catch (StorageReadWriteException e) {
            ui.displayErrorMessage(e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } 
    }
    
    /** Process user command and execute until an exit command is given. */
    private void runCommandUntilExit() {                     
        while (true) {
            Command command;
            String commandText = ui.getCommand();
            ui.displayBorder();
            
            try {  
                command = new Parser().parseCommand(commandText);
                CommandResult output = command.execute(this.duke);
                
                try {
                    storage.save(storage.getFilePath(), duke.getTaskList());
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } 
                
                ui.displayOutputMessage(output);             
                if (ExitCommand.isExit(command)) {
                    break;
                }

            } catch (InvalidTaskArgumentException e) {                
                ui.displayErrorMessage(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.displayErrorMessage(e.getMessage());
            } catch (InvalidCommandException e) {
                ui.displayErrorMessage(e.getMessage());
            } catch (IllegalArgumentException e) {
                ui.displayErrorMessage(e.getMessage());
            } finally {
                ui.displayBorder();
            }
        }
    }
    
    /** Exit the program with a goodbye message. */
    private void exit() {
        ui.displayExitMessage();
        System.exit(0);
    }    
}

