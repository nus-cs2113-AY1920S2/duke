import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello ! I'm Duke\n");
        System.out.println("What can I do for you ? \n");

        String command;
        int i = 0;
        String[] list = new String[100];
        Scanner in = new Scanner (System.in);
        command = in.nextLine();
        String option_1 = "bye";
        String option_2 = "list";

        while (true)
        {
            if (command.equals(option_1))
            {
                System.out.println("------------------------");
                System.out.println("Bye, see you again soon!");
                System.out.println("------------------------");
                System.exit(0);
            }
            else if (command.equals(option_2))
            {
                System.out.println("------------------------");

                for (int j = 0; j < i; j++)
                {
                    System.out.print(j+1);
                    System.out.print(". ");
                    System.out.println(list[j]);
                }

                System.out.println("------------------------");
                command = in.nextLine();
            }
            else
            {
                list[i] = command;
                System.out.println("------------------------");
                System.out.println("Added: " + command);
                System.out.println("------------------------");
                i++;
                command = in.nextLine();
            }
        }

        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

    }
}
