import commands.Command;
import commands.ExitCommand;
import data.Duke;
import parser.Parser;
import ui.TextUi;
import java.util.Scanner;

public class Main {

    Duke duke = new Duke();
    private TextUi ui;

    public static void main(String[] args) {

        new Main().run();

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
        command.setData(duke);
        command.execute();
    }


}


