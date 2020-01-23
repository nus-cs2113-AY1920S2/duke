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
        System.out.println("What can i do for you?");
        System.out.println("____________________________________________________________");

        String[] tasksList = new String[100];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();
            if (userInput.toLowerCase().equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; ++i) {
                    System.out.println((i+1) + ". " + tasksList[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (userInput.toLowerCase().equals("blah")) {
                System.out.println("____________________________________________________________");
                System.out.println("\tblah");
                System.out.println("____________________________________________________________");
            } else if (userInput.toLowerCase().equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.toLowerCase().startsWith("done")) {
                System.out.println("____________________________________________________________");
                int doneTaskIndex =  Integer.parseInt(userInput.substring(5));
                tasksList[--doneTaskIndex] = tasksList[doneTaskIndex].replace("[\u2718] ", "[\u2713] ");
                System.out.println("\tNice! I've marked this task as done: ");
                System.out.println("\t\t" + tasksList[doneTaskIndex]);
                System.out.println("____________________________________________________________");
            } else {
                tasksList[taskCount++] = "[\u2718] "+ userInput;
                System.out.println("____________________________________________________________");
                System.out.println("\tadded: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }
        in.close();
    }
}
