import java.io.IOException;

import duke.Duke;
import duke.task.InvalidTaskArgumentException;
import command.CommandResult;
import command.ExitCommand;
import command.InvalidCommandException;
import command.Command;
import parser.Parser;
import storage.InvalidStorageFilePathException;
import storage.Storage;
import storage.StorageReadWriteException;
import ui.Ui;

public class Main {

    private Ui ui;
    private Duke duke;
    private Storage storage;   
    
    public static void main(String... args) {
        new Main().run(args);
    }
    
    public void run(String[] args) {
        start(args);
        runCommandUntilExit();
        exit();
    }
    
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
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
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
            } finally {
                ui.displayBorder();
            }
        }
    }
    
    private void exit() {
        ui.displayExitMessage();
        System.exit(0);
    }    
}

