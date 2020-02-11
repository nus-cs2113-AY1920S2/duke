import duke.Duke;
import duke.task.InvalidTaskArgumentException;
import command.CommandResult;
import command.ExitCommand;
import command.InvalidCommandException;
import command.Command;
import parser.Parser;
import ui.Ui;

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
        this.ui = new Ui();
        this.duke = new Duke();
        ui.displayWelcomeMessage();
    }
    
    private void runCommandUntilExit() {                     
        while (true) {
            Command command;
            String commandText = ui.getCommand();
            ui.displayBorder();
            
            try {  
                command = new Parser().parseCommand(commandText);
                CommandResult output = command.execute(this.duke);
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

