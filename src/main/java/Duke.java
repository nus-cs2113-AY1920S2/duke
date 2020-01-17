import java.util.Scanner;

public class Duke {

    private static void greeting () {

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void farewell() {

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void echo(String command_word) {

        System.out.println("____________________________________________________________");
        System.out.println("  "+command_word);
        System.out.println("____________________________________________________________");
    }

    private static void runCommandLoopUntilExitCommand() {

        final String exit_command = "bye";
        Scanner sc = new Scanner(System.in);
        String command_word = sc.nextLine();

        while (!command_word.equals(exit_command)) {
            echo(command_word);
            command_word = sc.nextLine();
        }
    }



    public static void main(String[] args) {
        greeting();
        runCommandLoopUntilExitCommand();
        farewell();
    }



}


