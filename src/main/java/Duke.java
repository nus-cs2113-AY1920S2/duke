import commands.Command;
import commands.ExitCommand;
import parser.Parser;
import ui.TextUi;

import java.util.Scanner;

public class Duke {


    private TextUi ui;

    public static void main(String[] args) {

        new Duke().run();

    }

    private void run(){
        TextUi.showWelcomeMessage();
        runCommandLoopUntilExitCommand();
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        Scanner scanner = new Scanner(System.in);
        do {
            String userCommandText = scanner.nextLine();
            //process from String to command
            command = new Parser().parseCommand(userCommandText);;
            executeCommand(command);
        } while (!ExitCommand.isExit(command));
    }

    private void executeCommand(Command command) {
        command.execute();
    }


}


