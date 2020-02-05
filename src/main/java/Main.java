import duke.Duke;
import duke.exception.DukeException;
import ui.Ui;
import command.*;
import parser.Parser;

public class Main {

    private Ui ui;
    private Duke duke;
    
    public static void main(String[] args) {
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
            this.duke = new Duke();
            ui.displayWelcomeMessage();
        } catch (DukeException e) {
            ui.displayErrorMessage();
        }
    }
    
    private void runCommandUntilExit() {
        Command command;
        do {
            String commandText = ui.getCommand();
            command = new Parser().parseCommand(commandText);       
            ui.displayBorder();
            CommandResult output = executeCommand(command);
            ui.displayOutputMessage(output);
            ui.displayBorder();
        } while (!ExitCommand.isExit(command));           
    }
    
    private CommandResult executeCommand(Command command) {
        try {
            CommandResult output = command.execute(this.duke);    
            return output;
        } catch(Exception e) {
            ui.displayMessage(e.getMessage());
            throw new RuntimeException(e);
        } 
    }
    
    private void exit() {
        ui.displayExitMessage();
        System.exit(0);
    }   
}
