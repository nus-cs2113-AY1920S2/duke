import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hey there! My name is Duke and i will be your personal assistant.");
        System.out.println("First of all, how may i address you?");
        System.out.println("Please enter your username: ");
        String userName = myScanner.nextLine();
        System.out.println("Hi "+ userName + "!" + " What can i do for you?");
        System.out.println("____________________________________________________________");
        System.out.println("Functions Available: ");
        System.out.println("Task Name ----- Add task to your TO-DO list");
        System.out.println("list ----- Show your current TO-DO list");
        System.out.println("done [Task Number] ----- Mark task as completed in your TO-DO list");

        Task [] t = new Task[101];
        int size = 1;
        while(true){
            String function = myScanner.next();
            switch (function) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!\n");
                    System.out.println("____________________________________________________________");
                    break;
                case "list":
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are your task(s) currently in your TO-DO List:");
                    for (int i = 1; i < size; i++) {
                        System.out.println(i + ".[" + t[i].getStatusIcon() + "] " + t[i].getDescription());
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "done":
                    int taskNumber = myScanner.nextInt();
                    t[taskNumber].markAsDone(t[taskNumber]);
                    System.out.println("____________________________________________________________");
                    System.out.println("Great job! I've marked this task as done in your TO-DO List:");
                    System.out.println("[" + t[taskNumber].getStatusIcon() + "] " + t[taskNumber].getDescription());
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    t[size++] = new Task(function);
                    System.out.println("____________________________________________________________");
                    System.out.println("Storing " + function);
                    System.out.println("Please wait for a moment...");
                    System.out.println("____________________________________________________________");
            }
        }

    }
}