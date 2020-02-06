package src.main.java;

import java.util.Scanner;

public class Duke {

    static final int MAX_TASK_AMOUNT = 100;

    public static void main(String[] args) {
        greetUser();
        Task[] taskList = new Task[MAX_TASK_AMOUNT];
        inputAndExecute(taskList);
    }

    private static void inputAndExecute(Task[] taskList) {
        String userInput = "";
        do {
            userInput = scanInput();
            String[] userCommand = userInput.split(" ", 2);
            executeCommand(taskList, userInput, userCommand);
        } while (!userInput.equalsIgnoreCase("bye"));
    }

    private static void executeCommand(Task[] taskList, String userInput, String[] userCommand) {
        switch (userCommand[0]) {
            case "list":
                listTasks(taskList);
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "todo":
                addNewTodo(taskList, userCommand[1]);
                break;
            case "deadline":
                addNewDeadline(taskList, userCommand[1]);
                break;
            case "event":
                addNewEvent(taskList, userCommand[1]);
                break;
            case "done":
                markedAsDone(userCommand[1], taskList);
                break;
            default:
                addNewTask(userInput, taskList);
                break;
        }
    }

    private static void addNewTask(String taskDescription, Task[] taskList) {
        addTaskInList(new Task(taskDescription), taskList);
        printAddedTask(taskList);
    }

    private static void addNewEvent(Task[] taskList, String s) {
        String[] taskDetails = s.split(" /at ", 2);
        Event event = new Event(taskDetails[0], taskDetails[1]);
        addTaskInList(event, taskList);
        printAddedTask(taskList);
    }

    private static void addNewDeadline(Task[] taskList, String taskDescription) {
        String[] taskDetails = taskDescription.split(" /by ", 2);
        Deadline deadline = new Deadline(taskDetails[0], taskDetails[1]);
        addTaskInList(deadline, taskList);
        printAddedTask(taskList);
    }

    private static void addNewTodo(Task[] taskList, String taskDescription) {
        Todo toDo = new Todo(taskDescription);
        addTaskInList(toDo, taskList);
        printAddedTask(taskList);
    }

    private static void printAddedTask(Task[] taskList) {
        System.out.println("  " + taskList[Task.totalNumberOfTask - 1].toString());
        System.out.println("Now you have " + Task.totalNumberOfTask + " tasks in the list");
    }

    private static void greetUser() {
        System.out.println("Hello from Optimus Prime");
        System.out.println("What can I do for you?");
    }

    private static void addTaskInList(Task taskDescription, Task[] taskList) {
        System.out.println("Got it. I've added this task");
        taskList[Task.totalNumberOfTask - 1] = taskDescription;
    }

    private static void markedAsDone(String numberInput, Task[] taskList) {
        int taskNumber = Integer.parseInt(numberInput) - 1;
        taskList[taskNumber].completedTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList[taskNumber].toString());
    }

    private static void listTasks(Task[] taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.totalNumberOfTask; i++) {
            System.out.println((i + 1) + "." + taskList[i].toString());
        }
    }

    private static String scanInput() {
        String userInput;
        Scanner scan = new Scanner(System.in);
        userInput = scan.nextLine();
        return userInput;
    }
}
