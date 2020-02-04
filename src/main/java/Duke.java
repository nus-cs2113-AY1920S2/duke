import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        executeCommands();
        exit();
    }

    private static void executeCommands() {
        Task[] taskList = new Task[100];
        int taskCounter = 0;
        int taskIndex;
        String input;
        String line;
        Scanner in = new Scanner(System.in);
        while(true) {
            input = in.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < taskCounter; i++) {
                    line = "    " + Integer.toString(i+1) + ".[" + taskList[i].getStatusIcon() + "] " +
                            taskList[i].getDescription();
                    System.out.println(line);
                }
            } else if (input.substring(0,4).equals("done")) {
                 taskIndex = Integer.parseInt(input.substring(5)) - 1;
                 taskList[taskIndex].markAsDone();
                 line = "      " + "[" + taskList[taskIndex].getStatusIcon() + "] " +
                         taskList[taskIndex].getDescription();
                 System.out.println("    Nice! I've marked this task as done:");
                 System.out.println(line);
            } else {
                taskList[taskCounter] = new Task(input);
                System.out.println("    added: " + input);
                taskCounter++;
            }
        }
    }

    private static void exit() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    private static void greet() {
        System.out.println("    Hello I'm Yapyap.");
        System.out.println("    What can I do for you?");
    }
}
