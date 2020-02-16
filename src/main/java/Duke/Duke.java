package Duke;

import java.util.Scanner;

public class Duke {

    public static final int MAXSIZE = 100;
    public static final String GREETING = "Hello! I'm Duke.Duke\n" + "What can I do for you?";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        System.out.println(GREETING);

        Task[] taskList = new Task[MAXSIZE];

        Scanner scanner = new Scanner(System.in);
        int exit = 0;
        int taskListSize = 0;

        while (exit == 0) {
            String userInput = scanner.nextLine();
            String[] tokens = userInput.split(" ", 2);
            String instruction = tokens[0];

            switch(instruction) {
            case "bye":
                System.out.println(GOODBYE);
                exit = 1;
                break;
            case "list":
                printTasks(taskList, taskListSize);
                break;
            case "done":
                if (checkEmptyDescription(tokens, instruction)) break;
                //to do more error handling here
                int taskDone = Integer.valueOf(tokens[1]) - 1;
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(taskList[taskDone].markAsDone());
                break;
            case "todo":
                if (checkEmptyDescription(tokens, instruction)) break;
                String toDo = tokens[1];
                taskList[taskListSize] = new ToDo(tokens[1]);
                addedResponse(taskList[taskListSize], taskListSize);
                taskListSize++;
                break;
            case "deadline":
                if (checkEmptyDescription(tokens, instruction)) break;
                String[] deadlineInfo = tokens[1].split(" /by ");
                if (checkDateEntered(deadlineInfo)) break;
                taskList[taskListSize] = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                addedResponse(taskList[taskListSize], taskListSize);
                taskListSize++;
                break;
            case "event":
                if (checkEmptyDescription(tokens, instruction)) break;
                String[] eventInfo = tokens[1].split(" /at ");
                if (checkDateEntered(eventInfo)) break;
                taskList[taskListSize] = new Event(eventInfo[0], eventInfo[1]);
                addedResponse(taskList[taskListSize], taskListSize);
                taskListSize++;
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
        }
    }

    private static boolean checkDateEntered(String[] information) {
        if (information.length == 1) {
            System.out.println("☹ OOPS!!! You did not enter a date");
            return true;
        }
        return false;
    }

    private static boolean checkEmptyDescription(String[] tokens, String instruction) {
        if (tokens.length == 1) {
            System.out.println("☹ OOPS!!! The description of a "+ instruction +" cannot be empty.");
            return true;
        }
        return false;
    }

    private static void printTasks(Task[] taskList, int taskListSize) {
        for (int i = 1; i <= taskListSize; i++) {
            System.out.print(i);
            System.out.println("." + taskList[i - 1].toString());
        }
    }

    private static void addedResponse(Task task, int taskListSize) {
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" + task.toString());
        System.out.print("Now you have ");
        System.out.print(taskListSize+1);
        if (taskListSize == 0) {
            System.out.print(" task in the list.\n" +
                    "____________________________________________________________\n");
        } else {
            System.out.print(" tasks in the list.\n" +
                    "____________________________________________________________\n");
        }
    }
}
