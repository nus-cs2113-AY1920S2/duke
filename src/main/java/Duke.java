import java.util.Scanner;

public class Duke {

    //level 1 greet, echo, bye (function)
    public static void echoCommand(String line)
    {
        String comparator = "bye";
        System.out.println("--------------------------");

        if(line.equals(comparator))
        {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("---------------------------");
        }
        else
        {
            System.out.println(line);
            System.out.println("---------------------------");
            String command;
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            echoCommand(command);
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello ! I'm Duke\n");
        System.out.println("What can I do for you ? \n");

        //level 1 - greet, echo, exit
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        echoCommand(line);

        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/


    }
}
