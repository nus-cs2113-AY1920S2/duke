import java.util.Scanner;

public class Duke {
    public static void instruction(String dukeCommand){
        if (dukeCommand.equals("greet")) {
            System.out.println("____________________________________________________________");
            System.out.println("Hello! I'm Duke\n" +
                    " What can I do for you?");
            System.out.println("____________________________________________________________");
        }else if (dukeCommand.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
        }else{
            System.out.println("____________________________________________________________");
            System.out.println(dukeCommand + "\n");
            System.out.println("____________________________________________________________");
        }
        /*else{
            System.out.println("____________________________________________________________");
            System.out.println("Duke cannot understand your command.\n");
            System.out.println("____________________________________________________________");
        }*/
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        instruction("greet");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            instruction(line);
            line = in.nextLine();
        }
        instruction("bye");

    }
}
