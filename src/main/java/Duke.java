import java.util.Scanner;

public class Duke {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String lineDivider = "____________________________________________________________";
    private static final String indentation = "     ";
    private static final String greetingWords = indentation + "Hello! I'm Duke\n" + indentation + "What can I do for you?";
    private static final String byeWords = "Bye. Hope to see you again soon!";

    private static void greet() {
        System.out.println(logo);
        System.out.println(greetingWords);
    }

    private static void echo(String command) {
        System.out.println(lineDivider);
        System.out.println(indentation + command);
        System.out.println(lineDivider);
    }
    
    private static void bye() {
        System.out.println(lineDivider);
        System.out.println(indentation + byeWords);
        System.out.println(lineDivider);
    }

    public static void main(String[] args) {
        greet();

        Scanner s  = new Scanner(System.in);
        String command = s.nextLine();
        while (!command.equals("bye")) {
            echo(command);
            command = s.nextLine();
        }
        bye();
    }
}
