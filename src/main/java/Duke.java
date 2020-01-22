import ui.TextUi;

import java.util.Scanner;

public class Duke {

    private static void runCommandLoopUntilExitCommand() {

        final String exit_command = "bye";
        Scanner sc = new Scanner(System.in);
        String command_word = sc.nextLine();

        while (!command_word.equals(exit_command)) {
            TextUi.echo(command_word);
            command_word = sc.nextLine();
        }
    }



    public static void main(String[] args) {

        TextUi.showWelcomeMessage();

        runCommandLoopUntilExitCommand();

        TextUi.showFarewellMessage();
    }



}


