import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Task[] tasks = new Task[100];
        int counter = 0;

        while(true) {
            String line;
            Scanner input = new Scanner(System.in);
            line = input.nextLine();

            if (line.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            else if (line.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++){
                    System.out.print(i+1 + ". ");
                    System.out.print(tasks[i].getStatusIcon());
                    System.out.println(tasks[i].description);
                }
                System.out.println("____________________________________________________________");

            }

            else if (line.startsWith("done")){

                String strNumber = line.substring(5);
                int number = Integer.parseInt(strNumber);
                tasks[number-1].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: " + tasks[number-1].description);
                System.out.println("____________________________________________________________");
            }

            else{
                Task t = new Task(line);
                tasks[counter] = t;
                System.out.println("____________________________________________________________");
                System.out.println("added: "+ t.description);
                System.out.println("____________________________________________________________");
                counter += 1;
            }
        }


    }
}
