package Duke;

import java.util.Scanner;

public class Duke {

    public static final int MAXSIZE = 100;

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke.Duke\n" + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(greeting);

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
                System.out.println(goodbye);
                exit = 1;
                break;
            case "list":
                printTasks(taskList, taskListSize);
                break;
            case "done":
                int taskDone = Integer.valueOf(tokens[1]) - 1;
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(taskList[taskDone].markAsDone());
                break;
            case "todo":
                String toDo = tokens[1];
                taskList[taskListSize] = new ToDo(tokens[1]);
                addedResponse(taskList[taskListSize], taskListSize);
                taskListSize++;
                break;
            case "deadline":
                String[] deadlineInfo = tokens[1].split(" /by ");
                taskList[taskListSize] = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                addedResponse(taskList[taskListSize], taskListSize);
                taskListSize++;
                break;
            case "event":
                String[] eventInfo = tokens[1].split(" /at ");
                taskList[taskListSize] = new Event(eventInfo[0], eventInfo[1]);
                addedResponse(taskList[taskListSize], taskListSize);
                taskListSize++;
                break;
            default:
                taskList[taskListSize] = new Task(userInput);
                addedResponse(taskList[taskListSize], taskListSize);
                taskListSize++;
                break;
            }
        }
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
