import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String indentation = "     ";
        String line = "    ____________________________________________________________";
        String greetings = "     Hello! I'm Duke\n" +
                "     What can I do for you?";
        String bye = "Bye. Hope to see you again soon!";

        //System.out.println("Hello from\n" + logo);

        System.out.println(line);
        System.out.println(greetings);
        System.out.println(line);

        Scanner s  = new Scanner(System.in);
        String command = s.nextLine();
        while (!command.equals("bye")) {
            System.out.println(line);
            System.out.println(indentation + command);
            System.out.println(line);
            command = s.nextLine();
        }
        System.out.println(line);
        System.out.println(indentation + bye);
        System.out.println(line);
    }
}
