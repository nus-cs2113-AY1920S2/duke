import java.util.Scanner;
import java.util.Arrays;

//test

public class Duke {

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(greeting);

        Task[] taskList = new Task[100];

        Scanner scanner = new Scanner(System.in);
        int exit = 0;
        int taskListSize = 0;

        while (exit == 0) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(goodbye);
                exit = 1;
            } else if (userInput.equals("list")){
                for (int i = 1; i <= taskListSize; i++) {
                    System.out.print(i);
                    System.out.println("." + taskList[i-1].toString());
                }
            } else if (userInput.startsWith("done")){
                String taskToDelete = userInput.substring(5);
                int ID = Integer.valueOf(taskToDelete) - 1;
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(taskList[ID].markAsDone());
            } else if (userInput.startsWith("todo")) {
                taskList[taskListSize] = new ToDo(userInput.substring(5));
                addedResponse(taskList[taskListSize], taskListSize);
                taskListSize++;
            } else if (userInput.startsWith("deadline")) {
                String[] userInputs = userInput.substring(9).split(" /by ");
                taskList[taskListSize] = new Deadline(userInputs[0], userInputs[1]);
                addedResponse(taskList[taskListSize], taskListSize);
                taskListSize++;
            } else if (userInput.startsWith("event")) {
                String[] userInputs = userInput.substring(6).split(" /at ");
                taskList[taskListSize] = new Event(userInputs[0], userInputs[1]);
                addedResponse(taskList[taskListSize], taskListSize);
                taskListSize++;
            } else {
                taskList[taskListSize] = new Task(userInput);
                addedResponse(taskList[taskListSize], taskListSize);
                taskListSize++;
            }
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
