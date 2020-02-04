import java.util.Scanner;

import duke.Duke;
import duke.exception.*;
import duke.task.*;
import ui.Ui;
import misc.Messages;
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
        Duke duke = this.duke;
        do {
            String commandText = ui.getCommand();
            command = new Parser().parseCommand(commandText);
            CommandResult output = command.execute(duke);
            ui.displayOutputMessage(output);
        } while (!ExitCommand.isExit(command));           
    }
    
    private void exit() {
        ui.displayExitMessage();
        System.exit(0);
    }
    
    
    
    /*
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();
        System.out.println(duke);
        
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                duke = duke.run(input);
            } catch (DukeException e) {
                System.err.println(e);
            }
        }
        
        sc.close();
    } */
}
