package src.main.java;

import src.main.java.duke.task.Deadline;
import src.main.java.duke.exceptions.AlreadyDoneException;
import src.main.java.duke.exceptions.InvalidCommandException;
import src.main.java.duke.exceptions.InvalidDateException;
import src.main.java.duke.exceptions.InvalidDoneException;
import src.main.java.duke.task.Event;
import src.main.java.duke.task.Task;
import src.main.java.duke.task.Todo;

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
            try {
                executeCommand(taskList, userInput, userCommand);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The description of " + userCommand[0] + " cannot be empty.");
            } catch (InvalidDateException e) {
                System.out.println("Please re-enter command with the time and/or date.");
            } catch (InvalidCommandException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-<");
            } catch (InvalidDoneException e) {
                System.out.println("Unable to process done statement");
            } catch (AlreadyDoneException e) {
                System.out.println("Task has already been completed.");
                System.out.println("  " + taskList[Integer.parseInt(userCommand[1]) - 1].toString());
            }
        } while (!userInput.equalsIgnoreCase("bye"));
    }

    private static void executeCommand(Task[] taskList, String userInput, String[] userCommand) throws InvalidCommandException, InvalidDateException, InvalidDoneException, AlreadyDoneException {
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
                throw new InvalidCommandException();
        }
    }

    private static void addNewEvent(Task[] taskList, String s) throws InvalidDateException {
        String[] taskDetails = s.split(" /at ", 2);
        if (taskDetails.length == 1) {
            throw new InvalidDateException();
        }
        Event event = new Event(taskDetails[0], taskDetails[1]);
        addTaskInList(event, taskList);
        printAddedTask(taskList);
    }

    private static void addNewDeadline(Task[] taskList, String taskDescription) throws InvalidDateException {
        String[] taskDetails = taskDescription.split(" /by ", 2);
        if (taskDetails.length == 1) {
            throw new InvalidDateException();
        }
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

    private static void markedAsDone(String numberInput, Task[] taskList) throws InvalidDoneException, AlreadyDoneException {
        int taskNumber = Integer.parseInt(numberInput) - 1;
        if (taskNumber == -1 || taskNumber >= taskList.length) {
            throw new InvalidDoneException();
        }
        if (taskList[taskNumber].getIsDone() == true) {
            throw new AlreadyDoneException();
        } else {
            taskList[taskNumber].completedTask();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + taskList[taskNumber].toString());
        }
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
